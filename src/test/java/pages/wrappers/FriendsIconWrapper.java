package pages.wrappers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import pages.FriendsPage;

import static java.lang.Thread.sleep;

public class FriendsIconWrapper extends BaseWrapper{

    private static final By avatarIcon = By.xpath(".//*[@class = 'user-grid-card_img']");
    private static final By name = By.xpath(".//*[@class = 'n-t bold']");

    public FriendsIconWrapper(WebElement icon, WebDriver driver) {
        super(icon, driver);
    }



    public MoveToAvatarLayer moveToAvatar(){

        WebElement avatar = icon.findElement(avatarIcon);
        Actions moveToAvatar = new Actions(driver);
        moveToAvatar.moveToElement(avatar).build().perform();

        return new MoveToAvatarLayer(avatar);
    }

    public String getName(){
        return icon.findElement(name).getText();
    }

    public boolean isID(String id){
        if(isElementMiss(icon, By.xpath(".//*[contains(@href, '" + id + "' )]"))){
            return false;
        }
        return true;
    }

    public FriendsPage deleteFriend(){
        MoveToAvatarLayer moveToAvatarLayer = new MoveToAvatarLayer(icon.findElement(avatarIcon));
        moveToAvatarLayer.stopFriends();
        return new FriendsPage(driver);
    }


    class MoveToAvatarLayer{
        private final By stopFriends = By.xpath(".//*[text()='Прекратить дружбу']");
        private WebElement avatar;
        public MoveToAvatarLayer(WebElement avatar) {
            this.avatar = avatar;
        }

        public void stopFriends() {
            avatar.findElement(stopFriends).click();
        }
    }
}


