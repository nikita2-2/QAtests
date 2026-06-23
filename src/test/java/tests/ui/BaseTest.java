package tests.ui;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.ScreenshotUtil;

public class BaseTest {

    protected WebDriver driver;
    private static final Logger log = LoggerFactory.getLogger(BaseTest.class);
    protected boolean isTestFailed = true;

    public void initDriver(String browserName, String browserVersion) throws MalformedURLException {
        log.info("Инициализация браузера {} версии {}...", browserName, browserVersion);
        String mode = System.getenv().getOrDefault("execution_mode", "LOCAL");

        if ("SELENOID".equalsIgnoreCase(mode)) {
            log.info("Запуск в Docker-контейнере через Selenoid");
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setBrowserName(browserName);
            capabilities.setVersion(browserVersion);

            capabilities.setCapability("selenoid:options", java.util.Map.of(
                    "enableVNC", true, "screenResolution", "1920x1080x24"
            ));
            String selenoidHost = System.getenv().getOrDefault("SELENOID_HOST", "localhost");
            String selenoidUrl = "http://" + selenoidHost + ":4444/wd/hub";

            driver = new RemoteWebDriver(
                    new URL(selenoidUrl),
                    capabilities
            );
        } else {
            log.info("Запуск локально");
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://user:senlatest@regoffice.senla.eu/");
        isTestFailed = true;
    }

    public static java.util.stream.Stream<String> provideBrowsers() {
        String browsers = System.getenv("BROWSERS_LIST");
        if (browsers == null) {
            browsers = System.getProperty("browsers.list", "chrome:120.0");
        }
        return java.util.stream.Stream.of(browsers.split(","));
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            isTestFailed = false;
            ScreenshotUtil.captureScreenshotIfFailed(driver, isTestFailed);
            log.info("Закрытие браузера Chrome и завершение сессии.");
            driver.quit();
        }

    }
}