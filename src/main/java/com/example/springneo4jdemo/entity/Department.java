package com.example.springneo4jdemo.entity;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.*;

import java.util.List;
import java.util.UUID;
@Data
@Node(primaryLabel = "Department")
public class Department {
    @Id
    @GeneratedValue
    private UUID deptId;
    @Property(name = "department_name")
    private String departmentName;
    @Relationship(type = "HAVING_SUBJECT",direction = Relationship.Direction.OUTGOING)
    private List<Subject> subjectList;
}
