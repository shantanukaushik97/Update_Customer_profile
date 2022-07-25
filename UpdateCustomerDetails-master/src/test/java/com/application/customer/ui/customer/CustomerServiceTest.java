package com.application.customer.ui.customer;


import com.application.customer.ui.core.ApiResponse;
import com.application.customer.ui.customer.model.Customer;
import com.application.customer.ui.core.RestTemplateResponseErrorHandler;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class CustomerServiceTest {

    private static final String BASE_URL = "https://localhost:8000/crm-data";
    private CustomerService customerService;
    private RestTemplate restTemplate;
    private MockHttpServletRequest mockHttpServletRequest;

    @Before
    public void before() {
        restTemplate = mock(RestTemplate.class);
        RestTemplateBuilder builder = mock(RestTemplateBuilder.class);
        RestTemplateResponseErrorHandler errorHandler = mock(RestTemplateResponseErrorHandler.class);
        when(builder.rootUri(eq(BASE_URL))).thenReturn(builder);
        when(builder.build()).thenReturn(restTemplate);
        when(builder.errorHandler(isA(RestTemplateResponseErrorHandler.class))).thenReturn(builder);
        mockHttpServletRequest = new MockHttpServletRequest();
        customerService = new CustomerService(BASE_URL, builder, errorHandler);
    }

    @Test
    public void shouldGetCustomerDetails() {
        Customer cust = new Customer();
        ApiResponse<Customer> serviceApiResponse = new ApiResponse<>(cust);
        ResponseEntity responseEntity = new ResponseEntity<>(serviceApiResponse, HttpStatus.ACCEPTED);

        ServletRequestAttributes attributes = new ServletRequestAttributes(getRequest());
        RequestContextHolder.setRequestAttributes(attributes);
        when(restTemplate.exchange(
                anyString(),
                eq(HttpMethod.GET),
                ArgumentMatchers.<HttpEntity<Object>>any(),
                ArgumentMatchers.<ParameterizedTypeReference<ApiResponse<Customer>>>any())
        ).thenReturn(responseEntity);

        Assert.assertEquals(customerService.getCustomerDetails("1234"), cust);
    }

    @Test
    public void shouldCreateCustomer_withResponse() {
        ApiResponse serviceApiResponse = new ApiResponse<>();
        ResponseEntity responseEntity = new ResponseEntity<>(serviceApiResponse, HttpStatus.ACCEPTED);
        ArgumentCaptor<HttpEntity> httpEntityCaptor = ArgumentCaptor.forClass(HttpEntity.class);

        when(restTemplate.exchange(
                anyString(),
                eq(HttpMethod.POST),
                httpEntityCaptor.capture(),
                ArgumentMatchers.<ParameterizedTypeReference<ApiResponse<String>>>any()
        )).thenReturn(responseEntity);

        ApiResponse createResponse = customerService.createCustomer(setCustomer());
    }

    private HttpServletRequest getRequest() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        return request;
    }

    private Customer setCustomer() {
        Customer customer = new Customer();
        customer.setEmail("abc@gmail.com");
        customer.setFirstName("Mark");
        return customer;
    }

}
