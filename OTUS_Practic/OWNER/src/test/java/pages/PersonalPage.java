package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class PersonalPage extends AbstractPage {

    WebElement firstName = driver.findElement(By.id("id_fname_latin"));
    WebElement lastName = driver.findElement(By.id("id_lname"));
    WebElement lastNameTwo = driver.findElement(By.id("id_lname_latin"));
    WebElement date = driver.findElement(By.cssSelector(".input-icon > input:nth-child(1)"));
    WebElement country = driver.findElement(By.cssSelector(".js-lk-cv-dependent-master > label:nth-child(1) > div:nth-child(2)"));
    WebElement city = driver.findElement(By.cssSelector(".js-lk-cv-dependent-slave-city > label:nth-child(1) > div:nth-child(2)"));
    WebElement english = driver.findElement(By.cssSelector("div.container__col_12:nth-child(3) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(3) > div:nth-child(2) > div:nth-child(1) > label:nth-child(1) > div:nth-child(2)"));
    WebElement add = driver.findElement(By.cssSelector("button.lk-cv-block__action.lk-cv-block__action_md-no-spacing.js-formset-add.js-lk-cv-custom-select-add"));
    WebElement contactOnePut = driver.findElement(By.id("id_contact-0-value"));


    public String actualfName;
    public String actuallName;
    public String actuallNameTwo;
    public String actualdate;
    public String actualCountry;
    public String actualCity;
    public String actualBeginner;
    public String actualContactOne;
    public String actualContactTwo;


    By russia = By.xpath("//*[contains(text(),'Россия')]");
    By moscow = By.xpath("//*[contains(text(),'Москва')]");
    By beginner = By.xpath("//*[contains(text(), 'Начальный уровень (Beginner)')]");
    By saveButton = By.xpath("//*[contains (text(),'Сохранить и продолжить')]");
    By contactby = By.id("id_contact-1-value");


    public PersonalPage(WebDriver driver) {
        super(driver);
    }

    public void fillInformation(String expectedFirstName, String expectedLastName, String expectedLastNameTwo, String expectedDate, String expectedCountry, String expectedCity, String expectedBegginer, String expectedContactOne, String expectedContactTwo) {
        clear();
        add.click();
        firstName.sendKeys(expectedFirstName);
        actualfName = firstName.getAttribute("value");

        lastName.sendKeys(expectedLastName);
        actuallName = lastName.getAttribute("value");

        lastNameTwo.sendKeys(expectedLastNameTwo);
        actuallNameTwo = lastNameTwo.getAttribute("value");

        date.sendKeys(expectedDate);
        actualdate = date.getAttribute("value");

        if (!country.getText().contains(expectedCountry)) {
            country.click();
            driver.findElement(russia).click();

        }

        if (!city.getText().contains(expectedCity)) {
            city.click();
            driver.findElement(moscow).click();

        }
        if (!english.getText().contains(expectedBegginer)) {
            english.click();
            driver.findElement(beginner).click();

        }
        contactOnePut.sendKeys(expectedContactOne);
        actualContactOne = contactOnePut.getAttribute("value");
        driver.findElement(contactby).sendKeys(expectedContactTwo);
        actualContactTwo = driver.findElement(contactby).getAttribute("value");


        actualCountry = country.getText();
        actualCity = city.getText();
        actualBeginner = english.getText();


        driver.findElement(saveButton).click();


    }


    private void clear() {
        firstName.clear();
        lastName.clear();
        lastNameTwo.clear();
        date.clear();
        contactOnePut.clear();

    }


}
