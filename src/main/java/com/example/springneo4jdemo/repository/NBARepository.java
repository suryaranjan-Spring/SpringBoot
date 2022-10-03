package com.example.springneo4jdemo.repository;


import com.example.springneo4jdemo.entity.Student;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface NBARepository extends Neo4jRepository<Student, UUID> {
}
