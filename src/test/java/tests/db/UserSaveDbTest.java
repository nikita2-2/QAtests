package tests.db;

import data.UserRequestData;
import data.UserRequestResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.Test;
import tests.api.BaseApiTest;
import tests.api.Specifications;
import utils.DbManager;

import static data.TestDataFactory.createValidBirthUserData;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserSaveDbTest extends BaseApiTest {
    private final String name = "Nikita";

    @Epic("Тесты БД")
    @Feature("Сохранение пользователя")
    @Description("Тест отправляет API-запрос на создание гражданина и проверяет его корректное сохранение в БД")
    @Test
    public void testUserSuccessfullySavedInDb() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec());
        UserRequestData birthBody = createValidBirthUserData(name);

        UserRequestResponse responseBody = given()
                .body(birthBody)
                .when()
                .post("/sendUserRequest")
                .then()
                .extract()
                .as(UserRequestResponse.class);

        int createdUserId = responseBody.getData().getApplicantid();
        int createdApplicationId = responseBody.getData().getApplicationid();

        String actualUserFromDb = DbManager.getApplicantNameById(createdUserId);
        assertEquals(name, actualUserFromDb, "Имя в БД не совпадает с отправленным по API!");
        DbManager.deleteApplicantWithApplication(createdUserId, createdApplicationId);

    }
}