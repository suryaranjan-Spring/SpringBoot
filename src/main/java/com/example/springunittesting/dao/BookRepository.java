package com.example.springunittesting.dao;

import com.example.springunittesting.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Dracula
 */
@Repository
public interface BookRepository extends JpaRepository <Book,Long>{

    public List<Book> findBookByAuthor(String author);
}
