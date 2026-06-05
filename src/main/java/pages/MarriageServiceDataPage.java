package pages;
import elements.NavigationButtons;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MarriageServiceDataPage {

    private WebDriver driver;

    public NavigationButtons buttons;

    public MarriageServiceDataPage(WebDriver driver){
        this.driver =driver;
        PageFactory.initElements(driver, this);
        this.buttons = new NavigationButtons(driver);
    }

    @FindBy(id="TextInputField-21")
    private WebElement registrationDateInput;

    @FindBy(id="TextInputField-22")
    private WebElement newLastNameInput;

    @FindBy(id="TextInputField-23")
    private WebElement spouseLastNameInput;

    @FindBy(id="TextInputField-24")
    private WebElement spouseFirstNameInput;

    @FindBy(id="TextInputField-25")
    private WebElement spouseMiddleNameInput;

    @FindBy(id="TextInputField-26")
    private WebElement spouseBirthDateInput;

    @FindBy(id="TextInputField-27")
    private WebElement spousePassportInput;

    public void fillMarriageDetails(String regDate, String newLastName, String spouseLastName,
                                    String spouseFirstName, String spouseMiddleName,
                                    String spouseBirthDate, String spousePassport) {
        registrationDateInput.sendKeys(regDate);
        newLastNameInput.sendKeys(newLastName);
        spouseLastNameInput.sendKeys(spouseLastName);
        spouseFirstNameInput.sendKeys(spouseFirstName);
        spouseMiddleNameInput.sendKeys(spouseMiddleName);
        spouseBirthDateInput.sendKeys(spouseBirthDate);
        spousePassportInput.sendKeys(spousePassport);
    }

}
