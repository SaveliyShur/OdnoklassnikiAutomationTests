package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

abstract public class BasePage {

    public void isElementPresent(By xpath, WebDriver driver){
        Assert.assertEquals("Отсутствует элемент", true, chechElement(xpath,driver));
    }
    private boolean chechElement(By xpath, WebDriver driver){
        try {
            driver.findElement(xpath);
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }
}
