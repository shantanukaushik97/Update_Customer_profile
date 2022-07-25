package com.application.customer.ui.customer;

import com.application.customer.ui.core.ApiResponse;
import com.application.customer.ui.customer.model.Customer;
import com.application.customer.ui.core.RestTemplateResponseErrorHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;

@Service
@Slf4j
public class CustomerService {

    private static final String CUST_ID_PATH_PARAM = "customerId";
    private static final String BASE_API_PATH = "/secure/api/v1/customerDetails/{" + CUST_ID_PATH_PARAM + "}";
    private static final String BASE_API_PATH_CUSTOMER = "/secure/api/v1/customerDetails";
    private final RestTemplate restTemplate;

    //rest-service.crmCapabilityUrl is the third party API URL defined in application.yml
    public CustomerService(@Value("${rest-service.crmCapabilityUrl}") String crmCapabilityUrl,
                          RestTemplateBuilder restTemplateBuilder,
                           RestTemplateResponseErrorHandler errorHandler) {

        this.restTemplate = restTemplateBuilder
                .rootUri(crmCapabilityUrl)
                .errorHandler(errorHandler)
                .build();
    }

    public Customer getCustomerDetails(String customerId){
        HashMap<String, String> requestParams = new HashMap<>();
        requestParams.put(CUST_ID_PATH_PARAM, customerId);
        String builtUrl = UriComponentsBuilder.fromPath(BASE_API_PATH)
                .buildAndExpand(requestParams).toString();

        try {
            Customer customer = restTemplate.exchange(
                    builtUrl, HttpMethod.GET, null,
                    new ParameterizedTypeReference<ApiResponse<Customer>>() {
                    }).getBody().getData();
        } finally {
            return new Customer();
        }
    }

    public ApiResponse createCustomer(Customer customer) {
        log.info("Create customer start");
        HttpEntity requestEntity = new HttpEntity<>(customer);
        HashMap<String, String> requestParams = new HashMap<>();
        String builtUrl = UriComponentsBuilder.fromPath(BASE_API_PATH_CUSTOMER)
                .buildAndExpand(requestParams).toString();
        ApiResponse apiResponse = restTemplate.exchange(
                builtUrl, HttpMethod.POST, requestEntity,
                new ParameterizedTypeReference<ApiResponse>() {
                }).getBody();

        log.info("Create customer end");
        return apiResponse;
    }

    public ApiResponse updateCustomer(Customer customer) {
        log.info("Update customer start");
        HttpEntity requestEntity = new HttpEntity<>(customer);
        HashMap<String, String> requestParams = new HashMap<>();
        String builtUrl = UriComponentsBuilder.fromPath(BASE_API_PATH_CUSTOMER)
                .buildAndExpand(requestParams).toString();
        ApiResponse apiResponse = restTemplate.exchange(
                builtUrl, HttpMethod.PUT, requestEntity,
                new ParameterizedTypeReference<ApiResponse>() {
                }).getBody();

        log.info("Update customer end");
        return apiResponse;
    }

    public ApiResponse deleteCustomer(String customerId) {
        log.info("Delete customer start");
        HashMap<String, String> requestParams = new HashMap<>();
        requestParams.put(CUST_ID_PATH_PARAM, customerId);
        String builtUrl = UriComponentsBuilder.fromPath(BASE_API_PATH)
                .buildAndExpand(requestParams).toString();
        ApiResponse apiResponse = restTemplate.exchange(builtUrl, HttpMethod.DELETE,
                null, new ParameterizedTypeReference<ApiResponse>() {
                }).getBody();
        log.info("Delete customer end");
        return apiResponse;
    }

}
