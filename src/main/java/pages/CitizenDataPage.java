package pages;

import elements.NavigationButtons;
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


    @FindBy(id="TextInputField-14")
    private WebElement lastNameInput;

    @FindBy(id="TextInputField-15")
    private WebElement firstNameInput;

    @FindBy(id="TextInputField-16")
    private WebElement middleNameInput;

    @FindBy(id="TextInputField-17")
    private WebElement birthDateInput;

    @FindBy(id="TextInputField-18")
    private WebElement passportInput;

    @FindBy(id="TextInputField-19")
    private WebElement genderInput;

    @FindBy(id="TextInputField-20")
    private WebElement addressInput;

    public void fillCitizenData(String lastName, String firstName, String middleName,
                                String birthDate, String passport, String gender, String address) {
        lastNameInput.sendKeys(lastName);
        firstNameInput.sendKeys(firstName);
        middleNameInput.sendKeys(middleName);
        birthDateInput.sendKeys(birthDate);
        passportInput.sendKeys(passport);
        genderInput.sendKeys(gender);
        addressInput.sendKeys(address);
    }


}
