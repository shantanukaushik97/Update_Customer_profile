package com.application.customer.ui.customer;

import com.application.customer.ui.core.ApiResponse;
import com.application.customer.ui.customer.model.Customer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api
@RestController
@Slf4j
@RequestMapping("/secure/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping(value =  "/{customerId}", produces = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Find customer by ID ",
            httpMethod = "GET",  produces = APPLICATION_JSON_VALUE)
    public ApiResponse<Customer> getCustomerDetails(@PathVariable("customerId") String customerId) {
        log.info("getCustomerDetails for customer id: {}", customerId);
        return new ApiResponse<Customer>(customerService.getCustomerDetails(customerId));
    }

    @PostMapping(value = "/", produces = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create a customer with the provided details", httpMethod = "POST", produces = APPLICATION_JSON_VALUE)
    public ApiResponse createCustomer(@Valid @RequestBody Customer customer) {
        log.info("Create customer");
        return new ApiResponse<>(customerService.createCustomer(customer));
    }

    @PutMapping(value = "/", produces = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update an existing customer", httpMethod = "PUT", produces = APPLICATION_JSON_VALUE)
    public ApiResponse updateCustomer(@Valid @RequestBody Customer customer) {
        log.info("Update Customer");
        return new ApiResponse<>(customerService.updateCustomer(customer));
    }

    @DeleteMapping(value = "/{customerId}", produces = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Delete the details for the customer", httpMethod = "DELETE", produces = APPLICATION_JSON_VALUE)
    public ApiResponse deleteCustomer(@PathVariable("customerId") String customerId) {
        log.info("delete customer for customer id: {}", customerId);
        return new ApiResponse<>(customerService.deleteCustomer(customerId));
    }
}
