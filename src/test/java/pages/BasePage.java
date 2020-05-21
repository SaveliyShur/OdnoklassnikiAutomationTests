package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

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

    public boolean isElementMiss(By element) {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        try {
            driver.findElement(element).isDisplayed();
            return false;
        } catch (NoSuchElementException e) {
        }
        driver.manage().timeouts().implicitlyWait(TIME_WAIT, TimeUnit.SECONDS);
        return true;
    }

    abstract void check(WebDriver driver);


    public void assertLocator(WebDriver driver, By xpath) {
        Assert.assertTrue("Элемент не найдет",
                new WebDriverWait(driver, 10).
                        until((ExpectedCondition<Boolean>) d -> isElementPresent(xpath)));
    }
}
