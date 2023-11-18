package shop.Categories;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class getSingleCategory {

    @Test
    //[GET] https://api.escuelajs.co/api/v1/categories/1
    public void oneCategory(){
        RestAssured.given().baseUri("https://api.escuelajs.co/api/v1")
        .when().get("/categories/1")
        .then().statusCode(200)
        .body("$", Matchers.not(empty()))
        .body("id", equalTo(1))
        .body("name", notNullValue())
        .body("image", notNullValue())
        .body("creationAt", notNullValue())
        .body("updatedAt", notNullValue())
        .log().all();
    }
}
