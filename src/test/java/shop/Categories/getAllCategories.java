package shop.Categories;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class getAllCategories {
    

    @Test
    //[GET] https://api.escuelajs.co/api/v1/categories
    public void allCategories(){
        RestAssured.given().baseUri("https://api.escuelajs.co/api/v1")
        .when().get("/categories")
        .then().statusCode(200)
        .body("$",Matchers.not(empty())) 
        .log().all();
    }
}
