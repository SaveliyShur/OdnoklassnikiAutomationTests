package tests;

import bot.Bot;
import bot.TechoBot5;
import bot.TechoBot6;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import pages.LoginPage;


public class TestFailed extends BaseTest {

    private final Bot bot1 = new TechoBot5();

    @Test
    public void failedTest() throws InterruptedException {
        driver.get("https://ok.ru/");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin(bot1);
        test.log(Status.DEBUG, "Логин bot1");

        Assert.assertTrue(false, "Упс)");
    }

    @AfterClass
    public void after() {
        driver.quit();
        test.log(Status.DEBUG, "After метод успешно отработал.");
    }
}

