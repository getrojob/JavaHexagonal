package com.thejob.hexagonal.adapters.in.controller;

import com.thejob.hexagonal.adapters.in.controller.mapper.CustomerMapper;
import com.thejob.hexagonal.adapters.in.controller.request.CustomerRequest;
import com.thejob.hexagonal.adapters.in.controller.response.CustomerResponse;
import com.thejob.hexagonal.application.core.domain.Address;
import com.thejob.hexagonal.application.core.domain.Customer;
import com.thejob.hexagonal.application.ports.in.DeleteCustomerByIdInputPort;
import com.thejob.hexagonal.application.ports.in.FindCustomerByIdInputPort;
import com.thejob.hexagonal.application.ports.in.InsertCustomerInputPort;
import com.thejob.hexagonal.application.ports.in.UpdateCustomerInputPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InsertCustomerInputPort mockInsertCustomerInputPort;
    @MockBean
    private UpdateCustomerInputPort mockUpdateCustomerInputPort;
    @MockBean
    private DeleteCustomerByIdInputPort mockDeleteCustomerByIdInputPort;
    @MockBean
    private CustomerMapper mockCustomerMapper;
    @MockBean
    private FindCustomerByIdInputPort mockFindCustomerByIdInputPort;

    @Test
    void testInsert() throws Exception {
        // Setup
        // Configure CustomerMapper.toCustomer(...).
        final Customer customer = new Customer("id", "nome", new Address("street", "city", "state"), "cpf", false);
        final CustomerRequest customerRequest = new CustomerRequest();
        customerRequest.setName("name");
        customerRequest.setCpf("cpf");
        customerRequest.setZipCode("zipCode");
        when(mockCustomerMapper.toCustomer(customerRequest)).thenReturn(customer);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/api/v1/customer")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
        verify(mockInsertCustomerInputPort).insert(any(Customer.class), eq("zipCode"));
    }

    @Test
    void testFindById() throws Exception {
        // Setup
        // Configure FindCustomerByIdInputPort.find(...).
        final Customer customer = new Customer("id", "nome", new Address("street", "city", "state"), "cpf", false);
        when(mockFindCustomerByIdInputPort.find("id")).thenReturn(customer);

        // Configure CustomerMapper.toCustomerResponse(...).
        final CustomerResponse customerResponse = new CustomerResponse();
        customerResponse.setNome("nome");
        customerResponse.setAddress(new Address("street", "city", "state"));
        customerResponse.setCpf("cpf");
        customerResponse.setIsValidCpf(false);
        when(mockCustomerMapper.toCustomerResponse(any(Customer.class))).thenReturn(customerResponse);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/v1/customer/{id}", "id")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testUpdate() throws Exception {
        // Setup
        // Configure CustomerMapper.toCustomer(...).
        final Customer customer = new Customer("id", "nome", new Address("street", "city", "state"), "cpf", false);
        final CustomerRequest customerRequest = new CustomerRequest();
        customerRequest.setName("name");
        customerRequest.setCpf("cpf");
        customerRequest.setZipCode("zipCode");
        when(mockCustomerMapper.toCustomer(customerRequest)).thenReturn(customer);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(put("/api/v1/customer/{id}", "id")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
        verify(mockUpdateCustomerInputPort).update(any(Customer.class), eq("zipCode"));
    }

    @Test
    void testDelete() throws Exception {
        // Setup
        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(delete("/api/v1/customer/{id}", "id")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
        verify(mockDeleteCustomerByIdInputPort).delete("id");
    }
}
