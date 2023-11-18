package shop.Categories;

import static org.hamcrest.Matchers.empty;

import java.util.concurrent.TimeUnit;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class deleteCategory {

    @Test
    //[DELETE] https://api.escuelajs.co/api/v1/categories/1
    public void deletedCategory(){
        int cateID = 8;
        RestAssured.given().baseUri("https://api.escuelajs.co/api/v1")
        .when().delete("/categories/"+cateID)
        .then().statusCode(200)
        .time(Matchers.lessThan(2L), TimeUnit.SECONDS)
        .body("$", Matchers.not(empty()))
        .log().all();
    }
    
}
