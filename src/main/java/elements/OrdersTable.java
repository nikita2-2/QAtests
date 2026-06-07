package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrdersTable {

    private WebDriver driver;

    public OrdersTable(WebDriver driver) {
        this.driver = driver;
    }

    public void approveOrderById(String orderId){

        String Buttonxpath = "(//td[text()='" + orderId + "']/..//button)[1]";
        driver.findElement(By.xpath(Buttonxpath)).click();

    }

    public void rejectOrderById(String orderId){

        String ButtonXpath = "(//td[text()='" + orderId + "']/..//button)[2]";
        driver.findElement(By.xpath(ButtonXpath)).click();
    }




}
