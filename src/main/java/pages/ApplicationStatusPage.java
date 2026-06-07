package pages;

import elements.NavigationButtons;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ApplicationStatusPage {

    private WebDriver driver;

    public NavigationButtons buttons;

    public ApplicationStatusPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.buttons = new NavigationButtons(driver);
    }

    @FindBy(xpath = "//button[contains(text(), 'Обновить')]")
    private WebElement refreshBtn;

    @FindBy(xpath = "//button[contains(text(), 'Создать новую заявку')]")
    private WebElement createNewOrderBtn;

    private By successSpan = By.xpath("//span[contains(text(), 'отправлена на рассмотрение')]");

    public void clickRefresh() {
        refreshBtn.click();
    }

    public void clickCreateNewOrder() {
        createNewOrderBtn.click();
    }

    public String getFinalSuccessText() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.not(
                ExpectedConditions.textToBePresentInElementLocated(successSpan, "null")
        ));

        return driver.findElement(successSpan).getText();
    }


}
