package com.example.springneo4jdemo.controller;

import com.example.springneo4jdemo.dto.UniversityPayLoadDTO;
import com.example.springneo4jdemo.service.IUniversityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/university")
public class UniversityController {
     private IUniversityService universityService;

    @PostMapping("/saveUniversity")
    public ResponseEntity<?> saveUniversity(@RequestBody UniversityPayLoadDTO universityPayLoadDTO) throws Exception {
        return new ResponseEntity<>(universityService.saveUniversity(universityPayLoadDTO), HttpStatus.OK);
    }

    public UniversityController(IUniversityService universityService) {
        this.universityService = universityService;
    }
}
