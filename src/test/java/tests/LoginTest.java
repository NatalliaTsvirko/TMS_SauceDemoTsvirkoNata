package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.LoginPage;
import pages.ShoppingCartPage;

import java.util.concurrent.TimeUnit;


public class LoginTest extends BaseTest {
    private final static String LOGIN_PAGE_URL = "https://www.saucedemo.com/";
    private String productName;


    @BeforeMethod
    private void navigateToLoginTest() {
        loginPage.open();
    }

    @AfterMethod
    public void clearCookies() {
        driver.manage().deleteAllCookies();
    }

    //Pattern Fluent/Chain of Invocations
    @Test
    public void goToShoppingPage() {
        productName = "Sauce Labs Bolt T-Shirt";
        int productsCount = loginPage.login(USERNAME, PASSWORD)
                .clickAddToCartButton(productName)
                .clickToCartLink()
                .getProductsCount();
        Assert.assertEquals(productsCount, 1);
    }
}








