package bot;

import org.openqa.selenium.WebDriver;

abstract public class Bot {

    private final String login;
    private final String password;
    private final String id;


    Bot(String login, String password, String id) {
        this.login = login;
        this.password = password;
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }


    public String getId() {
        return id;
    }

    public String getProfileUrl() {
        return "https://ok.ru/profile/" + id;
    }
}
