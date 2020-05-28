package pages.wrappers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.FriendsPage;

import static java.lang.Thread.sleep;

public class FriendsIconWrapper extends BaseWrapper{
    private static final By STOP_FRIENDS = By.xpath("//*[@id='hook_Block_MainContainer']/div[6]/table/tbody/tr/td/div/div/div[1]/div/div[7]/ul/li/a/span");
    private static final By LAYER = By.xpath("//*[@class = 'ic_delete']");
    private static final By YES = By.xpath("//*[@value='Прекратить']");

    private static final By AVATAR_ICON = By.xpath(".//*[@class = 'user-grid-card_img']");
    private static final By NAME = By.xpath(".//*[@class = 'n-t bold']");

    public FriendsIconWrapper(WebElement icon, WebDriver driver) {
        super(icon, driver);
    }



    public MoveToAvatarLayer moveToAvatar(){

        WebElement avatar = icon.findElement(AVATAR_ICON);
        Actions moveToAvatar = new Actions(driver);
        moveToAvatar.moveToElement(avatar).build().perform();

        return new MoveToAvatarLayer(avatar);
    }

    public String getName(){
        return icon.findElement(NAME).getText();
    }

    public boolean isID(String id){
        if(isElementMiss(icon, By.xpath(".//*[contains(@href, '" + id + "' )]"))){
            return false;
        }
        return true;
    }

    public FriendsPage deleteFriend(){
        MoveToAvatarLayer moveToAvatarLayer = new MoveToAvatarLayer(icon.findElement(AVATAR_ICON));
        moveToAvatarLayer.stopFriends();
        return new FriendsPage(driver);
    }


     class MoveToAvatarLayer{
        private WebElement avatar;
        public MoveToAvatarLayer(WebElement avatar) {
            this.avatar = avatar;
        }

        Actions act = new Actions(driver);


        public void stopFriends() {
            act.moveToElement(avatar).build().perform();
            assertLocator(driver,LAYER);
            driver.findElement(STOP_FRIENDS).click();
            driver.findElement(YES).click();
        }

         private void assertLocator(WebDriver driver, By xpath) {
             Assert.assertTrue(
                     new WebDriverWait(driver, 10).
                             until((ExpectedCondition<Boolean>) d -> isElementPresent(xpath)), "Элемент не найден");
         }
    }
}


