package com.example.springneo4jdemo.service;

import com.example.springneo4jdemo.dto.SubjectPayLoadDTO;
import com.example.springneo4jdemo.entity.Subject;
import com.example.springneo4jdemo.repository.SubjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SubjectImplTest {
    @Mock
    private SubjectRepository subjectRepository;
    @InjectMocks
    private SubjectImpl subjectImp;
    private List<SubjectPayLoadDTO> subjectPayLoadDTOList;
    @BeforeEach
    public void setUpData() {
        subjectPayLoadDTOList =new ArrayList<>();
       SubjectPayLoadDTO subject1 = new SubjectPayLoadDTO();
        subject1.setSubjectName("Java");
        SubjectPayLoadDTO subject2 = new SubjectPayLoadDTO();
        subject2.setSubjectName("Python");
        SubjectPayLoadDTO subject3 = new SubjectPayLoadDTO();
        subject3.setSubjectName("C");
        SubjectPayLoadDTO subject4 = new SubjectPayLoadDTO();
        subject4.setSubjectName("C++");
        subjectPayLoadDTOList.add(subject1);
        subjectPayLoadDTOList.add(subject2);
        subjectPayLoadDTOList.add(subject3);
        subjectPayLoadDTOList.add(subject4);
    }


    @Test
    void deleteBookByID() {
    }

    @Test
    @DisplayName("Save Subjects")
    void saveSubject() {


        subjectPayLoadDTOList.forEach(subjectPayLoadDTO -> {
            Subject subject = new Subject();
            BeanUtils.copyProperties(subjectPayLoadDTO, subject);
            when(subjectRepository.save(subject)).thenReturn(subject);
        });
        //assertEquals(subject.getSubjectName(), subjectImp.saveSubject(subject).getSubjectName());
        List<Subject> subjectList1 = subjectImp.saveSubject(subjectPayLoadDTOList);
        assertThat(subjectList1.size()).isEqualTo(subjectPayLoadDTOList.size());
        assertThat(subjectList1.get(0).getSubjectName()).isEqualTo(subjectPayLoadDTOList.get(0).getSubjectName());
        verify(subjectRepository, times(subjectPayLoadDTOList.size())).save(any());
    }
}