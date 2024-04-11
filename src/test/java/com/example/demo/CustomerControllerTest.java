package com.example.demo;

import com.example.demo.customer.Customer;
import com.example.demo.customer.CustomerController;
import com.example.demo.customer.CustomerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    Customer mockCustomer = new Customer(1L, "Juan Dela Cruz", "1234567890", "juandelacruz@gmail.com", "#123 dela cruz", "#123 juan");

    @Test
    public void testGetCustomerById() throws Exception {
        when(customerService.getCustomerByNumber(1L)).thenReturn(mockCustomer);

        MvcResult result = mockMvc.perform(get("/api/v1/account/1"))
                .andExpect(status().isFound())
                .andReturn();

        assertEquals(302, result.getResponse().getStatus());
    }

    @Test
    public void testGetCustomerByIdNotFound() throws Exception {
        when(customerService.getCustomerByNumber(1l)).thenThrow(IllegalStateException.class);
        MvcResult result = mockMvc.perform(get("/api/v1/account/1"))
                .andExpect(status().isNotFound())
                .andReturn();

        assertEquals(404, result.getResponse().getStatus());
    }
}
