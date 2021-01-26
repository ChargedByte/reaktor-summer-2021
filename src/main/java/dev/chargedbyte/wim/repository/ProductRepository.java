package dev.chargedbyte.wim.repository;

import dev.chargedbyte.wim.model.Category;
import dev.chargedbyte.wim.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, String> {
    List<Product> findByCategory(Category category);

    List<Product> deleteByCategory(Category category);
}
