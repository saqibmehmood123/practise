package com.example.pratisetest.web;

import com.example.pratisetest.dao.BookRepository;
import com.example.pratisetest.model.Book;
import com.example.pratisetest.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks()
    {
        return new ResponseEntity<>( bookService.getAllBooks(),  HttpStatus.OK);
    }

    @PostMapping("/book")
    public  ResponseEntity<Book> createBook(@RequestBody Book book) {
        return new ResponseEntity<>( bookService.createBook(book),  HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {

        return new ResponseEntity<>( bookService.updateBook(id, updatedBook),  HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id)
    {
        bookService.deleteBook(id);
    }
}