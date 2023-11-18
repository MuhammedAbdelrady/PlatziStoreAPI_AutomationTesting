package shop.Authentication;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.hasKey;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import io.restassured.path.json.JsonPath;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Login {

    String loginData;
    public void loginDataFile() throws IOException{
        loginData = new String(Files.readAllBytes(Paths.get("src/test/resource/Authentication/loginData.json")));
        JsonPath parserPath = new JsonPath(loginData);
    }

    @Test
    //[POST] https://api.escuelajs.co/api/v1/auth/login
    public void login() throws IOException{
        loginDataFile();
        Response res = RestAssured.given().baseUri("https://api.escuelajs.co/api/v1")
                        .contentType(ContentType.JSON)
                        .body(loginData)
                        .when().post("/auth/login")
                        .then().statusCode(201)
                        .body("$", Matchers.not(empty()))
                        .extract().response();
        Map<String, Object> resBody = res.getBody().as(Map.class);  
        System.out.println(resBody);
        assertThat(resBody, hasKey("access_token"));
        assertThat(resBody, hasKey("refresh_token"));
    }
    
}
