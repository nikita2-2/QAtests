package tests;

import data.AdminData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.AdminDashboardPage;
import pages.AdminRegistrationPage;
import pages.MainPage;
import java.time.Duration;

public class AdminActionsTest {
    WebDriver driver;
    MainPage mainPage;
    AdminRegistrationPage adminRegistrationPage;
    AdminDashboardPage adminDashboardPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        mainPage = new MainPage(driver);
        adminRegistrationPage = new AdminRegistrationPage(driver);
        adminDashboardPage = new AdminDashboardPage(driver);
    }

    @Test
    public void testAdminCanApproveMarriageOrder() {
        AdminData admin = AdminData.builder()
                .lastName("Петров")
                .firstName("Петр")
                .middleName("Петрович")
                .passport("KH765321")
                .phone("79991112233")
                .birthDate("01011988")
                .build();
        String targetOrderId = "65243";

        driver.get("https://user:senlatest@regoffice.senla.eu/");

        mainPage.clickLoginAsAdmin();

        adminRegistrationPage.fillAdminRegistrationData(admin);
        adminRegistrationPage.buttons.clickNext();

        adminDashboardPage.table.approveOrderById(targetOrderId);

        By statusLocator = By.xpath("//td[text()='" + targetOrderId + "']/../td[5]/span");

        String actualStatusText = driver.findElement(statusLocator).getText();


        Assertions.assertTrue(actualStatusText.contains("Одобрена"),
                "Ошибка! Статус заявки не изменился на 'Одобрено'.");





    }


    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}