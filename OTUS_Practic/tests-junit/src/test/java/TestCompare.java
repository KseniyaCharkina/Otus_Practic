import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class TestCompare {
    private Logger logger = LogManager.getLogger(TestCompare.class);
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        logger.info("Драйвер поднят");
        wait = new WebDriverWait(driver, 20);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

    }

    @After
    public void end() {
        if (driver != null)
            driver.quit();
    }


    @Test
    public void testCompare() {

        String zubr;
        String makita;
        By buttonPowertools = By.xpath("//a[@title='Электроинструменты']");
        String urlPowertools = "https://www.220-volt.ru/catalog/2-0/";
        By buttonPerforators = By.xpath("//span[contains(text(),'Перфораторы')]");
        By makitas = By.xpath("//label[contains(text(),'MAKITA')]");
        By zubrs = By.xpath("//label[contains(text(),'ЗУБР')]");
        By buttonSelection = By.id("filterSubm");
        String urlSorted = "https://www.220-volt.ru/catalog/perforatory/?f=br__16,473#h1";
        By filterList = By.cssSelector("span.select2-selection__arrow");
        By minPriceFilter = By.cssSelector("span.listing-select-icon1");
        By firstZubr = By.xpath("//a[starts-with(text(),'Перфоратор ЗУБР')]/../preceding-sibling::div[@class='new-item-list-compare custom-ui-compare compare']");
        By firstMakita = By.xpath("//a[starts-with(text(),'Перфоратор MAKITA')]/../preceding-sibling::div[@class='new-item-list-compare custom-ui-compare compare']");
        By buttonNext = By.cssSelector("div.button.line.toCompare");
        By buttonCompare = By.xpath("//a[contains(text(),'Перейти к сравнению')]");
        By zubrExpected = By.xpath("//span[contains(text(),'Перфоратор ЗУБР')]");
        By makitaExpected = By.xpath("//span[contains(text(),'Перфоратор MAKITA')]");
        By zubrActual = By.xpath("//a[contains(text(),'ЗУБР')]");
        By makitaActual = By.xpath("//a[contains(text(),'MAKITA')]");

        driver.get("https://www.220-volt.ru/");
        logger.info("Открыт сайт для теста");

        driver.findElement(buttonPowertools).click();
        wait.until(ExpectedConditions.urlToBe(urlPowertools));
        logger.info("Переход в раздел электроинструменты");


        driver.findElement(buttonPerforators).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(makitas));
        logger.info("Переход в раздел перфораторы");

        //Выборка и сортировка
        driver.findElement(makitas).click();
        logger.info("Выбор макита");

        driver.findElement(zubrs).click();
        logger.info("Выбор зубр");

        driver.findElement(buttonSelection).click();
        logger.info("Подобрать");
        wait.until(ExpectedConditions.urlToBe(urlSorted));
        logger.info("Отсортировали");


        //Фильтр
        wait.until(ExpectedConditions.visibilityOfElementLocated(filterList)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(minPriceFilter)).click();
        logger.info("Отфильтровали");

        //Добавить в сравнения
        wait.until(ExpectedConditions.elementToBeClickable(firstZubr)).click();
        zubr = wait.until(ExpectedConditions.visibilityOfElementLocated(zubrExpected)).getText();
        logger.info("Добавили в сравнение перфоратор ZUBR MAKITA");

        driver.findElement(buttonNext).click();

        driver.findElement(firstMakita).click();
        makita = wait.until(ExpectedConditions.visibilityOfElementLocated(makitaExpected)).getText();
        logger.info("Добавили в сравнение перфоратор MAKITA");


        //Переход в раздел сравнений
        wait.until(ExpectedConditions.visibilityOfElementLocated(buttonCompare)).click();
        logger.info("Переход в раздел сравнений");

        //Проверка товара
        Assert.assertEquals(zubr, driver.findElement(zubrActual).getText());
        Assert.assertEquals(makita, driver.findElement(makitaActual).getText());
        logger.info("Проверка пройдена");

    }

}
