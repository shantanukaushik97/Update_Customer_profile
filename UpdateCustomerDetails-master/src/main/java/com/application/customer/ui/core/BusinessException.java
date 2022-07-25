package com.application.customer.ui.core;

public class BusinessException extends RuntimeException {
    private final ApplicationError applicationError;

    public BusinessException(ApplicationError applicationError) {
        super(String.format("%s :: %s", applicationError.getCode(), applicationError.getMessage()));
        this.applicationError = applicationError;
    }

    public ApplicationError getApplicationError() {
        return this.applicationError;
    }
}