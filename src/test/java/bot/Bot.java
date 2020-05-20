package bot;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.HomePage;
import pages.ToolBar;

abstract public class Bot {

    private final String login;
    private final String password;

    private WebDriver driver;

    Bot(String login, String password, WebDriver driver) {
        this.login = login;
        this.password = password;
        this.driver = driver;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public WebDriver getDriver() {
        return driver;
    }

}
