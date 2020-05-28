package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public  class ToolBar extends BasePage {

    private static final By ARROW_TO_DOWN = By.xpath(".//*[@class='svg-ic svg-ico_mini_down_16']");
    private static final By CLICK_TO_FRIENDS = By.xpath(".//*[@id='hook_Block_HeaderTopFriendsInToolbar']");
    private static final By SEARCH_LOCATOR = By.xpath(".//input[@placeholder = 'Поиск']");
    private static final By SEARCH_PANEL = By.xpath("//*[@class = 'suggest-ul__a0i64']");
    private static final By SEARCH_ICON = By.xpath(".//*[@class = 'user-card-wrapper__mpodh']");

    private WebElement toolbar;

    public ToolBar(WebElement toolbar, WebDriver driver){
        super(driver);
        this.toolbar = toolbar;
    }

    public LoginPage exit(){
        toolbar.findElement(ARROW_TO_DOWN).click();
        ArrowToDownLayers arrowToDownLayers = new ArrowToDownLayers();
        arrowToDownLayers.exit();
        return new LoginPage(driver);
    }

    public FriendsPage clickToFriends()  {
        toolbar.findElement(CLICK_TO_FRIENDS).click();
        return new FriendsPage(driver);
    }

    @Override
    void check(WebDriver driver) { }

    class ArrowToDownLayers{
        private final By exitButton1 = By.xpath(".//*[text()='Выйти']");
        private final By exitButton2 = By.xpath("//*[@class='button-pro form-actions_yes']");

        void exit(){
            toolbar.findElement(exitButton1).click();
            assertLocator(driver,exitButton2);
            toolbar.findElement(exitButton2).click();
        }
    }
}
