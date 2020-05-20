package tests;

import bot.Bot;
import bot.OlegBot;
import bot.SaveliyBot;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.FriendsPage;
import pages.LoginPage;
import pages.PeoplePage;

import java.util.concurrent.TimeUnit;

public class TestAddFriend {

    public static WebDriver driver ;

    @BeforeAll
    public void before(){
        System.setProperty("webdriver.chrome.driver", "C:\\configs\\cromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(150, TimeUnit.SECONDS);
    }

    // TODO: 20.05.2020 comment test case 
    public void testAddFriend(){
        Bot oleg = new OlegBot(driver);
        Bot sava = new SaveliyBot(driver);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin(sava);

        FriendsPage savaFriendsPage = new FriendsPage(driver);

        driver.get(oleg.getProfileUrl());
        PeoplePage olegHomePage = new PeoplePage(driver, oleg);
        if (!olegHomePage.isFriend()) {
            olegHomePage.addFriend(sava);
        }

        Assert.assertTrue(olegHomePage.isFriendRequestSended());
    }
}
