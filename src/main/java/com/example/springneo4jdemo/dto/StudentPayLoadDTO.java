package com.example.springneo4jdemo.dto;

import lombok.Data;

import java.util.List;
import java.util.UUID;
@Data
public class StudentPayLoadDTO {
    private UUID studentId;

    private String name;

    private int age;
    private String bloodGroup;

    private DepartmentPayLoadDTO departmentPayLoadDTO;

    private List<IsLearningPayLoad> isLearningPayLoads;

}
