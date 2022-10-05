package com.example.springneo4jdemo.it;

import com.example.springneo4jdemo.dto.SubjectPayLoadDTO;
import com.example.springneo4jdemo.service.SubjectImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.Neo4jContainer;
import org.testcontainers.containers.Neo4jLabsPlugin;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.Fail.fail;


@Testcontainers
public abstract class Neo4JContainer {
    @Container
     private static Neo4jContainer<?> neo4jContainer =
            new Neo4jContainer<>(DockerImageName.parse("neo4j:4.4"))
                    .withAdminPassword(null)
                    .withEnv("NEO4J_apoc_import_file_enabled", "true")
                    .withEnv("NEO4J_apoc_export_file_enabled", "true")
                    .withLabsPlugins(Neo4jLabsPlugin.APOC);

    static {
        neo4jContainer.start();
    }

    private List<SubjectPayLoadDTO> subjectPayLoadDTOList;
    @Autowired
    private SubjectImpl subjectImp;

    @DynamicPropertySource
    static void neo4jProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.neo4j.uri", neo4jContainer::getBoltUrl);
        registry.add("spring.neo4j.authentication.username", () -> "neo4j");
        registry.add("spring.neo4j.authentication.password", neo4jContainer::getAdminPassword);
        registry.add("spring.data.neo4j.database", () -> "neo4j");
    }


    public void setUpData() {
        subjectPayLoadDTOList = new ArrayList<>();
        SubjectPayLoadDTO subject1 = new SubjectPayLoadDTO();
        subject1.setSubjectName("Java");
        SubjectPayLoadDTO subject2 = new SubjectPayLoadDTO();
        subject2.setSubjectName("Python");
        SubjectPayLoadDTO subject3 = new SubjectPayLoadDTO();
        subject3.setSubjectName("C");
        SubjectPayLoadDTO subject4 = new SubjectPayLoadDTO();
        subject4.setSubjectName("C++");
        subjectPayLoadDTOList.add(subject1);
        subjectPayLoadDTOList.add(subject2);
        subjectPayLoadDTOList.add(subject3);
        subjectPayLoadDTOList.add(subject4);
        subjectImp.saveSubject(subjectPayLoadDTOList);
    }

    @Test
    void testSomethingUsingBolt() {

        // Retrieve the Bolt URL from the container
        String boltUrl = neo4jContainer.getBoltUrl();
        System.out.println(boltUrl);
        System.out.println(neo4jContainer.getAdminPassword());

        try (
                Driver driver = GraphDatabase.driver(boltUrl, AuthTokens.none());
                Session session = driver.session()
        ) {
            long one = session.run("RETURN 1", Collections.emptyMap()).next().get(0).asLong();
            assertThat(one).isEqualTo(1L);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    @Disabled
    public void testFecthSubjectByName() {
        String subjectName = "Java";
        String outPut = subjectImp.findBySubjectName(subjectName);
        System.out.println(outPut);
        assertThat("Java").isEqualTo(outPut);
    }
}
