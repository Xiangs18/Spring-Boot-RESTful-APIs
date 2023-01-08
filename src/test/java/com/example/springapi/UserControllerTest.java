package com.example.springapi;

import io.restassured.module.mockmvc.RestAssuredMockMvc;

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
class UserControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    void setUp() {
        RestAssuredMockMvc.webAppContextSetup(webApplicationContext);
    }


    @Test
    void getUser() {
        RestAssuredMockMvc
                .given()
                .param("id", 1)
                .when()
                .get("/users/get")
                .then()
                .assertThat().statusCode(302)
                .body(containsString("{\"id\":1,\"name\":\"Sean\",\"email\":\"sean@andrew.cmu.edu\",\"username\":\"sean123\",\"password\":\"S123456\"}"));
    }

    @Test
    void deleteUser() {
        RestAssuredMockMvc
                .given()
                .param("id", 1)
                .when()
                .delete("/users/delete")
                .then()
                .assertThat().statusCode(204);
    }

    @Test
    void addUser() {
        RestAssuredMockMvc
                .given()
                .param("name", "pao")
                .param("email","pao@andrew.cmu.edu")
                .param("username","pao123456")
                .param("password", "S561234")
                .when()
                .post("/users/add")
                .then()
                .assertThat().statusCode(201)
                .body(containsString("{\"id\":4,\"name\":\"pao\",\"email\":\"pao@andrew.cmu.edu\",\"username\":\"pao123456\",\"password\":\"S561234\"}"));
    }

    @Test
    void getIsValidUsername() {
        RestAssuredMockMvc
                .given()
                .param("username", "pao1234")
                .when()
                .get("/users/valid/username")
                .then()
                .assertThat().statusCode(200)
                .body(containsString("true"));
    }

    @Test
    void getIsValidPassword() {
        RestAssuredMockMvc
                .given()
                .param("password", "G885454")
                .when()
                .get("/users/valid/password")
                .then()
                .assertThat().statusCode(200)
                .body(containsString("true"));
    }
}