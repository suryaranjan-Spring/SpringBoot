package com.example.springneo4jdemo.exception;

import lombok.Data;

@Data
public class DataNotFoundExeption extends RuntimeException{

    private String message;

    public DataNotFoundExeption(String message) {
        this.message = message;
    }
}
