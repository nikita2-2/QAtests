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
        UserRequestData birthBody = createBirthForApiStatus();

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

    private UserRequestData createBirthForApiStatus() {
        return UserRequestData.builder()
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
    }
}