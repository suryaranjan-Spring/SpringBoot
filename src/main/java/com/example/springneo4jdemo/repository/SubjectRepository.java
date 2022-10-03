package com.example.springneo4jdemo.repository;


import com.example.springneo4jdemo.entity.Subject;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.UUID;

public interface SubjectRepository extends Neo4jRepository<Subject, UUID> {

    public Subject findBySubjectName(String subjectName);
}
