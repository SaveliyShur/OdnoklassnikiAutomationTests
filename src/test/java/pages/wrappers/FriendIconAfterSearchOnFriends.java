package pages.wrappers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FriendIconAfterSearchOnFriends extends BaseWrapper {
    public FriendIconAfterSearchOnFriends(WebElement icon, WebDriver driver) {
        super(icon, driver);
    }

    public boolean isID(String id){
        if(isElementMiss(icon, By.xpath(".//*[contains(@href, '" + id + "' )]"))){
            return false;
        }
        return true;
    }

    public boolean isName(String name){
        if(isElementPresent(icon,By.xpath(".//*[contains(text(), '" + name + "' )]"))){
            return true;
        }
        return false;
    }
}
