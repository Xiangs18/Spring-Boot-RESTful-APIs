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
class ServiceControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    void setUp() {
        RestAssuredMockMvc.webAppContextSetup(webApplicationContext);
    }



    @Test
    void addService() {
        RestAssuredMockMvc
                .when()
                .post("/services/add")
                .then()
                .assertThat().statusCode(201)
                .body(containsString("{\"id\":4,\"name\":\"s4\"}"));
    }

    @Test
    void getAllServices() {
        RestAssuredMockMvc
                .when()
                .get("/services/listAll")
                .then()
                .assertThat().statusCode(302)
                .body("$.size()", Matchers.equalTo(4));
    }

    @Test
    void updateService() {
        RestAssuredMockMvc
                .given()
                .param("id", 2)
                .param("name", "new")
                .when()
                .put("/services/update")
                .then()
                .assertThat().statusCode(200);
    }

    @Test
    void deleteService() {
        RestAssuredMockMvc
                .given()
                .param("id", 1)
                .when()
                .delete("/services/delete")
                .then()
                .assertThat().statusCode(204);
    }
}

