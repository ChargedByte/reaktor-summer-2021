package dev.chargedbyte.wim.exception;

import dev.chargedbyte.wim.model.Category;

public class NotFoundException extends LegacyException {
    public NotFoundException(Category category) {
        super(category);
    }

    public NotFoundException(String manufacturer) {
        super(manufacturer);
    }
}
