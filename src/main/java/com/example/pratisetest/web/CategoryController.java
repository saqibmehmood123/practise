package com.example.pratisetest.web;

import com.example.pratisetest.dao.CategoryRepository;
import com.example.pratisetest.model.Book;
import com.example.pratisetest.model.Category;
import com.example.pratisetest.services.BookService;
import com.example.pratisetest.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public  ResponseEntity<List<Category>> getAllCategories() {

        return new ResponseEntity<>( categoryService.getAllCategories(),  HttpStatus.OK);

    }

    @PostMapping("/category")
    public ResponseEntity<Category> createCategory(@RequestBody Category category)

    {

        return  new ResponseEntity<>( categoryService.createCategory(category),  HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<Category>   updateCategory(@PathVariable Long id, @RequestBody Category updatedCategory) {


         return  new ResponseEntity<>( categoryService.updateCategory(id, updatedCategory),  HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
    }
}