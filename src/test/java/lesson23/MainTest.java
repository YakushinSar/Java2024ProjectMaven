package lesson23;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class MainTest {

    @Test
    public void testWaiter() throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
//        вейтеры лучше и правильнее использовать вместо слипов
//        Thread.sleep(2000);

        driver.get("https://www.selenium.dev/selenium/web/web-form.html");

        WebElement textInput = wait.until(ExpectedConditions.elementToBeClickable(By.name("my-text")));
        textInput.sendKeys("Selenium");

        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button")));
        submitButton.click();

        WebElement messageSubmit = wait.until(ExpectedConditions.elementToBeClickable(By.id("message")));

        Assert.assertEquals(messageSubmit.getText(), "Received!");

        driver.quit();
    }
}