package com.example.springunittesting.controller;

import com.example.springunittesting.model.Book;
import com.example.springunittesting.service.BookService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/**
 * @author Dracula
 */
@SpringBootTest
@AutoConfigureMockMvc
class BookControllerTest {
@Autowired
    private MockMvc mockMvc;
@MockBean
    private BookService bookService;

    @Test
    void getAllBook() throws Exception {
        Book book1 = new Book(1L, "Mockito In Action", "Mockito In Action Summary" ,"Surya",500);
        Book book2 = new Book(2L, "JUnit 5 In Action", "JUnit 5 In Action Summary","Rama" ,660);
        List<Book> newBooks = new ArrayList<>();
        newBooks.add(book1);
        newBooks.add(book2);
        when(bookService.getAllBook()).thenReturn(newBooks);
        mockMvc.perform(get("/Book/getAllBook"))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
               // .andExpect(jsonPath("$.id", is(1)));
    }

    @Test
    void getBookById() {
    }

    @Test
    void saveBook() {
    }

    @Test
    void updateBook() {
    }
}