package pages.wrappers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FriendsIconTransformer {

    public static List<FriendsIconWrapper> wrap(List<WebElement> elements, WebDriver driver){
        if (elements.isEmpty()){
            return Collections.<FriendsIconWrapper>emptyList();
        }
        List<FriendsIconWrapper> list = new ArrayList<FriendsIconWrapper>();
        for (WebElement element : elements){
            list.add(new FriendsIconWrapper(element, driver));
        }
        return list;
    }

}
