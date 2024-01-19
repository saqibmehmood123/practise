package com.example.pratisetest.services;

import com.example.pratisetest.dao.CustomerRepository;
import com.example.pratisetest.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
public class CustomerServices
{
    @Autowired
    private CustomerRepository customerRepository;
//    @Autowired
//    private BCryptPasswordEncoder passwordEncoder;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer createCustomer(Customer customer) {
        // Encrypt the password before saving it to the database
        //customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        return customerRepository.save(customer);
    }

    public Customer updateCustomer( Long id,  Customer updatedCustomer) {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        existingCustomer.setName(updatedCustomer.getName());
        existingCustomer.setEmail(updatedCustomer.getEmail());

        // Encrypt the password before updating it in the database
        ///  existingCustomer.setPassword(passwordEncoder.encode(updatedCustomer.getPassword()));

        return customerRepository.save(existingCustomer);
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }





}
