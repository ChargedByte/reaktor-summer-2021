package dev.chargedbyte.wim.meta.exception;

public class ManufacturerNotFoundException extends LegacyApiException {
    public ManufacturerNotFoundException() {
    }

    public ManufacturerNotFoundException(String manufacturer) {
        super("no manufacturer found for " + manufacturer);
    }

    public ManufacturerNotFoundException(String manufacturer, Throwable cause) {
        super("no manufacturer found for " + manufacturer, cause);
    }

    public ManufacturerNotFoundException(Throwable cause) {
        super(cause);
    }
}
