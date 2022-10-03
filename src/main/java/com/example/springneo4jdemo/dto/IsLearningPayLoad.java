package com.example.springneo4jdemo.dto;

import lombok.Data;

@Data
public class IsLearningPayLoad {

    private Long learningId;

    private int marks;

    private String subjectName;
}
