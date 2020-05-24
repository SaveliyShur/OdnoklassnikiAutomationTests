package pages;

import org.junit.Assert;
import org.openqa.selenium.*;
import pages.wrappers.FriendsIconWrapper;
import pages.wrappers.Transformer;
import java.util.List;


public class FriendsPage extends BasePage  {

    private final By searchInFriends = By.xpath("//*[@placeholder='Поиск среди друзей']");
    private final By frameWishFriends = By.xpath("//*[@id='hook_Block_MyFriendsSquareCardsPagingB']");
    private final By iconFriend = By.xpath(".//*[@class = 'ugrid_i']");

    private final By leftToolbarForNavigationFriends = By.xpath("//*[@id='UserFriendsCatalogRB']");
    private final By AllFriends = By.xpath(".//*[text() = 'Все']");
    private final By FriendRequests = By.xpath(".//*[text() = 'Заявки в друзья']");
    private final By OutgoingFriendRequests = By.xpath(".//*[text() = 'Исходящие заявки в друзья']");

    public FriendsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    void check(WebDriver driver) {
        assertLocator(driver, leftToolbarForNavigationFriends);
        assertLocator(driver, frameWishFriends);
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

    public FriendsPage clickToOutgoingFriendRequests() {
        driver.findElement(OutgoingFriendRequests).click();
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

    private List<FriendsIconWrapper> getFriendsIconOnBaseFriendsPageList(){
        Assert.assertTrue("Друзья не появились", isElementPresent(frameWishFriends));
        List<WebElement> elements = driver.findElement(frameWishFriends).findElements(iconFriend);
        List<FriendsIconWrapper> icons  = Transformer.wrap(elements,driver,FriendsIconWrapper.class);
        return icons;
    }

}
