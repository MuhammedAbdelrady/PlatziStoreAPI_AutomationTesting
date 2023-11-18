package shop.User;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class UpdateUser {
    public int createdUserID;
    String updateInfo;
    String newName, newEmail;

    public void getUpdateUserData() throws IOException{
        updateInfo = new String(Files.readAllBytes(Paths.get("src/test/resource/User/updateUserData.json")));
        JsonPath parserPath = new JsonPath(updateInfo);
        newName = parserPath.getString("name");
        newEmail = parserPath.getString("email");
    }

    @Test
    public void updateUser() throws IOException {
        getUpdateUserData();
        int id = createdUserID;
        Response res = RestAssured.given().baseUri("https://api.escuelajs.co/api/v1")
                            .contentType(ContentType.JSON)
                            .body(updateInfo)
                        .when()
                            .put("/users/2488")
                        .then()
                            .statusCode(200)
                            .assertThat().body(Matchers.not(""))
                            .assertThat().body("size()",Matchers.equalTo(8)) //to check number of values returned
                            .extract().response();

        JsonPath jsonPath = res.jsonPath();
        String userName = jsonPath.getString("name");
        String userEmail = jsonPath.getString("email");

        assert userName.equals(newName);
        assert userEmail.equals(newEmail);
    }
}
