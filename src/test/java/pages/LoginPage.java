package pages;

import bot.Bot;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private static final By loginField = By.xpath("//input[@name='st.email']");
    private static final By passwordField = By.xpath("//input[@type='password']");
    private static final By clickComeIn = By.xpath("//input[@value = 'Войти в Одноклассники']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }


    @Override
    void check(WebDriver driver) {
        assertLocator(driver, 10, loginField);
        assertLocator(driver, 10, passwordField);
        assertLocator(driver, 10, clickComeIn);
    }

    public HomePage doLogin(Bot bot){
        driver.findElement(loginField).clear();
        driver.findElement(passwordField).clear();
        driver.findElement(loginField).sendKeys(bot.getLogin());
        driver.findElement(passwordField).sendKeys(bot.getPassword());
        driver.findElement(clickComeIn).click();
        return new HomePage(driver);
    }

}
