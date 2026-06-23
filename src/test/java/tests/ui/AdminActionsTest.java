package tests.ui;

import data.AdminData;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pages.AdminDashboardPage;
import pages.AdminRegistrationPage;
import pages.MainPage;

import java.net.MalformedURLException;
import java.time.Duration;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AdminActionsTest extends BaseTest {
    MainPage mainPage;
    AdminRegistrationPage adminRegistrationPage;
    AdminDashboardPage adminDashboardPage;
    AdminData dataAdmin;

    @BeforeEach
    public void setUp() {
        dataAdmin = AdminData.builder()
                .lastName("Петров")
                .firstName("Петр")
                .middleName("Петрович")
                .passport("KH765321")
                .phone("79991112233")
                .birthDate("01011988")
                .build();
    }

    @org.junit.jupiter.params.ParameterizedTest(name = "Тест в {0}")
    @org.junit.jupiter.params.provider.MethodSource("tests.ui.BaseTest#provideBrowsers")
    @Epic("Панель админа ЗАГС")
    @Feature("Администрирование заявок")
    @Story("Одобрение заявки по айди")
    @Description("Тест проверяет авторизацию админа, поиск заявки в таблице по айди и смену статуса на 'Одобрена'")
    public void testAdminCanApproveMarriageOrder(String browserAndVersion) throws MalformedURLException {
        String[] parts = browserAndVersion.split(":");
        String browser = parts[0];
        String version = parts[1];

        initDriver(browser, version);

        mainPage = new MainPage(driver);

        adminRegistrationPage = new AdminRegistrationPage(driver);
        adminDashboardPage = new AdminDashboardPage(driver);

        mainPage.clickLoginAsAdmin();

        adminRegistrationPage.fillAdminRegistrationData(dataAdmin);
        adminRegistrationPage.buttons.clickNext();

        adminDashboardPage.goToPage(2);

        String targetOrderId = adminDashboardPage.table.getFirstOrderId();

        adminDashboardPage.table.approveOrderById(targetOrderId);

        adminDashboardPage.waitForAppruved(targetOrderId);

        String actualStatusText = adminDashboardPage.getOrderStatusById(targetOrderId);

        Assertions.assertTrue(actualStatusText.contains("Одобрена"),
                "Ошибка, статус заявки не изменился на 'Одобрена'.");
    }
}
