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
class ApiControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    void setUp() {
        RestAssuredMockMvc.webAppContextSetup(webApplicationContext);
    }


    @Test
    void addApi() {
        RestAssuredMockMvc
                .when()
                .post("/apis/add")
                .then()
                .assertThat().statusCode(201)
                .body(containsString("{\"id\":3,\"name\":\"a3\"}"));
    }

    @Test
    void getAllApis() {
        RestAssuredMockMvc
                .when()
                .get("/apis/listAll")
                .then()
                .assertThat().statusCode(302)
                .body("$.size()", Matchers.equalTo(2));
    }

    @Test
    void deleteApi() {
        RestAssuredMockMvc
                .given()
                .param("id", 1)
                .when()
                .delete("/apis/delete")
                .then()
                .assertThat().statusCode(204);
    }


    @Test
    void updateApi() {
        RestAssuredMockMvc
                .given()
                .param("id", 1)
                .param("name", "new")
                .when()
                .put("/apis/update")
                .then()
                .assertThat().statusCode(200);
    }
}
