package pages.wrappers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class IconSearhInToolbarWrapper extends BaseWrapper{


    private static final By NAME_LOCATOR = By.xpath(".//*[@class = 'card-caption__02cy5 __small__02cy5']");

    public IconSearhInToolbarWrapper(WebElement icon, WebDriver driver) {
        super(icon,driver);
    }

    public String getName(){
        return icon.findElement(NAME_LOCATOR).getText();
    }


}
