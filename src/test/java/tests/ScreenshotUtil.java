package tests;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.ByteArrayInputStream;

public class ScreenshotUtil {
    public static void captureScreenshotIfFailed(WebDriver driver, boolean isTestFailed) {
        if (isTestFailed && driver != null) {
            try {
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                Allure.addAttachment("Скриншот в момент падения", new ByteArrayInputStream(screenshot));
            } catch (Exception e) {
                System.out.println("Не удалось сделать скриншот " + e.getMessage());
            }
        }
    }
}
