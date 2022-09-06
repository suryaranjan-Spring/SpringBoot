package com.example.springunittesting.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Dracula
 */
class CalculatorServiceTest {
    private CalculatorService calculatorService;
    @Test
    void add() {
        CalculatorService calculatorService=new CalculatorService ();
        int actualResult=calculatorService.add(30,10);
        int expectedResult=40;
        Assertions.assertEquals(expectedResult,actualResult);

    }

    @Test
    void mul() {
    }
}