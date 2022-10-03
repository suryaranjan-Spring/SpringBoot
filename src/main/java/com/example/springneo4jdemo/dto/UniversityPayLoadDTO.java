package com.example.springneo4jdemo.dto;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class UniversityPayLoadDTO {
    private UUID universityId;
    private String universityName;
    private List<DepartmentPayLoadDTO> departmentList;
    private List<CollegePayLoadDTO> collegeList;
}
