package shop.Product;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class CreateProduct {

    String productInfo, title, description;
    int price, categoryID;
    ArrayList<String> images;


    public void getNewProduct() throws IOException {
        productInfo = new String(Files.readAllBytes(Paths.get("src/test/resource/Product/newProductData.json")));
        JsonPath parserPath = new JsonPath(productInfo);
        title = parserPath.getString("title");
        description = parserPath.getString("description");
        price = parserPath.getInt("price");
        categoryID = parserPath.getInt("categoryId");
        images = parserPath.get("images");
    }

    @Test
    public void createProduct() throws IOException{
        getNewProduct();
        RestAssured.given().baseUri("https://api.escuelajs.co/api/v1")
                .contentType(ContentType.JSON)
                .body(productInfo)
                .when().post("/products/")
                .then().statusCode(201)
                .time(Matchers.lessThan(2L), TimeUnit.SECONDS)
                .body("title", notNullValue()).body("title", equalTo(title))
                .body("description", notNullValue()).body("description", equalTo(description))
                .body("price", notNullValue()).body("price", equalTo(price))
                .log().all();
    }
}
