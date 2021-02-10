package dev.chargedbyte.wim.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.keyvalue.annotation.KeySpace;

import java.util.List;

/**
 * Storage helper for products
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@KeySpace
public class ProductStorage {
    @Id
    private Category id;
    private List<Product> products;
}
