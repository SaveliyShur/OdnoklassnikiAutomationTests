package tests;

import bot.*;
import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.*;
import pages.factory.PeoplePageFactory;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class TestAddFriend extends BaseTests {
    public static WebDriver driver ;

    // TODO: 22.05.2020 Проверку видимости заявки в отдельных тестах
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
        PeoplePageInterface peoplePage = PeoplePageFactory.getPeoplePage(driver);
        Assert.assertTrue("Отправление запроса в друзья",peoplePage.addFriend().isFriendRequestSended());
    }

    @AfterAll
    public static void tearDown() throws InterruptedException {
        Bot bot2 = new TechoBot6();
        driver.get(bot2.getProfileUrl());
        PeoplePageInterface peoplePage = PeoplePageFactory.getPeoplePage(driver);
        peoplePage.removingFriendRequests();
        driver.quit();
    }
}
