package tests.friends;

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
import tests.BaseTests;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;


/*
Тест переходит на страницу друга
Добавляет друга в друзья
Проверяет надпись что заявка отправлена
Переходит во вкладку друзья
Переходит в исходящие заявки в друзья
Проверяет наличие заявки в друзья
Переходит на страницу к человеку которому отправили заявку
Проверяет видимость заявки
Переходит обратно на страницу к себе
Удаляет заявку
*/

public class TestAddFriendRequest extends BaseTests {

    @BeforeTest
    public void before(){
        setDriver();
    }
    @Test
    public void testAddFriend() throws InterruptedException {
        test = extent.createTest(this.getClass().getSimpleName());

        Bot bot1 = new TechoBot5();
        Bot bot2 = new TechoBot6();

        driver.get("https://ok.ru/");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin(bot1);

        driver.get(bot2.getProfileUrl());
        PeoplePage peoplePage = new PeoplePage(driver);
        Assert.assertTrue(peoplePage.addFriend().isFriendRequestSended(), "Отправление запроса в друзья. Проверка на странице друга");
        peoplePage.getToolbar()
                .clickToFriends()
                .clickToOutGoingFriendRequests()
                .checkFriendByURLOnOutGoingFriendRequests(bot2.getId());
        sleep(3000);

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
