package com.application.customer.ui.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.application.customer.ui.customer"))
                .paths(PathSelectors.any())
                .build().apiInfo(apiInfo()).forCodeGeneration(true);
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Customer Rest API",
                "This is a customer API.  The API is used to,\n" +
                        "    1. Create customer record in the organisation\n" +
                        "    2. Update the existing customer record\n" +
                        "    3. Fetch customer details by name/id\n" +
                        "    4. Delete the customer record",
                "v1",
                null,
                null,
                null, null, Collections.emptyList());
    }
}
