package pages.wrappers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.FriendsPage;

public class IconFriendRequest extends BaseWrapper {

    private static final By ADD_FRIEND = By.xpath(".//*[text() = 'Принять']");

    public IconFriendRequest(WebElement icon, WebDriver driver) {
        super(icon, driver);

    }
    //Сравнивает значения URl и возвращает true если URL одинаковые
    public boolean isID(String id){
        if (isElementMiss(icon, By.xpath(".//*[@data-entity-id='" + id + "']"))){
            return false;
        }
        return true;
    }

    public void addFriend(){
        isElementPresent(icon, ADD_FRIEND);
        icon.findElement(ADD_FRIEND).click();
    }

}
