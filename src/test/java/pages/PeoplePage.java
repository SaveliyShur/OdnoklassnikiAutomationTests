package pages;

import bot.Bot;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.lang.Thread.sleep;

public class PeoplePage extends BasePage {

    private static final By ADD_TO_FRIENDS = By.xpath("//*[@id='hook_Block_MainMenu']//*[text()='Добавить в друзья']");
    private static final By REQUEST_IS_SENDED = By.xpath("//*[@id='hook_Block_MainMenu']//*[text()='Запрос отправлен']");
    private static final By ALLREADY_FRIENDS = By.xpath("//*[@id='hook_Block_MainMenu']//*[text()='Друзья']");
    private static final By FRIENDS_MENU = By.xpath("//*[@id='hook_Block_MainMenu']");
   // private static final By FRIENDS_MENU_DROPDOWN = By.xpath("//*[@class='dropdown_cnt u-menu_lvl2']");
    private static final By REMOVING_FRIEND_REQUEST = By.xpath("//*[text() = 'Отменить запрос']");
    public PeoplePage(WebDriver driver) {
        super(driver);
    }

    public boolean isFriend() {
        return isElementPresent(ALLREADY_FRIENDS);
    }

    public boolean isFriendRequestSended() {
        driver.findElement(REQUEST_IS_SENDED);
        return isElementPresent(REQUEST_IS_SENDED);
    }

    public PeoplePage addFriend() {
        Assert.assertTrue("Уже отправлен запрос на добавление",isElementMiss(REQUEST_IS_SENDED));
        Assert.assertTrue("Уже есть в друзьях",isElementMiss(ALLREADY_FRIENDS));
        driver.findElement(ADD_TO_FRIENDS).click();
        return new PeoplePage(driver);
    }

    public PeoplePage removingFriendRequests() throws InterruptedException {
        driver.findElement(REQUEST_IS_SENDED).click();
        assertLocator(driver, REMOVING_FRIEND_REQUEST);
        sleep(4000);
        Assert.assertTrue("Не видно кнопки отменить запрос", isElementPresent(REMOVING_FRIEND_REQUEST));
        driver.findElement(REMOVING_FRIEND_REQUEST).click();
        sleep(4000);

        return new PeoplePage(driver);
    }

    @Override
    void check(WebDriver driver) {
        assertLocator(driver,  FRIENDS_MENU);
    }
}
