package tests.ui;

import data.CitizenData;
import data.MarriageData;
import data.UserData;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.*;

@Slf4j
public class MarriageRegistrationTest extends BaseTest {
    MainPage mainPage;
    UserRegistrationPage userRegistrationPage;
    ServiceSelectionPage serviceSelectionPage;
    CitizenDataPage citizenDataPage;
    MarriageServiceDataPage marriageServiceDataPage;
    ApplicationStatusPage applicationStatusPage;
    UserData dataUser;
    CitizenData dataCitizen;
    MarriageData marriageData;

    @BeforeEach
    public void setUp() {
        mainPage = new MainPage(driver);
        userRegistrationPage = new UserRegistrationPage(driver);
        serviceSelectionPage = new ServiceSelectionPage(driver);
        citizenDataPage = new CitizenDataPage(driver);
        marriageServiceDataPage = new MarriageServiceDataPage(driver);
        applicationStatusPage = new ApplicationStatusPage(driver);

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
                .birthDate("01011995")
                .passport("KH765321")
                .gender("Муж")
                .address("Брест, ул. Советская, 5")
                .build();

        marriageData = MarriageData.builder()
                .regDate("12122026")
                .newLastName("Иванова")
                .spouseLastName("Сидорова")
                .spouseFirstName("Мария")
                .spouseMiddleName("Игоревна")
                .spouseBirthDate("05051998")
                .spousePassport("MP9876543")
                .build();
    }

    @Test
    @Epic("Регистрация заявлений ЗАГС")
    @Feature("Регистрация брака")
    @Story("Успешная подача заявки на регистрацию брака")
    @Description("Тест проверяет пошаговое заполнение 5 окон данных для регистрации брака")
    public void testSuccessfulMarriageRegistrationE2E() {
        mainPage.clickLoginAsUser();

        userRegistrationPage.fillRegistrationData(dataUser);
        userRegistrationPage.buttons.clickNext();

        serviceSelectionPage.selectMarriageRegistration();

        citizenDataPage.fillCitizenData(dataCitizen);
        citizenDataPage.buttons.clickNext();

        marriageServiceDataPage.fillMarriageDetails(marriageData);
        marriageServiceDataPage.buttons.clickEnd();

        applicationStatusPage.clickRefresh();
        String finalResultText = applicationStatusPage.getFinalSuccessText();

        Assertions.assertTrue(finalResultText.contains("отправлена на рассмотрение."), "Ошибка, заявка не отправлена");
        isTestFailed = false;
    }
}
