package com.example.springneo4jdemo.dto;

import lombok.Data;

import java.util.UUID;
@Data
public class DepartmentPayLoadDTO {

    private UUID deptId;

    private String departmentName;
}
