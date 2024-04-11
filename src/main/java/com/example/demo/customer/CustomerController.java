package com.example.demo.customer;

import com.example.demo.util.ResponseBuilder;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping (path = "api/v1/account")
public class CustomerController {

    ResponseEntity responseEntity = null;
    @Autowired
    private CustomerService customerService;

    public CustomerController(CustomerService customerService) { this.customerService = customerService; }

    @GetMapping("/{customerNumber}")
    public ResponseEntity<ResponseBuilder> getCustomerById(@PathVariable("customerNumber") Long customerNumber){
        ResponseBuilder response = new ResponseBuilder();

        try{
            Customer customer = customerService.getCustomerByNumber(customerNumber);

            response.setResponse("Customer", customer);
            response.setResponse("transactionStatusCode",302);
            response.setResponse("transactionStatusDescription", "Customer Account found");

            responseEntity = new ResponseEntity<>(response, HttpStatus.FOUND);
        } catch (IllegalStateException ex) {

            response.setResponse("transactionStatusCode", 404);
            response.setResponse("transactionStatusDescription", ex.getMessage());
            responseEntity = new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

        } finally {
            return responseEntity;
        }
    }

    @PostMapping
    public ResponseEntity<ResponseEntity> registerCustomer(@RequestBody @Valid Customer customer,  BindingResult bindingResult){
        ResponseBuilder response = new ResponseBuilder();

        if(bindingResult.hasErrors()){
            String error = bindingResult.getAllErrors()
                    .stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.joining());

            response.setResponse("transactionStatusCode", 400);
            response.setResponse("transactionStatusDescription", error);
            responseEntity = new ResponseEntity(response, HttpStatus.BAD_REQUEST);

            return responseEntity;
        }
        try{
            Long customerNumber = customerService.registerCustomer(customer);

            response.setResponse("customerNumber", customerNumber);
            response.setResponse("transactionStatusCode", 201);
            response.setResponse("transactionStatusDescription", "Customer account created");

            responseEntity = new ResponseEntity(response, HttpStatus.CREATED);
        } catch (Exception ex){
            response.setResponse("transactionStatusCode", 400);
            response.setResponse("transactionStatusDescription", ex.getMessage());

            responseEntity = new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        } finally {
            return responseEntity;
        }
    }


}
