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

    @FindBy(xpath = "(//div[@role='dialog']//input)[1]")
    private WebElement registrationDateInput;

    @FindBy(xpath = "(//div[@role='dialog']//input)[2]")
    private WebElement newLastNameInput;

    @FindBy(xpath = "(//div[@role='dialog']//input)[3]")
    private WebElement spouseLastNameInput;

    @FindBy(xpath = "(//div[@role='dialog']//input)[4]")
    private WebElement spouseFirstNameInput;

    @FindBy(xpath = "(//div[@role='dialog']//input)[5]")
    private WebElement spouseMiddleNameInput;

    @FindBy(xpath = "(//div[@role='dialog']//input)[6]")
    private WebElement spouseBirthDateInput;

    @FindBy(xpath = "(//div[@role='dialog']//input)[7]")
    private WebElement spousePassportInput;

    public void fillMarriageDetails(data.MarriageData marriage) {
        registrationDateInput.sendKeys(marriage.getRegDate());
        newLastNameInput.sendKeys(marriage.getNewLastName());
        spouseLastNameInput.sendKeys(marriage.getSpouseLastName());
        spouseFirstNameInput.sendKeys(marriage.getSpouseFirstName());
        spouseMiddleNameInput.sendKeys(marriage.getSpouseMiddleName());
        spouseBirthDateInput.sendKeys(marriage.getSpouseBirthDate());
        spousePassportInput.sendKeys(marriage.getSpousePassport());
    }

}
