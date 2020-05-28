package tests.friends;

import bot.Bot;
import bot.OlegBot;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import pages.FriendsPage;
import pages.LoginPage;
import pages.wrappers.RecommendedSubscriptionIconWrapper;
import tests.BaseTest;

import java.util.List;

import static java.lang.Thread.sleep;
/*
Логинимся
Переходим во вкладку друзья
Переходим во вкладку мои подписки
Берем количество рекомендованных подписок
Прокручиваем страницу вниз
Ждем появятся ли еще рекомендованные подписки и проверяем это
 */
public class TestProcrutky extends BaseTest {
    Bot botOleg = new OlegBot();

    @Test
    public void testProcrutky() throws InterruptedException {
        LoginPage loginPage = getLoginPage();
        FriendsPage friendsPage = loginPage.doLogin(botOleg)
                .getToolbar()
                .clickToFriends()
                .clickToMySubscription();
        List<RecommendedSubscriptionIconWrapper> elementsBeforeProcrutky = friendsPage.getRecommendedPeoples();
        int elementsBeforeSize = elementsBeforeProcrutky.size();

        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollTo(0, document.body.scrollHeight);");

        Assert.assertTrue(new WebDriverWait(driver,5)
                        .until((ExpectedCondition<Boolean>) d -> comparison(elementsBeforeSize,friendsPage.getRecommendedPeoples().size() )), "Новые рекомендованные подписки не появились");
    }

    @AfterClass
    public void after() throws InterruptedException {
        driver.quit();
        test.log(Status.DEBUG, "After метод успешно отработал");
    }

    private boolean comparison(int t1, int t2){
        return t2 > t1;
    }
}
