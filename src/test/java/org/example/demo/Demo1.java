package org.example.demo;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.matcher.RestAssuredMatchers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ResponseBody;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class Demo1 {

    @BeforeClass
    public void setup() {
    RestAssured.baseURI = "https://maps.googleapis.com/";
    }

    @Test(priority = -1)
    public void headersValidationTest() {

        ValidatableResponse response = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .queryParam("query","Muzaffarpur")
                .queryParam("key","xyz")
                .auth()
                .oauth2("")
                .when()
                .get("/maps/api/place/textsearch/json")
                .then()
                .statusCode(200)
                .log().all();

        String header = response.extract().response().getContentType();
        System.out.println("Content-Type is: "+ header);

        Headers headers = response.extract().response().headers();
        List<Header> list = headers.asList();
//        list.get(0).
        for(int i=0; i<list.size(); i++) {
            System.out.println(list.get(i).toString());
        }

    }

    @Test(priority = -2)
    public void validateBodyTest() {
      ResponseBody body = RestAssured.given()
                .header("Conteny-Type","application/json")
                .when()
                .log().all()
                .get("https://demoqa.com/BookStore/v1/Books")
                .getBody();

//        System.out.println(body.prettyPrint());

       JsonPath jsonPath = body.jsonPath();
       Map map = jsonPath.getMap("");
        System.out.println("Map size: "+ map.size());
        List<Map<String,Object>> list = (List<Map<String,Object>>) map.get("books");

        for(Map<String,Object> book : list) {
            System.out.println("Title Name - "+ book.get("title"));
        }

        System.out.println("Author Name : "+ jsonPath.getString("books[0].author"));

    }
}
