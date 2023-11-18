package shop.Product;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.empty;

public class DeleteProduct {

    @Test
    public void deleteProduct(){
        int productId = 71;
        RestAssured.given().baseUri("https://api.escuelajs.co/api/v1")
                .when().delete("/products/"+productId)
                .then().statusCode(200)
                .body("$", Matchers.not(empty()))
                .log().all();
    }
}
