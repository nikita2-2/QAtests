package pages;


import elements.NavigationButtons;
import elements.OrdersTable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdminDashboardPage {

    private WebDriver driver;
    public NavigationButtons buttons;
    public OrdersTable table;

    public AdminDashboardPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.buttons = new NavigationButtons(driver);
        this.table = new OrdersTable(driver);
    }

    @FindBy(xpath = "//button[@aria-label='Go to next page']")
    private WebElement nextPageArrow;

    public void goToPage(int pageNumber) {
        By pageLocator = By.xpath("//button[text()='" + pageNumber + "']");
        driver.findElement(pageLocator).click();
    }


}
