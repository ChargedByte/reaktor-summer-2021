package dev.chargedbyte.wim.exception;

/**
 * Exception thrown to indicate a problem with the legacy api
 */
public class LegacyException extends Exception {
    public LegacyException() {
    }

    public LegacyException(String message) {
        super(message);
    }

    public LegacyException(String message, Throwable cause) {
        super(message, cause);
    }

    public LegacyException(Throwable cause) {
        super(cause);
    }
}
