package com.example.springneo4jdemo.repository;


import com.example.springneo4jdemo.entity.Student;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface StudentRepository extends Neo4jRepository<Student, UUID> {

    public List<Student> findByStudentId(UUID studentId);

    public List<Student> findByName(String name);
    @Query("match(node:Student)-[anyRel]-(anyNode)" +
            " where node.studentId=$id " +
            " delete node,anyRel")
    public void delete(UUID id);
}
