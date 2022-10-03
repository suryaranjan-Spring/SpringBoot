package com.example.springneo4jdemo.dto;

import lombok.Data;

import java.util.UUID;
@Data
public class SubjectPayLoadDTO {

    private UUID subjectId;

    private String subjectName;
}
