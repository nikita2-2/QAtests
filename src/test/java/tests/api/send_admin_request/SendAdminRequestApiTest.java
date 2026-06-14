package tests.api.send_admin_request;

import data.AdminRequestData;
import data.AdminResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tests.api.BaseApiTest;
import tests.api.Specifications;
import static io.restassured.RestAssured.given;


@Slf4j
public class SendAdminRequestApiTest extends BaseApiTest {

    @Epic("АПИ ТЕСТЫ")
    @Feature("Создание админа")
    @Story("Создание нового администратора ЗАГС")
    @Description("Тест отправляет валидный JSON на /sendAdminRequest/ и проверяет создание админа")
    @Test
    public void testCreateAdminUserSuccessfully() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec());
        AdminRequestData adminBody = AdminRequestData.builder()
                .dateofbirth("1985-01-01")
                .personalFirstName("smdfsk")
                .personalLastName("smdfsk")
                .personalMiddleName("smdfsk")
                .personalNumberOfPassport("ФИ123456")
                .personalPhoneNumber("7999123445")
                .build();

        AdminResponse responseBody = given()
                .body(adminBody)
                .when()
                .post("/sendAdminRequest/")
                .then()
                .extract()
                .as(AdminResponse.class);


        Assertions.assertNotNull(responseBody.getData().getStaffid(), "ID админа пустой!");
    }
}
