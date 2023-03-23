package org.fw.utils;

import org.fw.managers.LoggingManager;
import org.fw.managers.MobileDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ClickUtils {

    public static void click(Class<?> className, By locator, String errorMessage) {
        WebElement element = MobileDriverManager.getMobileDriver().findElement(locator);
        element.click();
    }

    public static void click(Class<?> className, WebElement element, String errorMessage) {
        element.click();
    }

    public static void waitUntilClickable(Class<?> className, WebElement element, String errorMessage) {
        try {
            WaitUtils.waitDefault().ignoring(StaleElementReferenceException.class).until(ExpectedConditions.elementToBeClickable(element));

        } catch (Exception exception) {
            LoggingManager.logError(className, errorMessage, exception);
        }
    }

    public static void waitUntilClickable(Class<?> className, By locator, String errorMessage) {
        try {
            WaitUtils.waitDefault().ignoring(StaleElementReferenceException.class).until(ExpectedConditions.elementToBeClickable(locator));

        } catch (Exception exception) {
            LoggingManager.logError(className, errorMessage, exception);
        }
    }

}
