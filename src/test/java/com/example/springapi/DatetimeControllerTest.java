package com.example.springapi;

import io.restassured.module.mockmvc.RestAssuredMockMvc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
class DatetimeControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    void setUp() {
        RestAssuredMockMvc.webAppContextSetup(webApplicationContext);
    }


    @Test
    void getDate() {
        RestAssuredMockMvc
                .when()
                .get("/datetime/date")
                .then()
                .assertThat().statusCode(302)
                .body(containsString(LocalDateTime.now().toString().substring(0,9)));
    }

    @Test
    void getDay() {
        RestAssuredMockMvc
                .when()
                .get("/datetime/day")
                .then()
                .assertThat().statusCode(302)
                .body(containsString(String.valueOf(LocalDateTime.now().getDayOfMonth())));
    }

    @Test
    void getMonth() {
        RestAssuredMockMvc
                .when()
                .get("/datetime/month")
                .then()
                .assertThat().statusCode(302)
                .body(containsString(String.valueOf(LocalDateTime.now().getMonth())));
    }

    @Test
    void getYear() {
        RestAssuredMockMvc
                .when()
                .get("/datetime/year")
                .then()
                .assertThat().statusCode(302)
                .body(containsString(String.valueOf(LocalDateTime.now().getYear())));
    }
}

