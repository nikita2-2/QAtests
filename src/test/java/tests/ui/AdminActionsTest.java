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

    @ParameterizedTest(name = "Тест в {0} {1}")
    @CsvSource({
            "chrome, 120.0",
            "chrome, 110.0",
            "MicrosoftEdge, 114.0"
    })
    @Epic("Панель админа ЗАГС")
    @Feature("Администрирование заявок")
    @Story("Одобрение заявки по айди")
    @Description("Тест проверяет авторизацию админа, поиск заявки в таблице по айди и смену статуса на 'Одобрена'")
    public void testAdminCanApproveMarriageOrder(String browser, String version) throws MalformedURLException {
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
