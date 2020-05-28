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
    Bot botOleg = new SaveliyBot();

    @Test
    public void testRecommendedSubscriptions() throws InterruptedException {
        LoginPage loginPage = getLoginPage();
        FriendsPage friendsPage =loginPage.doLogin(botOleg)
                .getToolbar()
                .clickToFriends()
                .clickToMySubscription();
        List<RecommendedSubscriptionIconWrapper> listRecommendedSubscription = friendsPage.getRecommendedPeoples();
        RecommendedSubscriptionIconWrapper people = listRecommendedSubscription.get(1);
        String name = people.getName();

        List<MySubscriptionWrapper> listMySubscription1 = friendsPage.getMySubscriptionWrappers();
        long numberPeople = listMySubscription1.stream().filter(s -> s.getName().equals(name)).count();

        people.SubscribeToPerson();

        driver.navigate().refresh();

        List<MySubscriptionWrapper> listMySubscription2 = friendsPage.getMySubscriptionWrappers();
        long numberPeople2 = listMySubscription2.stream().filter(s -> s.getName().equals(name)).count();

        Assert.assertTrue(numberPeople2-numberPeople == 1, "Дичь");
        List<MySubscriptionWrapper> listMySubscription3 = friendsPage.getMySubscriptionWrappers();
        listMySubscription3.stream().filter(x -> x.getName().equals(name)).forEach(MySubscriptionWrapper::unsubscribe);
    }

    @AfterClass
    public void after() throws InterruptedException {
        driver.quit();
        test.log(Status.DEBUG, "After метод успешно отработал");
    }

}
