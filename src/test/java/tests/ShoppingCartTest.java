package tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.ShoppingCartPage;

public class ShoppingCartTest extends BaseShoppingCartTest {

    private final static String PRODUCT_PAGE_URL = "https://www.saucedemo.com/inventory.html";
    private String productName;
    private ShoppingCartPage shoppingCartPage;

    @BeforeClass(alwaysRun = true)
    public void setShoppingCartTests() {
        shoppingCartPage = new ShoppingCartPage(driver);
    }

    @AfterMethod
    public void clearCookie(){
        driver.manage().deleteAllCookies();
        loginPage.logout();
    }
//By separately tests is going.Together two tests fall

    @Test
    public void verifyDescriptionItemInCart() {
        productName = "Sauce Labs Backpack";
        //login
        loginPage.login(USERNAME, PASSWORD);
        //click to item add button
        productPage.clickAddToCartButton(productName);
        //go to cart page
        productPage.clickToCartLink();
        //checking product description
        Assert.assertEquals(shoppingCartPage.getProductDescription(), "carry.allTheThings() with the sleek," +
                " streamlined Sly Pack that melds uncompromising " +
                "style with unequaled laptop and tablet protection.");
    }
    @Test
    public void backToContinueShopping(){
        productName = "Sauce Labs Backpack";
        //login
        loginPage.login(USERNAME, PASSWORD);
        //click to item add button
        productPage.clickAddToCartButton(productName);
        //go to cart page
        productPage.clickToCartLink();
        //click to button "continue shopping"
        shoppingCartPage.clickToContinueShoppingButton();
        Assert.assertEquals(productPage.getCurrentPageUrl(),PRODUCT_PAGE_URL);

    }

    @Test
    public void removeItemFromCart(){
        //I don't understand hot to make Assert...
        productName = "Sauce Labs Onesie";
        //login
        loginPage.login(USERNAME, PASSWORD);
        //click to item add button
        productPage.clickAddToCartButton(productName);
        //go to cart page
        productPage.clickToCartLink();
        //click to remove button
        shoppingCartPage.clickRemoveButton();
        //check what cart is empty
        Assert.assertTrue(productName.isEmpty(),"");

    }
}
