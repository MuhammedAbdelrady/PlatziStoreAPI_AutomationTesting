package shop.User;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;

public class GetSingleUser {
    @Test
    public void getOneUserById(){
        int expectedID = 1;
        RestAssured
                .given().baseUri("https://api.escuelajs.co/api/v1")
                .when()
                .contentType(ContentType.JSON)
                .get("/users/"+expectedID)
                .then()
                .statusCode(200)
                .assertThat().body(Matchers.not(""))
                .assertThat().body("size()",Matchers.equalTo(8)) //to check number of values returned
                .body("id",equalTo(expectedID));
    }

}
