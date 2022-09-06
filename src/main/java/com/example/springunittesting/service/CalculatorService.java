package com.example.springunittesting.service;

import org.springframework.stereotype.Service;

/**
 * @author Dracula
 */
@Service
public class CalculatorService {

    public int add(int a,int b){
        return a+b;
    }

    public int mul(int a,int b){
        return a*b;
    }
}
