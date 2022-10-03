package com.example.springneo4jdemo.controller;

import com.example.springneo4jdemo.dto.CollegePayLoadDTO;
import com.example.springneo4jdemo.service.ICollegeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/college")
public class CollegeController {
    private ICollegeService collegeService;

    public CollegeController(ICollegeService collegeService) {
        this.collegeService = collegeService;
    }

    @PostMapping("/savecollege")
    public ResponseEntity<?> saveCollege(@RequestBody CollegePayLoadDTO collegePayLoadDTO) throws Exception {
        return new ResponseEntity<>(collegeService.saveCollege(collegePayLoadDTO), HttpStatus.OK);
    }
@PutMapping("/adddept/{collegename}/{departmentname}/{relationType}")
    public ResponseEntity<?> updateCollegeWithDEpartment(@PathVariable String collegename, @PathVariable String departmentname, @PathVariable String relationType) throws Exception {
        return new ResponseEntity<>(collegeService.updateCollegeWithDEpartment(collegename, departmentname, relationType), HttpStatus.OK);
    }

    @DeleteMapping("/deleteCollege/{id}")
    public ResponseEntity<?> deleteCollegeById(@PathVariable UUID id) throws Exception {
        collegeService.deleteCollegeById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/addDepartment")
    public ResponseEntity<?> addDepartment(@RequestBody CollegePayLoadDTO collegePayLoadDTO){
        return new ResponseEntity<>(collegeService.addDepartment(collegePayLoadDTO),HttpStatus.OK);
    }
}
