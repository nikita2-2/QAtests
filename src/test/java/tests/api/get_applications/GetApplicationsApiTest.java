package tests.api.get_applications;

import data.GetApplicationsResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;
import tests.api.BaseApiTest;
import tests.api.Specifications;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class GetApplicationsApiTest extends BaseApiTest {

    @Epic("АПИ ТЕСТЫ")
    @Feature("Работа с заявками")
    @Story("Получение списка заявок")
    @Description("Тест запрашивает общий список заявок без параметров")
    @Test
    public void testGetApplications() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec());

        GetApplicationsResponse responseBody = given()
                .when()
                .get("/getApplications")
                .then()
                .extract()
                .as(GetApplicationsResponse.class);

        assertFalse(responseBody.getData().isEmpty(), "Сервер вернул пустой список заявок!");
    }

    @Epic("АПИ ТЕСТЫ")
    @Feature("Работа с заявками")
    @Story("Ошибка авторизации")
    @Description("Негативный тест: проверка, что при неверном пароле сервер возвращает 401")
    @Test
    public void testGetApplicationsWithWrongAuth() {
        given()
                .baseUri("https://regoffice.senla.eu")
                .auth().basic("user", "WRONG_PASSWORD")
                .queryParam("page", 1)
                .queryParam("size", 10)
                .when()
                .get("/getApplications")
                .then()
                .statusCode(401);
    }
}
