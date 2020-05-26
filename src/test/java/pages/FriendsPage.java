package pages;


import org.openqa.selenium.*;
import org.testng.Assert;
import pages.wrappers.FriendsIconWrapper;
import pages.wrappers.IconFriendRequest;
import pages.wrappers.Transformer;
import java.util.List;


public class FriendsPage extends BasePage  {

    private static final By searchInFriends = By.xpath("//*[@placeholder='Поиск среди друзей']");
    private static final By frameWishFriends = By.xpath("//*[@id='hook_Block_UserFriendsCatalogRB']");
    private static final By iconFriend = By.xpath(".//*[@class = 'ugrid_i']");
    private static final By TOOLBAR = By.xpath("//*[@class='toolbar']");
    private static final By leftToolbarForNavigationFriends = By.xpath("//*[@id='UserFriendsCatalogRB']");
    private static final By AllFriends = By.xpath(".//*[contains(text(), 'Все')]");
    private static final By FriendRequests = By.xpath(".//*[contains(text(), 'Заявки в друзья')]");
    private static final By OutgoingFriendRequests = By.xpath(".//*[contains(text(), 'Исходящие заявки в друзья')]");
    private static final By IconFriendRequestLocator = By.xpath("//*[@class='ucard-w-list_i']");

    public FriendsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    void check(WebDriver driver) {
        assertLocator(driver, leftToolbarForNavigationFriends);
        assertLocator(driver, frameWishFriends);
    }

    public ToolBar getToolbar(){
        Assert.assertTrue( isElementPresent(TOOLBAR), "Не виден тулбар");
        ToolBar toolBar = new ToolBar(driver.findElement(TOOLBAR), driver);
        return toolBar;
    }

    public FriendsPage findFriend(String name) {
        driver.findElement(searchInFriends).sendKeys(name);
        return this;
    }


    public FriendsPage clickToAllFriends() {
        driver.findElement(frameWishFriends).findElement(AllFriends).click();
        return this;
    }

    public FriendsPage clickToFriendRequests() {
        driver.findElement(frameWishFriends).findElement(FriendRequests).click();
        return this;
    }

    public FriendsPage clickToOutGoingFriendRequests() {
        driver.findElement(frameWishFriends).findElement(OutgoingFriendRequests).click();
        return this;
    }

    public IconFriendRequest checkFriendByURLOnFriendRequests(String id){
        List<IconFriendRequest> elements = getIconsFriendRequestOnToOutgoingFriendRequests();
        for (IconFriendRequest element : elements){
            if(element.isID(id)){
                return element;
            }
        }
        Assert.fail("Друг по ID не найден. Отсутсвует заявка.");
        return null;
    }


    public String checkFindFriend(){
        return driver.findElement(By.xpath("//*[@id='searchResults']/div[1]/div[2]/span")).getText();
    }

    public FriendsIconWrapper getFriendsIcon(String id){
        List<FriendsIconWrapper> friends = getFriendsIconOnBaseFriendsPageList();
        for (FriendsIconWrapper friend : friends){
            if (friend.isID(id)){
                return friend;
            }
        }
        Assert.fail("Отсутствует друг с ID  " + id);
        return null;
    }


    private List<IconFriendRequest> getIconsFriendRequestOnToOutgoingFriendRequests(){
        Assert.assertTrue(isElementPresent(IconFriendRequestLocator), "Отсутсвуют заявки");
        List<WebElement> elements = driver.findElements(IconFriendRequestLocator);
        List<IconFriendRequest> icons = Transformer.wrap(elements,driver,IconFriendRequest.class);
        return icons;
    }

    private List<FriendsIconWrapper> getFriendsIconOnBaseFriendsPageList(){
        Assert.assertTrue(isElementPresent(frameWishFriends), "Друзья не появились");
        List<WebElement> elements = driver.findElement(frameWishFriends).findElements(iconFriend);
        List<FriendsIconWrapper> icons  = Transformer.wrap(elements,driver,FriendsIconWrapper.class);
        return icons;
    }

}
