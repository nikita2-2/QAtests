package tests.api.get_applications;

import data.GetApplicationsResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;
import tests.api.BaseApiTest;
import tests.api.Specifications;
import java.util.List;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class GetApplicationsApiTestArr extends BaseApiTest {

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

        org.junit.jupiter.api.Assertions.assertTrue(responseBody.getData() instanceof java.util.List,
                "Ошибка: Ожидался массив JSON (List) в поле data!");
    }
}
