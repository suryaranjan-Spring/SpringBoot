package com.example.springunittesting.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @author Dracula
 */
@NoArgsConstructor
@AllArgsConstructor
public class NotFoundException extends RuntimeException {

    private String msg;

}
