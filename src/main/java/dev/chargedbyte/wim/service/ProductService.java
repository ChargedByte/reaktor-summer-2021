package dev.chargedbyte.wim.service;

import dev.chargedbyte.wim.model.Category;
import dev.chargedbyte.wim.model.Product;
import dev.chargedbyte.wim.repository.ProductRepository;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service for {@link Product} interactions
 */
@Service
@CacheConfig(cacheNames = {"product"})
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Cacheable(value = "products")
    public List<Product> findByCategory(Category category) {
        return productRepository.findByCategory(category);
    }

    @CacheEvict(value = "products")
    public void deleteByCategory(Category category) {
        productRepository.deleteByCategory(category);
    }

    @CachePut(value = "products")
    public void saveAll(List<Product> products) {
        productRepository.saveAll(products);
    }
}
