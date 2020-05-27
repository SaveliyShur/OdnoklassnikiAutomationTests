package tests.friends;

import bot.Bot;
import bot.TechoBot5;
import bot.TechoBot6;
import com.aventstack.extentreports.Status;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.FriendsPage;
import pages.LoginPage;
import pages.PeoplePage;
import pages.ToolBar;
import tests.BaseTests;

/*
заходим на страницу к другу
Добавляем его
Логинимся на страницу друга
Принимаем заявку
Проверяем что он есть у нас в друзьях
Логинимся со страницы с которой отправляли заявку
Проверяем наличие друга в друзях
Удаляем друга
 */
public class TestAddFriends extends BaseTests {
    Bot bot1 = new TechoBot5();
    Bot bot2 = new TechoBot6();

    @BeforeTest
    public void before(){
        setDriver();
    }

    @Test(priority = 2)
    public void testAddFriend() throws InterruptedException {
        test = extent.createTest(this.getClass().getSimpleName());
        driver.get("https://ok.ru/");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin(bot1);


        driver.get(bot2.getProfileUrl());
        PeoplePage peoplePage = new PeoplePage(driver);
        FriendsPage friendsPage = peoplePage.addFriend()
                .getToolbar()
                .exit()
                .doLogin(bot2)
                .getToolbar()
                .clickToFriends();
        friendsPage.clickToFriendRequests()
                .checkFriendByURLOnFriendRequests(bot1.getId())
                .addFriend();
        friendsPage.clickToAllFriends()
                .getFriendsIcon(bot1.getId());
        LoginPage loginPage2 = friendsPage.getToolbar()
                .exit();
        loginPage2.doLogin(bot1)
                .getToolbar()
                .clickToFriends()
                .getFriendsIcon(bot2.getId());
    }

    @AfterClass
    public void deleteFriend(){
        getLoginPage().doLogin(bot1);
        driver.get(bot2.getProfileUrl());
        PeoplePage bot2Page = new PeoplePage(driver);
        if(bot2Page.isFriendRequestSended()){
            bot2Page.removingFriendRequests();
        } else if (bot2Page.isFriend()){
            bot2Page.getToolbar()
                    .clickToFriends()
                    .getFriendsIcon(bot2.getId())
                    .deleteFriend();
        }
        driver.quit();
        test.log(Status.DEBUG, "After метод успешно отработал");
    }
}
