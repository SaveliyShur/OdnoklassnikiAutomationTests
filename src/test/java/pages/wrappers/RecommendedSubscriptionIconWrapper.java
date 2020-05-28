package pages.wrappers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import pages.FriendsPage;

public class RecommendedSubscriptionIconWrapper extends BaseWrapper {

    private static final By LAYER_AFTER_MOVE = By.xpath("//*[@class = 'entity-shortcut-menu_cnt']");
    private static final By SUBSCRIBE = By.xpath("//*[@id='hook_Block_ShortcutMenu']//*[@class = 'tico entity-shortcut-menu_footer-tico']");
    private static final By CARDS_RECOMMENDED_SUBSCRIPTION_AVATER_FIELD = By.xpath(".//*[@data-l='t,avatar']");
    private static final By NAME_LOCATOR = By.xpath(".//*[@class='bold n-t']");
    public RecommendedSubscriptionIconWrapper(WebElement icon, WebDriver driver) {
        super(icon, driver);
    }

    public String getName(){
        return icon.findElement(NAME_LOCATOR).getText();
    }

    public void SubscribeToPerson(){
        WebElement PersonName = icon.findElement(CARDS_RECOMMENDED_SUBSCRIPTION_AVATER_FIELD);
        Actions act = new Actions(driver);
        act.moveToElement(PersonName).build().perform();
        assertLocator(driver,LAYER_AFTER_MOVE);
        driver.findElement(SUBSCRIBE).click();
    }
}
