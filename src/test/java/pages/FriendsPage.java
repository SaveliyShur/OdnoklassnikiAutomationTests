package pages;


import org.openqa.selenium.*;
import org.testng.Assert;
import pages.wrappers.FriendsIconWrapper;
import pages.wrappers.IconFriendRequest;
import pages.wrappers.Transformer;
import java.util.List;


public class FriendsPage extends BasePage  {

    private final By searchInFriends = By.xpath("//*[@placeholder='Поиск среди друзей']");
    private final By frameWishFriends = By.xpath("//*[@id='hook_Block_MyFriendsSquareCardsPagingB']");
    private final By iconFriend = By.xpath(".//*[@class = 'ugrid_i']");

    private final By leftToolbarForNavigationFriends = By.xpath("//*[@id='UserFriendsCatalogRB']");
    private final By AllFriends = By.xpath(".//*[text() = 'Все']");
    private final By FriendRequests = By.xpath(".//*[text() = 'Заявки в друзья']");
    private final By OutgoingFriendRequests = By.xpath(".//*[text() = 'Исходящие заявки в друзья ']");
    private final By IconFriendRequestLocator = By.xpath("//*[@class='ucard-w-list_i']");

    public FriendsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    void check(WebDriver driver) {
        //assertLocator(driver, leftToolbarForNavigationFriends);
        //assertLocator(driver, frameWishFriends);
    }

    public FriendsPage findFriend(String name) {
        driver.findElement(searchInFriends).sendKeys(name);
        return this;
    }


    public FriendsPage clickToAllFriends() {
        driver.findElement(AllFriends).click();
        return this;
    }

    public FriendsPage clickToFriendRequests() {
        driver.findElement(FriendRequests).click();
        return this;
    }

    public FriendsPage clickToOutGoingFriendRequests() {
        driver.findElement(OutgoingFriendRequests).click();
        return this;
    }

    public FriendsPage checkFriendByURLOnOutGoingFriendRequests(String url){
        List<IconFriendRequest> elements = getIconsFriendRequestOnToOutgoingFriendRequests();
        for (IconFriendRequest element : elements){
            element.isURL(url);
            return this;
        }
        Assert.fail("Друг по URL не найден. Отсутсвует исходящая заявка.");
        return this;
    }


    public String checkFindFriend(){
        return driver.findElement(By.xpath("//*[@id='searchResults']/div[1]/div[2]/span")).getText();
    }

    public FriendsIconWrapper getFriendsIcon(String name){
        List<FriendsIconWrapper> friends = getFriendsIconOnBaseFriendsPageList();
        for (FriendsIconWrapper friend : friends){
            if (friend.getName().equals(name)){
                return friend;
            }
        }
        Assert.fail("Отсутствует друг по имени " + name);
        return new FriendsIconWrapper(null, driver);
    }

    private List<IconFriendRequest> getIconsFriendRequestOnToOutgoingFriendRequests(){
        Assert.assertTrue(isElementPresent(IconFriendRequestLocator), "Отсутсвуют исходящие заявки");
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
