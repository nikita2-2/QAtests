package tests.api.send_admin_request;

import data.AdminRequestData;
import data.AdminResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tests.api.BaseApiTest;
import tests.api.Specifications;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static data.TestDataFactory.createAdminData;


public class SendAdminRequestApiTest extends BaseApiTest {

    @Epic("АПИ ТЕСТЫ")
    @Feature("Создание админа")
    @Story("Создание нового администратора ЗАГС")
    @Description("Тест отправляет валидный JSON на /sendAdminRequest/ и проверяет создание админа")
    @Test
    public void testCreateAdminUserSuccessfully() {
        Specifications.installSpecifications(Specifications.requestSpec(), Specifications.responseSpec());
        AdminRequestData adminBody = createAdminData();

        AdminResponse responseBody = given()
                .body(adminBody)
                .when()
                .post("/sendAdminRequest/")
                .then()
                .extract()
                .as(AdminResponse.class);

        Assertions.assertNotNull(responseBody.getData().getStaffid(), "ID админа пустой!");
    }

    @Epic("АПИ ТЕСТЫ")
    @Feature("Создание админа")
    @Story("Создание нового администратора ЗАГС")
    @Description("Тест отправляет валидный JSON на /sendAdminRequest/ и проверяет айди админа")
    @Test
    public void testCreateAdminUserSuccessfullyCheckId() {
        AdminRequestData adminBody = createAdminData();

        AdminResponse responseBody = given()
                .body(adminBody)
                .when()
                .post("/sendAdminRequest/")
                .then()
                .extract()
                .as(AdminResponse.class);

        assertTrue(responseBody.getData().getStaffid() > 0,
                "Ошибка: ID созданного админа должен быть больше нуля!");
    }

    @Epic("АПИ ТЕСТЫ")
    @Feature("Создание админа")
    @Story("Ошибка авторизации регистрации админа")
    @Description("Негативный тест: проверка, что при неверном пароле сервер возвращает 401")
    @Test
    public void testCreateAdminWithWrongAuth() {
        AdminRequestData adminBody = createAdminData();

        given()
                .baseUri(Specifications.apiUrl)
                .auth().basic(Specifications.apiUser, "WRONG_PASSWORD")
                .contentType(io.restassured.http.ContentType.JSON)
                .body(adminBody)
                .when()
                .post("/sendAdminRequest/")
                .then()
                .statusCode(401);
    }

    @Epic("АПИ ТЕСТЫ")
    @Feature("Создание админа")
    @Story("Ошибка валидации регистрации админа")
    @Description("Негативный тест: проверка, что при пустом JSON сервер возвращает 400")
    @Test
    public void testCreateAdminWithInvalidData() {
        AdminRequestData invalidBody = AdminRequestData.builder().build();

        given()
                .spec(Specifications.requestSpec())
                .body(invalidBody)
                .when()
                .post("/sendAdminRequest/")
                .then()
                .statusCode(400);
    }
}
