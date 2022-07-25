package com.application.customer.ui.core;

import java.io.Serializable;

public class ApplicationError implements Serializable {
    private final String code;
    private final String message;
    private String traceId;

    private ApplicationError() {
        this.code = null;
        this.message = null;
        this.traceId = null;
    }

    public ApplicationError(String code, String message, String traceId) {
        this.code = code;
        this.message = message;
        this.traceId = traceId;
    }

    public ApplicationError(String code, String message) {
        this.code = code;
        this.message = message;
        this.traceId = null;
    }

    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public String getTraceId() {
        return this.traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

}
