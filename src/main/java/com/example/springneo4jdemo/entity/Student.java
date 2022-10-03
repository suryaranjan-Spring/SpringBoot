package com.example.springneo4jdemo.entity;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.*;

import java.util.List;
import java.util.UUID;

@Data
@Node(primaryLabel = "Student")
public class Student {
    @Id
    @GeneratedValue
    private UUID studentId;

    private String name;

    private int age;
    @Property(name = "blood_group")
    private String bloodGroup;
    @Relationship(type = "BELONGS_TO" ,direction = Relationship.Direction.OUTGOING)
    private Department department;
    @Relationship(type = "IS_LEARNING",direction = Relationship.Direction.OUTGOING)
    private List<IsLearning> isLearningList;

}
