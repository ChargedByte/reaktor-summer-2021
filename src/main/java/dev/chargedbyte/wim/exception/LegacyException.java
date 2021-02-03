package dev.chargedbyte.wim.exception;

import dev.chargedbyte.wim.model.Category;

/**
 * Exception thrown to indicate a problem with the legacy api
 */
public class LegacyException extends Exception {
    public LegacyException() {
    }

    public LegacyException(Category category) {
        super(category.name());
    }

    public LegacyException(String manufacturer) {
        super(manufacturer);
    }
}
