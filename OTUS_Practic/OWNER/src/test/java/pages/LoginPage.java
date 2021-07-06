package pages;

import config.ServerConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends AbstractPage {
    private ServerConfig cfg = ConfigFactory.create(ServerConfig.class);
    By button = By.xpath("//button[contains(text(),'Вход')]");
    By inputLogin = By.cssSelector("div.new-input-line_slim:nth-child(3) > input:nth-child(1)");
    By inputPassword = By.cssSelector(".js-psw-input");
    By authorizationButton = By.cssSelector("div.new-input-line_last:nth-child(5) > button:nth-child(1)");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage open() {
        driver.get(cfg.url());
        return this;
    }

    public void authorization() {
        driver.findElement(button).click();
        driver.findElement(inputLogin).sendKeys(cfg.login());
        driver.findElement(inputPassword).sendKeys(cfg.password());
        driver.findElement(authorizationButton).submit();
    }
}
