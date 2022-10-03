package com.example.springneo4jdemo.controller;


import com.example.springneo4jdemo.dto.StudentPayLoadDTO;
import com.example.springneo4jdemo.entity.Student;
import com.example.springneo4jdemo.service.IStudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/Student")
@CrossOrigin("*")
@Slf4j
public class StudentController {
    @Autowired
    private IStudentService studentService;

    @PostMapping("/saveStudent")
    public Student saveStudent(@RequestBody StudentPayLoadDTO studentPayLoadDTO) throws Exception {
        log.debug(studentPayLoadDTO.toString());
        return studentService.saveStudent(studentPayLoadDTO);
    }

    @GetMapping("/test")
    public String checkStatus() {
        return "It is working fine !!";
    }

    //find student by id
    @GetMapping ("/findById/{id}")
    public Student findById(@PathVariable UUID id){
        return studentService.findById(id);
    }

    //find student by name
    @GetMapping("/findByName/{name}")
    public List<Student> findByName(@PathVariable String name){
        return studentService.findByName(name);
    }


    @PostMapping("/delete/{id}")
    public String deleteStudentByID(@PathVariable UUID id) {
        studentService.delete(id);
        return "done";
    }
//json sample
   /* {

        "studentId": null,
            "name": "Surya",
            "age": 27,
            "bloodGroup": "A+",
            "departmentPayLoadDTO": {
        "deptId": null,
                "departmentName": "Electrical"
    },
        "isLearningPayLoads": [{
        "learningId": null,
                "marks": 80,
                "subjectName": "Java"
    },
        {
            "learningId": null,
                "marks": 70,
                "subjectName": "Python"
        }
		]

    }
*/





}
