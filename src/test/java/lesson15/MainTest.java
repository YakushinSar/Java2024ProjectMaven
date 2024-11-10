package lesson15;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class MainTest {

    @Test
    public void test() {
// создаем драйвер, браузер с которым будем работать, тут хром
        WebDriver driver = new ChromeDriver();
        // устанавливаем размер окна браузера
//        driver.manage().window().maximize();
//переходим на страницу методом get
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");
//выставляем ожидания
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
//взаимодействуем с элементами
        WebElement textBox = driver.findElement(By.name("my-text"));
        WebElement submitButton = driver.findElement(By.cssSelector("button"));

        textBox.sendKeys("Selenium");
        submitButton.click();

        WebElement message = driver.findElement(By.id("message"));
        Assert.assertEquals(message.getText(), "Received!");
//закрытие браузера, полностью завершаются все процессы
        driver.quit();

/*
запускается перед новым тестом

    @BeforeMethod
    public void baseUrl() {
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");
        driver.manage().window().maximize();

закрывает тест
    @AfterTest
        public void closeBrowser() {
            driver.quit();
*/

    }
}
