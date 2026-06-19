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

import static data.TestDataFactory.createDeathData;
import static io.restassured.RestAssured.given;


public class UserRequestDeathTest extends BaseApiTest {

    @Epic("АПИ ТЕСТЫ")
    @Feature("Создание новой заявки")
    @Story("Создание заявки на регистрацию смерти")
    @Description("Тест проверяет, что при отправке валидных данных создается заявка на регистрацию смерти и возращается ее номер")
    @Test
    public void testCreateDeathRequestApi() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec());
        UserRequestData deathBody = createDeathData();

        UserRequestResponse responseBody = given()
                .body(deathBody)
                .when()
                .post("/sendUserRequest")
                .then()
                .extract()
                .as(UserRequestResponse.class);

        Assertions.assertNotNull(responseBody.getData().getApplicationid(), "ID заявки пустой!");
    }

    @Test
    @Epic("АПИ ТЕСТЫ")
    @Story("Ошибка авторизации регистрации смерти")
    public void testCreateDeathRequestWithWrongAuth() {
        UserRequestData birthBody = createDeathData();
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
    @Story("Ошибка валидации регистрации смерти")
    public void testCreateDeathRequestWithEmptyMode() {
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