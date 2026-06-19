package tests.api.get_appl_status;

import data.UserRequestData;
import data.UserRequestResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;
import tests.api.BaseApiTest;
import tests.api.Specifications;

import static data.TestDataFactory.createValidBirthUserData;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GetApplStatusApiTest extends BaseApiTest {

    @Epic("АПИ ТЕСТЫ")
    @Feature("Работа с заявками")
    @Story("Получение заявки по айди")
    @Description("Тест проверяет получение заявки по айди")
    @Test
    public void testGetApplicationStatusSuccessfully() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec());
        UserRequestData birthBody = createValidBirthUserData("Петя");

        UserRequestResponse responseBody = given()
                .body(birthBody)
                .when()
                .post("/sendUserRequest")
                .then()
                .extract()
                .as(UserRequestResponse.class);

        UserRequestResponse responsesBody = given()
                .pathParam("applicationId", responseBody.getData().getApplicationid())
                .when()
                .get("/getApplStatus/{applicationId}")
                .then()
                .extract()
                .as(UserRequestResponse.class);

        assertNotNull(responsesBody.getData().getStatusofapplication(), "Статус полученной заявки пустой!");
    }

    @Epic("АПИ ТЕСТЫ")
    @Feature("Работа с заявками")
    @Story("Ошибка валидации")
    @Description("Негативный тест: проверка, что при передаче отрицательного ID сервер возвращает ошибку клиента")
    @Test
    public void testGetApplicationStatusWithInvalidId() {
        given()
                .spec(Specifications.requestSpec())
                .pathParam("applicationId", -999)
                .when()
                .get("/getApplStatus/{applicationId}")
                .then()
                .statusCode(400);
    }

    @Epic("АПИ ТЕСТЫ")
    @Feature("Работа с заявками")
    @Story("Ошибка авторизации")
    @Description("Негативный тест: проверка, что при неверном пароле сервер возвращает 401")
    @Test
    public void testGetApplicationStatusWithWrongAuth() {
        given()
                .baseUri("https://regoffice.senla.eu")
                .auth().basic("user", "WRONG_PASSWORD")
                .pathParam("applicationId", 11111)
                .when()
                .get("/getApplStatus/{applicationId}")
                .then()
                .statusCode(401);
    }
}