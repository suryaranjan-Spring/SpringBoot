package com.example.springneo4jdemo.entity;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.*;

import java.util.List;
import java.util.UUID;

@Data
@Node(primaryLabel = "College")
public class College {
    @Id
    @GeneratedValue
    private UUID collegeId;

    private String collegeName;

    @Relationship(value = "DEPARTMENT_TAKEN",direction = Relationship.Direction.OUTGOING)
    private List<Department> departmentList;
}
