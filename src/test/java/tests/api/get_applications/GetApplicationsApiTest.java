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
import static org.junit.jupiter.api.Assertions.assertFalse;

public class GetApplicationsApiTest extends BaseApiTest {

    @Epic("АПИ ТЕСТЫ")
    @Feature("Работа с заявками")
    @Story("Получение списка заявок с пагинацией")
    @Description("Тест запрашивает страницу заявок через параметры")
    @Test
    public void testGetApplicationsWithPagination() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec());

        GetApplicationsResponse responseBody = given()
                .when()
                .get("/getApplications")
                .then()
                .extract()
                .as(GetApplicationsResponse.class);

        assertFalse(responseBody.getData().isEmpty(), "Сервер вернул пустой список заявок!");
    }
}
