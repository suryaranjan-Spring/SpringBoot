package com.example.springneo4jdemo.service;

import com.example.springneo4jdemo.dto.CollegePayLoadDTO;
import com.example.springneo4jdemo.dto.StudentPayLoadDTO;
import com.example.springneo4jdemo.entity.College;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

public interface ICollegeService {
    public College saveCollege(CollegePayLoadDTO collegePayLoadDTO) throws Exception;

    public int updateCollegeWithDEpartment( String collegename,  String departmentname,  String relationType) throws Exception;
    public void deleteCollegeById(UUID id) throws Exception;

    public String addDepartment(CollegePayLoadDTO collegePayLoadDTO) ;
}
