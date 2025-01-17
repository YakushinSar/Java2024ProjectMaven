package lesson26;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.AssertJUnit.fail;

public class BaseTest {

    WebDriver driver = new ChromeDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    @Test
        //проверка соответствия текста в алерте
    void testAlertText() throws InterruptedException {

        driver.get("https://www.selenium.dev/selenium/web/alerts.html");

        driver.findElement(By.id("alert")).click();
        //переключаемся на алерт
        final Alert alert = driver.switchTo().alert();
        //находим текст в алерте
        String alertText = alert.getText();
        Assert.assertEquals("cheese",alertText);
        Thread.sleep(3000);

        driver.quit();
    }

    //проверка соответствия текста в алерте с ожиданием когда появится он на странице
    @Test
    void testSlowAlert() throws InterruptedException {

        driver.get("https://www.selenium.dev/selenium/web/alerts.html");
        driver.findElement(By.id("slow-alert")).click();
        wait.until(ExpectedConditions.alertIsPresent());
        String alertText = driver.switchTo().alert().getText();
        Assert.assertEquals("Slow",alertText);
        Thread.sleep(3000);

        driver.quit();
    }

    //проверка соответствия текста в алерте с ожиданием когда появится он на странице
    @Test
    void testClickToAlert() throws InterruptedException {

        driver.get("https://www.selenium.dev/selenium/web/alerts.html");

        driver.findElement(By.id("slow-alert")).click();
        //ждем появления алерта
        wait.until(ExpectedConditions.alertIsPresent());
        //используем класс Алерт драйвера
        Alert alert = driver.switchTo().alert();
        alert.accept(); //нажимаем на кнопку алерта
        try {
            driver.switchTo().alert();
            fail("Алерт на странице есть, тест не пройден");
        } catch (NoAlertPresentException e) {
            //pass, проглатываем ошибку и идем далее
        }
        Thread.sleep(3000);

        driver.quit();
    }

    //алерт с выбором нажать/ненажать
    @Test
    void testAcceptAlert() throws InterruptedException {

        driver.get("https://www.selenium.dev/selenium/web/alerts.html");

        driver.findElement(By.id("confirm")).click();
        Thread.sleep(1000);

        Alert alert = driver.switchTo().alert();
        alert.accept(); //нажимаем на кнопку алерта
        //элемент на страницу куда мы перейдем после клика
        String text = driver.findElement(By.tagName("h1")).getText();
        //проверяем что перешли на другую страницу
        Assert.assertEquals("Heading", text);
        Thread.sleep(2000);

        driver.quit();
    }

    //алерт с возможностью ввода текста
    @Test
    void testAcceptPromt() throws InterruptedException {

        driver.get("https://www.selenium.dev/selenium/web/alerts.html");

        driver.findElement(By.id("prompt")).click();
        Thread.sleep(1000);

        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String enteredText = "я ввел этот текст в поле ввода";
        alert.sendKeys(enteredText); //вводим текст в поле ввода
        Thread.sleep(2000);
        alert.accept();

        //проверяем что текст из поля ввода соответствует ожидаемому
        String actualText = driver.findElement(By.xpath("//*[@id='text']/p")).getText();
        Assert.assertEquals(enteredText, actualText);
        Thread.sleep(3000);

        driver.quit();
    }
}
