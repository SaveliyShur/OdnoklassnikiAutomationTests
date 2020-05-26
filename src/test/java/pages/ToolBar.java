package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pages.wrappers.Transformer;
import pages.wrappers.IconSearhInToolbarWrapper;

import java.util.List;

import static java.lang.Thread.sleep;

public  class ToolBar extends BasePage {

    private final By arrowToDown = By.xpath(".//*[@class='svg-ic svg-ico_mini_down_16']");
    private final By clickToFriends = By.xpath(".//*[@id='hook_Block_HeaderTopFriendsInToolbar']");
    private final By searchLocator = By.xpath(".//input[@placeholder = 'Поиск']");
    private final By searchPanel = By.xpath("//*[@class = 'suggest-ul__a0i64']");
    private final By searchIcon = By.xpath(".//*[@class = 'user-card-wrapper__mpodh']");

    private WebElement toolbar;

    public ToolBar(WebElement toolbar, WebDriver driver){
        super(driver);
        this.toolbar = toolbar;
    }

    public LoginPage exit(){
        toolbar.findElement(arrowToDown).click();
        ArrowToDownLayers arrowToDownLayers = new ArrowToDownLayers();
        arrowToDownLayers.exit();
        return new LoginPage(driver);
    }

    public FriendsPage clickToFriends() throws InterruptedException {
        toolbar.findElement(clickToFriends).click();
        return new FriendsPage(driver);
    }

    public void search(String name){
        toolbar.findElement(searchLocator).sendKeys(name);
        Assert.assertTrue(isElementPresent(searchPanel), "Список друзей не выпал");
        List<WebElement> iconSearch = driver.findElement(searchPanel).findElements(searchIcon);
        List<IconSearhInToolbarWrapper> iconSearchWrap = Transformer.wrap(iconSearch, driver, IconSearhInToolbarWrapper.class);
        // TODO: 20.05.2020 return
    }

    @Override
    void check(WebDriver driver) {
        // TODO: 20.05.2020
    }

    class ArrowToDownLayers{
        private final By exitButton1 = By.xpath(".//*[text()='Выйти']");
        private final By exitButton2 = By.xpath("//*[@id='hook_FormButton_logoff.confirm_not_decorate']");

        void exit(){
            toolbar.findElement(exitButton1).click();
            toolbar.findElement(exitButton2).click();
        }
    }
}
