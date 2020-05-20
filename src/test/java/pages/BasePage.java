package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

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

    abstract void check(WebDriver driver);


    public void assertLocator(WebDriver driver, int time, By xpath) {
        Assert.assertTrue("Элемент не найдет",
                new WebDriverWait(driver, time).
                        until((ExpectedCondition<Boolean>) d -> isElementPresent(xpath)));
    }
}
