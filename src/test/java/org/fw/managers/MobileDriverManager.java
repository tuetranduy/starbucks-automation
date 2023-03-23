package org.fw.managers;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.fw.utils.Constants;
import org.fw.utils.PropertyUtils;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.SessionId;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class MobileDriverManager {

    private static final String PLATFORM_VERSION = PropertyUtils.getProperty(Constants.PLATFORM_VERSION);
    private static final String PLATFORM_NAME = PropertyUtils.getProperty(Constants.PLATFORM_NAME);
    private static final String DEVICE_NAME = PropertyUtils.getProperty(Constants.DEVICE_NAME);
    private static final String APP_NAME = PropertyUtils.getProperty(Constants.APP_NAME);
    private static final String AUTOMATION_NAME = PropertyUtils.getProperty(Constants.AUTOMATION_NAME);

    private static final ThreadLocal<AppiumDriver> mobileDrivers = new ThreadLocal<>();

    public static synchronized AppiumDriver getMobileDriver() {
        return mobileDrivers.get();
    }

    public static synchronized void setMobileDrivers(AppiumDriver driver) {
        mobileDrivers.set(driver);
    }

    public static synchronized Boolean doesDriverExist() {
        return getMobileDriver() != null;
    }

    public static synchronized Boolean isDriverSessionActive() {
        return getDriverSessionId() != null;
    }

    private static synchronized SessionId getDriverSessionId() {
        SessionId sessionId = null;

        if (doesDriverExist()) {
            sessionId = MobileDriverManager.getMobileDriver().getSessionId();
        }

        return sessionId;
    }

    public static AndroidDriver createAndroidDriver() {
        LoggingManager.logInfo(MobileDriverManager.class, "=====> Creating new Android driver ===");

        AndroidDriver driver = null;
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, PLATFORM_NAME);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, PLATFORM_VERSION);
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, DEVICE_NAME);
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AUTOMATION_NAME);

        capabilities.setCapability("appium:appPackage", "com.starbucks.mobilecard");
        capabilities.setCapability("appium:appActivity", "com.starbucks.mobilecard.main.activity.LandingPageActivity");

        capabilities.setCapability("appium:autoAcceptAlerts", true);
        capabilities.setCapability("appium:autoGrantPermissions", true);

        capabilities.setCapability(MobileCapabilityType.APP, getAppAbsolutePath(File.separator + APP_NAME));

        try {
            driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
        } catch (MalformedURLException exception) {
            LoggingManager.logError(MobileDriverManager.class, "Error when creating Android driver", exception);
        }

        return driver;
    }

    public static void createMobileDriver() {
        AndroidDriver driver = createAndroidDriver();
        setMobileDrivers(driver);
    }

    private static String getAppAbsolutePath(String appName) {
        return PropertyUtils.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "apps" + File.separator + appName;
    }

    public static void tearDownDriver() {
        AppiumDriver driver = getMobileDriver();
        if (driver != null) {
            driver.quit();
        }
    }
}
