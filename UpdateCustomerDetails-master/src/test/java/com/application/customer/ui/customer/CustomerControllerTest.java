package com.application.customer.ui.customer;

import com.application.customer.ui.core.ApiResponse;
import com.application.customer.ui.customer.model.Customer;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
@RunWith(MockitoJUnitRunner.class)
public class CustomerControllerTest {

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    @Test
    public void shouldCallCustomerService_getCustomerDetails() {
        Customer customer = new Customer();
        customer.setEmail("abc@gmail.com");
        when(customerService.getCustomerDetails(anyString())).thenReturn(customer);
        ApiResponse<Customer> response = customerController.getCustomerDetails("123");
        Customer result = response.getData();
        verify(customerService, times(1)).getCustomerDetails(anyString());
        Assert.assertEquals(result.getEmail(), customer.getEmail());
    }

    @Test
    public void shouldCallCustomerService_createCustomerDetails() {
        when(customerService.createCustomer(any(Customer.class))).thenReturn(new ApiResponse(null,null));
        ApiResponse response = customerController.createCustomer(setCustomer());
        Assert.assertNotNull(response.getData());
        verify(customerService, times(1)).createCustomer(any(Customer.class));
    }

    private Customer setCustomer() {
        Customer customer = new Customer();
        customer.setEmail("abc@gmail.com");
        customer.setFirstName("Mark");
        return customer;
    }
}