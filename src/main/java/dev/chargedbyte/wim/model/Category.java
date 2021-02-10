package dev.chargedbyte.wim.model;

import java.util.Arrays;

public enum Category {
    Unknown,
    Beanies,
    Facemasks,
    Gloves;

    public static Category find(String s) {
        return Arrays.stream(values())
            .filter(x -> x.name().equalsIgnoreCase(s))
            .findFirst()
            .orElse(Category.Unknown);
    }
}
