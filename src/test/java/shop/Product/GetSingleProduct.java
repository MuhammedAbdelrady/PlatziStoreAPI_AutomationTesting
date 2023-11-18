package shop.Product;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class GetSingleProduct {
    @Test
    //[GET] https://api.escuelajs.co/api/v1/products/4
    public void getOneProduct(){
        RestAssured.given().baseUri("https://api.escuelajs.co/api/v1")
                .when()
                .get("/products/2")
                .then().statusCode(200)
                .assertThat()
                .body("id", equalTo(2))
                .body("title", notNullValue())
                .body("price", notNullValue()).body("price", Matchers.isA(Number.class))
                .body("description",notNullValue())
                .body("images", notNullValue());
    }
}
