package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public  class ToolBar extends BasePage {
    private final By arrowToDown = By.xpath(".//*[@class='svg-ic svg-ico_mini_down_16']");
    private final By clickToFriends = By.xpath(".//*[@id='hook_Block_HeaderTopFriendsInToolbar']");
    private final By searchLocator = By.xpath(".//input[@placeholder = 'Поиск']");

    private WebElement toolbar;
    private WebDriver driver;

    public ToolBar(WebElement toolbar, WebDriver driver){
        this.toolbar = toolbar;
        this.driver = driver;
    }

    public void exit(){
        toolbar.findElement(arrowToDown).click();
        ArrowToDownLayers arrowToDownLayers = new ArrowToDownLayers();
        arrowToDownLayers.exit();
    }

    public Friends clickToFriends() throws InterruptedException {
        toolbar.findElement(clickToFriends).click();
        return new Friends(driver);
    }

    public void search(String name){
        toolbar.findElement(searchLocator).sendKeys(name);
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
