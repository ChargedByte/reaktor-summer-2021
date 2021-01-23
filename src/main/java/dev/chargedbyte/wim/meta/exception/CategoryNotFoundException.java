package dev.chargedbyte.wim.meta.exception;

public class CategoryNotFoundException extends LegacyApiException {
    public CategoryNotFoundException() {
    }

    public CategoryNotFoundException(String category) {
        super("no category found for " + category);
    }

    public CategoryNotFoundException(String category, Throwable cause) {
        super("no category found for " + category, cause);
    }

    public CategoryNotFoundException(Throwable cause) {
        super(cause);
    }
}
