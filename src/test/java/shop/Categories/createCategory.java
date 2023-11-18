package shop.Categories;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.notNullValue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

public class createCategory {
    public String newCategoryInfo;
    public String name, image;
    
    public void getCreateData () throws IOException{
        newCategoryInfo = new String(Files.readAllBytes(Paths.get("src\\test\\resource\\Category\\createCategory.json")));
        JsonPath parserPath = new JsonPath(newCategoryInfo);
        name = parserPath.getString("name");
        image = parserPath.getString("image");
    }

    @Test
    //[POST] https://api.escuelajs.co/api/v1/categories/
    public void newCategory() throws IOException{
        getCreateData();
        RestAssured.given().baseUri("https://api.escuelajs.co/api/v1")
        .contentType(ContentType.JSON)
        .body(newCategoryInfo)
        .when().post("/categories/")
        .then().statusCode(201)
        .time(Matchers.lessThan(2L), TimeUnit.SECONDS)
        .body("$", Matchers.not(empty()))
        .body("id", notNullValue()).body("id", greaterThan(0))
        .body("name", notNullValue()).body("name", equalTo(name))
        .body("image", notNullValue()).body("image", equalTo(image))
        .log().all();
    }
}
