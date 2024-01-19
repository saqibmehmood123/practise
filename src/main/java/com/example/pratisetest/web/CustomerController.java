package com.example.pratisetest.web;
import com.example.pratisetest.dao.CustomerRepository;
import com.example.pratisetest.model.Customer;
import com.example.pratisetest.services.CustomerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerServices customerServices;


    @GetMapping
    public ResponseEntity<List<Customer>>  getAllCustomers() {

        return  new ResponseEntity<>( customerServices.getAllCustomers(),  HttpStatus.OK);
    }

    @PostMapping("/customer")
    public ResponseEntity<Customer>  createCustomer(@RequestBody Customer customer)
    {
        return  new ResponseEntity<>( customerServices.createCustomer(customer),  HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer updatedCustomer) {

        return  new ResponseEntity<>(customerServices.updateCustomer(id, updatedCustomer),  HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        customerServices.deleteCustomer(id);
    }
}