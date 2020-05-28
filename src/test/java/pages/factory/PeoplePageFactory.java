package pages.factory;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import pages.ClosePeoplePage;
import pages.PeoplePage;
import pages.PeoplePageInterface;

public class PeoplePageFactory {
    private static final By CLOSE_PAGE = By.xpath("//*[@id='frFriendsPanel']//*[text() = 'Информация доступна только друзьям']");

    public static PeoplePageInterface getPeoplePage(WebDriver driver){
        if(!isElementPresent(CLOSE_PAGE, driver)){
            return new PeoplePage(driver);

        }
        return new ClosePeoplePage(driver);
    }

    private static boolean isElementPresent(By element, WebDriver driver) {
        try {
            driver.findElement(element).isDisplayed();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

}
