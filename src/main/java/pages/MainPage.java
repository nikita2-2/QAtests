package pages;

import io.qameta.allure.Step;
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

    @Step("Вход как пользователь")
    public void clickLoginAsUser() {
        loginAsUserBtn.click();
    }

    @Step("Вход как админ")
    public void clickLoginAsAdmin() {
        loginAsAdminBtn.click();
    }
}
