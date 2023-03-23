package org.fw.pages;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.fw.managers.MobileDriverManager;
import org.fw.utils.ClickUtils;
import org.fw.utils.TextUtils;
import org.fw.utils.VisibilityUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
    public BasePage() {
        initializePageElements();
    }

    private void initializePageElements() {
        PageFactory.initElements(new AppiumFieldDecorator(MobileDriverManager.getMobileDriver()), this);
    }

    public BasePage click(WebElement element, String errorMessage) {
        waitUntilVisible(element, errorMessage);
        waitUntilClickable(element, errorMessage);
        ClickUtils.click(getClass(), element, errorMessage);
        return this;
    }

    public BasePage setText(WebElement element, String input, String errorMessage) {
        waitUntilVisible(element, errorMessage);
        TextUtils.setText(getClass(), element, input, errorMessage);

        return this;
    }

    public void waitUntilVisible(WebElement element, String errorMessage) {
        VisibilityUtils.waitUntilVisible(getClass(), element, errorMessage);
    }

    public BasePage waitUntilClickable(WebElement element, String errorMessage) {
        ClickUtils.waitUntilClickable(getClass(), element, errorMessage);
        return this;
    }

    public BasePage waitUntilClickable(By locator, String errorMessage) {
        ClickUtils.waitUntilClickable(getClass(), locator, errorMessage);
        return this;
    }
}
