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
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserSaveDbTest extends BaseApiTest {

    @Epic("Тесты БД")
    @Feature("Сохранение пользователя")
    @Description("Тест отправляет API-запрос на создание гражданина и проверяет его корректное сохранение в БД")
    @Test
    public void testUserSuccessfullySavedInDb() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec());
        String testPassport = "AB123456";
        String expectedPhone = "79991112233";
        String name = "Nikita";


        UserRequestData birthBody = UserRequestData.builder()
                .mode("birth")
                .personalFirstName(name)
                .personalLastName("Иванов")
                .personalMiddleName("Иванович")
                .personalPhoneNumber(expectedPhone)
                .personalNumberOfPassport(testPassport)

                .citizenLastName("Иванов")
                .citizenFirstName("Иван")
                .citizenMiddleName("Иванович")
                .citizenBirthDate("1995-10-10")
                .citizenNumberOfPassport(testPassport)
                .citizenGender("Муж")

                .dateOfMarriage("2026-10-10")
                .newLastName("Иванова")
                .anotherPersonLastName("Сидорова")
                .anotherPersonFirstName("Мария")
                .anotherPersonMiddleName("Алексеевна")
                .birth_of_anotoherPerson("15051997")
                .anotherPersonPassport("CD789012")

                .birth_place("Москва, ул Длинная 4")
                .birth_mother("Anna")
                .birth_father("Egor")
                .build();

        UserRequestResponse responseBody = given()
                .body(birthBody)
                .when()
                .post("/sendUserRequest")
                .then()
                .extract()
                .as(UserRequestResponse.class);

        int createdUserId = responseBody.getData().getApplicantid();
        String actualUserFromDb = DbManager.getApplicantNameById(createdUserId);
        assertEquals(name, actualUserFromDb, "Телефон в БД не совпадает с отправленным по API!");
    }
}