package shop.Authentication;

import static org.hamcrest.Matchers.empty;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class Session {

    @Test
    //[GET] https://api.escuelajs.co/api/v1/auth/profile
    public void userSession(){
        String access_token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOjEsImlhdCI6MTcwMDMwNTUxNCwiZXhwIjoxNzAyMDMzNTE0fQ.m4yZCCLU4QO6xnT-NXW2Nlyb4wenAgvV_4y_NjwQtVs";
        RestAssured.given()
                    .baseUri("https://api.escuelajs.co/api/v1")
                    .header("Authorization", "Bearer "+access_token)
                    .when().get("/auth/profile")
                    .then().statusCode(200)
                    .body("$", Matchers.not(empty()))
                    .log().all();
    }
    
}
