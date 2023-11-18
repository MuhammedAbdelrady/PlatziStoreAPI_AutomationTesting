package shop.Product;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class UpdateProduct {
    public String updateProductInfo, newTitle;
    public int newPrice;
    public void updateProductData() throws IOException {
        updateProductInfo = new String(Files.readAllBytes(Paths.get("src/test/resource/Product/updateProductData.json")));
        JsonPath parserPath = new JsonPath(updateProductInfo);
        newTitle = parserPath.getString("title");
        newPrice = parserPath.getInt("price");
    }

    @Test
    public void updateProduct() throws IOException{
        int productId = 1;
        updateProductData();
        RestAssured.given().baseUri("https://api.escuelajs.co/api/v1")
                .contentType(ContentType.JSON)
                .body(updateProductInfo)
                .when().put("/products/" + productId)
                .then().statusCode(200)
                .body("title", notNullValue()).body("title",equalTo(newTitle))
                .body("price", notNullValue()).body("price", equalTo(newPrice))
                .log().all();
    }
}
