package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.LoginPage;

import java.util.concurrent.TimeUnit;


public class LoginTest extends BaseTest {
    private final static String LOGIN_PAGE_URL = "https://www.saucedemo.com/";


    @BeforeMethod
    private void navigateToLoginTest() {
        loginPage.open();
    }

    @AfterMethod
    public void clearCookies() {
        driver.manage().deleteAllCookies();
    }

    @BeforeMethod
    public void navigateToLoginPage() {
        loginPage.open();
    }

    @Test
    public void loginPositiveTest() {
        loginPage.login(USERNAME, PASSWORD);
        String expectedUrl = "https://www.saucedemo.com/inventory.html";
        Assert.assertEquals(loginPage.getCurrentPageUrl(), expectedUrl);
    }

    @Test
    public void loginWithEmptyUsername() {
        String expectedErrorMessage = "Epic sadface: Username is required";
        loginPage.login("", PASSWORD);
        Assert.assertEquals(loginPage.getCurrentPageUrl(), LOGIN_PAGE_URL);
        Assert.assertTrue(loginPage.isErrorMessageDisplayed());
        Assert.assertEquals(loginPage.getErrorMessageText(), expectedErrorMessage);
    }


    @Test
    public void loginWithEmptyPassword() {
        String expectedErrorMessage = "Epic sadface: Password is required";
        String currentPageUrl = loginPage.getCurrentPageUrl();
        loginPage.login(USERNAME, "");
        Assert.assertEquals(loginPage.getCurrentPageUrl(), currentPageUrl);
        Assert.assertTrue(loginPage.isErrorMessageDisplayed());
        Assert.assertEquals(loginPage.getErrorMessageText(), expectedErrorMessage);
    }
}






//        WebElement buttonAddToCart = driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-backpack']"));
//        buttonAddToCart.click();
//        WebElement buttonCart = driver.findElement(By.cssSelector("a[class='shopping_cart_link']"));
//        buttonCart.click();
//        WebElement priceItem = driver.findElement(By.xpath("//div[text()='29.99']"));
//        WebElement nameItem = driver.findElement(By.xpath("//div[text()='Sauce Labs Backpack']"));
//        Assert.assertEquals(priceItem.getText(), "$29.99");
//        Assert.assertEquals(nameItem.getText(), "Sauce Labs Backpack");
//
//    }








