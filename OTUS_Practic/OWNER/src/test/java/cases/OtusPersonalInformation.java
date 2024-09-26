package cases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import pages.LoginPage;
import pages.OtusPage;
import pages.PersonalPage;
import utils.BaseSettings;

public class OtusPersonalInformation extends BaseSettings {
    private final Logger logger = LogManager.getLogger(OtusPersonalInformation.class);
    String firstName = "Kseniya";
    String lastName = "Чаркина";
    String lastNameTwo = "Charkina";
    String date = "04.03.1993";
    String country = "Россия";
    String city = "Москва";
    String begginer = "Начальный уровень (Beginner)";
    String contactOne = "VK";
    String contactTwo = "Telegram";

    @Test
    public void testOtus() {
        LoginPage loginPage = new LoginPage(driver);
        logger.info("Драйвер поднят");
        loginPage.open()
                .authorization();
        logger.info("Авторизация прошла успешно");
        OtusPage otusPage = new OtusPage(driver);
        otusPage.enterLK();
        logger.info("Переход в личный кабинет");
        PersonalPage personalPage = new PersonalPage(driver);
        personalPage.fillInformation(firstName, lastName, lastNameTwo, date, country, city, begginer, contactOne, contactTwo);
        logger.info("Внесенные изменения сохранены");

        driver.manage().deleteAllCookies();

        logger.info("Драйвер поднят");
        loginPage.open()
                .authorization();
        logger.info("Авторизация прошла успешно");
        otusPage.enterLK();
        logger.info("Переход в личный кабинет");


        Assert.assertEquals(firstName, personalPage.actualfName);
        Assert.assertEquals(lastName, personalPage.actuallName);
        Assert.assertEquals(lastNameTwo, personalPage.actuallNameTwo);

        Assert.assertEquals(country, personalPage.actualCountry);
        Assert.assertEquals(city, personalPage.actualCity);
        Assert.assertEquals(begginer, personalPage.actualBeginner);

        Assert.assertEquals(contactOne, personalPage.actualContactOne);
        Assert.assertEquals(contactTwo, personalPage.actualContactTwo);

    }
}
