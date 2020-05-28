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
import org.testng.annotations.*;
import pages.LoginPage;
import pages.ToolBar;

import java.util.concurrent.TimeUnit;


abstract public class BaseTests {
    protected WebDriver driver ;
    public final static long TIME_WAIT = 10;
    protected static ExtentHtmlReporter htmlReporter;
    protected static ExtentReports extent;
    protected ExtentTest test;
    private String SaveliyPath = "C:\\configs\\cromedriver\\chromedriver.exe";

    private static final By TOOLBAR = By.xpath("//*[@class='toolbar']");

    @BeforeSuite
    public void setUp() {
        htmlReporter = new ExtentHtmlReporter("report.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    @BeforeClass
    public void before(){
        setDriver();
        test = extent.createTest(this.getClass().getSimpleName());
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
        System.setProperty("webdriver.chrome.driver", SaveliyPath);
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
