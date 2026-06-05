package pages;


import elements.NavigationButtons;
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

    @FindBy(id="TextInputField-42")
    private WebElement birthPlaceInput;

    @FindBy(id="TextInputField-43")
    private WebElement motherInput;

    @FindBy(id="TextInputField-44")
    private WebElement fatherInput;

    @FindBy(id="TextInputField-45")
    private WebElement grandmotherInput;

    @FindBy(id="TextInputField-46")
    private WebElement grandfatherInput;


    public void fillBirthDetails(String birthPlace, String mother, String father,
                                 String grandmother, String grandfather) {
        birthPlaceInput.sendKeys(birthPlace);
        motherInput.sendKeys(mother);
        fatherInput.sendKeys(father);
        grandmotherInput.sendKeys(grandmother);
        grandfatherInput.sendKeys(grandfather);
    }

}
