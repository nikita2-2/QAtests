package pages;

import elements.NavigationButtons;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BirthServiceDataPage {
    private WebDriver driver;

    public NavigationButtons buttons;

    public BirthServiceDataPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.buttons = new NavigationButtons(driver);
    }

    @FindBy(xpath = "(//div[@role='dialog']//input)[1]")
    private WebElement birthPlaceInput;

    @FindBy(xpath = "(//div[@role='dialog']//input)[2]")
    private WebElement motherInput;

    @FindBy(xpath = "(//div[@role='dialog']//input)[3]")
    private WebElement fatherInput;

    @FindBy(xpath = "(//div[@role='dialog']//input)[4]")
    private WebElement grandmotherInput;

    @FindBy(xpath = "(//div[@role='dialog']//input)[5]")
    private WebElement grandfatherInput;

    @Step("Заполнение данных услуги рождение")
    public void fillBirthDetails(data.BirthData birth) {
        birthPlaceInput.sendKeys(birth.getBirthPlace());
        motherInput.sendKeys(birth.getMotherName());
        fatherInput.sendKeys(birth.getFatherName());
        grandmotherInput.sendKeys(birth.getGrandmotherName());
        grandfatherInput.sendKeys(birth.getGrandfatherName());
    }

}
