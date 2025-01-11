package lesson24;

import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.stream.Collectors;

public class SelectTest {

    //объявление драйвера,веитера
    WebDriver driver = new ChromeDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    //метод для экшенов
    private Actions actions;

    private Actions getActions() {
        if (actions == null) {
            actions = new Actions(driver);
        }
        return actions;
    }

    void goToForm() {
        driver.findElement(By.linkText("web-form.html")).click();
    }

    void goToDragAndDrop() {
        driver.findElement(By.linkText("dragAndDropTest.html")).click();
    }


    @Test
    //работа с единичным выбором
    public void testSelect() throws InterruptedException {

        driver.get("https://www.selenium.dev/selenium/web/selectPage");

        WebElement selectWithoutMultiple = wait.until(ExpectedConditions.elementToBeClickable(By.id("selectWithoutMultiple")));
        Select sinpleDropDown = new Select(selectWithoutMultiple);
        Thread.sleep(1000);
        //в селекте выбираем позицию 2, то есть ключ в селекте
        sinpleDropDown.selectByValue("two");

        String newValue = selectWithoutMultiple.getAttribute("value");
        System.out.println(newValue);
        //проверяем что значение в селекте изменилось с 1 на 2
        Assert.assertEquals("two", newValue);
        Thread.sleep(3000);

        driver.quit();
    }

    //работа с множественным выбором
    @Test
    public void testSelectMultiple() throws InterruptedException {

        driver.get("https://www.selenium.dev/selenium/web/selectPage");

        WebElement selectElement = wait.until(ExpectedConditions.elementToBeClickable(By.id("selectWithMultipleEqualsMultiple")));
        Select multiSelect = new Select(selectElement);
        multiSelect.selectByIndex(2);
        Thread.sleep(1000);
        multiSelect.selectByIndex(0);
        Thread.sleep(1000);
        multiSelect.deselectByVisibleText("Cheddar");
        Thread.sleep(1000);

        System.out.println(selectElement.getAttribute("value"));
        System.out.println(multiSelect.getAllSelectedOptions().stream().map(WebElement::getText).collect(Collectors.toList()));
        Thread.sleep(2000);

        driver.quit();
    }

    //работа со списком со скроллом
    @Test
    public void testLongList() throws InterruptedException {

        driver.get("https://www.selenium.dev/selenium/web/selectPage");

        WebElement selectElement = wait.until(ExpectedConditions.elementToBeClickable(By.id("selectWithMultipleLongList")));
        Select select = new Select(selectElement);
        select.deselectByVisibleText("five");
        select.deselectByVisibleText("six");

        System.out.println(selectElement.getAttribute("value"));
        System.out.println(select.getAllSelectedOptions().stream().map(WebElement::getText).collect(Collectors.toList()));
        Thread.sleep(2000);

        driver.quit();
    }

    //работа с ползунком
    @Test
    public void testRangeSelect() throws InterruptedException {

        driver.get("https://www.selenium.dev/selenium/web/web-form.html");

        WebElement slider = wait.until(ExpectedConditions.elementToBeClickable(By.name("my-range")));
        Thread.sleep(1000);
        slider.sendKeys(Keys.LEFT, Keys.LEFT);
        Thread.sleep(1000);
        slider.sendKeys(Keys.LEFT, Keys.LEFT);
//        значение ползунка после передвижение влево с 5 позиции стало = 1
        Assert.assertEquals("1", slider.getAttribute("value"));
        Thread.sleep(3000);

        driver.quit();
    }

    //    экшены, действия с мышью
    @Test
    public void testSliderAction() throws InterruptedException {

        driver.get("https://www.selenium.dev/selenium/web/web-form.html");

        WebElement slider = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='my-range']")));
        Thread.sleep(1000);
        getActions()
                .clickAndHold(slider) //Actions
                .moveByOffset(-50, 0) //Actions
                .release() //Actions
                .perform();// метод который запускает все Actions которые выбраны выше, без перформ акшены не запустятся!!!

        System.out.println(slider.getAttribute("value"));
        Assert.assertEquals("2", slider.getAttribute("value"));
        Thread.sleep(3000);

        driver.quit();
    }

    //напечатать в textarea с нажатым ШИФТ
    @Test
    public void testKeysActions() throws InterruptedException {
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");

        final WebElement textarea = wait.until(ExpectedConditions.elementToBeClickable(By.name("my-textarea")));
        getActions().click(textarea)
                .keyDown(Keys.SHIFT) //нажали шифт
                .sendKeys("adfK")
                .keyUp(Keys.SHIFT) //отпустили шифт
                .perform();

        Assert.assertEquals("ADFK", textarea.getAttribute("value"));
        Thread.sleep(3000);

        driver.quit();
    }

    //драг анд дроп
    @Test
    void testDragAndDrop() throws Exception {
        driver.get("https://www.selenium.dev/selenium/web/dragAndDropTest.html");
        WebElement test1 = wait.until(ExpectedConditions.elementToBeClickable(By.id("test1")));
        getActions().clickAndHold(test1)
                .moveByOffset(250, 50)
                .pause(2000)
                .moveByOffset(100, 100)
                .release() //отпустили мышь
                .perform();
        Thread.sleep(3000);

        driver.quit();
    }

    // облегченный вариант драг анд дроп  с помощью dragAndDropBy, то есть можно сразу схватить и переместить
    @Test
    void testDragAndDropBy() throws Exception {

        driver.get("https://www.selenium.dev/selenium/web/dragAndDropTest.html");

        WebElement test1 = wait.until(ExpectedConditions.elementToBeClickable(By.id("test1")));
        getActions()
                .dragAndDropBy(test1, 200, 400)
                .perform();
        Thread.sleep(3000);

        driver.quit();
    }

    // перетаскивание ко второму выбранному элементу
    @Test
    void testDragAndDropToElement() throws Exception {

        driver.get("https://www.selenium.dev/selenium/web/dragAndDropTest.html");

        WebElement test1 = wait.until(ExpectedConditions.elementToBeClickable(By.id("test1")));
        System.out.println(test1.getLocation());

        WebElement test4 = wait.until(ExpectedConditions.elementToBeClickable(By.id("test4")));
        System.out.println(test4.getLocation());
        getActions()
                .dragAndDrop(test1, test4)
                .perform();
        System.out.println(test1.getLocation());
        Assert.assertEquals(test4.getLocation(), test1.getLocation());
        Thread.sleep(3000);

        driver.quit();
    }

    //выбор цвета, находим поле,табом перехожим на цвет и выбираем (шифт+таб это двигаться в обратном направлении)
    @Test
    void testColorPicker() throws Exception {

        driver.get("https://www.selenium.dev/selenium/web/web-form.html");

        final WebElement colorPicker = driver.findElement(By.name("my-colors"));
        System.out.println(colorPicker.getAttribute("value"));

        colorPicker.click();
        getActions()
                .sendKeys(Keys.TAB,Keys.TAB,Keys.TAB)
                .sendKeys("111")
                .sendKeys(Keys.TAB)
                .pause(500)
                .sendKeys("111")
                .sendKeys(Keys.TAB)
                .pause(500)
                .sendKeys("111")
                .pause(500)
                .sendKeys(Keys.ENTER)
                .pause(500)
                .perform();

        final String color = colorPicker.getAttribute("value");
        System.out.println(color);
        Assert.assertEquals("#6f6f7c", color);

        driver.quit();

    }
}


