package pages.wrappers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static java.lang.Thread.sleep;

public class FriendsIconWrapper {

    private WebElement icon;
    private WebDriver driver;

    private final By avatarIcon = By.xpath(".//*[@class = 'user-grid-card_img']");
    public final By name = By.xpath(".//*[@class = 'n-t bold']");

    public FriendsIconWrapper(WebElement icon, WebDriver driver) {
        this.icon = icon;
        this.driver = driver;
    }

    public FriendsIconWrapper() {
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


