package tests.ui;

import data.CitizenData;
import data.DeathData;
import data.UserData;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pages.*;

import java.net.MalformedURLException;

public class DeathRegistrationTest extends BaseTest {
    MainPage mainPage;
    UserRegistrationPage userRegistrationPage;
    ServiceSelectionPage serviceSelectionPage;
    CitizenDataPage citizenDataPage;
    DeathServiceDataPage deathServiceDataPage;
    ApplicationStatusPage applicationStatusPage;
    UserData dataUser;
    CitizenData dataCitizen;
    DeathData deathData;

    @BeforeEach
    public void setUp() {
        dataUser = UserData.builder()
                .lastName("Иванов")
                .firstName("Иван")
                .middleName("Иванович")
                .phone("79991112233")
                .address("Минск, ул. Ленина, 10")
                .passport("AB123456")
                .build();

        dataCitizen = CitizenData.builder()
                .lastName("Петров")
                .firstName("Петр")
                .middleName("Петрович")
                .birthDate("10101995")
                .passport("KH765321")
                .gender("Муж")
                .address("Брест, ул. Советская, 5")
                .build();

        deathData = DeathData.builder()
                .deathDate("01011995")
                .deathPlace("город Минск улица Казинца")
                .build();
    }

    @org.junit.jupiter.params.ParameterizedTest(name = "Тест в {0}")
    @org.junit.jupiter.params.provider.MethodSource("tests.ui.BaseTest#provideBrowsers")
    @Epic("Регистрация заявлений ЗАГС")
    @Feature("Регистрация смерти")
    @Story("Успешная подача заявки на регистрацию смерти")
    @Description("Тест проверяет пошаговое заполнение 5 окон данных для регистрации смерти")
    public void testDeathRegistrationE2E(String browserAndVersion) throws MalformedURLException {
        String[] parts = browserAndVersion.split(":");
        String browser = parts[0];
        String version = parts[1];

        initDriver(browser, version);

        mainPage = new MainPage(driver);
        userRegistrationPage = new UserRegistrationPage(driver);
        serviceSelectionPage = new ServiceSelectionPage(driver);
        citizenDataPage = new CitizenDataPage(driver);
        deathServiceDataPage = new DeathServiceDataPage(driver);
        applicationStatusPage = new ApplicationStatusPage(driver);

        mainPage.clickLoginAsUser();
        userRegistrationPage.fillRegistrationData(dataUser);
        userRegistrationPage.buttons.clickNext();

        serviceSelectionPage.selectDeathRegistration();

        citizenDataPage.fillCitizenData(dataCitizen);
        citizenDataPage.buttons.clickNext();

        deathServiceDataPage.fillDeathDetails(deathData);
        deathServiceDataPage.buttons.clickEnd();

        applicationStatusPage.clickRefresh();
        String finalResultText = applicationStatusPage.getFinalSuccessText();

        Assertions.assertTrue(finalResultText.contains("отправлена на рассмотрение."), "Ошибка, заявка не отправлена");
    }
}
