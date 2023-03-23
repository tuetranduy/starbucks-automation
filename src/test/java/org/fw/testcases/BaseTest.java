package org.fw.testcases;

import org.fw.managers.LoggingManager;
import org.fw.managers.MobileDriverManager;
import org.junit.After;
import org.junit.Before;

public class BaseTest {

    @Before
    public void setUp() {
        LoggingManager.logInfo(getClass(), "Setting up MobileDriver");
        setupMobileDriver();
    }

    @After
    public void tearDown() {
        tearDownMobileDriver();
    }

    public static void setupMobileDriver() {
        if (!MobileDriverManager.isDriverSessionActive()) {
            MobileDriverManager.createMobileDriver();
            LoggingManager.logInfo(BaseTest.class, "Opened an application");
        }
    }

    public static void tearDownMobileDriver() {
        if (MobileDriverManager.isDriverSessionActive()) {
            MobileDriverManager.tearDownDriver();
        }
    }
}
