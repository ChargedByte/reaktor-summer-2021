package dev.chargedbyte.wim.task;

import dev.chargedbyte.wim.exception.LegacyException;
import dev.chargedbyte.wim.model.*;
import dev.chargedbyte.wim.repository.ProductRepository;
import dev.chargedbyte.wim.service.LegacyService;
import dev.chargedbyte.wim.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

/**
 * Update {@link ProductRepository} according to changes in the legacy API
 */
@Component
public class ProductUpdateTask implements Runnable {
    private static final Logger log = LoggerFactory.getLogger(ProductUpdateTask.class);

    private final LegacyService legacyService;
    private final ProductService productService;
    private final AtomicBoolean running = new AtomicBoolean(false);
    private final AtomicBoolean firstRun = new AtomicBoolean(true);

    private ErrorState errorState;

    public ProductUpdateTask(LegacyService legacyService, ProductService productService) {
        this.legacyService = legacyService;
        this.productService = productService;
    }

    public AtomicBoolean isRunning() {
        return running;
    }

    public AtomicBoolean isFirstRun() {
        return firstRun;
    }

    private List<LegacyProduct> getProducts() {
        List<CompletableFuture<List<LegacyProduct>>> tasks = new ArrayList<>();
        for (Category item : Category.values()) {
            if (item == Category.Unknown)
                continue;

            tasks.add(legacyService.getLegacyProductsByCategory(item));
        }

        return tasks.stream()
            .map(x -> {
                try {
                    return x.get();
                } catch (ExecutionException ex) {
                    if (ex.getCause() instanceof LegacyException) {
                        Category category = Category.find(ex.getCause().getMessage());
                        errorState.addFailedCategory(category);
                    }
                } catch (InterruptedException ex) {
                    errorState.getProductsTasksInterrupted().compareAndExchange(false, true);
                }
                return null;
            })
            .filter(Objects::nonNull)
            .flatMap(List::stream)
            .collect(Collectors.toList());
    }

    private List<LegacyAvailability> getAvailabilities(List<String> manufacturers) {
        List<CompletableFuture<List<LegacyAvailability>>> tasks = new ArrayList<>();
        for (String manufacturer : manufacturers) {
            tasks.add(legacyService.getLegacyAvailabilitiesByManufacturer(manufacturer));
        }

        return tasks.stream()
            .map(x -> {
                try {
                    return x.get();
                } catch (ExecutionException ex) {
                    if (ex.getCause() instanceof LegacyException) {
                        String manufacturer = ex.getCause().getMessage();
                        errorState.addFailedManufacturer(manufacturer);
                    }

                } catch (InterruptedException ex) {
                    errorState.getAvailabilityTasksInterrupted().compareAndExchange(false, true);
                }
                return null;
            })
            .filter(Objects::nonNull)
            .flatMap(List::stream)
            .collect(Collectors.toList());
    }

    private List<Product> convertProducts(List<LegacyProduct> legacyProducts, List<LegacyAvailability> legacyAvailabilities) {
        List<CompletableFuture<Product>> tasks = new ArrayList<>();
        for (LegacyProduct lp : legacyProducts) {
            LegacyAvailability la = legacyAvailabilities.stream()
                .filter(x -> x.getId().equalsIgnoreCase(lp.getId()))
                .findFirst()
                .orElse(null);

            // Should help with processing speed later
            if (la != null) {
                legacyAvailabilities.remove(la);
            }

            tasks.add(legacyService.convertLegacyProduct(lp, la));
        }

        return tasks.stream()
            .map(x -> {
                try {
                    return x.get();
                } catch (InterruptedException | ExecutionException ex) {
                    // From what I can tell this shouldn't be able to fail other than being interrupted
                    errorState.getProductConversionTasksFailed().compareAndSet(false, true);
                }
                return null;
            })
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
    }

    private void work() {
        errorState = new ErrorState();

        List<LegacyProduct> legacyProducts = getProducts();

        List<String> manufacturers = legacyProducts.stream()
            .map(LegacyProduct::getManufacturer)
            .distinct()
            .collect(Collectors.toList());

        // Add any manufacturers currently in storage
        manufacturers.addAll(productService.findAllManufacturers());

        // Filter out duplicates
        manufacturers = manufacturers.stream()
            .distinct()
            .collect(Collectors.toList());

        List<LegacyAvailability> legacyAvailabilities = getAvailabilities(manufacturers);

        List<Product> products = convertProducts(legacyProducts, legacyAvailabilities);

        List<Category> updatedCategories = products.stream()
            .map(Product::getCategory)
            .distinct()
            .collect(Collectors.toList());

        // Save converted products
        for (Category item : updatedCategories) {
            productService.save(new ProductStorage(
                item,
                products.stream()
                    .filter(x -> x.getCategory() == item)
                    .collect(Collectors.toList())
            ));
        }

        // Find and update products using the remaining availabilities

        List<Category> remainingCategories = Arrays.stream(Category.values())
            .filter(x -> !updatedCategories.contains(x))
            .filter(x -> x != Category.Unknown)
            .collect(Collectors.toList());

        for (Category item : remainingCategories) {
            Optional<ProductStorage> storage = productService.findByCategory(item);

            if (storage.isPresent()) {
                List<Product> storageProducts = storage.get().getProducts();

                List<LegacyAvailability> las = legacyAvailabilities.stream()
                    .filter(x -> storageProducts.stream()
                        .anyMatch(y -> y.getId().equalsIgnoreCase(x.getId())))
                    .collect(Collectors.toList());

                for (LegacyAvailability la : las) {
                    Product product = storageProducts.stream()
                        .filter(x -> x.getId().equalsIgnoreCase(la.getId()))
                        .findFirst()
                        .orElse(null);

                    // Should always be found
                    if (product != null) {
                        int index = storageProducts.indexOf(product);

                        Availability availability = legacyService.convertLegacyAvailability(la);

                        if (product.getAvailability() != availability) {
                            product.setAvailability(availability);

                            storageProducts.set(index, product);
                        }
                    }
                }

                if (storage.get().getProducts() != storageProducts) {
                    storage.get().setProducts(storageProducts);

                    productService.save(storage.get());
                }
            }
        }

        productService.setErrorState(errorState);
    }

    @Override
    public void run() {
        // Only run one at a time
        if (running.get())
            return;

        running.set(true);

        Instant start = Instant.now();

        work();

        Instant end = Instant.now();
        log.info("Done in {} seconds", (end.toEpochMilli() - start.toEpochMilli()) / 1000.0);

        if (firstRun.get())
            firstRun.set(false);

        running.set(false);
    }
}
