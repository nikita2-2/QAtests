package tests.api.send_admin_request;

import data.AdminRequestData;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import tests.api.BaseApiTest;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.notNullValue;

@Slf4j
public class SendAdminRequestApiTest extends BaseApiTest {

    @Test
    public void testCreateAdminUserSuccessfully() {
        log.info("API ТЕСТ: Создание пользователя-администратора через /sendAdminRequest");

        AdminRequestData adminBody = AdminRequestData.builder()
                .dateofbirth("1985-01-01")
                .personalFirstName("Петр")
                .personalLastName("Петров")
                .personalMiddleName("Петрович")
                .personalNumberOfPassport("KH123456")
                .personalPhoneNumber("79998887766")
                .build();

        Response response = given()
                .contentType(ContentType.JSON)
                .body(adminBody)

                .when()
                .post("/sendAdminRequest")

                .then()
                .log().ifValidationFails()
                .statusCode(200)
                .body("data.staffid", notNullValue())
                .body("data.staffid", greaterThan(0))
                .extract().response();
        int createdApplicationId = response.path("data.staffid");
        log.info("Админ с айди {} успешно создан", createdApplicationId);

        log.info("API ТЕСТ УСПЕШНО ЗАВЕРШЕН");
    }
}
