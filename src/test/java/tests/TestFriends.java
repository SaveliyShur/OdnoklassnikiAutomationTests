package tests;

import bot.Bot;
import bot.SaveliyBot;
import org.junit.*;
import org.junit.Test;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.*;
import pages.Friends;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class TestFriends {


    public static WebDriver driver ;


    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\configs\\cromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(150, TimeUnit.SECONDS);
    }

    @Before
    public void afterAllTests(){
        Bot saveliy = new SaveliyBot(driver);
        saveliy.doLogin();
    }


    @Test
    @DisplayName("Тест поиска одного друга")
     public void test() throws InterruptedException {
        HomePage startHomePage = new HomePage(driver);
        Friends friends = startHomePage.getFriendsFromToolBar();
        friends.findFriend("Олег");
        Assert.assertEquals("Найден 1 друг", friends.checkFindFriend());
    }

    @Test
    public void test2() throws InterruptedException {
        HomePage startHomePage = new HomePage(driver);
        Friends friends = startHomePage.getFriendsFromToolBar();
        friends.getFriendsIcon("Олег Грабарь");
    }

    @After
    public void afterEveryTest() throws InterruptedException {
        driver.get("https://ok.ru/");
        HomePage startHomePage = new HomePage(driver);
        startHomePage.getToolbar().exit();
    }

    @AfterClass
    public static void tearDown(){
        driver.quit();
    }




}
