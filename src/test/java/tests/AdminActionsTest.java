package tests;

import data.AdminData;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.AdminDashboardPage;
import pages.AdminRegistrationPage;
import pages.MainPage;

public class AdminActionsTest extends BaseTest {
    MainPage mainPage;
    AdminRegistrationPage adminRegistrationPage;
    AdminDashboardPage adminDashboardPage;
    AdminData dataAdmin;

    @BeforeEach
    public void setUp() {
        mainPage = new MainPage(driver);

        adminRegistrationPage = new AdminRegistrationPage(driver);
        adminDashboardPage = new AdminDashboardPage(driver);

        dataAdmin = AdminData.builder()
                .lastName("Петров")
                .firstName("Петр")
                .middleName("Петрович")
                .passport("KH765321")
                .phone("79991112233")
                .birthDate("01011988")
                .build();
    }

    @Test
    @Epic("Панель админа ЗАГС")
    @Feature("Администрирование заявок")
    @Story("Одобрение заявки по айди")
    @Description("ТТест проверяет авторизацию админа, поиск заявки в таблице по айди и смену статуса на 'Одобрена'")
    public void testAdminCanApproveMarriageOrder() {
        String targetOrderId = "65095";

        mainPage.clickLoginAsAdmin();

        adminRegistrationPage.fillAdminRegistrationData(dataAdmin);
        adminRegistrationPage.buttons.clickNext();

        adminDashboardPage.goToPage(2);

        adminDashboardPage.table.approveOrderById(targetOrderId);

        String actualStatusText = adminDashboardPage.getOrderStatusById(targetOrderId);

        Assertions.assertTrue(actualStatusText.contains("Одобрена"),
                "Ошибка, статус заявки не изменился на 'Одобрена'.");
        isTestFailed = false;
    }
}
