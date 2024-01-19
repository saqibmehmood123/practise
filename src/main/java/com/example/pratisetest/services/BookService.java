package com.example.pratisetest.services;

import com.example.pratisetest.dao.BookRepository;
import com.example.pratisetest.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
public class BookService
{

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }


    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public Book updateBook( Long id,  Book updatedBook) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        existingBook.setTitle(updatedBook.getTitle());
        existingBook.setAuthor(updatedBook.getAuthor());
        existingBook.setPublisher(updatedBook.getPublisher());
        existingBook.setPublishingYear(updatedBook.getPublishingYear());
        existingBook.setCategory(updatedBook.getCategory());

        return bookRepository.save(existingBook);
    }

    public void deleteBook( Long id) {
        bookRepository.deleteById(id);
    }

}
