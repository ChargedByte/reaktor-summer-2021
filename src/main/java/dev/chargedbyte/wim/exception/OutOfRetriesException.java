package dev.chargedbyte.wim.exception;

import dev.chargedbyte.wim.model.Category;

public class OutOfRetriesException extends LegacyException {
    public OutOfRetriesException(Category category) {
        super(category);
    }

    public OutOfRetriesException(String manufacturer) {
        super(manufacturer);
    }
}
