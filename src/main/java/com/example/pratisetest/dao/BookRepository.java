package com.example.pratisetest.dao;

import com.example.pratisetest.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}