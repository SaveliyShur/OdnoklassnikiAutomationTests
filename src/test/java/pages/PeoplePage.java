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

    public PeoplePage addFriend(Bot bot) {
        driver.findElement(ADD_TO_FRIENDS).click();
        return new PeoplePage(driver, bot);
    }

    @Override
    void check(WebDriver driver) {
        //todo
    }
}
