package pages;


import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import pages.wrappers.*;

import java.util.ArrayList;
import java.util.List;


public class FriendsPage extends BasePage  {

    private static final By SEARCH_IN_FRIENDS = By.xpath("//*[@placeholder='Поиск среди друзей']");
    private static final By FRAME_WISH_FRIENDS = By.xpath("//*[@id='hook_Block_MyFriendsSquareCardsPagingB']");
    private static final By ICON_FRIEND = By.xpath(".//*[@class = 'ugrid_i']");
    private static final By TOOLBAR = By.xpath("//*[@class='toolbar']");
    private static final By LEFT_TOOLBAR_FOR_NAVIGATION_FRIENDS = By.xpath("//*[@id='UserFriendsCatalogRB']");
    private static final By ALL_FRIENDS = By.xpath(".//*[contains(text(), 'Все')]");
    private static final By FRIEND_REQUESTS = By.xpath(".//*[contains(@href, '/friendRequests')]");
    private static final By OUTGOING_FRIEND_REQUESTS = By.xpath(".//*[contains(text(), 'Исходящие заявки в друзья')]");
    private static final By ICON_FRIEND_REQUEST_LOCATOR = By.xpath("//*[@class='ucard-w-list_i']");
    private static final By ICON_FRIEND_AFTER_SEARCH = By.xpath("//*[@class='gs_result_list']//*[contains(@class, 'gs_result_i_w')]");
    private static final By FRAME_WISH_SEARCH_FRIENDS = By.xpath("//*[@id='hook_Block_MyFriendsFriendSearchPagingB']");
    private static final By My_SUBSCRIPTION = By.xpath(".//*[contains(text(), 'Мои подписки')]");
    private static final By RECOMMENDED_SUBSCRIPTIONS = By.xpath("//*[@id = 'listBlockPanelUserSubscriptionsStarListMRB']");
    private static final By CARDS_RECOMMENDED_SUBSCRIPTION = By.xpath(".//*[@class='ugrid_i']");
    private static final By MY_SUBSCRIPTION_FRAME = By.xpath("//*[@class='ugrid_cnt']");
    private static final By MY_SUBSCRIPTION_PEOPLE = By.xpath(".//*[@class='ugrid_i']");

    public FriendsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    void check(WebDriver driver) {
        assertLocator(driver, LEFT_TOOLBAR_FOR_NAVIGATION_FRIENDS);
        assertLocator(driver, FRAME_WISH_FRIENDS);
    }

    public ToolBar getToolbar(){
        Assert.assertTrue( isElementPresent(TOOLBAR), "Не виден тулбар");
        ToolBar toolBar = new ToolBar(driver.findElement(TOOLBAR), driver);
        return toolBar;
    }

    public FriendsPage findFriend(String name) {
        driver.findElement(SEARCH_IN_FRIENDS).clear();
        driver.findElement(SEARCH_IN_FRIENDS).sendKeys(Keys.ENTER);
        assertLocator(driver, FRAME_WISH_FRIENDS);
        driver.findElement(SEARCH_IN_FRIENDS).sendKeys(name);
        assertLocator(driver, FRAME_WISH_SEARCH_FRIENDS);
        return this;
    }


    public FriendsPage clickToAllFriends() {
        driver.findElement(LEFT_TOOLBAR_FOR_NAVIGATION_FRIENDS).findElement(ALL_FRIENDS).click();
        return this;
    }

    public FriendsPage clickToMySubscription() {
        driver.findElement(LEFT_TOOLBAR_FOR_NAVIGATION_FRIENDS).findElement(My_SUBSCRIPTION).click();
        assertLocator(driver,RECOMMENDED_SUBSCRIPTIONS);
        return this;
    }



    public FriendsPage clickToFriendRequests() {
        driver.findElement(LEFT_TOOLBAR_FOR_NAVIGATION_FRIENDS).findElement(FRIEND_REQUESTS).click();
        return this;
    }

    public FriendsPage clickToOutGoingFriendRequests() {
        driver.findElement(LEFT_TOOLBAR_FOR_NAVIGATION_FRIENDS).findElement(OUTGOING_FRIEND_REQUESTS).click();
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

    public List<RecommendedSubscriptionIconWrapper> getRecommendedPeoples(){
        List<WebElement> elements = driver.findElement(RECOMMENDED_SUBSCRIPTIONS).findElements(CARDS_RECOMMENDED_SUBSCRIPTION);
        Assert.assertTrue(!elements.isEmpty(), "Рекомендованных подписок нет!");
        List<RecommendedSubscriptionIconWrapper> icons = Transformer.wrap(elements,driver,RecommendedSubscriptionIconWrapper.class);
        return icons;
    }

    public List<MySubscriptionWrapper> getMySubscriptionWrappers(){
        List<WebElement> elements = driver.findElement(MY_SUBSCRIPTION_FRAME).findElements(MY_SUBSCRIPTION_PEOPLE);
        List<MySubscriptionWrapper> icons = Transformer.wrap(elements, driver, MySubscriptionWrapper.class);
        return icons;
    }

    private List<IconFriendRequestOnFriends> getIconsFriendRequestOnToOutgoingFriendRequests(){
        List<WebElement> elements = driver.findElements(ICON_FRIEND_REQUEST_LOCATOR);
        List<IconFriendRequestOnFriends> icons = Transformer.wrap(elements,driver, IconFriendRequestOnFriends.class);
        return icons;
    }

    private List<FriendsIconWrapper> getFriendsIconOnBaseFriendsPageList(){
        List<WebElement> elements = driver.findElement(FRAME_WISH_FRIENDS).findElements(ICON_FRIEND);
        List<FriendsIconWrapper> icons  = Transformer.wrap(elements,driver,FriendsIconWrapper.class);
        return icons;
    }

}
