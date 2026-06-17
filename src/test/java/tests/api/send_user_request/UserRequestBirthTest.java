package tests.api.send_user_request;

import data.UserRequestData;
import data.UserRequestResponse;
import io.qameta.allure.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tests.api.BaseApiTest;
import tests.api.Specifications;

import static io.restassured.RestAssured.given;

@Slf4j
public class UserRequestBirthTest extends BaseApiTest {

    @Epic("АПИ ТЕСТЫ")
    @Feature("Создание новой заявки")
    @Story("Создание заявки на регистрацию рождения")
    @Description("Тест проверяет, что при отправке валидных данных создается заявка на регистрацию рождения и возращается ее номер")
    @Test
    public void testCreateBirthRequestApi() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec());
        UserRequestData birthBody = createBirthData();

        UserRequestResponse responseBody = given()
                .body(birthBody)
                .when()
                .post("/sendUserRequest")
                .then()
                .extract()
                .as(UserRequestResponse.class);

        Assertions.assertNotNull(responseBody.getData().getApplicationid(), "ID заявки пустой!");
        }

    private UserRequestData createBirthData(){
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