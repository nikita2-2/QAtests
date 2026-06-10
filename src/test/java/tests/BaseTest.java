package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;

public class BaseTest {
    protected static WebDriver driver;
    protected boolean isTestFailed = true;

    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://user:senlatest@regoffice.senla.eu/");
        isTestFailed = true;
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            ScreenshotUtil.captureScreenshotIfFailed(driver, isTestFailed);
            driver.quit();
        }
    }
}