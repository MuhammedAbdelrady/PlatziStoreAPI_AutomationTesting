package shop.Product;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

public class GetAllProduct {
    @Test
    //[GET] https://api.escuelajs.co/api/v1/products
    public void getProducts(){
        Response res = RestAssured.given().baseUri("https://api.escuelajs.co/api/v1")
                .when().get("/products")
                .then().statusCode(200).assertThat().body(Matchers.not(("")))
                .assertThat().contentType(ContentType.JSON)
                .extract().response();

        List<Object> productList = res.jsonPath().getList("$");
        System.out.println(productList);
        int counter = 0;
        for(int idx=0 ; idx<productList.size() ; idx++){
            String id = res.jsonPath().getString("["+idx+"].id");
            counter++;
        }
        assertNotNull(productList);
        assertTrue(productList.size()>50, "All products is here");
    }
}
