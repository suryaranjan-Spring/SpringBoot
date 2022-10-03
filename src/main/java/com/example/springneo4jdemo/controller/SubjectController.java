package com.example.springneo4jdemo.controller;


import com.example.springneo4jdemo.dto.SubjectPayLoadDTO;
import com.example.springneo4jdemo.service.ISubjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/subject")
public class SubjectController {

    private ISubjectService subjectService;

    public SubjectController(ISubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @PostMapping("/delete/{id}")
    public String deleteBookByID(@PathVariable UUID id) {
        subjectService.deleteBookByID(id);
        return "done";
    }

    @PostMapping("/saveallsubjects")
    public ResponseEntity<?> saveSuibjects(@RequestBody List<SubjectPayLoadDTO> subjectPayLoadDTOList) {
        return new ResponseEntity<>(subjectService.saveSubject(subjectPayLoadDTOList), HttpStatus.OK);
    }


}
