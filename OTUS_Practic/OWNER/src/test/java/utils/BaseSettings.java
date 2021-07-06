package utils;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class BaseSettings {
    protected static WebDriver driver;

    @BeforeClass
    public static void setUp() {
        driver = WebDriverFactory.createNewDriver(WebDriverType.GHROME);
        if (driver != null) {
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
            driver.manage().window().maximize();

        }

    }

    public static void refresh() {
        driver.navigate().refresh();
    }

    @AfterClass
    public static void setDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @After
    public void cleanUp() {
        driver.manage().deleteAllCookies();
    }


}
