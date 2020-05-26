package pages.wrappers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class IconFriendRequest extends BaseWrapper {

    public IconFriendRequest(WebElement icon, WebDriver driver) {
        super(icon, driver);

    }
    //Сравнивает значения URl и возвращает true если URL одинаковые
    public boolean isURL(String url){
        if (isElementMiss(icon, By.xpath(".//*[@data-entity-id='" + url + "']"))){
            return false;
        }
        return true;
    }
}
