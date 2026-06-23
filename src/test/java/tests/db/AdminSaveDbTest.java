package tests.db;

import data.AdminRequestData;
import data.AdminResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tests.api.BaseApiTest;
import tests.api.Specifications;
import utils.DbManager;

import static data.TestDataFactory.createValidAdminData;
import static io.restassured.RestAssured.given;

public class AdminSaveDbTest extends BaseApiTest {
    private final String adminName = "Петр";

    @Epic("Тесты БД")
    @Feature("Валидация сохранения администраторов")
    @Description("Тест создает админа по API и проверяет его сохранение в таблице staff через JDBC")
    @Test
    public void testAdminSuccessfullySavedInDb() {

        AdminRequestData adminBody = createValidAdminData(adminName);

        AdminResponse responseBody = given()
                .body(adminBody)
                .when()
                .post("/sendAdminRequest/")
                .then()
                .extract()
                .as(AdminResponse.class);

        int createdStaffId = responseBody.getData().getStaffid();
        String actualAdminNameFromDb = DbManager.getAdminNameById(createdStaffId);
        Assertions.assertEquals(adminName, actualAdminNameFromDb,
                "Имя администратора в базе данных не совпадает с отправленным по API!");
    }
}
