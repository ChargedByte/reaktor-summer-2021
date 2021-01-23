package dev.chargedbyte.wim.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private String id;
    private Category category;
    private String name;
    private List<String> colors;
    private Integer price;
    private String manufacturer;
    private Availability availability;
}
