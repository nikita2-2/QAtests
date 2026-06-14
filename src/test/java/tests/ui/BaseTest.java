package tests.ui;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.ScreenshotUtil;

public class BaseTest {
    protected static WebDriver driver;
    private static final Logger log = LoggerFactory.getLogger(BaseTest.class);
    protected boolean isTestFailed = true;

    @BeforeEach
    public void setup() {
        log.info("Инициализация и запуск браузера Chrome...");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        log.info("Переход на главную страницу ЗАГС");
        driver.get("https://user:senlatest@regoffice.senla.eu/");
        isTestFailed = true;
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            ScreenshotUtil.captureScreenshotIfFailed(driver, isTestFailed);
            log.info("Закрытие браузера Chrome и завершение сессии.");
            driver.quit();
        }
    }
}