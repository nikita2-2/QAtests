package tests.api.request_process;

import data.ProcessResponse;
import data.RequestProcessData;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tests.api.BaseApiTest;
import tests.api.Specifications;
import static io.restassured.RestAssured.given;

public class RequestProcessRejectedApiTest extends BaseApiTest {

    @Epic("АПИ ТЕСТЫ")
    @Feature("Работа с заявками")
    @Story("Изменение статуса заявки")
    @Description("Тест проверяет отклонение заявки по айди заявки и админа")
    @Test
    public void testProcessApplicationSuccessfully(){
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec());
        RequestProcessData processBody = RequestProcessData.builder()
                .applId(65432)
                .staffid(36018)
                .action("rejected")
                .build();

        ProcessResponse responseBody = given()
                .body(processBody)
                .when()
                .post("/requestProcess")
                .then()
                .extract()
                .as(ProcessResponse.class);
        Assertions.assertEquals("rejected", responseBody.getData().getStatusofapplication(), "Статус заявки не изменился на 'rejected'!");

    }
}
