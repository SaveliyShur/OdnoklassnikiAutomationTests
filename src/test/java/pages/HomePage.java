package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    private final By toolbarLocator = By.xpath("//*[@class = 'toolbar']");
    private final By nameLocator = By.xpath(".//*[@data-l='t,userPage']");
    private final By hookBlockNavigation = By.xpath("//*[@id = 'hook_Block_Navigation']");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Override
    void check(WebDriver driver) {
        // TODO: 20.05.2020
    }


    public ToolBar getToolbar(){
        Assert.assertTrue("Toolbar не виден", isElementPresent(toolbarLocator));
        ToolBar toolBar = new ToolBar(driver.findElement(toolbarLocator), driver);
        return toolBar;
    }

    public String getNamePage(){
        return driver.findElement(hookBlockNavigation).findElement(nameLocator).getText();
    }





}
