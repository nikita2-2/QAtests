package tests.api.send_user_request;


import data.UserRequestData;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.http.ContentType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import tests.api.BaseApiTest;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;
import io.restassured.response.Response;

@Slf4j
public class UserRequestDeathTest extends BaseApiTest {

    @Epic("АПИ ТЕСТЫ")
    @Feature("Создание новой заявки")
    @Story("Создание заявки на регистрацию смерти")
    @Description("Тест проверяет, что при отправке валидных данных создается заявка на регистрацию смерти и возращается ее номер")
    @Test
    public void testCreateDeathRequestApi() {
        log.info("СТАРТ API ТЕСТА: Успешное создание заявки на смерть через /sendUserRequest");

        UserRequestData deathBody = UserRequestData.builder()
                .mode("death")

                .personalFirstName("Иванвцовв")
                .personalLastName("Иванов")
                .personalMiddleName("Иванович")
                .personalPhoneNumber("79999856985")
                .personalNumberOfPassport("ММ466666")

                .citizenLastName("Петрова")
                .citizenFirstName("Иван")
                .citizenMiddleName("Иванович")
                .citizenBirthDate("1995-10-10")
                .citizenNumberOfPassport("AB123456")
                .citizenGender("М")

                .death_placeOfDeath("Москва улица Ленина")
                .death_dateOfDeath("2025-10-10")

                .build();

        Response response = given()
                .contentType(ContentType.JSON)
                .body(deathBody)


                .when()
                .post("/sendUserRequest")


                .then()
                .log().ifValidationFails()
                .log().all()
                .statusCode(200)
                .body("data.applicationid", notNullValue())
                .extract().response();

        int createdApplicationId = response.path("data.applicationid");
        log.info("Заявка на регистрацию смерти номер {} успешно создана и отправлена", createdApplicationId);

        log.info("API ТЕСТ УСПЕШНО ЗАВЕРШЕН");
    }
}