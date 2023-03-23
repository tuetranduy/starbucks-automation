package org.fw.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.fw.managers.LoggingManager;
import org.fw.managers.MobileDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.sql.DriverManager;
import java.util.List;

public class StarbucksPage extends BasePage {

    @FindBy(xpath = ".//android.widget.Button[@text='DENY']")
    WebElement denyLocation;
    @FindBy(xpath = "//*[contains(@text, 'Sign in')]")
    WebElement signIn;
    @AndroidFindBy(accessibility = "Edit box for Email or username")
    WebElement emailOrUsernameInput;
    @AndroidFindBy(accessibility = "Edit box for Password")
    WebElement passwordInput;
    @AndroidFindBy(xpath = "//android.widget.FrameLayout[@content-desc='Sign in']/android.widget.LinearLayout/android.widget.Button")
    WebElement signInBtn;
    @AndroidFindBy(xpath = "//android.widget.FrameLayout[@content-desc=\"Order\"]/android.view.ViewGroup/android.widget.TextView")
    WebElement orderBtn;
    @AndroidFindBy(xpath = "//android.widget.TextView[@content-desc=\"Search\"]")
    WebElement searchBtn;
    @AndroidFindBy(xpath = "//*[contains(@text, 'Enter a location')]")
    WebElement searchInput;
    @AndroidFindBy(xpath = "//*[contains(@text, 'Not now')]")
    WebElement notNowBtn;
    @AndroidFindBy(xpath = "//*[contains(@text, 'Order here')]")
    WebElement orderHereBtn;
    @AndroidFindBy(accessibility = "1 of 4")
    WebElement menuCard;
    @AndroidFindBy(xpath = "//*[contains(@text, 'Hot Coffees')]")
    WebElement hotCoffeesMenu;
    @AndroidFindBy(xpath = "//*[contains(@text, 'Caffè Latte')]")
    WebElement cafeLatte;
    @AndroidFindBy(accessibility = "Tall, 12 fl oz. 2 of 4")
    WebElement tallSize;
    @AndroidFindBy(xpath = "//*[contains(@text, 'Add to order')]")
    WebElement addToOrderBtn;
    @AndroidFindBy(xpath = "//android.widget.RelativeLayout[@content-desc=\"1 item in your order\"]")
    WebElement cart;
    @AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc=\"Close\"]")
    WebElement closePopup;
    @AndroidFindBy(xpath = "//android.widget.Button[contains(@content-desc, 'Pickup at')]")
    WebElement pickupStoreDropdown;

    public void denyLocation() {
        click(denyLocation, "Unable to deny location");
    }

    public void enterSignInScreen() {
        LoggingManager.logInfo(getClass(), "Entering Sign In screen");
        click(signIn, "Unable to enter Sign In screen");
    }

    public void logIn(String username, String password) {
        LoggingManager.logInfo(getClass(), "Signing in...");

        setText(emailOrUsernameInput, username, "Unable to fill username / email");
        setText(passwordInput, password, "Unable to fill password");

        click(signInBtn, "Unable to click Sign In button");
    }

    public void clickOrderMenuButton() {
        LoggingManager.logInfo(getClass(), "Clicking Order in Menu button...");

        click(orderBtn, "Unable to click Order button");

//        try {
//            click(notNowBtn, "Unable to click Not now button");
//        } catch (Throwable e) {
//            LoggingManager.logDebug(getClass(), "Pop up not displayed, next");
//        }
    }

    public void clickSearchButton() {
        LoggingManager.logInfo(getClass(), "Clicking Search button...");

        click(searchBtn, "Unable to click Search button");
    }

    public void enterLocation(String location) {
        setText(searchInput, location, "unable to enter search location");
    }

    public void clickSearchResult(String location) {
        String locator = "//android.widget.TextView[contains(lower-case(@text), '" + location.toLowerCase() + "')]";

        waitUntilClickable(By.xpath(locator), "");
        List<WebElement> searchResults = MobileDriverManager.getMobileDriver().findElements(By.xpath(locator));
        click(searchResults.get(0), "unable to click to search result");
    }

    public void chooseStore(String location) {
        String locator = "//android.widget.TextView[contains(lower-case(@text), '" + location.toLowerCase() + "')]";

        waitUntilClickable(By.xpath(locator), "");
        List<WebElement> stores = MobileDriverManager.getMobileDriver().findElements(By.xpath(locator));
        click(stores.get(0), "Unable to choose store");
    }

    public void clickOrderHereButton() {
        LoggingManager.logInfo(getClass(), "Clicking Order Here button!");
        click(orderHereBtn, "Unable to click Order Here button");
    }

    public void clickMenuCard() {
        LoggingManager.logInfo(getClass(), "Go to Menu tab!");
        click(menuCard, "Unable to change to Menu");
    }

    public void chooseHotCoffee() {
        LoggingManager.logInfo(getClass(), "Choosing Hot Coffee Category!");
        click(hotCoffeesMenu, "Unable to choose hot coffee");
    }

    public void chooseCoffeeLatte() {

        LoggingManager.logInfo(getClass(), "Choosing Latte Coffee!");

        int count = MobileDriverManager.getMobileDriver().findElements(By.xpath("//*[contains(@text, 'Caffè Latte')]")).size();

        while (count == 0) {
            String scrollToCoffeeLatte = "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollToEnd(1, 60)";
            MobileDriverManager.getMobileDriver().findElements(new AppiumBy.ByAndroidUIAutomator(scrollToCoffeeLatte));

            count = MobileDriverManager.getMobileDriver().findElements(By.xpath("//*[contains(@text, 'Caffè Latte')]")).size();

            if (count > 0) {
                LoggingManager.logInfo(getClass(), "Latte Found!");
                click(cafeLatte, "Unable to choose Latte");
                break;
            }
        }
    }

    public void chooseSize() {
        LoggingManager.logInfo(getClass(), "Choosing Size!");
        click(tallSize, "Unable to choose size");
    }

    public void addToOrder() {
        LoggingManager.logInfo(getClass(), "Adding to order!");
        click(addToOrderBtn, "Unable to add to order");
    }

    public void clickCart() {
        LoggingManager.logInfo(getClass(), "Go to Cart Page...!");
        click(cart, "Unable to go to cart");
    }

    public void closePopUp() {
        try {
            click(closePopup, "Unable to close pop up");
        } catch (NoSuchElementException e) {
            LoggingManager.logInfo(getClass(), "Popup not show up, next...");
        }
    }

    public void clickPickupStoreDropdown() {
        click(pickupStoreDropdown, "Unable to click on pickup store dropdown");
    }

//    public void tryAllowNotification() {
//        try {
//            //For Android
//            MobileDriverManager.getMobileDriver().findElement(By.xpath(".//android.widget.Button[@text='Allow']")).click();
//        } catch (NoSuchElementException e) {
//            LoggingManager.logDebug(getClass(), "No pop up to click");
//        }
//    }

}
