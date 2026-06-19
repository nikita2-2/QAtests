package tests.api.send_user_request;

import data.UserRequestData;
import data.UserRequestResponse;
import io.qameta.allure.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tests.api.BaseApiTest;
import tests.api.Specifications;

import static data.TestDataFactory.createValidBirthUserData;
import static io.restassured.RestAssured.given;

public class UserRequestBirthTest extends BaseApiTest {

    @Epic("АПИ ТЕСТЫ")
    @Feature("Создание новой заявки")
    @Story("Создание заявки на регистрацию рождения")
    @Description("Тест проверяет, что при отправке валидных данных создается заявка на регистрацию рождения и возращается ее номер")
    @Test
    public void testCreateBirthRequestApi() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec());
        UserRequestData birthBody = createValidBirthUserData("Ivann");

        UserRequestResponse responseBody = given()
                .body(birthBody)
                .when()
                .post("/sendUserRequest")
                .then()
                .extract()
                .as(UserRequestResponse.class);

        Assertions.assertNotNull(responseBody.getData().getApplicationid(), "ID заявки пустой!");
        }

    @Test
    @Epic("АПИ ТЕСТЫ")
    @Story("Ошибка авторизации для заявки рождения")
    public void testCreateBirthRequestWithWrongAuth() {
        UserRequestData birthBody = createValidBirthUserData("Ivann");
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
    @Story("Ошибка валидации заявки рождения")
    public void testCreateBirthRequestWithEmptyMode() {
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