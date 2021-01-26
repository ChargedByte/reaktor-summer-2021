package dev.chargedbyte.wim.task;

import dev.chargedbyte.wim.model.Category;
import dev.chargedbyte.wim.model.LegacyAvailability;
import dev.chargedbyte.wim.model.LegacyProduct;
import dev.chargedbyte.wim.model.Product;
import dev.chargedbyte.wim.repository.ProductRepository;
import dev.chargedbyte.wim.service.LegacyService;
import dev.chargedbyte.wim.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
    private final AtomicBoolean isRunning = new AtomicBoolean(false);

    public ProductUpdateTask(LegacyService legacyService, ProductService productService) {
        this.legacyService = legacyService;
        this.productService = productService;
    }

    public AtomicBoolean getIsRunning() {
        return isRunning;
    }

    private List<LegacyProduct> getLegacyProducts() {
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
                } catch (InterruptedException | ExecutionException ex) {
                    // TODO: Handle
                    ex.printStackTrace();
                }
                return null;
            })
            .filter(Objects::nonNull)
            .flatMap(List::stream)
            .collect(Collectors.toList());
    }

    private List<LegacyAvailability> getLegacyAvailabilities(List<String> manufacturers) {
        List<CompletableFuture<List<LegacyAvailability>>> tasks = new ArrayList<>();
        for (String manufacturer : manufacturers) {
            tasks.add(legacyService.getLegacyAvailabilitiesByManufacturer(manufacturer));
        }

        return tasks.stream()
            .map(x -> {
                try {
                    return x.get();
                } catch (InterruptedException | ExecutionException ex) {
                    // TODO: Handle
                    ex.printStackTrace();
                }
                return null;
            })
            .filter(Objects::nonNull)
            .flatMap(List::stream)
            .collect(Collectors.toList());
    }

    private List<Product> getProducts(List<LegacyProduct> legacyProducts, List<LegacyAvailability> legacyAvailabilities) {
        List<CompletableFuture<Product>> tasks = new ArrayList<>();
        for (LegacyProduct lp : legacyProducts) {
            LegacyAvailability la = legacyAvailabilities.stream()
                .filter(x -> x.getId().equalsIgnoreCase(lp.getId()))
                .findFirst()
                .orElse(null);

            tasks.add(legacyService.convertLegacyProduct(lp, la));
        }

        return tasks.stream()
            .map(x -> {
                try {
                    return x.get();
                } catch (InterruptedException | ExecutionException ex) {
                    // TODO: Handle
                    ex.printStackTrace();
                }
                return null;
            })
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
    }

    @Override
    public void run() {
        // Only run one at a time
        if (isRunning.get())
            return;

        isRunning.set(true);

        Instant start = Instant.now();

        List<LegacyProduct> legacyProducts = getLegacyProducts();

        List<String> manufacturers = legacyProducts.stream()
            .map(LegacyProduct::getManufacturer)
            .distinct()
            .collect(Collectors.toList());

        List<LegacyAvailability> legacyAvailabilities = getLegacyAvailabilities(manufacturers);

        List<Product> products = getProducts(legacyProducts, legacyAvailabilities);

        List<Category> categories = products.stream()
            .map(Product::getCategory)
            .distinct()
            .collect(Collectors.toList());

        for (Category item : categories) {
            productService.deleteByCategory(item);
        }

        productService.saveAll(products);

        Instant end = Instant.now();
        log.info("Done with product update in {} seconds", (end.toEpochMilli() - start.toEpochMilli()) / 1000.0);

        isRunning.set(false);
    }
}
