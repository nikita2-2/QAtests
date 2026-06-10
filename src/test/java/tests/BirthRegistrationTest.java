package tests;

import data.BirthData;
import data.CitizenData;
import data.UserData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.*;

public class BirthRegistrationTest extends BaseTest {
    MainPage mainPage;
    UserRegistrationPage userRegistrationPage;
    ServiceSelectionPage serviceSelectionPage;
    CitizenDataPage citizenDataPage;
    BirthServiceDataPage birthServiceDataPage;
    ApplicationStatusPage applicationStatusPage;
    UserData dataUser;
    CitizenData dataCitizen;
    BirthData birthData;

    @BeforeEach
    public void setUp() {
        mainPage = new MainPage(driver);
        userRegistrationPage = new UserRegistrationPage(driver);
        serviceSelectionPage = new ServiceSelectionPage(driver);
        citizenDataPage = new CitizenDataPage(driver);
        birthServiceDataPage = new BirthServiceDataPage(driver);
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

        birthData = BirthData.builder()
                .birthPlace("Гродно")
                .motherName("Смирнова Анна Игоревна")
                .fatherName("Смирнов Олег Петрович")
                .grandmotherName("Смирнова Мария Ивановна")
                .grandfatherName("Смирнов Петр Сергеевич")
                .build();
    }

    @Test
    public void testBirthRegistrationE2E() {
        mainPage.clickLoginAsUser();
        userRegistrationPage.fillRegistrationData(dataUser);
        userRegistrationPage.buttons.clickNext();

        serviceSelectionPage.selectBirthRegistration();

        citizenDataPage.fillCitizenData(dataCitizen);
        citizenDataPage.buttons.clickNext();

        birthServiceDataPage.fillBirthDetails(birthData);
        birthServiceDataPage.buttons.clickEnd();

        applicationStatusPage.clickRefresh();
        String finalResultText = applicationStatusPage.getFinalSuccessText();

        Assertions.assertTrue(finalResultText.contains("отправлена на рассмотрение."), "Ошибка, заявка не отправлена");
    }
}
