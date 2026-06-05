package pages;

import elements.NavigationButtons;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class ServiceSelectionPage {

    private WebDriver driver;

    public NavigationButtons buttons;

    public ServiceSelectionPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.buttons = new NavigationButtons(driver);
    }

    @FindBy(xpath = "//button[text()='Регистрация брака']")
    private WebElement marriageRegistrationBtn;

    @FindBy(xpath = "//button[text()='Регистрация рождения']")
    private WebElement birthRegistrationBtn;

    @FindBy(xpath = "//button[text()='Регистрация смерти']")
    private WebElement deathRegistrationBtn;


    public void selectMarriageRegistration() {
        marriageRegistrationBtn.click();
    }

    public void selectBirthRegistration() {
        birthRegistrationBtn.click();
    }

    public void selectDeathRegistration() {
        deathRegistrationBtn.click();
    }

}
