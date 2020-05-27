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

    Bot bot1 = new TechoBot5();
    Bot bot2 = new TechoBot6();

    @BeforeClass
    public void before(){
        setDriver();
    }

    @Test(priority = 1)
    public void testAddFriend() throws InterruptedException {
        test = extent.createTest(this.getClass().getSimpleName());

        test.log(Status.INFO, "Логинимся");
        LoginPage loginPage = getLoginPage();
        loginPage.doLogin(bot1);
        test.log(Status.INFO, "Залогинились");

        driver.get(bot2.getProfileUrl());
        test.log(Status.INFO, "Перешли на страницу бота2");
        PeoplePage peoplePage = new PeoplePage(driver);
        Assert.assertTrue(peoplePage.addFriend().isFriendRequestSended(), "Отправление запроса в друзья. Проверка на странице друга");
        test.log(Status.INFO, "Отправили запрос в друзья");
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
        driver.get(bot2.getProfileUrl());
        PeoplePage peoplePage = new PeoplePage(driver);
        peoplePage.removingFriendRequests();
        driver.quit();
        test.log(Status.DEBUG, "After метод успешно отработал");
    }
}
