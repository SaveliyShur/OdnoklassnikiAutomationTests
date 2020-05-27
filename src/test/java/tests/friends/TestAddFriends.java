package tests.friends;

import bot.Bot;
import bot.TechoBot5;
import bot.TechoBot6;
import com.aventstack.extentreports.Status;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import pages.FriendsPage;
import pages.LoginPage;
import pages.PeoplePage;
import tests.BaseTest;

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
public class TestAddFriends extends BaseTest {
    private final Bot bot1 = new TechoBot5();
    private final Bot bot2 = new TechoBot6();


    @Test(priority = 2)
    public void testAddFriend() throws InterruptedException {
        driver.get("https://ok.ru/");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin(bot1);
        test.log(Status.DEBUG, "Логин bot1");

        driver.get(bot2.getProfileUrl());
        test.log(Status.DEBUG, "Переход на страницу bot2");
        PeoplePage peoplePage = new PeoplePage(driver);
        peoplePage.addFriend()
                .getToolbar()
                .exit();
        test.log(Status.DEBUG, "Логаут bot1");
        FriendsPage friendsPage = loginPage.doLogin(bot2)
                .getToolbar()
                .clickToFriends();
        test.log(Status.DEBUG, "Логин bot2 и переход во вкладку ДРУЗЬЯ");
        friendsPage.clickToFriendRequests()
                .checkFriendByURLOnFriendRequests(bot1.getId())
                .addFriend();
        test.log(Status.DEBUG, "Подтверждение запроса в друзья от bot1 к bot2");
        friendsPage.clickToAllFriends()
                .getFriendsIcon(bot1.getId());
        LoginPage loginPage2 = friendsPage.getToolbar()
                .exit();
        test.log(Status.DEBUG, "Логаут bot2. Уже устал писать слишком подробные логи");
        loginPage2.doLogin(bot1)
                .getToolbar()
                .clickToFriends()
                .getFriendsIcon(bot2.getId());
        test.log(Status.DEBUG, "Логин bot1. Проверка наличия в друзьях bot2");
    }

    @AfterClass
    public void deleteFriend() {
        getLoginPage().doLogin(bot1);
        test.log(Status.DEBUG, "Логин bot1");
        driver.get(bot2.getProfileUrl());
        test.log(Status.DEBUG, "Переход на страницу bot2");

        PeoplePage bot2Page = new PeoplePage(driver);
        if (bot2Page.isFriendRequestSended()) {
            bot2Page.removingFriendRequests();
            test.log(Status.DEBUG, "Удаление запроса в друзья от bot2");
        } else if (bot2Page.isFriend()) {
            bot2Page.getToolbar()
                    .clickToFriends()
                    .getFriendsIcon(bot2.getId())
                    .deleteFriend();
            test.log(Status.DEBUG, "Удаление bot2 из друзей");
        }
        driver.quit();
        test.log(Status.DEBUG, "After метод успешно отработал.");
    }
}
