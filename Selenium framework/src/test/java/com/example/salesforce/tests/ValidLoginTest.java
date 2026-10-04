package com.example.salesforce.tests;

import com.example.salesforce.base.BaseTest;
import com.example.salesforce.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ValidLoginTest extends BaseTest {
    @Test
    public void validCredentialsRedirectFromLoginPage() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open(salesforceUrl);
        loginPage.login(
                requiredEnvironmentVariable("SALESFORCE_USERNAME"),
                requiredEnvironmentVariable("SALESFORCE_PASSWORD"));

        Assert.assertTrue(
                loginPage.waitForSuccessfulLoginRedirect(),
                "Expected valid credentials to redirect away from the Salesforce login page.");
    }
}