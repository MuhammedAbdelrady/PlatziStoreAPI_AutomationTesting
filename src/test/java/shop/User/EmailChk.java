package shop.User;

import static org.testng.Assert.assertFalse;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class EmailChk {
    String emailCheaker;
    String newEmail;
    public void getEmail() throws IOException {
        emailCheaker = new String(Files.readAllBytes(Paths.get("src/test/resource/User/emailChk.json")));
        JsonPath parserPath = new JsonPath(emailCheaker);
        newEmail = parserPath.getString("email");
    }

    @Test
    public void chkEmail() throws IOException {
        getEmail();
        Response res = RestAssured.given().baseUri("https://api.escuelajs.co/api/v1")
                    .contentType(ContentType.JSON)
                    .body(emailCheaker)
                .when()
                    .post("/users/is-available")
                .then()
                    .statusCode(201)
                    .extract().response();
        assertFalse(res.jsonPath().getBoolean("isAvailable"));
    }
    
}
