package dev.chargedbyte.wim.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.keyvalue.annotation.KeySpace;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@KeySpace("product")
public class Product {
    @Id
    private String id;
    private Category category;
    private String name;
    private List<String> colors;
    private Integer price;
    private String manufacturer;
    private Availability availability;
}
