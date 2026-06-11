package tests.api.send_user_request;

import data.UserRequestData;
import io.qameta.allure.*;
import io.restassured.http.ContentType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import tests.api.BaseApiTest;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;
import io.restassured.response.Response;


@Slf4j
public class UserRequestBirthTest extends BaseApiTest {

    @Epic("АПИ ТЕСТЫ")
    @Feature("Создание новой заявки")
    @Story("Создание заявки на регистрацию рождения")
    @Description("Тест проверяет, что при отправке валидных данных создается заявка на регистрацию рождения и возращается ее номер")
    @Test
    public void testCreateBirthRequestApi() {
        log.info("СТАРТ API ТЕСТА: Успешное создание заявки на брак через /sendUserRequest");

        UserRequestData birthBody = UserRequestData.builder()
                .mode("birth")

                .personalFirstName("Петя")
                .personalLastName("Иванов")
                .personalMiddleName("Иванович")
                .personalPhoneNumber("79991112233")
                .personalNumberOfPassport("AB123456")

                .citizenLastName("Иванов")
                .citizenFirstName("Иван")
                .citizenMiddleName("Иванович")
                .citizenBirthDate("1995-10-10")
                .citizenNumberOfPassport("AB123456")
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

        Response response = given()
                .contentType(ContentType.JSON)
                .body(birthBody)

                .when()
                .post("/sendUserRequest")

                .then()
                .log().ifValidationFails()
                .statusCode(200)
                .body("data.applicationid", notNullValue())
                .extract().response();
        int createdApplicationId = response.path("data.applicationid");
        log.info("Заявка на регистрацию рождения номер {} успешно создана и отправлена", createdApplicationId);


        log.info("API ТЕСТ УСПЕШНО ЗАВЕРШЕН");
    }
}