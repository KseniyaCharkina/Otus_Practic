import config.ServerConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;



import java.util.concurrent.TimeUnit;

public class Two {
    private Logger logger = LogManager.getLogger(HomeWorkLesson7.class);
    protected WebDriver driver;
    private ServerConfig cfg = ConfigFactory.create(ServerConfig.class);

    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        logger.info("Драйвер поднят");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    }

    @After
    public void end() {
        if (driver != null)
            driver.quit();
    }

    public void auth() {
        String login = "kseniyacharkina@gmail.com";
        String password = "Alaundo123";
        String locator = "button.header2__auth";
        driver.findElement(By.cssSelector(locator)).click();
        driver.findElement(By.cssSelector("div.new-input-line_slim:nth-child(3) > input:nth-child(1)")).sendKeys(login);
        driver.findElement(By.cssSelector(".js-psw-input")).sendKeys(password);
        driver.findElement(By.cssSelector("div.new-input-line_last:nth-child(5) > button:nth-child(1)")).submit();
        logger.info("Авторизация прошла успешно");


    }

    private void enterContacts() {
        //driver.findElement(By.xpath("//a[@href='/contacts/']")).click();

        try {
            driver.findElement(By.xpath("//a[contains(text(),'Контакты')]")).click();
        } catch (StaleElementReferenceException e) {
            driver.findElement(By.xpath("//a[contains(text(),'Контакты')]")).click();
        }
        driver.get("https://otus.ru/contacts");
        logger.info("Переход в раздел Контакты");


    }


    @Test
    public void testOne() {
        //1.Открыть otus.ru
        driver.get(cfg.url());
        logger.info("Сайт открыт");

        //2.Авторизация
        auth();

        //3.Войти в раздел Контакты
        enterContacts();

        Assert.assertEquals("125167, г. Москва, Нарышкинская аллея., д. 5, стр. 2, тел. +7 499 938-92-02", driver.findElement(By.cssSelector("div.c0qfa0-1:nth-child(3) > div:nth-child(2)")).getText());
        //Assert.assertEquals("125167, г. Москва, Нарышкинская аллея., д. 5, стр. 2, тел. +7 499 938-92-02",driver.findElement(By.xpath("//div[contains(text(),'125167, г. Москва, Нарышкинская аллея., д. 5, стр. 2')]")).getText());


    }

}
