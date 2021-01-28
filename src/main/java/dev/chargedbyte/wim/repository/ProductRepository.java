package dev.chargedbyte.wim.repository;

import dev.chargedbyte.wim.model.Category;
import dev.chargedbyte.wim.model.ProductStorage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<ProductStorage, Category> {
    List<ProductStorage> findAll();
}
