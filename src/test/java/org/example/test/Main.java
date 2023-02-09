package org.example.test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        RestAssured.baseURI = "https://demoqa.com/BookStore/v1/Books";
        RequestSpecification httprequest = RestAssured.given();
        Response response = httprequest.header("content-type","application/json").when().get();
        System.out.println("Output :- " + response.header("Connection"));
        response.then().log().all().statusCode(200).statusLine("HTTP/1.1 200 OK");
        JsonPath jsonPath = response.body().jsonPath();
        List<Object> list = jsonPath.getList("books");

        for(Object o: list) {
            LinkedHashMap<String,Object> map = (LinkedHashMap<String, Object>) o;
            System.out.println("Result:- "+ map.get("title"));
            if(map.get("title").equals("Speaking JavaScript"))
                System.out.println("Author Name:- "+ map.get("author"));
        }

    }
}