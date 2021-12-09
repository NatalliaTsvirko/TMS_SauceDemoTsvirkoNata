package tests;

import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.ProductsPage;
import utils.AllureUtils;


public class LoginTest extends BaseTest {

    private final static String LOGIN_PAGE_URL = "https://www.saucedemo.com/";
    protected ProductsPage productPage;
    private String productName;

    @BeforeMethod
    private void navigateToLoginTest() {
        loginPage.open();
    }

    @AfterMethod
    public void clearCookies() {
        driver.manage().deleteAllCookies();
    }

    @Test(description = "Checked current URL after login",invocationCount = 4,threadPoolSize = 2,groups = {"Smoke","Positive"})
    public void loginPositiveTest() {
        loginPage.login(USERNAME, PASSWORD);
        String expectedUrl = "https://www.saucedemo.com/inventory.html";
        Assert.assertEquals(loginPage.getCurrentPageUrl(), expectedUrl);
    }

    @Test(description = "Login with empty field 'username'",retryAnalyzer = ReTry.class,groups = {"Negative"})
    public void loginWithoutUsername() {
        loginPage.login("", PASSWORD);
        String expectedUrl = "https://www.saucedemo.com/inventory.html";
        Assert.assertEquals(loginPage.getCurrentPageUrl(), expectedUrl);
    }

    @Test(description = "SauceDemo verify login page is opened",groups = {"Regression"})
    public void loginPageIsOpened() {
        loginPage.open();
        boolean isLoginPageOpened = loginPage.isPageOpened();
        AllureUtils.attachScreenshot(driver);
        Assert.assertTrue(isLoginPageOpened, "Login page should be opened");
        loginPage.login(USERNAME, PASSWORD);
        Assert.assertTrue(productPage.isPageOpened(), "Product page should be opened");
    }

    @Test(description = "Checked ERROR message ",dataProvider = "Negative Test Login Data",groups = {"Negative"})
    public void loginNegativeTest(String username, String password, String expectedErrorMessage) {
        loginPage.login(username, password);
        AllureUtils.attachScreenshot(driver);
        String actualErrorMessageText = loginPage.getErrorMessageText();
        Assert.assertTrue(loginPage.isErrorMessageDisplayed());
        Assert.assertEquals(actualErrorMessageText, expectedErrorMessage);
    }

    @DataProvider(name = "Negative Test Login Data")
    public Object[][] getNegativeLoginData() {
        return new Object[][]{
                {"", "secret_sauce", "Epic sadface: Username is required"},
                {"standard_user", "", "Epic sadface: Password is required"},
                {"locked_out_user", "secret_sauce", "Epic sadface: Sorry, this user has been locked out."}
        };
    }
}
