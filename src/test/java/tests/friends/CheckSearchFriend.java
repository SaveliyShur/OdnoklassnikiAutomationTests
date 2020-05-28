package tests.friends;

import bot.Bot;
import bot.SaveliyBot;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.FriendsPage;
import pages.wrappers.FriendIconAfterSearchOnFriends;
import tests.BaseTest;

import java.util.List;
import java.util.stream.Stream;

import static java.lang.Thread.sleep;

/*
Логинимся
Берем каждого друга который есть в друзьях
Ищем его по имени и фамилии
Проверяем что он есть в результатах поиска
 */
public class CheckSearchFriend extends BaseTest {

    private final Bot saveliy = new SaveliyBot();


    @Test
    public void testSearchFriend() throws InterruptedException {
        FriendsPage friendsPage = getLoginPage()
                .doLogin(saveliy)
                .getToolbar()
                .clickToFriends();
        test.log(Status.DEBUG, "Перешли на страницу друзей Савелия");
        List<String> namesFriends = friendsPage.getNamesFriends();
        test.log(Status.DEBUG, "Получили список его друзей");
        for (String name : namesFriends) {
            friendsPage.findFriend(name);
            List<FriendIconAfterSearchOnFriends> friendsIconAfterSearch = friendsPage.getFriendsIconAfterSearchOnFriends();
            Stream<FriendIconAfterSearchOnFriends> stream = friendsIconAfterSearch.stream();
            if (stream.allMatch(s -> s.isName(name))) {
                Assert.fail("Поиск друзей не работает");
            }
        }
    }

    @AfterClass
    public void after() {
        driver.quit();
        test.log(Status.DEBUG, "After метод успешно отработал");
    }
}
