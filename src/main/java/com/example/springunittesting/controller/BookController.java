package com.example.springunittesting.controller;

import com.example.springunittesting.dao.BookRepository;
import com.example.springunittesting.exception.NotFoundException;
import com.example.springunittesting.model.Book;
import com.example.springunittesting.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author Dracula
 */
@RestController
@RequestMapping("/Book")
@Slf4j
public class BookController {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookService bookService;

    @GetMapping("/getAllBook")
    public ResponseEntity<?> getAllBook() {
        log.debug("inside getAllBook()");
        return this.getResponseEntity(HttpStatus.BAD_REQUEST,Optional.of(bookService.getAllBook()));
    }

    @GetMapping("/getBookById/{bookId}")
    public Book getBookById(@PathVariable Long bookId) {
        return bookRepository.findById(bookId).get();
    }

    @PostMapping("/saveBook")
    public Book saveBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    @PutMapping("/updateBook")
    public Book updateBook(@RequestBody Book book) {

        if (book.getBookId() == null) {
            throw new NotFoundException("Book id can not be null");
        }
        Optional<Book> bookOptional = bookRepository.findById(book.getBookId());
        if (!bookOptional.isPresent()) {
            throw new NotFoundException("Book is not available");
        }
        Book existingBook = bookOptional.get();
        existingBook.setName(book.getName());
        existingBook.setSumary(book.getSumary());
        existingBook.setPrice(book.getPrice());
        return bookRepository.save(existingBook);
    }

    public ResponseEntity<?> getResponseEntity(HttpStatus status,Optional<Object> book){

        ResponseEntity entity=new ResponseEntity(book.isPresent() ? book.get() :new Book(),status);
        return entity;
    }
}
