package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class HomePage extends BasePage {

    private static final By TOOLBAR_LOCATOR = By.xpath("//*[@class = 'toolbar']");
    private static final By NAME_LOCATOR = By.xpath(".//*[@data-l='t,userPage']");
    private static final By HOOK_BLOCK_NAVIGATION = By.xpath("//*[@id = 'hook_Block_Navigation']");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Override
    void check(WebDriver driver) {
        assertLocator(driver, HOOK_BLOCK_NAVIGATION);
    }


    public ToolBar getToolbar(){
        Assert.assertTrue(isElementPresent(TOOLBAR_LOCATOR), "Toolbar не виден");
        ToolBar toolBar = new ToolBar(driver.findElement(TOOLBAR_LOCATOR), driver);
        return toolBar;
    }

    public String getNamePage(){
        return driver.findElement(HOOK_BLOCK_NAVIGATION).findElement(NAME_LOCATOR).getText();
    }





}
