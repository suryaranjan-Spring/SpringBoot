package com.example.springunittesting.model;

import lombok.*;

import javax.persistence.*;

/**
 * @author Dracula
 */
@Entity
@Table(name = "Book_Details")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;
    @NonNull
    private String name;
    @NonNull
    private String sumary;

    private String author;
    private int price;

}
