package bot;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

abstract public class Bot {
    private final String login;
    private final String password;

    private WebDriver driver;
    private boolean entry = false;

    private static final By loginField = By.xpath("//input[@name='st.email']");
    private static final By passwordField = By.xpath("//input[@type='password']");
    private static final By clickComeIn = By.xpath("//input[@value = 'Войти в Одноклассники']");

    Bot(String login, String password, WebDriver driver) {
        this.login = login;
        this.password = password;
        this.driver = driver;
    }

    public final void doLogin(){
        driver.get("https://ok.ru/");
        driver.findElement(loginField).clear();
        driver.findElement(passwordField).clear();
        driver.findElement(loginField).sendKeys(login);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(clickComeIn).click();
        entry = true;
    }
}
