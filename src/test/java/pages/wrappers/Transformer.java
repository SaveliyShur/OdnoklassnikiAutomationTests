package pages.wrappers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.lang.reflect.Constructor;

public class Transformer {

    public static <T> List<T> wrap(List<WebElement> elements, WebDriver driver, Class<T> clazz)  {
        if (elements.isEmpty()){
            return Collections.<T>emptyList();
        }
        List<T> list = new ArrayList<T>();
        for (WebElement element : elements){
            try {
                Constructor constructor = clazz.getDeclaredConstructor(WebElement.class, WebDriver.class);
                T instance = (T) constructor.newInstance(element,driver);
                list.add(instance);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

        }
        return list;
    }


}
