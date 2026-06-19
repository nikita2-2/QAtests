package tests.api.request_process;

import data.*;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tests.api.BaseApiTest;
import tests.api.Specifications;
import static io.restassured.RestAssured.given;

public class RequestProcessApiTest extends BaseApiTest {
    int testAppId = 11111;
    int testStaffId = 72703;

    @Epic("АПИ ТЕСТЫ")
    @Feature("Работа с заявками")
    @Story("Изменение статуса заявки")
    @Description("Тест проверяет одобрение заявки по динамическому айди заявки и админа")
    @Test
    public void testProcessApplicationSuccessfully(){
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec());

        int dynamicId = createFreshApplicationId();

        RequestProcessData processBody = RequestProcessData.builder()
                .applId(dynamicId)
                .staffid(testStaffId)
                .action("approved")
                .build();

        ProcessResponse responseBody = given()
                .body(processBody)
                .when()
                .post("/requestProcess")
                .then()
                .extract()
                .as(ProcessResponse.class);
        Assertions.assertEquals("approved", responseBody.getData().getStatusofapplication(), "Статус заявки не изменился на 'approved'!");
    }

    @Epic("АПИ ТЕСТЫ")
    @Feature("Работа с заявками")
    @Story("Изменение статуса заявки")
    @Description("Тест проверяет отклонение заявки по динамическому айди заявки и админа")
    @Test
    public void testProcessApplicationNotSuccessfully(){
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec());

        int dynamicId = createFreshApplicationId();

        RequestProcessData processBody = RequestProcessData.builder()
                .applId(dynamicId)
                .staffid(testStaffId)
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

    @Epic("АПИ ТЕСТЫ")
    @Feature("Работа с заявками")
    @Story("Ошибка авторизации")
    @Description("Негативный тест: проверка, что при неверном пароле сервер возвращает 401")
    @Test
    public void testProcessApplicationWithWrongAuth() {
        RequestProcessData processBody = RequestProcessData.builder()
                .applId(testAppId)
                .staffid(testStaffId)
                .action("approved")
                .build();

        given()
                .baseUri("https://regoffice.senla.eu")
                .auth().basic("user", "WRONG_PASSWORD")
                .contentType(io.restassured.http.ContentType.JSON)
                .body(processBody)
                .when()
                .post("/requestProcess")
                .then()
                .statusCode(401);
    }

    @Epic("АПИ ТЕСТЫ")
    @Feature("Работа с заявками")
    @Story("Ошибка валидации")
    @Description("Негативный тест: проверка, что при передаче несуществующего действия 'delete' сервер возвращает 400")
    @Test
    public void testProcessApplicationWithUnsupportedAction() {
        RequestProcessData invalidBody = RequestProcessData.builder()
                .applId(testAppId)
                .staffid(testStaffId)
                .action("delete")
                .build();

        given()
                .spec(Specifications.requestSpec())
                .body(invalidBody)
                .when()
                .post("/requestProcess")
                .then()
                .statusCode(400);
    }

    @Epic("АПИ ТЕСТЫ")
    @Feature("Работа с заявками")
    @Story("Ошибка валидации")
    @Description("Негативный тест: проверка, что при пустом поле action сервер возвращает 400")
    @Test
    public void testProcessApplicationWithInvalidData() {
        RequestProcessData invalidBody = RequestProcessData.builder()
                .applId(testAppId)
                .staffid(testStaffId)
                .build();

        given()
                .spec(Specifications.requestSpec())
                .body(invalidBody)
                .when()
                .post("/requestProcess")
                .then()
                .statusCode(400);
    }

    private int createFreshApplicationId() {
        UserRequestData birthBody = TestDataFactory.createValidBirthUserData("Петя");

        UserRequestResponse createResponse = given()
                .spec(Specifications.requestSpec())
                .body(birthBody)
                .when()
                .post("/sendUserRequest")
                .then()
                .extract()
                .as(UserRequestResponse.class);

        return createResponse.getData().getApplicationid();
    }
}
