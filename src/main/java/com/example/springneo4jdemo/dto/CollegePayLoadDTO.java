package com.example.springneo4jdemo.dto;

import com.example.springneo4jdemo.entity.Department;
import lombok.Data;

import java.util.List;
@Data
public class CollegePayLoadDTO {
    private String collegeId;
    private String collegeName;
    private List<DepartmentPayLoadDTO> departmentList;
}
