package com.example.demo;


import com.example.demo.customer.Customer;
import com.example.demo.customer.CustomerRepository;
import com.example.demo.customer.CustomerService;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {
    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    Customer mockCustomer = new Customer(1L, "Juan Dela Cruz", "1234567890", "juandelacruz@gmail.com", "#123 dela cruz", "#123 juan");

    @Test
    public void testGetCustomerByNumber(){

        when(customerRepository.findById(1L)).thenReturn(Optional.of(mockCustomer));
        when(customerRepository.getById(1L)).thenReturn(mockCustomer);

        Customer customer = customerService.getCustomerByNumber(1L);

        assertTrue(1L == customer.getCustomerNumber());
    }

    @Test
    public void testGetCustomerByNumberNotFound(){

        when(customerRepository.findById(2L)).thenThrow(new IllegalStateException("Customer not found"));

        IllegalStateException thrown = assertThrows(IllegalStateException.class, () -> customerService.getCustomerByNumber(2L));
        assertTrue(thrown.getMessage().contains("Customer not found"));
    }

    @Test
    public void testRegisterCustomer(){
        Long expectedResult = 1L;
        when(customerRepository.save(mockCustomer)).thenReturn(mockCustomer);

        Long actualResult = customerService.registerCustomer(mockCustomer);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testRegisterCustomerError(){
        mockCustomer.setCustomerEmail(null);
        when(customerRepository.save(mockCustomer)).thenThrow(new IllegalStateException("Email is required field"));

        IllegalStateException thrown = assertThrows(IllegalStateException.class, () -> customerService.registerCustomer(mockCustomer));

        assertTrue(thrown.getMessage().contains("Email is required field"));

    }

}
