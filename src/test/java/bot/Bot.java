package bot;

import org.apache.xpath.operations.String;
import org.openqa.selenium.WebDriver;

abstract public class Bot {

    private final String login;
    private final String password;
    private final String id;
    private WebDriver driver;


    Bot(String login, String password, String id, WebDriver driver) {
        this.login = login;
        this.password = password;
        this.id = id;
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

    public String getId() {
        return id;
    }

    public String getProfileUrl() {
        return "https://ok.ru/profile/" + id;
    }
}
