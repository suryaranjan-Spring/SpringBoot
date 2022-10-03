package com.example.springneo4jdemo.repository;

import com.example.springneo4jdemo.entity.University;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.UUID;

public interface UniversityRepository extends Neo4jRepository<University, UUID> {
}
