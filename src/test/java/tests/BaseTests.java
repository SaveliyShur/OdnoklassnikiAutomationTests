package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import pages.LoginPage;
import pages.ToolBar;

import java.util.concurrent.TimeUnit;


abstract public class BaseTests {
    public static WebDriver driver ;
    public static long TIME_WAIT = 10;
    public static ExtentHtmlReporter htmlReporter;
    public static ExtentReports extent;
    public static ExtentTest test;

    private static final By TOOLBAR = By.xpath("//*[@class='toolbar']");

    @BeforeSuite
    public void setUp() {
        htmlReporter = new ExtentHtmlReporter("report.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    @AfterMethod
    public void getResult(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            test.fail(MarkupHelper.createLabel(result.getName() + "failed", ExtentColor.RED));
            test.fail(result.getThrowable());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.pass(MarkupHelper.createLabel(result.getName() + "passed", ExtentColor.GREEN));
        } else {
            test.pass(MarkupHelper.createLabel(result.getName() + "skipped", ExtentColor.YELLOW));
        }
    }

    @AfterSuite
    public void tearDown() {
        extent.flush();
    }

    protected void setDriver(){
        System.setProperty("webdriver.chrome.driver", "C:\\configs\\cromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(TIME_WAIT, TimeUnit.SECONDS);
    }

    protected LoginPage getLoginPage(){
        driver.get("https://ok.ru/");
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        if(driver.findElements(TOOLBAR).size() == 0){
            return new LoginPage(driver);
        } else {
            ToolBar toolBar = new ToolBar(driver.findElement(TOOLBAR), driver);
            toolBar.exit();
            return new LoginPage(driver);
        }
    }
}
