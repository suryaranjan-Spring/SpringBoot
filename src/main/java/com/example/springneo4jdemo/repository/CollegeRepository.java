package com.example.springneo4jdemo.repository;

import com.example.springneo4jdemo.dto.CollegeData;
import com.example.springneo4jdemo.entity.College;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CollegeRepository extends Neo4jRepository<College, UUID> {
    @Query("match(department:Department{department_name:$departmentName}) " +
            "match(college:College{collegeName:$collegeName}) " +
            "CALL apoc.create.relationship(college, $relationshipType, {}, department) " +
            "YIELD rel " +
            "return count(*) ")
    public int updateCollegeWithSingleDepartment(String collegeName, String departmentName, String relationshipType);

    public College findByCollegeName(String collegeName);

    @Query("with $departmentList as departmentlist\n" +
            "match(college:College{collegeName:$collegeName})\n" +
            "match(department:Department) where department.department_name  IN departmentlist with collect(department) as dept,college\n" +
            "FOREACH (department IN dept | merge (college)-[:DEPARTMENT_TAKEN]->(department) )\n" +
            "return  \"department is added to college \" as message")
    public String addDepartment(String collegeName, List<String> departmentList);
}
