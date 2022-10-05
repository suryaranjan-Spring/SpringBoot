package com.example.springneo4jdemo.repository;

import com.example.springneo4jdemo.entity.Department;
import com.example.springneo4jdemo.entity.Student;
import com.example.springneo4jdemo.it.Neo4JContainer;
import org.assertj.core.api.Assertions;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@RunWith(SpringRunner.class)
class StudentRepositoryTest extends Neo4JContainer {
    @Autowired
    private StudentRepository studentRepository;

    @Test
    void findByStudentId() {
    }

    @Test
    void findByName() {
    }

    @Test
    void delete() {
    }
    @Test
    public void givenStudentObject_whenSave_returnStudentObject(){
       Student student=new Student();
        student.setAge(20);
        student.setBloodGroup("B+");
        student.setName("Surya");
       Student saveStudent= studentRepository.save(student);

        Assertions.assertThat(saveStudent).isNotNull();
        Assertions.assertThat(saveStudent.getStudentId()).isNotNull();
    }
}