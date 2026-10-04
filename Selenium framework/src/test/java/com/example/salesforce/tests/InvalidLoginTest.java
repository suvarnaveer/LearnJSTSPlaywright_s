package com.example.salesforce.tests;

import com.example.salesforce.base.BaseTest;
import com.example.salesforce.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class InvalidLoginTest extends BaseTest {
    @Test
    public void invalidPasswordDisplaysLoginError() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open(salesforceUrl);
        loginPage.login(
                requiredEnvironmentVariable("SALESFORCE_USERNAME"),
                requiredEnvironmentVariable("SALESFORCE_INVALID_PASSWORD"));

        Assert.assertTrue(
                loginPage.waitForLoginError(),
                "Expected Salesforce to display a login error for an invalid password.");
    }
}