package dev.chargedbyte.wim.meta.exception;

public class LegacyApiException extends Exception {
    public LegacyApiException() {
    }

    public LegacyApiException(String message) {
        super(message);
    }

    public LegacyApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public LegacyApiException(Throwable cause) {
        super(cause);
    }
}
