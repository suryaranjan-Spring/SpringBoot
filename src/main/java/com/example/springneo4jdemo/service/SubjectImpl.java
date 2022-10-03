package com.example.springneo4jdemo.service;


import com.example.springneo4jdemo.dto.SubjectPayLoadDTO;
import com.example.springneo4jdemo.entity.Subject;
import com.example.springneo4jdemo.repository.SubjectRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class SubjectImpl implements ISubjectService {
    private SubjectRepository subjectRepository;

    public SubjectImpl(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public void deleteBookByID(UUID id) {
        subjectRepository.deleteById(id);
    }
    @Override
    public String findBySubjectName(String subjectName) {

        Subject subject = subjectRepository.findBySubjectName(subjectName);
        return subject.getSubjectName() ;
    }

    public List<Subject> saveSubject(List<SubjectPayLoadDTO> subjectPayLoadDTOList) {
        List<Subject> savedSubjecttList = new ArrayList<>();
        subjectPayLoadDTOList.forEach(subjectPayLoadDTO -> {
            Subject subject = new Subject();
            BeanUtils.copyProperties(subjectPayLoadDTO, subject);
            savedSubjecttList.add(subjectRepository.save(subject));
        });
        return savedSubjecttList;
    }




}
