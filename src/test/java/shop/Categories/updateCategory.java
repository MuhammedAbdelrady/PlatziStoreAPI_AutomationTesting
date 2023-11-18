package shop.Categories;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

public class updateCategory {
        String updateInfo, name;

        public void getCreateData () throws IOException{
        updateInfo = new String(Files.readAllBytes(Paths.get("src\\test\\resource\\Category\\createCategory.json")));
        JsonPath parserPath = new JsonPath(updateInfo);
        name = parserPath.getString("name");
    }

    @Test
    //[PUT] https://api.escuelajs.co/api/v1/categories/3

    public void updatedCategory() throws IOException{
        getCreateData();
        RestAssured.given().baseUri("https://api.escuelajs.co/api/v1")
        .contentType(ContentType.JSON)
        .body(updateInfo)
        .when().put("/categories/3")
        .then().statusCode(200)
        .time(Matchers.lessThan(2L), TimeUnit.SECONDS)
        .body("$", notNullValue())
        .body("name", notNullValue()).body("name", equalTo(name))
        .log().all();
    }
}
