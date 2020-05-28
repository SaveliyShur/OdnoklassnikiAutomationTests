package tests.friends;

import bot.Bot;
import bot.SaveliyBot;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import pages.FriendsPage;
import pages.LoginPage;
import pages.wrappers.MySubscriptionWrapper;
import pages.wrappers.RecommendedSubscriptionIconWrapper;
import tests.BaseTest;

import java.util.List;

import static java.lang.Thread.sleep;

public class TestRecommendedSubscriptions extends BaseTest {
    private final Bot botOleg = new SaveliyBot();
    private FriendsPage friendsPage;
    private String subscriptionName;

    @Test
    public void testRecommendedSubscriptions() throws InterruptedException {
        LoginPage loginPage = getLoginPage();
        friendsPage =loginPage.doLogin(botOleg)
                .getToolbar()
                .clickToFriends()
                .clickToMySubscription();
        test.log(Status.DEBUG, "Логин Савелий, переход на вкладку друзья, подписчики");

        List<RecommendedSubscriptionIconWrapper> listRecommendedSubscription = friendsPage.getRecommendedPeoples();
        RecommendedSubscriptionIconWrapper subsctiption = listRecommendedSubscription.get(0);
        subscriptionName = subsctiption.getName();
        test.log(Status.DEBUG, "Собираемся подписаться на " + subscriptionName);

        List<MySubscriptionWrapper> listMySubscription = friendsPage.getMySubscriptionWrappers();
        long countSubscriptionsBefore = listMySubscription.stream().filter(s -> s.getName().equals(subscriptionName)).count();

        subsctiption.SubscribeToPerson();
        test.log(Status.DEBUG, "Подписались на " + subscriptionName);

        driver.navigate().refresh();

        listMySubscription = friendsPage.getMySubscriptionWrappers();
        long countSubscriptionsAfter = listMySubscription.stream().filter(s -> s.getName().equals(subscriptionName)).count();

        Assert.assertTrue(countSubscriptionsAfter - countSubscriptionsBefore == 1, "Разница между " +
                "колчеством подписчиков с именем " + subscriptionName + " до и после не равна 1.");
    }

    @AfterClass
    public void after() throws InterruptedException {
        List<MySubscriptionWrapper> listMySubscription = friendsPage.getMySubscriptionWrappers();
        listMySubscription.stream().filter(x -> x.getName().equals(subscriptionName)).
                forEach(MySubscriptionWrapper::unsubscribe);
        test.log(Status.DEBUG, "Отписались от " + subscriptionName);
        driver.quit();
        test.log(Status.DEBUG, "After метод успешно отработал");
    }

}
