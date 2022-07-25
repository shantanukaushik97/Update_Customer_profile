## Customer UI API 
  
This API microservice template is implemented to handle customer operations like create, update and delete customer profiles.  
  
This is only configured to run REST JSON APIs. Not configured to run any Web Application.  
  
**What's Configured**  
  
This microservice has been configured with:  
  - Spring boot starter parent  
 - Basic Spring boot Actuator  
 - Swagger  
 - Basic HTTP Authentication 
 - Unit tests including Spring boot starter test, Mockito and Hamcrest  
 - Loggers - Info


## Structure

Code was structure in the following way under the package `com.application.customer.ui`:
| Package | Description |
|--|--|
| config | Contains config classes for SpringSecurity, Swagger
   | core| Contains core classes like ApiResponse, ErrorObjects, Exceptions for REST APIs
   | customer| Contains model classes, RestController and Service for Customer operations

 **How to run it locally**

mvn spring-boot:run -Dspring.profiles.active=dev
username:user|password:password
localhost:8080/swagger-ui.html
http://localhost:8080/secure/customer/{custId}

**Testing**

There are sample test cases implemented as example / foundation to implement further tests.


**Third party API**

RestTemplate exchange is used to invoke CRM API, url is defined in application.yml
crmCapabilityUrl : https://${HOSTNAME:localhost}:8000/crm-data
We can change the URL when we get the details for this service.
"# CustomerDetails" 
