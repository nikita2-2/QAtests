package tests.api.send_user_request;

import data.UserRequestData;
import data.UserRequestResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tests.api.BaseApiTest;
import tests.api.Specifications;

import static data.TestDataFactory.createMarriageData;
import static io.restassured.RestAssured.given;

public class UserRequestMarriageTest extends BaseApiTest {

    @Epic("АПИ ТЕСТЫ")
    @Feature("Создание новой заявки")
    @Story("Создание заявки на регистрацию брака")
    @Description("Тест проверяет, что при отправке валидных данных создается заявка на регистрацию брака и возращается ее номер")
    @Test
    public void testCreateMarriageRequestApi() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec());
        UserRequestData marriageBody = createMarriageData();

        UserRequestResponse responseBody = given()
                .body(marriageBody)
                .when()
                .post("/sendUserRequest")
                .then()
                .extract()
                .as(UserRequestResponse.class);

        Assertions.assertNotNull(responseBody.getData().getApplicationid(), "ID заявки пустой!");
    }

    @Test
    @Epic("АПИ ТЕСТЫ")
    @Story("Ошибка авторизации регистрации брака")
    public void testCreateMarriageRequestWithWrongAuth() {
        UserRequestData birthBody = createMarriageData();
        given()
                .baseUri(Specifications.apiUrl)
                .auth().basic(Specifications.apiUser, "WRONG_PASSWORD")
                .contentType(io.restassured.http.ContentType.JSON)
                .body(birthBody)
                .when()
                .post("/sendUserRequest")
                .then()
                .statusCode(401);
    }

    @Test
    @Epic("АПИ ТЕСТЫ")
    @Story("Ошибка валидации регистрации брака")
    public void testCreateMarriageRequestWithEmptyMode() {
        UserRequestData invalidBody = UserRequestData.builder().personalFirstName("Петр").build();
        given()
                .spec(Specifications.requestSpec())
                .body(invalidBody)
                .when()
                .post("/sendUserRequest")
                .then()
                .statusCode(400);
    }
}