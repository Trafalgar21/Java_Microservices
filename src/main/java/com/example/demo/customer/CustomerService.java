package com.example.demo.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    public final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) { this.customerRepository = customerRepository; }

    public Customer getCustomerByNumber(Long customerNumber){

        Optional<Customer> customer = Optional.ofNullable(customerRepository.findById(customerNumber)
                .orElseThrow(() -> new IllegalStateException("Customer not found")));

        return customerRepository.getById(customerNumber);
    }

    public Long registerCustomer(Customer customer){


        Customer savedCustomer = customerRepository.save(customer);
        return savedCustomer.getCustomerNumber();

    }
}
