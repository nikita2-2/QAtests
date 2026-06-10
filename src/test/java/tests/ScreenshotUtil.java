package tests;

import io.qameta.allure.Allure;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.ByteArrayInputStream;

@Slf4j
public class ScreenshotUtil {
    public static void captureScreenshotIfFailed(WebDriver driver, boolean isTestFailed) {
        if (isTestFailed && driver != null) {
            try {
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                Allure.addAttachment("Скриншот в момент падения", new ByteArrayInputStream(screenshot));
                log.info("Скриншот момента упавшего теста сделан и и добавлен в Allure");
            } catch (Exception e) {
                log.info("Не удалось сделать скрин упавшего теста ");
            }
        }
    }
}
