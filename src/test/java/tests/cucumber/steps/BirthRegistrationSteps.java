package tests.cucumber.steps;

import data.UserRequestData;
import data.UserRequestResponse;
import io.cucumber.java.ru.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import tests.api.BaseApiTest;
import tests.api.Specifications;
import static data.TestDataFactory.createValidBirthUserData;
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
        birthBody = createValidBirthUserData("Ivan");

        responseBody = given()
                .body(birthBody)
                .when()
                .post("/sendUserRequest")
                .then()
                .statusCode(200)
                .extract()
                .as(UserRequestResponse.class);
        log.info("Сервер успешно обработал запрос и вернул статус 200!");
    }

    @Тогда("в ответе присутствует не пустой ID новой заявки")
    public void verifyApplicationId() {
        Assertions.assertNotNull(responseBody.getData().getApplicationid(), "ID заявки пустой!");
        log.info("ID заявки: " + responseBody.getData().getApplicationid());
    }
}
