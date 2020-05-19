package pages.wrappers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IconSearchInToolbarTransformer {
    public static List<IconSearhInToolbarWrapper> wrap(List<WebElement> elements, WebDriver driver){
        if (elements.isEmpty()){
            return Collections.<IconSearhInToolbarWrapper>emptyList();
        }
        List<IconSearhInToolbarWrapper> list = new ArrayList<IconSearhInToolbarWrapper>();
        for (WebElement element : elements){
            list.add(new IconSearhInToolbarWrapper(element, driver));
        }
        return list;
    }
}
