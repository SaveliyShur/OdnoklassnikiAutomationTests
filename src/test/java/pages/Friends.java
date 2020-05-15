package pages;

import org.junit.Assert;
import org.openqa.selenium.*;
import pages.wrappers.FriendsIconTransformer;
import pages.wrappers.FriendsIconWrapper;

import java.util.List;

import static java.lang.Thread.sleep;

public class Friends extends BasePage {

    private final By searchInFriends = By.xpath("//*[@placeholder='Поиск среди друзей']");
    private final By frameWishFriends = By.xpath("//*[@id='hook_Block_MyFriendsSquareCardsPagingB']");
    private final By iconFriend = By.xpath(".//*[@class = 'ugrid_i']");

    private WebDriver driver;

    public Friends(WebDriver driver) {
        this.driver = driver;
    }

    public void findFriend(String name) throws InterruptedException {
        driver.findElement(searchInFriends).sendKeys(name);
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
        Assert.assertEquals(false, true);
        return new FriendsIconWrapper();
    }

    private List<FriendsIconWrapper> getFriendsIconOnBaseFriendsPageList(){

        isElementPresent(frameWishFriends,driver);
        List<WebElement> elements = driver.findElement(frameWishFriends).findElements(iconFriend);
        List<FriendsIconWrapper> icons  = FriendsIconTransformer.wrap(elements, driver);
        return icons;
    }

}
