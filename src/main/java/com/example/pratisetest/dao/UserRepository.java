package com.example.pratisetest.dao;

import com.example.pratisetest.model.Customer;
import com.example.pratisetest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

    public interface UserRepository extends JpaRepository<User, Integer> {
        // Since email is unique, we'll find users by email
        Optional<User> findByEmail(String email);

}