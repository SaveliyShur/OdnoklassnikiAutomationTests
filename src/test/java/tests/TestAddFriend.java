package tests;

import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class TestAddFriend {

    public static WebDriver driver ;

    @BeforeAll
    public void before(){
        System.setProperty("webdriver.chrome.driver", "C:\\configs\\cromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(150, TimeUnit.SECONDS);
    }

    // TODO: 20.05.2020 comment test case 
    public void testAddFriend(){

    }
}
