package pages.factory;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.ClosePeoplePage;
import pages.PeoplePage;
import pages.PeoplePageInterface;
import tests.BaseTest;

import java.util.concurrent.TimeUnit;

public class PeoplePageFactory {
    private static final By CLOSE_PAGE = By.xpath("//*[@id='frFriendsPanel']//*[text() = 'Информация доступна только друзьям']");
    private static final By FRIENDS_MENU = By.xpath("//*[@id='hook_Block_MainMenu']");

    public static PeoplePageInterface getPeoplePage(WebDriver driver) {
        wait(driver,FRIENDS_MENU);
        if (!isElementPresent(CLOSE_PAGE, driver)) {
            return new PeoplePage(driver);

        }
        return new ClosePeoplePage(driver);
    }

    private static void wait(WebDriver driver, By xpath) {
    Assert.assertTrue(new WebDriverWait(driver, 10)
            .until((ExpectedCondition<Boolean>) d -> isElementPresent(xpath, driver)),"Страница не прогрузилась");
    }

    private static boolean isElementPresent(By element, WebDriver driver) {
        try {
            driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            driver.findElement(element).isDisplayed();
            driver.manage().timeouts().implicitlyWait(BaseTest.getTimeWait(), TimeUnit.SECONDS);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

}
