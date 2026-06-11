package tests.api;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class BaseApiTest {
    @BeforeAll
    public static void setupApiConfig() {
        RestAssured.baseURI = "https://regoffice.senla.eu/";
        RestAssured.authentication = RestAssured.basic("user", "senlatest");
    }
}
