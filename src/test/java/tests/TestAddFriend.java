package tests;

import bot.*;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.PeoplePage;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class TestAddFriend extends BaseTests {
    public static WebDriver driver ;

    @BeforeTest
    public void before(){
        //todo Поменяй местоположении драйвера
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Grabar\\Desktop\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(TIME_WAIT, TimeUnit.SECONDS);
    }

    @Test
    public void testAddFriend() throws InterruptedException {
        test = extent.createTest(this.getClass().getSimpleName()); // вот эта штука умеет логировать

        Bot bot1 = new TechoBot5();
        Bot bot2 = new TechoBot6();

        driver.get("https://ok.ru/");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin(bot1);

        driver.get(bot2.getProfileUrl());
        test.log(Status.INFO, "И это работает"); // вот так это делается. Есть еще дебаг и еррор информация
        PeoplePage peoplePage = new PeoplePage(driver);
        Assert.assertTrue(peoplePage.addFriend().isFriendRequestSended(), "Отправление запроса в друзья");
        sleep(2000);
    }

    @AfterTest
    public void after() throws InterruptedException {
        Bot bot2 = new TechoBot6();
        driver.get(bot2.getProfileUrl());
        PeoplePage peoplePage = new PeoplePage(driver);
        peoplePage.removingFriendRequests();
        driver.quit();
    }
}
