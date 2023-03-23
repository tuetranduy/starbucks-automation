package org.fw.utils;


import org.fw.managers.LoggingManager;
import org.fw.managers.MobileDriverManager;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class WaitUtils {

    private static final double WAIT_INTERVAL = 1;
    private static final long WAIT_INTERVAL_MS = (long) (WAIT_INTERVAL * 1000);
    private static final long WAIT_TIME = 60;

    public static WebDriverWait waitDefault() {
        return new WebDriverWait(MobileDriverManager.getMobileDriver(), Duration.of(WAIT_TIME, ChronoUnit.SECONDS), Duration.of(WAIT_INTERVAL_MS, ChronoUnit.MILLIS));
    }

    public static void sleep(Class<?> className, int seconds) {
        try {
            seconds = seconds * 1000;
            Thread.sleep(seconds);
        } catch (InterruptedException exception) {
            LoggingManager.logError(className, "Error during perform request", exception);
        }
    }

}