package com.example.salesforce.pages;

import java.time.Duration;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private static final Duration WAIT_TIMEOUT = Duration.ofSeconds(15);

    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(xpath = "//input[@id='username']")
    private WebElement username;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement password;

    @FindBy(xpath = "//input[@id='Login']")
    private WebElement loginButton;

    @FindBy(xpath = "//*[@id='error']")
    private WebElement loginError;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, WAIT_TIMEOUT);
        PageFactory.initElements(driver, this);
    }

    public void open(String url) {
        driver.get(url);
        wait.until(ExpectedConditions.visibilityOf(username));
    }

    public void login(String usernameValue, String passwordValue) {
        try {
            wait.until(ExpectedConditions.visibilityOf(username)).sendKeys(usernameValue);
            wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
            wait.until(ExpectedConditions.visibilityOf(password)).sendKeys(passwordValue);
            wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
        } catch (TimeoutException exception) {
            throw new IllegalStateException("Salesforce login did not reach the expected password or submit state.", exception);
        }
    }

    public boolean waitForSuccessfulLoginRedirect() {
        return wait.until(ExpectedConditions.not(ExpectedConditions.urlContains("login.salesforce.com")));
    }

    public boolean waitForLoginError() {
        return wait.until(ExpectedConditions.visibilityOf(loginError)).isDisplayed();
    }
}