package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrdersTable {
    private final WebDriver driver;
    public String orderId;
    private By approveButtonLocator(String orderId) {
        return By.xpath("//td[text()='"+ orderId + "']/..//button[.//div/*[name()='svg' and @data-testid='ThumbUpIcon']]");
    }
    private By rejectButtonLocator(String orderId) {
        return By.xpath("//td[text()='"+ orderId + "']/..//button[.//div/*[name()='svg' and @data-testid='ThumbDownIcon']]");
    }
    public OrdersTable(WebDriver driver) {
        this.driver = driver;
    }
    public void approveOrderById(String orderId){
        driver.findElement(approveButtonLocator(orderId)).click();
    }
    public void rejectOrderById(String orderId){
        driver.findElement(rejectButtonLocator(orderId)).click();
    }
}
