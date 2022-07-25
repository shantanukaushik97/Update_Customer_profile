package com.application.customer.ui.core;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

import static org.springframework.http.HttpStatus.Series.CLIENT_ERROR;
import static org.springframework.http.HttpStatus.Series.SERVER_ERROR;

@Component
public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse httpResponse) throws IOException {
        return (httpResponse.getStatusCode().series() == CLIENT_ERROR
                || httpResponse.getStatusCode().series() == SERVER_ERROR);
    }

    @Override
    public void handleError(ClientHttpResponse httpResponse) throws IOException {
        ApplicationError error = getErrorResponse(httpResponse);

        if (httpResponse.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR) {
            throw new TechnicalException(error, null);
        } else if (httpResponse.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR) {
            throw new BusinessException(error);
        }

        throw new TechnicalException(new ApplicationError("",""), null);
    }

    private ApplicationError getErrorResponse(ClientHttpResponse httpResponse) {
        try {
            String responseBody = IOUtils.toString(httpResponse.getBody());
            return new ApplicationError("", responseBody);
        } catch (Exception e) {
            throw new TechnicalException(
                    new ApplicationError("",""), e);
        }
    }
}
