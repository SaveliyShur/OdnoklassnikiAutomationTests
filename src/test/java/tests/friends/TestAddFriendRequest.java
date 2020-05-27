package tests.friends;

import bot.*;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.FriendsPage;
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

    private final Bot bot1 = new TechoBot5();
    private final Bot bot2 = new TechoBot6();


    @Test(priority = 1)
    public void testAddFriend() throws InterruptedException {
        LoginPage loginPage = getLoginPage();
        loginPage.doLogin(bot1);
        test.log(Status.DEBUG, "Логин bot1");

        driver.get(bot2.getProfileUrl());
        test.log(Status.DEBUG, "Переход на страницу bot2");
        PeoplePage peoplePage = new PeoplePage(driver);
        Assert.assertTrue(peoplePage.addFriend().isFriendRequestSended(), "Отправление запроса в друзья. Проверка на странице друга");
        test.log(Status.DEBUG, "Отправление запроса в друзья от bot1 к bot2");
        FriendsPage friendsPage = peoplePage.getToolbar()
                .clickToFriends();
        friendsPage.clickToOutGoingFriendRequests()
                .checkFriendByURLOnFriendRequests(bot2.getId());
        LoginPage loginPage1 = friendsPage.getToolbar()
                .exit();
        FriendsPage friendsPage2 = loginPage1.doLogin(bot2)
                .getToolbar()
                .clickToFriends()
                .clickToFriendRequests();
        friendsPage2.checkFriendByURLOnFriendRequests(bot1.getId());
        friendsPage2.getToolbar()
                .exit();
    }

    @AfterClass
    public void after() throws InterruptedException {
        getLoginPage().doLogin(bot1);
        test.log(Status.DEBUG, "Логин bot1");
        driver.get(bot2.getProfileUrl());
        test.log(Status.DEBUG, "Переход на страницу bot2");
        PeoplePage peoplePage = new PeoplePage(driver);
        peoplePage.removingFriendRequests();
        test.log(Status.DEBUG, "Удаление запроса на добавление в друзья");
        driver.quit();
        test.log(Status.DEBUG, "After метод успешно отработал");
    }
}
