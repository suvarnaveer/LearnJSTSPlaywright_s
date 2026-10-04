package com.example.salesforce.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

public abstract class BaseTest {
    private static final String DEFAULT_SALESFORCE_URL = "https://login.salesforce.com/?locale=in";

    protected WebDriver driver;
    protected String salesforceUrl;

    @BeforeTest(alwaysRun = true)
    public void configureTest() {
        String configuredUrl = System.getenv("SALESFORCE_URL");
        salesforceUrl = configuredUrl == null || configuredUrl.isBlank()
                ? DEFAULT_SALESFORCE_URL
                : configuredUrl;
    }

    @BeforeMethod(alwaysRun = true)
    public void startBrowser() {
        try {
            driver = new ChromeDriver(new ChromeOptions().addArguments("--start-maximized"));
        } catch (WebDriverException exception) {
            throw new IllegalStateException("Could not start Chrome for the Salesforce login test.", exception);
        }
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    protected String requiredEnvironmentVariable(String name) {
        String value = System.getenv(name);
        if (value == null || value.isBlank()) {
            throw new SkipException("Set the " + name + " environment variable to run this test.");
        }
        return value;
    }
}