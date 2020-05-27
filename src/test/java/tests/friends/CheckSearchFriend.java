package tests.friends;

import bot.Bot;
import bot.SaveliyBot;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.FriendsPage;
import pages.PeoplePage;
import pages.wrappers.FriendIconAfterSearchOnFriends;
import tests.BaseTests;

import java.util.List;
import java.util.stream.Stream;

import static java.lang.Thread.sleep;

/*
Логинимся
Берем каждого друга который есть в друзьях
Ищем его по имени и фамилии
Проверяем что он есть в результатах поиска
 */
public class CheckSearchFriend extends BaseTests {

    Bot saveliy = new SaveliyBot();

    @BeforeTest
    public void before(){
        setDriver();
    }

    @Test
    public void testSearchFriend() throws InterruptedException {
        test = extent.createTest(this.getClass().getSimpleName());

        FriendsPage friendsPage = getLoginPage()
                .doLogin(saveliy)
                .getToolbar()
                .clickToFriends();
        List<String> namesFriends = friendsPage.getNamesFriends();
        for (String name : namesFriends) {
            friendsPage.findFriend(name);
            List<FriendIconAfterSearchOnFriends> friendsIconAfterSearch = friendsPage.getFriendsIconAfterSearchOnFriends();
            Stream<FriendIconAfterSearchOnFriends> stream = friendsIconAfterSearch.stream();
            if (stream.allMatch(s -> s.isName(name))) {
                Assert.fail("Поиск друзей не работает");
            }
        }
    }

    @AfterTest
    public void after()  {
        driver.quit();
    }
}
