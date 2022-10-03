package com.example.springneo4jdemo.service;


import com.example.springneo4jdemo.dto.StudentPayLoadDTO;
import com.example.springneo4jdemo.entity.Student;

import java.util.List;
import java.util.UUID;

public interface IStudentService {

    public Student saveStudent(StudentPayLoadDTO studentPayLoadDTO) throws Exception;
    public Student findById(UUID id);

    public List<Student> findByName(String name);

    public void delete(UUID id);
}
