package tests.api.get_applications;

import io.restassured.http.ContentType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import tests.api.BaseApiTest;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@Slf4j
public class GetApplicationsApiTest extends BaseApiTest {

    @Test
    public void testGetApplicationsWithPagination() {
        log.info("API ТЕСТ: Получение списка заявок с пагинацией");

        given()
                .contentType(ContentType.JSON)
                .queryParam("page", 1)
                .queryParam("size", 10)

                .when()
                .get("/getApplications")

                .then()
                .log().ifValidationFails()
                .statusCode(200)
                .body("data", is(not(empty())));


        log.info("Страница со списком заявок получена");
        log.info("API ТЕСТ УСПЕШНО ЗАВЕРШЕН");
    }
}