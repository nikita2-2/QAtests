package pages;


import elements.NavigationButtons;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdminRegistrationPage {
    private final WebDriver driver;
    public NavigationButtons buttons;

    public AdminRegistrationPage(WebDriver driver) {
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
    private WebElement phoneInput;

    @FindBy(xpath = "(//div[@role='dialog']//input)[5]")
    private WebElement passportInput;

    @FindBy(xpath = "(//div[@role='dialog']//input)[6]")
    private WebElement birthDateInput;

    @Step("Заполнение данных админа")
    public void fillAdminRegistrationData(data.AdminData admin) {
        lastNameInput.sendKeys(admin.getLastName());
        firstNameInput.sendKeys(admin.getFirstName());
        middleNameInput.sendKeys(admin.getMiddleName());
        phoneInput.sendKeys(admin.getPhone());
        passportInput.sendKeys(admin.getPassport());
        birthDateInput.sendKeys(admin.getBirthDate());
    }
}
