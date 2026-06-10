package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
public class MainPage {
    private WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[contains(text(), 'Войти как пользователь')]")
    private WebElement loginAsUserBtn;

    @FindBy(xpath = "//button[contains(text(), 'Войти как администратор')]")
    private WebElement loginAsAdminBtn;

    @FindBy(xpath = "//button[contains(text(), 'Заказать справку')]")
    private WebElement orderCertificateBtn;

    public void clickLoginAsUser() {
        loginAsUserBtn.click();
    }

    public void clickLoginAsAdmin() {
        loginAsAdminBtn.click();
    }
}
