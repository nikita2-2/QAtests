package tests.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Specifications {

    public static RequestSpecification requestSpec() {
        return new RequestSpecBuilder()
                .setBaseUri("https://regoffice.senla.eu")
                .setAuth(io.restassured.RestAssured.basic("user", "senlatest"))
                .setContentType(ContentType.JSON)
                .build();
    }

    public static ResponseSpecification responseSpec() {
        return new ResponseSpecBuilder()
                .build();
    }

    public static void installSpecifications(RequestSpecification request, ResponseSpecification response) {
        io.restassured.RestAssured.requestSpecification = request;
        io.restassured.RestAssured.responseSpecification = response;
    }
}
