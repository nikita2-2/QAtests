package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UserRegistrationTest {

    private WebDriver driver;



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

    @FindBy(xpath = "//button[text()='Закрыть']")
    private WebElement closeBtn;

    @FindBy(xpath = "//button[text()='Далее']")
    private WebElement nextBtn;

    public UserRegistrationTest(WebDriver driver) {
        this.driver = driver;


    }


    public void fillRegistrationData(String lName, String fName, String mName, String phoneNum, String pass, String addr) {
        lastName.sendKeys(lName);
        firstName.sendKeys(fName);
        middleName.sendKeys(mName);
        phone.sendKeys(phoneNum);
        passport.sendKeys(pass);
        address.sendKeys(addr);
    }


    public void clickNext() {
        nextBtn.click();
    }

    public void clickClose() {
        closeBtn.click();
    }








}
