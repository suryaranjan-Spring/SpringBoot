package com.example.springneo4jdemo.service;

import com.example.springneo4jdemo.dto.UniversityPayLoadDTO;
import com.example.springneo4jdemo.entity.University;

public interface IUniversityService {

    public University saveUniversity(UniversityPayLoadDTO universityPayLoadDTO) throws Exception;
}
