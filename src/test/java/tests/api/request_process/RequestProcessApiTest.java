package tests.api.request_process;

import data.RequestProcessData;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import tests.api.BaseApiTest;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@Slf4j
public class RequestProcessApiTest extends BaseApiTest {

    @Test
    public void testProcessApplicationSuccessfully(){
        log.info("API ТЕСТ: Изменение статуса заявки через /requestProcess");

        RequestProcessData processBody = RequestProcessData.builder()
                .applId(60001)
                .staffid(35988)
                .action("approved")
                .build();

        Response response = given()
                .contentType(ContentType.JSON)
                .body(processBody)

                .when()
                .post("/requestProcess")

                .then()
                .log().ifValidationFails()
                .statusCode(200)
                .body("data.applicationid", equalTo(processBody.getApplId()))
                .body("data.statusofapplication", equalTo(processBody.getAction()))
                .extract().response();


        int createdApplicationId = response.path("data.applicationid");
        String changedStatus = response.path("data.statusofapplication");
        log.info("Cтатус заявки c номером {} успешно изменен на {}", createdApplicationId, changedStatus );

        log.info("API ТЕСТ УСПЕШНО ЗАВЕРШЕН");
    }
}
