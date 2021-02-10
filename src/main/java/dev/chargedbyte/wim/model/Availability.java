package dev.chargedbyte.wim.model;

import java.util.Arrays;

public enum Availability {
    Unknown,
    InStock,
    LessThan10,
    OutOfStock;

    public static Availability find(String s) {
        return Arrays.stream(values())
            .filter(x -> x.name().equalsIgnoreCase(s))
            .findFirst()
            .orElse(Availability.Unknown);
    }
}
