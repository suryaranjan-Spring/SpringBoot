package com.example.springneo4jdemo.entity;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

import java.util.UUID;
@Data
@Node(primaryLabel = "Subject")
public class Subject {
    @Id
    @GeneratedValue
    private UUID subjectId;
    @Property(name = "subject_name")
    private String subjectName;

}
