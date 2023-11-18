package shop.User;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class GetAllUsers {
    
    @Test
    public void getUsers(){
        RestAssured
                .given().baseUri("https://api.escuelajs.co/api/v1")
                .when().contentType(ContentType.JSON)
                .get("/users")
                .then().statusCode(200)
                .assertThat().body(Matchers.not(""));
                //.assertThat().body("size()",Matchers.equalTo(2482));
    }
}
