package com.example.springneo4jdemo.it;

import com.example.springneo4jdemo.dto.SubjectPayLoadDTO;
import com.example.springneo4jdemo.entity.Subject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class SubjectControllerTest extends Neo4JContainer {

    @Autowired
    private WebTestClient webTestClient;
    @Autowired
    private ObjectMapper objectMapper;
    private List<SubjectPayLoadDTO> subjectPayLoadDTOList;

    @BeforeEach
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
    }

    private String convertObjectToJson(Object object) throws JsonProcessingException {
        // ObjectMapper mapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }
    @Test
    public void testSaveSubject() throws JsonProcessingException {

        webTestClient.post()
                .uri("/subject/saveallsubjects")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(subjectPayLoadDTOList),Object.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .consumeWith(response ->  Assertions.assertNotNull(response.getResponseBody()));

    }
    @Test
    public void testSaveSubjectwithEmptyBody() throws JsonProcessingException {
WebTestClient.BodyContentSpec entity=
        webTestClient.post()
                .uri("/subject/saveallsubjects")
                .contentType(MediaType.APPLICATION_JSON)
                .body(null)
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody();


    }
}
