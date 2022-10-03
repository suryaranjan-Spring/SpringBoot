package com.example.springneo4jdemo.entity;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

@RelationshipProperties
@Data
public class IsLearning {
    @Id
    @GeneratedValue
    private Long learningId;

    private int marks;
    @TargetNode
    private Subject subject;
}
