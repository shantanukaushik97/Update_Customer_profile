package com.application.customer.ui.core;

public class TechnicalException extends RuntimeException {
    private final ApplicationError applicationError;

    public TechnicalException(String message) {
        super(message);
        this.applicationError = null;
    }

    public TechnicalException(Throwable cause) {
        super(cause);
        this.applicationError = null;
    }

    public TechnicalException(String message, Throwable cause) {
        super(message, cause);
        this.applicationError = null;
    }

    public TechnicalException(ApplicationError applicationError, Throwable cause) {
        super(String.format("%s :: %s", applicationError.getCode(), applicationError.getMessage()), cause);
        this.applicationError = applicationError;
    }

    public ApplicationError getApplicationError() {
        return this.applicationError;
    }
}
