package com.example.springunittesting.service;

import com.example.springunittesting.dao.BookRepository;
import com.example.springunittesting.exception.NotFoundException;
import com.example.springunittesting.model.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/**
 * @author Dracula
 */
@SpringBootTest
@ExtendWith(MockitoExtension.class)
class BookServiceTest {
    @Mock
    private BookRepository bookRepository;
    @InjectMocks
    private BookService bookService;
    @Test
    public void getAllBookTest(){
    Book book1 = new Book(1L, "Mockito In Action", "Mockito In Action Summary" ,"Surya",500);
    Book book2 = new Book(2L, "JUnit 5 In Action", "JUnit 5 In Action Summary","Rama" ,660);
    List<Book> newBooks = new ArrayList<>();
    newBooks.add(book1);
    newBooks.add(book2);
    when(bookRepository.findAll()).thenReturn(newBooks);
    assertEquals(3,bookService.getAllBook().size());
    }

    @Test
    public void getBookByIdPositiveTest() throws Exception {
        Book book1 = new Book(1L, "Mockito In Action", "Mockito In Action Summary", "Surya", 500);
        Book book2 = new Book(2L, "JUnit 5 In Action", "JUnit 5 In Action Summary", "Rama", 660);
        when(bookRepository.findById(1l)).thenReturn(Optional.of(book1));
        assertEquals(1L, bookService.getBookById(1l).getBookId());
        assertEquals("Mockito In Action", bookService.getBookById(1l).getName());
        assertEquals("Mockito In Action Summary", bookService.getBookById(1l).getSumary());
        assertEquals("Surya", bookService.getBookById(1l).getAuthor());
        assertEquals(500, bookService.getBookById(1l).getPrice());
    }
    @Test
    public void getBookByIdTestForException() throws Exception {
        Book book1 = new Book(1L, "Mockito In Action", "Mockito In Action Summary", "Surya", 500);
        Book book2 = new Book(2L, "JUnit 5 In Action", "JUnit 5 In Action Summary", "Rama", 660);
        when(bookRepository.findById(4l)).thenThrow(NotFoundException.class);
        assertThrows(NotFoundException.class,()->bookService.getBookById(4l));

    }
    // 500 apply 10% -> 10% of 500 -> 50 -> 500 - 50 -> 450
    @Test
    void getNewBooksWithAppliedPrice() {
        Book book1 = new Book(1L, "Mockito In Action", "Mockito In Action Summary", "Surya", 500);
        Book book2 = new Book(2L, "JUnit 5 In Action", "JUnit 5 In Action Summary", "Rama", 660);
        List<Book> newBooks = new ArrayList<>();
        newBooks.add(book1);
       // newBooks.add(book2);
        when(bookRepository.findBookByAuthor("Surya")).thenReturn(newBooks);
        List<Book> discountedBooks=bookService.getNewBooksWithAppliedPrice(10,"Surya");
        assertEquals(450,discountedBooks.get(0).getPrice());
    }
}