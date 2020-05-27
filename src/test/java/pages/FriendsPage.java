package pages;


import org.openqa.selenium.*;
import org.testng.Assert;
import pages.wrappers.FriendIconAfterSearchOnFriends;
import pages.wrappers.FriendsIconWrapper;
import pages.wrappers.IconFriendRequestOnFriends;
import pages.wrappers.Transformer;

import java.util.ArrayList;
import java.util.List;


public class FriendsPage extends BasePage  {

    private static final By searchInFriends = By.xpath("//*[@placeholder='Поиск среди друзей']");
    private static final By frameWishFriends = By.xpath("//*[@id='hook_Block_MyFriendsSquareCardsPagingB']");
    private static final By iconFriend = By.xpath(".//*[@class = 'ugrid_i']");
    private static final By TOOLBAR = By.xpath("//*[@class='toolbar']");
    private static final By leftToolbarForNavigationFriends = By.xpath("//*[@id='UserFriendsCatalogRB']");
    private static final By AllFriends = By.xpath(".//*[contains(text(), 'Все')]");
    private static final By FriendRequests = By.xpath(".//*[contains(@href, '/friendRequests')]");
    private static final By OutgoingFriendRequests = By.xpath(".//*[contains(text(), 'Исходящие заявки в друзья')]");
    private static final By IconFriendRequestLocator = By.xpath("//*[@class='ucard-w-list_i']");
    private static final By ICON_FRIEND_AFTER_SEARCH = By.xpath("//*[@class='gs_result_list']//*[contains(@class, 'gs_result_i_w')]");
    private static final By frameWishSearchFriends = By.xpath("//*[@id='hook_Block_MyFriendsFriendSearchPagingB']");
    private static final By CROSS_IN_SEARCH_LINE = By.xpath("//*[@class = 'search-input_ic ic curPointer ic_close-g']");
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
        driver.findElement(searchInFriends).clear();
        driver.findElement(searchInFriends).sendKeys(Keys.ENTER);
        assertLocator(driver,frameWishFriends);
        driver.findElement(searchInFriends).sendKeys(name);
        assertLocator(driver,frameWishSearchFriends);
        return this;
    }


    public FriendsPage clickToAllFriends() {
        driver.findElement(leftToolbarForNavigationFriends).findElement(AllFriends).click();
        return this;
    }

    public FriendsPage clickToFriendRequests() {
        driver.findElement(leftToolbarForNavigationFriends).findElement(FriendRequests).click();
        return this;
    }

    public FriendsPage clickToOutGoingFriendRequests() {
        driver.findElement(leftToolbarForNavigationFriends).findElement(OutgoingFriendRequests).click();
        return this;
    }

    public IconFriendRequestOnFriends checkFriendByURLOnFriendRequests(String id){
        List<IconFriendRequestOnFriends> elements = getIconsFriendRequestOnToOutgoingFriendRequests();
        for (IconFriendRequestOnFriends element : elements){
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

    public List<FriendIconAfterSearchOnFriends> getFriendsIconAfterSearchOnFriends(){
        List<WebElement> elements = driver.findElements(ICON_FRIEND_AFTER_SEARCH);
        List<FriendIconAfterSearchOnFriends> icons = Transformer.wrap(elements,driver,FriendIconAfterSearchOnFriends.class);
        return icons;
    }

    public List<String> getNamesFriends(){
        List<FriendsIconWrapper> elements = getFriendsIconOnBaseFriendsPageList();
        List<String> nameFriends = new ArrayList<>();
        for (FriendsIconWrapper element : elements){
            nameFriends.add(element.getName());
        }
        return nameFriends;
    }

    private List<IconFriendRequestOnFriends> getIconsFriendRequestOnToOutgoingFriendRequests(){
        // TODO: 27.05.2020 Oleg Надо добавить лог что заявок нет, при их отсутсвии ниже написан лог через ассерты перепиши
        Assert.assertTrue(isElementPresent(IconFriendRequestLocator), "Отсутсвуют заявки");
        List<WebElement> elements = driver.findElements(IconFriendRequestLocator);
        List<IconFriendRequestOnFriends> icons = Transformer.wrap(elements,driver, IconFriendRequestOnFriends.class);
        Assert.assertTrue(!elements.isEmpty(), "Нет заявок у человека");
        return icons;
    }

    private List<FriendsIconWrapper> getFriendsIconOnBaseFriendsPageList(){
        // TODO: 27.05.2020 Oleg Надо добавить лог что друзей нет, при их отсутсвии ниже написан лог через ассерты перепиши
        Assert.assertTrue(isElementPresent(frameWishFriends), "Друзья не появились");
        List<WebElement> elements = driver.findElement(frameWishFriends).findElements(iconFriend);
        List<FriendsIconWrapper> icons  = Transformer.wrap(elements,driver,FriendsIconWrapper.class);
        Assert.assertTrue(!elements.isEmpty(), "Нет друзей у человека");
        return icons;
    }

}
