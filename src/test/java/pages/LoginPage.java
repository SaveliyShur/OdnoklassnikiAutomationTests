package pages;

import bot.Bot;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private static final By LOGIN_FIELD = By.xpath("//input[@name='st.email']");
    private static final By PASSWORD_FIELD = By.xpath("//input[@type='password']");
    private static final By CLICK_COME_IN = By.xpath("//input[@value = 'Войти в Одноклассники']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }


    @Override
    void check(WebDriver driver) {
        assertLocator(driver, LOGIN_FIELD);
        assertLocator(driver, PASSWORD_FIELD);
        assertLocator(driver, CLICK_COME_IN);
    }

    public HomePage doLogin(Bot bot){
        driver.findElement(LOGIN_FIELD).clear();
        driver.findElement(PASSWORD_FIELD).clear();
        driver.findElement(LOGIN_FIELD).sendKeys(bot.getLogin());
        driver.findElement(PASSWORD_FIELD).sendKeys(bot.getPassword());
        driver.findElement(CLICK_COME_IN).click();
        return new HomePage(driver);
    }

}
