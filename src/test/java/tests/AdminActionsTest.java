package tests;

import data.AdminData;
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
    public void testAdminCanApproveMarriageOrder() {
        String targetOrderId = "65279";

        mainPage.clickLoginAsAdmin();

        adminRegistrationPage.fillAdminRegistrationData(dataAdmin);
        adminRegistrationPage.buttons.clickNext();

        adminDashboardPage.table.approveOrderById(targetOrderId);

        String actualStatusText = adminDashboardPage.getOrderStatusById(targetOrderId);
        // вынести локатор в пейдж
        Assertions.assertTrue(actualStatusText.contains("Одобрена"),
                "Ошибка! Статус заявки не изменился на 'Одобрена'.");
    }
}
