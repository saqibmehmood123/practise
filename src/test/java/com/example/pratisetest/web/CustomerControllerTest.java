package com.example.pratisetest.web;
import com.example.pratisetest.model.Customer;
import com.example.pratisetest.services.CustomerServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

//import static org.hamcrest.Matchers.equalTo;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTest
{

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private  static CustomerServices customerServices;
    public static final List<Customer> customerList = new ArrayList<>();

    public static final String  BASE_URL="/api/customers";

    String token;
    @BeforeAll
    static void beforeAllTests() {
        Customer customer = new Customer();
        customer.setName("saqib");
        customer.setEmail("saqib@gmail.com");
        customer.setPassword("saqib123");
        customerList.add(customer);

    }

        @Test
        void getAllCustomersTest() throws Exception {
            when(customerServices.getAllCustomers() ).thenReturn(customerList);
         mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL)
                         .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(jsonPath("$[0].name", equalTo("saqib") ) );
        }

        @Test
        void  createCustomer() throws Exception
        {
            Customer outputCustomer = new Customer();
            outputCustomer.setId(1L);
            outputCustomer.setName("John Doe");
            when(customerServices.createCustomer(any(Customer.class))).thenReturn(outputCustomer);
            token = generateToken("today1344" );
            mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL+"/customer")
                            .  header("AUTHORIZATION","Bearer "+token).contentType(MediaType.APPLICATION_JSON)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(new ObjectMapper().writeValueAsString(outputCustomer)))
                    .andExpect(MockMvcResultMatchers.status().isCreated())
                    .andExpect(jsonPath("$.name").value("John Doe"));
        }

    @Test
    void  UpdateCustomerTest() throws Exception
    {

        Long customerId = 1L;
        Customer updatedCustomer = new Customer();
        updatedCustomer.setId(customerId);
        updatedCustomer.setName("Updated Name");

        // Mock the service method
        when(customerServices.updateCustomer(customerId, updatedCustomer)).thenReturn(updatedCustomer);
        // Act and Assert
         token = generateToken("today1544" );


        mockMvc.perform(MockMvcRequestBuilders.put(BASE_URL+"/{id}", customerId)
                        .  header("AUTHORIZATION","Bearer "+token).contentType(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(updatedCustomer)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    public void deleteCustomerTest() throws Exception {
        Long customerId = 1L;
        // Mock the service method to do nothing (void method)
        doNothing().when(customerServices).deleteCustomer(customerId);
        // Act and Assert
        token = generateToken("today164" );

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/customers/{id}", customerId)
                        .  header("AUTHORIZATION","Bearer "+token).contentType(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
        // Verify that the service method was called
        verify(customerServices, times(1)).deleteCustomer(customerId);
    }
    /*
      Here i will make call to end point of token generating .
      To make json body for request i have called makesEmailBodyJson method
      After getting token in body of response , i will call another method tokenizingResposne which
      eliminate leading and ending brackets and collen e.h toekn:{abckkkkd} and gives  abckkkkd

     */
    private String generateToken(String emailAddress ) throws IOException, InterruptedException {

        String requestBody = makesEmailBodyJson(emailAddress);
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .uri(URI.create("http://localhost:8080/api/v1/auth/signup"))
                .header("Content-Type", "application/json")
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient()
                .send(request, HttpResponse.BodyHandlers.ofString());

        return   tokenizingResposne(response.body() );
    }
    private String makesEmailBodyJson(String emailAddress ) throws IOException, InterruptedException {

        String requestBody = "{\n" +
                "    \"firstName\":\"saqib1\",\n" +
                "    \"lastName\": \"saqib2\",\n" +
                "     \"email\": \"" + emailAddress + "\" ,\n" +
                "    \"password\": \"123456\"\n" +
                "    \n" +
                "} ";
        return requestBody;
    }
    private String tokenizingResposne(String  responseBody ) throws IOException, InterruptedException {
        String[] parts = responseBody.split(":");
        String part1 = parts[0]; // 004
        token = parts[1]; // 034556
        return   token = token.substring(1,token.length()-2);
    }
}

///  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
// git remote add origin https://saqibmehmood123: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@gitlab.com/saqibmehmood123/https://github.com/saqibmehmood123/restcontrollerjwtmockito.git
