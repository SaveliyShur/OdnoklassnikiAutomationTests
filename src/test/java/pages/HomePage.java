package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    WebDriver driver;
    private final By toolbarLocator = By.xpath("//*[@class = 'toolbar']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }


    public Friends getFriendsFromToolBar() throws InterruptedException {
        isElementPresent(toolbarLocator, driver);
        ToolBar toolBar = new ToolBar(driver.findElement(toolbarLocator), driver);
        return toolBar.clickToFriends();
    }

    public ToolBar getToolbar(){
        isElementPresent(toolbarLocator, driver);
        ToolBar toolBar = new ToolBar(driver.findElement(toolbarLocator), driver);
        return toolBar;
    }





}
