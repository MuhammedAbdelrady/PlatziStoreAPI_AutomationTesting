package shop.User;

import static org.hamcrest.Matchers.empty;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class CreateUser {
    public int createdUserID;
    String userInfo;
    String name, email, password, avatar;
        //Helper Methods
    public void getUserData() throws IOException {
        userInfo = new String(Files.readAllBytes(Paths.get("src/test/resource/User/createUserData.json")));
        JsonPath parserPath = new JsonPath(userInfo);
        name = parserPath.getString("name");
        email = parserPath.getString("email");
        password = parserPath.getString("password");
        avatar = parserPath.getString("avatar");
    }

    @Test
    public void createUser() throws IOException {
        getUserData();
        Response res = RestAssured
                        .given().baseUri("https://api.escuelajs.co/api/v1")
                            .contentType(ContentType.JSON)
                            .body(userInfo)
                        .when()
                            .post("/users")
                        .then()
                            .statusCode(201)
                            .body("createdAt", Matchers.not(empty())).log().all()
                        .extract().response();

        JsonPath jsonPath = res.jsonPath();
        String userName = jsonPath.getString("name");
        String userEmail = jsonPath.getString("email");
        String userPassword = jsonPath.getString("password");
        createdUserID = jsonPath.getInt("id");
        System.out.println(createdUserID);
        assert userName.equals(name);
        assert userEmail.equals(email);
        assert userPassword.equals(password);
    }
}
