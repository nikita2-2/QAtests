import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;



import java.time.Duration;
import org.junit.jupiter.api.Assertions;


public class TestWithoutAsserts {
    WebDriver driver;

    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @Test
    public void start() {
        driver.get("https://user:senlatest@regoffice.senla.eu/");

        //Main page
        driver.findElement(By.xpath("//button[text()='Войти как пользователь']")).click();


        // 1 step
        driver.findElement(By.xpath("//input[@placeholder='Введите фамилию (минимум 2 символа)']")).sendKeys("Ivanov");
        driver.findElement(By.xpath("//input[@placeholder='Введите имя (минимум 2 символа)']")).sendKeys("Ivan");
        driver.findElement(By.xpath("//input[contains(@placeholder, 'отчество')]")).sendKeys("Ivanich");
        driver.findElement(By.xpath("//input[@placeholder='Введите номер телефона (не более 11 символов)']")).sendKeys("79991234567");
        driver.findElement(By.xpath("//input[contains(@placeholder, 'паспорт')]")).sendKeys("АБ123456");
        driver.findElement(By.xpath("//input[contains(@placeholder, 'прописки')]")).sendKeys("Минск, улица Горького дом два");

        driver.findElement(By.xpath("//button[text()='Далее']")).click();
        System.out.println("Шаг 1 успешно заполнен и отправлен.");



        //2 step
        driver.findElement(By.xpath("//button[contains(text(), 'брака')]")).click();
        System.out.println("Шаг 2 успешно отправлен.");





        //3 step
        driver.findElement(By.id("TextInputField-7")).sendKeys("Ivan");
        driver.findElement(By.id("TextInputField-8")).sendKeys("IvanIvan");
        driver.findElement(By.id("TextInputField-9")).sendKeys("Ivanovich");
        driver.findElement(By.id("TextInputField-10")).sendKeys("12102000");
        driver.findElement(By.id("TextInputField-11")).sendKeys("АВ123456");
        driver.findElement(By.id("TextInputField-12")).sendKeys("Муж");
        driver.findElement(By.id("TextInputField-13")).sendKeys("Минск проспект Независимости");

        driver.findElement(By.xpath("//button[text()='Далее']")).click();
        System.out.println("Шаг 3 успешно заполнен и отправлен.");

        // 4 step
        driver.findElement(By.id("TextInputField-14")).sendKeys("12122030");
        driver.findElement(By.id("TextInputField-15")).sendKeys("Ivanova");
        driver.findElement(By.id("TextInputField-16")).sendKeys("Petrova");
        driver.findElement(By.id("TextInputField-17")).sendKeys("Maria");
        driver.findElement(By.id("TextInputField-18")).sendKeys("Ivanovna");
        driver.findElement(By.id("TextInputField-19")).sendKeys("12102000");
        driver.findElement(By.id("TextInputField-20")).sendKeys("АВ123436");


        driver.findElement(By.xpath("//button[contains(text(), 'Завершить')]")).click();
        System.out.println("Шаг 4 успешно заполнен и отправлен.");






        // 5 step


        By successSpan = By.xpath("//*[contains(text(), 'Спасибо')]");
        String expectedResult = "Спасибо за обращение!";



        String actualResult = driver.findElement(successSpan).getText();
        Assertions.assertTrue(actualResult.contains(expectedResult),"Заявка не создана!");
        System.out.println("ЛОГ: заявка успешно создана. Текст на экране: " + actualResult);











    }

    @AfterEach
    public void close(){
        if (driver != null){
            driver.quit();
        }
    }


}
