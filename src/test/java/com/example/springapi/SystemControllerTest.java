package com.example.springapi;

import io.restassured.module.mockmvc.RestAssuredMockMvc;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
class SystemControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    void setUp() {
        RestAssuredMockMvc.webAppContextSetup(webApplicationContext);
    }


    @Test
    void addSystem() {
        RestAssuredMockMvc
                .when()
                .post("/systems/add")
                .then()
                .assertThat().statusCode(201)
                .body(containsString("{\"version\":3,\"name\":\"v3\"}"));
    }

    @Test
    void getAllSystems() {
        RestAssuredMockMvc
                .when()
                .get("/systems/listAll")
                .then()
                .assertThat().statusCode(302)
                .body("$.size()", Matchers.equalTo(2));
    }
}