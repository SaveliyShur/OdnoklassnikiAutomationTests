package tests.friends;

import bot.Bot;
import bot.OlegBot;
import bot.SaveliyBot;
import bot.TechoBot9;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import pages.*;
import pages.factory.PeoplePageFactory;
import tests.BaseTest;

public class TestAddClosePagePeople extends BaseTest {
    Bot botWishClosePage = new TechoBot9();
    Bot botWhoAddNewFriend = new SaveliyBot();

    @Test
    public void testAddClosePagePeople() throws InterruptedException {
        LoginPage loginPage = getLoginPage();
        loginPage.doLogin(botWhoAddNewFriend);
        driver.get(botWishClosePage.getProfileUrl());
        PeoplePageInterface pageCloseBotBeforeAdd = PeoplePageFactory.getPeoplePage(driver);
        Assert.assertTrue(pageCloseBotBeforeAdd.getClass().equals(ClosePeoplePage.class), "Страница человека оказалась открытой");
        pageCloseBotBeforeAdd.addFriend();
        pageCloseBotBeforeAdd.getToolbar() // можно попробовать убрать
                .exit();
        addFriend(botWishClosePage, botWhoAddNewFriend)
                .getToolbar()
                .exit();
        LoginPage loginPage2 = getLoginPage();
        loginPage2.doLogin(botWhoAddNewFriend);
        driver.get(botWishClosePage.getProfileUrl());
        PeoplePageInterface pageCloseBotAfterAdd = PeoplePageFactory.getPeoplePage(driver);
        Assert.assertTrue(pageCloseBotAfterAdd.getClass().equals(PeoplePage.class), "Страница человека после добавления осталась закрытой");
        pageCloseBotAfterAdd.getToolbar().exit();
    }

    @AfterClass
    public void deleteFriend() {
        getLoginPage().doLogin(botWhoAddNewFriend);
        test.log(Status.DEBUG, "Логин bot1");
        driver.get(botWishClosePage.getProfileUrl());
        test.log(Status.DEBUG, "Переход на страницу bot2");

        PeoplePage bot2Page = new PeoplePage(driver);
        if (bot2Page.isFriendRequestSended()) {
            bot2Page.removingFriendRequests();
            test.log(Status.DEBUG, "Удаление запроса в друзья от bot2");
        } else if (bot2Page.isFriend()) {
            bot2Page.getToolbar()
                    .clickToFriends()
                    .getFriendsIcon(botWishClosePage.getId())
                    .deleteFriend();
            test.log(Status.DEBUG, "Удаление bot2 из друзей");
        }
        driver.quit();
        test.log(Status.DEBUG, "After метод успешно отработал.");
    }

    private FriendsPage addFriend(Bot whoAdd, Bot whichAdd) throws InterruptedException {
        LoginPage loginPage = getLoginPage();
        FriendsPage friendsPage = loginPage.doLogin(whoAdd)
                .getToolbar()
                .clickToFriends();
        test.log(Status.DEBUG, "Логин whoAdd и переход во вкладку ДРУЗЬЯ");
        friendsPage.clickToFriendRequests()
                .checkFriendByURLOnFriendRequests(whichAdd.getId())
                .addFriend();
        return friendsPage;
    }

}
