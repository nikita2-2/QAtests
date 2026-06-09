package pages;

import elements.NavigationButtons;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class DeathServiceDataPage {
    private WebDriver driver;

    public NavigationButtons buttons;

    public DeathServiceDataPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.buttons = new NavigationButtons(driver);
    }

    @FindBy(xpath = "(//div[@role='dialog']//input)[1]")
    private WebElement deathDateInput;

    @FindBy(xpath = "(//div[@role='dialog']//input)[2]")
    private WebElement deathPlaceInput;

    public void fillDeathDetails(data.DeathData death) {
        deathDateInput.sendKeys(death.getDeathDate());
        deathPlaceInput.sendKeys(death.getDeathPlace());
    }
}
