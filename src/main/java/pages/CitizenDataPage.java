package pages;

import elements.NavigationButtons;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class CitizenDataPage {
    private WebDriver driver;

    public NavigationButtons buttons;

    public CitizenDataPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.buttons = new NavigationButtons(driver);
    }


    @FindBy(xpath = "(//div[@role='dialog']//input)[1]")
    private WebElement lastNameInput;

    @FindBy(xpath = "(//div[@role='dialog']//input)[2]")
    private WebElement firstNameInput;

    @FindBy(xpath = "(//div[@role='dialog']//input)[3]")
    private WebElement middleNameInput;

    @FindBy(xpath = "(//div[@role='dialog']//input)[4]")
    private WebElement birthDateInput;

    @FindBy(xpath = "(//div[@role='dialog']//input)[5]")
    private WebElement passportInput;

    @FindBy(xpath = "(//div[@role='dialog']//input)[6]")
    private WebElement genderInput;

    @FindBy(xpath = "(//div[@role='dialog']//input)[7]")
    private WebElement addressInput;

    @Step("Заполнение данных гражданина")
    public void fillCitizenData(data.CitizenData citizen) {
        lastNameInput.sendKeys(citizen.getLastName());
        firstNameInput.sendKeys(citizen.getFirstName());
        middleNameInput.sendKeys(citizen.getMiddleName());
        birthDateInput.sendKeys(citizen.getBirthDate());
        passportInput.sendKeys(citizen.getPassport());
        genderInput.sendKeys(citizen.getGender());
        addressInput.sendKeys(citizen.getAddress());
    }
}
