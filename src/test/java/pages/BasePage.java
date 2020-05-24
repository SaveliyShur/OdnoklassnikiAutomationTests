package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

import static tests.BaseTests.TIME_WAIT;

abstract public class BasePage {

    WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        check(driver);
    }

    public boolean isElementPresent(By element) {
        try {
            driver.findElement(element).isDisplayed();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isElementPresent(WebElement element, By xpath) {
        try {
            element.findElement(xpath).isDisplayed();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isElementMiss(By element) {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        if (driver.findElements(element).size() != 0){
            driver.manage().timeouts().implicitlyWait(TIME_WAIT, TimeUnit.SECONDS);
            return false;
        }
        driver.manage().timeouts().implicitlyWait(TIME_WAIT, TimeUnit.SECONDS);
        return true;
    }

    public boolean isElementMiss(WebElement element, By xpath) {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        if (element.findElements(xpath).size() != 0){
            driver.manage().timeouts().implicitlyWait(TIME_WAIT, TimeUnit.SECONDS);
            return false;
        }
        driver.manage().timeouts().implicitlyWait(TIME_WAIT, TimeUnit.SECONDS);
        return true;
    }

    abstract void check(WebDriver driver);


    public void assertLocator(WebDriver driver, By xpath) {
        Assert.assertTrue(
                new WebDriverWait(driver, 10).
                        until((ExpectedCondition<Boolean>) d -> isElementPresent(xpath)), "Элемент не найден");
    }
}
