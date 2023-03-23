package org.fw.utils;

import org.fw.managers.MobileDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class TextUtils {
    public static void setText(Class<?> className, By locator, String input, String errorMessage) {
        WebElement element = MobileDriverManager.getMobileDriver().findElement(locator);
        element.sendKeys(input);
    }

    public static void setText(Class<?> className, WebElement element, String input, String errorMessage) {
        element.sendKeys(input);
    }
}
