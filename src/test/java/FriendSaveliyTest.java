import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.*;
import tests.TestBase;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class FriendSaveliyTest extends TestBase {
    private String baseUrl;
    private StringBuffer verificationErrors = new StringBuffer();

    public static WebDriver driver ;


    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\configs\\cromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://ok.ru/");
    }

    @Test
     public void test() {
        Page2 loginPage = new Page2(driver);
        loginPage.userLogin();
        loginPage.clickToFriends();

        Friends clickFriendOleg = new Friends(driver);
        clickFriendOleg.findFriend();

        FriendSaveliy friendSaveliy = new FriendSaveliy(driver);
        int beforeLikes = friendSaveliy.countLike();
        friendSaveliy.setLike();
        int afterLike = friendSaveliy.countLike();

        assertEquals(1, Math.abs(beforeLikes-afterLike));
    }


    @AfterClass
    public static void tearDown(){

    }




}
