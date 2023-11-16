package shop;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetUser {
    @Test
    public void getOneUserById(){
        Response res = RestAssured.given().baseUri("https://api.escuelajs.co/api/v1")
                                  .when().contentType(ContentType.JSON)
                                  .get("/users")
                                  .then()
                                  .statusCode(200)
                                  .extract().response();

        System.out.println(res.asString());
    }
}
