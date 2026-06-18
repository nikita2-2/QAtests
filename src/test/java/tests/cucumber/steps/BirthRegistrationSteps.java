package tests.cucumber.steps;

import data.UserRequestData;
import data.UserRequestResponse;
import io.cucumber.java.ru.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import tests.api.BaseApiTest;
import tests.api.Specifications;

import static io.restassured.RestAssured.given;

@Slf4j
public class BirthRegistrationSteps extends BaseApiTest {

    private static UserRequestData birthBody;
    private static UserRequestResponse responseBody;

    @Дано("Установлены базовые спецификации запросов и ответов")
    public void setupSpecs() {
        log.info("BDD: Установка API спецификаций...");
        io.restassured.RestAssured.baseURI = "";
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec());
    }

    @Когда("пользователь отправляет валидный запрос на регистрацию рождения")
    public void sendBirthRequest() {
        birthBody = createBirthUser();

        responseBody = given()
                .body(birthBody)
                .when()
                .post("/sendUserRequest")
                .then()
                .extract()
                .as(UserRequestResponse.class);
    }

    @Тогда("Сервер успешно обрабатывает запрос")
    public void verifyServerResponse() {
        log.info("Сервер вернул успешный статус ответа!");
    }

    @Допустим("в ответе присутствует не пустой ID новой заявки")
    public void verifyApplicationId() {
        Assertions.assertNotNull(responseBody.getData().getApplicationid(), "ID заявки пустой!");
        log.info("ID заявки: " + responseBody.getData().getApplicationid());
    }

    private UserRequestData createBirthUser(){
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
