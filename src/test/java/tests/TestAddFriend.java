package tests;

import bot.*;
import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.FriendsPage;
import pages.HomePage;
import pages.LoginPage;
import pages.PeoplePage;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class TestAddFriend extends BaseTests {
    public static WebDriver driver ;

    @BeforeAll
    public static void before(){
        System.setProperty("webdriver.chrome.driver", "C:\\configs\\cromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(TIME_WAIT, TimeUnit.SECONDS);
    }

    @Test
    public void testAddFriend() throws InterruptedException {
        Bot bot1 = new TechoBot5();
        Bot bot2 = new TechoBot6();

        driver.get("https://ok.ru/");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin(bot1);

        driver.get(bot2.getProfileUrl());
        PeoplePage peoplePage = new PeoplePage(driver);
        Assert.assertTrue("Отправление запроса в друзья",peoplePage.addFriend().isFriendRequestSended());
        sleep(2000);
    }

    @AfterAll
    public static void tearDown() throws InterruptedException {
        Bot bot2 = new TechoBot6();
        driver.get(bot2.getProfileUrl());
        PeoplePage peoplePage = new PeoplePage(driver);
        peoplePage.removingFriendRequests();
        driver.quit();
    }
}
