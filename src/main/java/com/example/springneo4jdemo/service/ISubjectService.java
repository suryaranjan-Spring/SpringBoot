package com.example.springneo4jdemo.service;

import com.example.springneo4jdemo.dto.SubjectPayLoadDTO;
import com.example.springneo4jdemo.entity.Subject;

import java.util.List;
import java.util.UUID;

public interface ISubjectService {
    public void deleteBookByID(UUID id);

    String findBySubjectName(String subjectName);

    public List<Subject> saveSubject(List<SubjectPayLoadDTO> subjectPayLoadDTOList);

}
