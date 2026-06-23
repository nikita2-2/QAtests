package tests.api;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class BaseApiTest {
    @BeforeAll
    public static void setupApiConfig() {
        Specifications.installSpecifications(
                Specifications.requestSpec(),
                Specifications.responseSpec()
        );
    }
}
