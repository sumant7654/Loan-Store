package dev.sumantakumar.loanstore.error;

public class InvalidPaymentDateException extends RuntimeException {

    public InvalidPaymentDateException() {
        super();
    }

    public InvalidPaymentDateException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public InvalidPaymentDateException(final String message) {
        super(message);
    }

    public InvalidPaymentDateException(final Throwable cause) {
        super(cause);
    }
}
