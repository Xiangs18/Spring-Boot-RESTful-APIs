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
class EventControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    void setUp() {
        RestAssuredMockMvc.webAppContextSetup(webApplicationContext);
    }


    @Test
    void getAllEvents() {
        RestAssuredMockMvc
                .when()
                .get("/events/listAll")
                .then()
                .assertThat().statusCode(302)
                .body("$.size()", Matchers.equalTo(4));
    }

    @Test
    void deleteEvent() {
        RestAssuredMockMvc
                .given()
                .param("id", 1)
                .when()
                .delete("/events/delete")
                .then()
                .assertThat().statusCode(204);
    }

    @Test
    void addEvent() {
        RestAssuredMockMvc
                .given()
                .param("name", "Meeting5")
                .param("datetime", "2022-10-5")
                .when()
                .post("/events/add")
                .then()
                .assertThat().statusCode(201)
                .body(containsString("{\"id\":5,\"name\":\"Meeting5\",\"date\":\"2022-10-5\"}"));
    }

    @Test
    void updateEvent() {
        RestAssuredMockMvc
                .given()
                .param("id", 1)
                .param("name", "new")
                .when()
                .put("/events/update")
                .then()
                .assertThat().statusCode(200);
    }

    @Test
    void getEvent() {
        RestAssuredMockMvc
                .given()
                .param("datetime", "2022-10-2")
                .when()
                .get("/events/get")
                .then()
                .assertThat().statusCode(302)
                .body(containsString("{\"id\":2,\"name\":\"Meeting2\",\"date\":\"2022-10-2\"}"));
    }
}


