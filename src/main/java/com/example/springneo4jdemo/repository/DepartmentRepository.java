package com.example.springneo4jdemo.repository;


import com.example.springneo4jdemo.entity.Department;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DepartmentRepository extends Neo4jRepository<Department, UUID> {

    public Department findByDepartmentName(String departmentname);

    @Query("with $subjectList as subjectList\n" +
            "match (department:Department{department_name:$departmentName})\n" +
            "match(subject:Subject) where subject.subject_name in  IN subjectList with collect(subject) as sub,department\n" +
            "FOREACH (subject IN sub | merge (department)-[:HAVING_SUBJECT]->(subject) )\n" +
            "return  \"subject is added to department \" as message")
    public String addSubjectInDepartment(String departmentName, List<String> subjectList);
}
