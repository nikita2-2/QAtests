package elements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NavigationButtons {
    private final WebDriver driver;
    private static final Logger log = LoggerFactory.getLogger(NavigationButtons.class);

    public NavigationButtons(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//button[text()='Далее']")
    private WebElement nextBtn;

    @FindBy(xpath = "//button[text()='Назад']")
    private WebElement backBtn;

    @FindBy(xpath = "//button[text()='Закрыть']")
    private WebElement closeBtn;

    @FindBy(xpath = "//button[text()='Завершить']")
    private WebElement endBtn;

    public void clickNext(){
        nextBtn.click();
    }

    public void clickBack(){
        backBtn.click();
    }

    public void clickClose(){
        closeBtn.click();
    }

    public void clickEnd(){
        endBtn.click();
    }
}
