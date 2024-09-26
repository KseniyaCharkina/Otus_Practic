import config.ServerConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
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



public class HomeWorkLesson7 {
    private Logger logger = LogManager.getLogger(HomeWorkLesson7.class);
    protected WebDriver driver;
    private ServerConfig cfg = ConfigFactory.create(ServerConfig.class);

    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        logger.info("Драйвер поднят");

    }

   @After
    public void end() {
        if (driver != null)
            driver.quit();
    }




    @Test
    public void testOne()  {
        driver.get(cfg.url());
        logger.info("Открыт сайт Отус");
        //driver.manage().addCookie(new Cookie("sessionid","cb8zkqggefnp460muswkw7wuw5hzbmtm"));
        driver.findElement(By.xpath("//a[contains(text(),'Контакты')]")).click();
        new WebDriverWait(driver,3).until(ExpectedConditions.urlToBe("https://otus.ru/contacts"));
        logger.info("Переход в раздел Контакты");
        Assert.assertEquals("125167, г. Москва, Нарышкинская аллея., д. 5, стр. 2, тел. +7 499 938-92-02",driver.findElement(By.xpath("//div[contains(text(),'125167, г. Москва, Нарышкинская аллея., д. 5, стр. 2')]")).getText());
        driver.manage().window().maximize();
        Assert.assertEquals("Контакты | OTUS",driver.getTitle());
    }


    @Test
    public void testTwo(){
        driver.get("https://msk.tele2.ru/shop/number");
        new WebDriverWait(driver,3).until(ExpectedConditions.elementToBeClickable(By.id("searchNumber"))).sendKeys("97");
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("span.phone-number")));
        logger.info("Появилось "+ driver.findElements(By.cssSelector("span.phone-number")).size() +" номера.");

    }

    @Test
    public void testThree(){
        driver.get(cfg.url());
        logger.info("Открыт сайт Отус");
        driver.findElement(By.xpath("//a[contains(text(),'FAQ')]")).click();
        logger.info("Переход в раздел FAQ");
        driver.findElement(By.xpath("//div[contains(text(),'Где посмотреть программу')]")).click();
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Программу курса в сжатом виде можно увидеть')]")));
        logger.info("Текст появился");
        Assert.assertEquals("Программу курса в сжатом виде можно увидеть на странице курса после блока с преподавателями. Подробную программу курса можно скачать кликнув на “Скачать подробную программу курса”",driver.findElement(By.xpath("//div[contains(text(),'Программу курса в сжатом виде можно увидеть')]")).getText());
    }
    @Test
    public void testFour(){
        driver.get(cfg.url());
        logger.info("Открыт сайт Отус");
        driver.findElement(By.name("email")).sendKeys("yomeb23492@o3live.com");
        new WebDriverWait(driver,3).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Подписаться')]")));
        driver.findElement(By.xpath("//button[contains(text(),'Подписаться')]")).click();
        new WebDriverWait(driver,3).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(),'Вы успешно')]")));
        Assert.assertEquals("Вы успешно подписались",driver.findElement(By.xpath("//p[contains(text(),'Вы успешно')]")).getText());


    }



}
