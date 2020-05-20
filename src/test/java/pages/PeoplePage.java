package pages;

import bot.Bot;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PeoplePage extends BasePage {
    //// TODO: 20.05.2020 Нужны норм xpath
    private static By ADD_TO_FRIENDS = By.xpath("//input[@name='st.email']");
    private static By REQUEST_IS_SENDED = By.xpath("//input[@name='st.email']");
    private static By ALLREADY_FRIENDS = By.xpath("//input[@name='st.email']");


    public PeoplePage(WebDriver driver, Bot bot) {
        super(driver);
    }

    public boolean isFriend() {
        return isElementPresent(ALLREADY_FRIENDS);
    }

    public boolean isFriendRequestSended() {
        return isElementPresent(REQUEST_IS_SENDED);
    }

    public FriendsPage addFriend(Bot bot) {
        assertLocator(driver, 10, ADD_TO_FRIENDS);
        driver.findElement(ADD_TO_FRIENDS).click();
        assertLocator(driver, 10, REQUEST_IS_SENDED);
        return new FriendsPage(driver);
    }

    @Override
    void check(WebDriver driver) {

    }
}
