package tests.api.get_appl_status;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import tests.api.BaseApiTest;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

@Slf4j
public class GetApplStatusApiTest extends BaseApiTest {

    @Test
    public void testGetApplicationStatusSuccessfully() {
        log.info("API ТЕСТ: Получение статуса заявки по ID");

        int targetApplicationId = 65372;

        Response response = given()
                .pathParam("applicationId", targetApplicationId)
                .contentType(ContentType.JSON)

                .when()
                .get("/getApplStatus/{applicationId}")

                .then()
                .log().ifValidationFails()
                .statusCode(200)

                .body("data.statusofapplication", notNullValue())
                .extract().response();

        String changedStatus = response.path("data.statusofapplication");
        log.info("Cтатус заявки c номером {} - {}", targetApplicationId, changedStatus );

        log.info("API ТЕСТ УСПЕШНО ЗАВЕРШЕН");
    }
}