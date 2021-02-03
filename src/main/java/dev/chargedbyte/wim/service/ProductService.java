package dev.chargedbyte.wim.service;

import dev.chargedbyte.wim.model.Category;
import dev.chargedbyte.wim.model.ErrorState;
import dev.chargedbyte.wim.model.Product;
import dev.chargedbyte.wim.model.ProductStorage;
import dev.chargedbyte.wim.repository.ProductRepository;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Service for {@link ProductRepository} interactions
 */
@Service
public class ProductService {
    private final static Logger log = LoggerFactory.getLogger(ProductService.class);

    private final ProductRepository productRepository;
    private final Map<Category, Instant> lastModified;

    @Getter
    @Setter
    private ErrorState errorState;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;

        this.lastModified = Collections.synchronizedMap(new HashMap<>());

        this.errorState = new ErrorState();
    }

    public List<String> findAllManufacturers() {
        return productRepository.findAll().stream()
            .map(ProductStorage::getProducts)
            .flatMap(List::stream)
            .map(Product::getManufacturer)
            .distinct()
            .collect(Collectors.toList());
    }

    public void save(ProductStorage storage) {
        lastModified.put(storage.getId(), Instant.now());
        productRepository.save(storage);
    }

    public Optional<Instant> getLastModified(Category category) {
        return Optional.ofNullable(lastModified.get(category));
    }

    public Optional<ProductStorage> findByCategory(Category category) {
        return productRepository.findById(category);
    }
}
