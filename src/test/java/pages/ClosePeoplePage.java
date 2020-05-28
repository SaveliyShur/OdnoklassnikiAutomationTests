package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class ClosePeoplePage extends BasePage implements PeoplePageInterface {

    private static final By ADD_TO_FRIENDS = By.xpath("//*[@id='hook_Block_MainMenu']//*[text()='Добавить в друзья']");
    private static final By REQUEST_IS_SENDED = By.xpath("//*[@id='hook_Block_MainMenu']//*[text()='Запрос отправлен']");
    private static final By ALLREADY_FRIENDS = By.xpath("//*[@id='hook_Block_MainMenu']//*[text()='Друзья']");
    private static final By FRIENDS_MENU = By.xpath("//*[@id='hook_Block_MainMenu']");
    private static final By REMOVING_FRIEND_REQUEST = By.xpath("//*[text() = 'Отменить запрос']");
    private static final By TOOLBAR = By.xpath("//*[@class='toolbar']");

    public ClosePeoplePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isFriend() {
        return isElementPresent(ALLREADY_FRIENDS);
    }

    @Override
    public boolean isFriendRequestSended() {
        driver.findElement(REQUEST_IS_SENDED);
        return isElementPresent(REQUEST_IS_SENDED);
    }

    @Override
    public PeoplePage addFriend() {
        Assert.assertTrue(isElementMiss(REQUEST_IS_SENDED), "Уже отправлен запрос на добавление");
        Assert.assertTrue(isElementMiss(ALLREADY_FRIENDS), "Уже есть в друзьях");
        driver.findElement(ADD_TO_FRIENDS).click();
        return new PeoplePage(driver);
    }


    @Override
    public PeoplePage removingFriendRequests() {
        driver.findElement(REQUEST_IS_SENDED).click();
        assertLocator(driver, REMOVING_FRIEND_REQUEST);
        Assert.assertTrue(isElementPresent(REMOVING_FRIEND_REQUEST), "Не видно кнопки отменить запрос");
        driver.findElement(REMOVING_FRIEND_REQUEST).click();

        return new PeoplePage(driver);
    }

    @Override
    public ToolBar getToolbar() {
        Assert.assertTrue(isElementPresent(TOOLBAR), "Не виден тулбар");
        ToolBar toolBar = new ToolBar(driver.findElement(TOOLBAR), driver);
        return toolBar;
    }


    @Override
    public boolean isOpen() {
        return false;
    }

    @Override
    void check(WebDriver driver) {
        assertLocator(driver, FRIENDS_MENU);
    }
}
