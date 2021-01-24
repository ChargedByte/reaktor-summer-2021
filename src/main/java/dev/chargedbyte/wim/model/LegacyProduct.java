package dev.chargedbyte.wim.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LegacyProduct {
    private String id;
    private String type;
    private String name;
    private String[] color;
    private Integer price;
    private String manufacturer;
}
