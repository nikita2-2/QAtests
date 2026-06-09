package tests;

import data.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.*;

public class ZagsE2ETest extends BaseTest {
    MainPage mainPage;
    UserRegistrationPage userRegistrationPage;
    ServiceSelectionPage serviceSelectionPage;
    CitizenDataPage citizenDataPage;
    MarriageServiceDataPage marriageServiceDataPage;
    BirthServiceDataPage birthServiceDataPage;
    DeathServiceDataPage deathServiceDataPage;
    ApplicationStatusPage applicationStatusPage;
    UserData dataUser;
    CitizenData dataCitizen;

    @BeforeEach
    public void setUp() {
        mainPage = new MainPage(driver);
        userRegistrationPage = new UserRegistrationPage(driver);
        serviceSelectionPage = new ServiceSelectionPage(driver);
        citizenDataPage = new CitizenDataPage(driver);
        marriageServiceDataPage = new MarriageServiceDataPage(driver);
        birthServiceDataPage = new BirthServiceDataPage(driver);
        deathServiceDataPage = new DeathServiceDataPage(driver);
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
    }

    @Test
    public void testSuccessfulMarriageRegistrationE2E() {
        MarriageData marriage = MarriageData.builder()
                .regDate("12122026")
                .newLastName("Иванова")
                .spouseLastName("Сидорова")
                .spouseFirstName("Мария")
                .spouseMiddleName("Игоревна")
                .spouseBirthDate("05.05.1998")
                .spousePassport("MP9876543")
                .build();

        mainPage.clickLoginAsUser();

        userRegistrationPage.fillRegistrationData(dataUser);
        userRegistrationPage.buttons.clickNext();

        serviceSelectionPage.selectMarriageRegistration();

        citizenDataPage.fillCitizenData(dataCitizen);
        citizenDataPage.buttons.clickNext();

        marriageServiceDataPage.fillMarriageDetails(marriage);
        marriageServiceDataPage.buttons.clickEnd();

        applicationStatusPage.clickRefresh();
        String finalResultText = applicationStatusPage.getFinalSuccessText();
        System.out.println(finalResultText);

        Assertions.assertTrue(finalResultText.contains("отправлена на рассмотрение."));
    }

    @Test
    public void testBirthRegistrationE2E() {
        BirthData birth = BirthData.builder()
                .birthPlace("Гродно")
                .motherName("Смирнова Анна Игоревна")
                .fatherName("Смирнов Олег Петрович")
                .grandmotherName("Смирнова Мария Ивановна")
                .grandfatherName("Смирнов Петр Сергеевич")
                .build();

        mainPage.clickLoginAsUser();
        userRegistrationPage.fillRegistrationData(dataUser);
        userRegistrationPage.buttons.clickNext();

        serviceSelectionPage.selectBirthRegistration();

        citizenDataPage.fillCitizenData(dataCitizen);
        citizenDataPage.buttons.clickNext();

        birthServiceDataPage.fillBirthDetails(birth);
        birthServiceDataPage.buttons.clickEnd();

        applicationStatusPage.clickRefresh();

        String finalResultText = applicationStatusPage.getFinalSuccessText();

        Assertions.assertTrue(finalResultText.contains("отправлена на рассмотрение."),
                "Ошибка!");
    }

    @Test
    public void testDeathRegistrationE2E() {
        DeathData death = DeathData.builder()
                .deathDate("10,10,2025")
                .deathPlace("город Минск улица Казинца")
                .build();

        mainPage.clickLoginAsUser();
        userRegistrationPage.fillRegistrationData(dataUser);
        userRegistrationPage.buttons.clickNext();

        serviceSelectionPage.selectDeathRegistration();

        citizenDataPage.fillCitizenData(dataCitizen);
        citizenDataPage.buttons.clickNext();

        deathServiceDataPage.fillDeathDetails(death);
        deathServiceDataPage.buttons.clickEnd();

        applicationStatusPage.clickRefresh();

        String finalResultText = applicationStatusPage.getFinalSuccessText();

        Assertions.assertTrue(finalResultText.contains("отправлена на рассмотрение."),
                "Ошибка!");
    }
}
