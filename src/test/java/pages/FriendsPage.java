package pages;

import org.junit.Assert;
import org.openqa.selenium.*;
import pages.wrappers.FriendsIconWrapper;
import pages.wrappers.Transformer;
import java.util.List;


public class FriendsPage extends BasePage {

    private final By searchInFriends = By.xpath("//*[@placeholder='Поиск среди друзей']");
    private final By frameWishFriends = By.xpath("//*[@id='hook_Block_MyFriendsSquareCardsPagingB']");
    private final By iconFriend = By.xpath(".//*[@class = 'ugrid_i']");


    public FriendsPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @Override
    void check(WebDriver driver) {
        // TODO: 20.05.2020
    }

    ////todo разве тут не нужно возвращать друга?
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
        Assert.assertEquals("Отсутствует друг по имени " + name, false, true);
        return new FriendsIconWrapper(null, driver);
    }

    private List<FriendsIconWrapper> getFriendsIconOnBaseFriendsPageList(){
        Assert.assertTrue("Друзья не появились", isElementPresent(frameWishFriends));
        List<WebElement> elements = driver.findElement(frameWishFriends).findElements(iconFriend);
        List<FriendsIconWrapper> icons  = Transformer.wrap(elements,driver,FriendsIconWrapper.class);
        return icons;
    }

}
