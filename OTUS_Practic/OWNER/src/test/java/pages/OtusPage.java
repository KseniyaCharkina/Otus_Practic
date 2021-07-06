package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class OtusPage extends AbstractPage {
    By icon = By.cssSelector(".ic-blog-default-avatar");

    public OtusPage(WebDriver driver) {
        super(driver);
    }

    public void enterLK() {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(icon)).build().perform();
        driver.get("https://otus.ru/lk/biography/personal/");


    }


}
