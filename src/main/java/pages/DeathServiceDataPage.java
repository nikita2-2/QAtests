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

    @FindBy(id="TextInputField-61")
    private WebElement deathDateInput;

    @FindBy(id="TextInputField-62")
    private WebElement deathPlaceInput;

    public void fillDeathDetails(String deathDate, String deathPlace) {
        deathDateInput.sendKeys(deathDate);
        deathPlaceInput.sendKeys(deathPlace);
    }


}
