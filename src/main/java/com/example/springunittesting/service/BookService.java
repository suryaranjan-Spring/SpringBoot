package com.example.springunittesting.service;

import com.example.springunittesting.dao.BookRepository;
import com.example.springunittesting.exception.NotFoundException;
import com.example.springunittesting.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Dracula
 */
@Service
public class BookService {
    @Autowired
private BookRepository bookRepository;
    public List<Book> getAllBook() {
        System.out.println("inside service");
        List<Book> booklist=bookRepository.findAll();
        System.out.println("last service");
        return booklist;
    }



    public List<Book> getNewBooksWithAppliedPrice(int appliedDiscount, String author){
        List<Book> newBooks = bookRepository.findBookByAuthor(author);
        // 500 apply 10% -> 10% of 500 -> 50 -> 500 - 50 -> 450

        for(Book book : newBooks){
            int price = book.getPrice();
            int newPrice = price - (appliedDiscount * price / 100);
            book.setPrice(newPrice);
        }

        return newBooks;
    }

    public Book getBookById(long id) throws Exception{
        Optional<Book> bookOptional= bookRepository.findById(id);
        if(bookOptional.isPresent())
            return bookOptional.get();
        else{
            throw new NotFoundException("data is not available in database");
        }
    }
}
