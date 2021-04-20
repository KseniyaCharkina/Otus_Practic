import config.ServerConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;




public class TestTitle {
    private Logger logger = LogManager.getLogger(TestTitle.class);
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
    public void testTitle(){
        driver.get(cfg.url());
        logger.info("Site OTUS open");
        String lineTitle = driver.getTitle();
        Assert.assertEquals("Онлайн‑курсы для профессионалов, дистанционное обучение современным профессиям", lineTitle);
        logger.info("Title good");

    }


}
