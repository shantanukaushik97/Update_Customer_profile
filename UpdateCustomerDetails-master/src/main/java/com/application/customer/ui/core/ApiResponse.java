package com.application.customer.ui.core;

import org.springframework.http.HttpStatus;

public class ApiResponse<T> {
    private T data;
    private HttpStatus status;
    ApplicationError error;

    public ApiResponse() {
    }

    public ApiResponse(T data) {
        this.data = data;
    }

    public ApiResponse(ApplicationError error) {
        this.error = error;
    }

    public ApiResponse(T data, ApplicationError error) {
        this.data = data;
        this.error = error;
    }

    public T getData() {
        return this.data;
    }

    public ApplicationError getError() {
        return this.error;
    }
}

