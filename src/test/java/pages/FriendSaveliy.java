package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FriendOleg {

    private WebDriver driver;
    private WebElement classButton = driver.findElement(By.xpath("//div[@class='lcTc_avatar __l']//div[@id='hook_Block_3631117576']//span[contains(@class, 'widget_count js-count')]"));

    public FriendOleg(WebDriver driver) {
        this.driver = driver;
    }

    public int countLike(){
        return Integer.valueOf(classButton.getText());
    }

    public void setLike(){
        classButton.click();
    }

}
