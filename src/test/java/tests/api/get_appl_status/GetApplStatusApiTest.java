package tests.api.get_appl_status;

import data.UserRequestResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import tests.api.BaseApiTest;
import tests.api.Specifications;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
public class GetApplStatusApiTest extends BaseApiTest {

    @Epic("АПИ ТЕСТЫ")
    @Feature("Работа с заявками")
    @Story("Получение заявки по айди")
    @Description("Тест проверяет получение заявки по айди")
    @Test
    public void testGetApplicationStatusSuccessfully() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec());
        int targetApplicationId = 65372;
        UserRequestResponse responseBody = given()
                .pathParam("applicationId", targetApplicationId)
                .when()
                .get("/getApplStatus/{applicationId}")
                .then()
                .extract()
                .as(UserRequestResponse.class);

        assertNotNull(responseBody.getData().getStatusofapplication(), "Статус полученной заявки пустой!");
    }
}