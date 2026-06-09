package pages;

import elements.NavigationButtons;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UserRegistrationPage {
    private WebDriver driver;

    public NavigationButtons buttons;

    public UserRegistrationPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver, this);
        this.buttons = new NavigationButtons(driver);
    }

    @FindBy(xpath = "//input[contains(@placeholder, 'фамилию')]")
    private WebElement lastName;

    @FindBy(xpath = "//input[contains(@placeholder, 'имя')]")
    private WebElement firstName;

    @FindBy(xpath = "//input[contains(@placeholder, 'отчество')]")
    private WebElement middleName;

    @FindBy(xpath = "//input[contains(@placeholder, 'телефона')]")
    private WebElement phone;

    @FindBy(xpath = "//input[contains(@placeholder, 'паспорта')]")
    private WebElement passport;

    @FindBy(xpath = "//input[contains(@placeholder, 'прописки')]")
    private WebElement address;


    public void fillRegistrationData(data.UserData user) {
        lastName.sendKeys(user.getLastName());
        firstName.sendKeys(user.getFirstName());
        middleName.sendKeys(user.getMiddleName());
        phone.sendKeys(user.getPhone());
        passport.sendKeys(user.getPassport());
        address.sendKeys(user.getAddress());
    }
}
