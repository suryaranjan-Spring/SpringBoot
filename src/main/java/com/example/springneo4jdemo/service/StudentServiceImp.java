package com.example.springneo4jdemo.service;


import com.example.springneo4jdemo.dto.StudentPayLoadDTO;
import com.example.springneo4jdemo.entity.Department;
import com.example.springneo4jdemo.entity.IsLearning;
import com.example.springneo4jdemo.entity.Student;
import com.example.springneo4jdemo.entity.Subject;
import com.example.springneo4jdemo.repository.DepartmentRepository;
import com.example.springneo4jdemo.repository.StudentRepository;
import com.example.springneo4jdemo.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentServiceImp implements IStudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private DepartmentRepository departmentRepository;


    @Override
    public Student saveStudent(StudentPayLoadDTO studentPayLoadDTO) throws Exception {

        // save department 1st
        Department department = new Department();
        department.setDepartmentName(studentPayLoadDTO.getDepartmentPayLoadDTO().getDepartmentName());
        departmentRepository.save(department);

        List<IsLearning> isLearningList = new ArrayList<IsLearning>();
        //save subject
        studentPayLoadDTO.getIsLearningPayLoads().forEach(isLearningPayLoad -> {
            Subject subject = new Subject();
            subject.setSubjectName(isLearningPayLoad.getSubjectName());
            subjectRepository.save(subject);
            IsLearning isLearning=new IsLearning();
            isLearning.setMarks(isLearningPayLoad.getMarks());
            isLearning.setSubject(subject);
            isLearningList.add(isLearning);
        });
        // save student object
        Student student = new Student();
        student.setName(studentPayLoadDTO.getName());
        student.setAge(studentPayLoadDTO.getAge());
        student.setBloodGroup(studentPayLoadDTO.getBloodGroup());
        student.setDepartment(department);
        student.setIsLearningList(isLearningList);
        return studentRepository.save(student);
    }

    //find student by nodeId

    public Student findById(UUID id) {
        Optional<Student> studentOptional = studentRepository.findById(id);
        if (studentOptional.isPresent())
            return studentOptional.get();
        else
            return null;
    }

    // find student by name

    public List<Student> findByName(String name) {
        return studentRepository.findByName(name);
    }


    public void delete(UUID id) {

        studentRepository.delete(id);
    }

}
