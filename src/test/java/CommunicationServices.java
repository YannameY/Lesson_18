import io.qameta.allure.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Epic("Тестирование веб-сайта MTS")
@Feature("Оплата услуг связи")
public class CommunicationServices {
    private static WebDriver driver;

    @BeforeAll
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.mts.by");
        closeCookieConsent();
    }

    /**
     * Закрываем всплывающее окно с куки
     */
    private static void closeCookieConsent() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement cookieButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cookie-agree")));
            cookieButton.click();
        } catch (Exception e) {

        }
    }

    /**
     * Вводит данные и отправляет форму через кнопку продолжить
     */
    @Test
    @Description("Тест отправки данных для оплаты услуг связи.")
    @Severity(SeverityLevel.NORMAL)
    public void testPay() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        driver.findElement(By.xpath("//span[text()='Услуги связи']")).click();
        driver.findElement(By.xpath("//p[text()='Услуги связи']")).click();
        driver.findElement(By.id("connection-phone")).sendKeys("297777777");
        driver.findElement(By.id("connection-sum")).sendKeys("100");
        driver.findElement(By.id("connection-email")).sendKeys("test@example.com");

        WebElement continueButton = driver.findElement(By.cssSelector("button.button__default"));
        js.executeScript("arguments[0].click();", continueButton);
    }

    /**
     * Тест для проверки элементов в окне платежа
     */
    @Test
    @Description("Тест проверки элементов в окне платежа.")
    @Severity(SeverityLevel.CRITICAL)
    public void testWindow() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='bepaid-iframe']")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("section.payment-page.payment-page_mobile.payment-page_pays")));

        String actualPhoneNumber = driver.findElement(By.xpath("//div[@class='pay-description__text']//span[text()]")).getText();
        String expectedPhoneNumber = "Оплата: Услуги связи Номер:375297777777";
        assert actualPhoneNumber.equals(expectedPhoneNumber) : "Номер телефона не соответствует!";

        String actualAmount = driver.findElement(By.xpath("//span[text()='100.00 BYN']")).getText();
        String expectedAmount = "100.00 BYN";
        assert actualAmount.equals(expectedAmount) : "Сумма не соответствует!";

        assert driver.findElement(By.xpath("//label[@class='ng-tns-c46-1 ng-star-inserted']")).getText().equals("Номер карты") : "Плейсхолдер для номера карты не соответствует!";
        assert driver.findElement(By.xpath("//img[@src='assets/images/payment-icons/card-types/visa-system.svg']")).isDisplayed() : "Иконка Visa не найдена!";
        assert driver.findElement(By.xpath("//img[@src='assets/images/payment-icons/card-types/mastercard-system.svg']")).isDisplayed() : "Иконка MasterCard не найдена!";
        assert driver.findElement(By.xpath("//img[@src='assets/images/payment-icons/card-types/belkart-system.svg']")).isDisplayed() : "Иконка belkart не найдена!";
        assert driver.findElement(By.xpath("//div[@class=\"cards-brands cards-brands_random ng-tns-c61-0 ng-star-inserted\"]")).isDisplayed() : "Иконка Мир не найдена!";
        assert driver.findElement(By.xpath("//div[@class=\"cards-brands cards-brands_random ng-tns-c61-0 ng-star-inserted\"]")).isDisplayed() : "Иконка Maestro не найдена!";

        assert driver.findElement(By.xpath("//label[@class='ng-tns-c46-4 ng-star-inserted']")).getText().equals("Срок действия") : "Плейсхолдер для срока действия не соответствует!";
        assert driver.findElement(By.xpath("//label[@class='ng-tns-c46-5 ng-star-inserted']")).getText().equals("CVC") : "Плейсхолдер для CVC не соответствует!";
        assert driver.findElement(By.xpath("//label[@class='ng-tns-c46-3 ng-star-inserted']")).getText().equals("Имя держателя (как на карте)") : "Плейсхолдер для имени держателя не соответствует!";

        String actualPayButtonText = driver.findElement(By.xpath("//button[text()=' Оплатить  100.00 BYN ']")).getText();
        String expectedPayButtonText = "Оплатить 100.00 BYN";
        assert actualPayButtonText.equals(expectedPayButtonText) : "Надпись на кнопке 'Оплатить' не соответствует!";

        driver.switchTo().defaultContent();
    }

    /**
     * Закрываем браузер
     */
    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
