package tests.api.send_user_request;

import data.UserRequestData;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import tests.api.BaseApiTest;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

@Slf4j
public class UserRequestMarriageTest extends BaseApiTest {

    @Epic("АПИ ТЕСТЫ")
    @Feature("Создание новой заявки")
    @Story("Создание заявки на регистрацию брака")
    @Description("Тест проверяет, что при отправке валидных данных создается заявка на регистрацию брака и возращается ее номер")
    @Test
    public void testCreateMarriageRequestApi() {
        log.info("СТАРТ API ТЕСТА: Успешное создание заявки на брак через /sendUserRequest");

        UserRequestData marriageBody = UserRequestData.builder()
                .mode("wedding")

                .personalLastName("Иванов")
                .personalFirstName("Иванвцовв")
                .personalMiddleName("Иванович")
                .personalPhoneNumber("79999856985")
                .personalNumberOfPassport("ММ466666")

                .citizenLastName("Петрова")
                .citizenFirstName("Иван")
                .citizenMiddleName("Иванович")
                .citizenBirthDate("1995-10-10")
                .citizenNumberOfPassport("AB123456")
                .citizenGender("М")

                .dateOfMarriage("2026-10-10")
                .newLastName("")
                .anotherPersonLastName("Сидорова")
                .anotherPersonFirstName("Мария")
                .anotherPersonMiddleName("Алексеевна")
                .birth_of_anotoherPerson("1999-01-01")
                .anotherPersonPassport("CD789012")

                .build();

        Response response = given()
                .contentType(ContentType.JSON)
                .body(marriageBody)

                .when()
                .post("/sendUserRequest")

                .then()
                .log().ifValidationFails()
                .log().all()
                .statusCode(200)
                .body("data.applicationid", notNullValue())
                .extract().response();
        int createdApplicationId = response.path("data.applicationid");
        log.info("Заявка на регистрацию брака номер {} успешно создана и отправлена", createdApplicationId);

        log.info("API ТЕСТ УСПЕШНО ЗАВЕРШЕН");
    }
}