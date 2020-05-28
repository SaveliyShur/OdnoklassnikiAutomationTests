package pages.wrappers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import pages.FriendsPage;

public class MySubscriptionWrapper extends BaseWrapper {

    private static final By NAME_LOCATOR = By.xpath(".//*[@class='n-t bold']");
    private static final By AVATAR = By.xpath(".//*[@class='user-grid-card_avatar']");
    private static final By LAYER_MOVE_TO_AVATAR = By.xpath("//*[@class='shortcutRoundedPanel']");
    private static final By UNSUBSCRIBE = By.xpath(".//*[@class='gwt-shortcutMenu-iconlink-item']/span[text()='Отписаться']");
    private static final By UNSUBSCRIBE_FINAL_LAYER = By.xpath("//*[@id='popLayerBodyWrapper']");
    private static final By UNSUBSCRIBE_FINAL_BUTTON = By.xpath("//*[@value='Отписаться']");
    public MySubscriptionWrapper(WebElement icon, WebDriver driver) {
        super(icon, driver);
    }

    public String getName(){
        return icon.findElement(NAME_LOCATOR).getText();
    }

    public void unsubscribe(){
        Actions actions = new Actions(driver);
        WebElement avatar = icon.findElement(AVATAR);
        actions.moveToElement(avatar).build().perform();
        assertLocator(driver,LAYER_MOVE_TO_AVATAR);
        icon.findElement(LAYER_MOVE_TO_AVATAR).findElement(UNSUBSCRIBE).click();
        assertLocator(driver,UNSUBSCRIBE_FINAL_LAYER);
        driver.findElement(UNSUBSCRIBE_FINAL_LAYER).findElement(UNSUBSCRIBE_FINAL_BUTTON).click();

    }

}
