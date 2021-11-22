package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.ProductsPage;
import pages.ShoppingCartPage;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;


public class ShoppingCartTest extends BaseProductsTest {

    private final static String ITEM_DESCRIPTION_BACKPACK = "carry.allTheThings() with the sleek, " +
            "streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.";
    private final static int EXPECTED_PRODUCT_COUNT = 6;
    private final static By PRODUCT_PRICE_ANY_ITEM = By.cssSelector("div[class='inventory_item_price']");
    private final static By QUANTITY_ITEM = By.cssSelector(".cart_quantity");

    protected ShoppingCartPage shoppingCartPage;
    private String productName;

    @BeforeClass(alwaysRun = true)
    public void setShoppingCartTests() {
        shoppingCartPage = new ShoppingCartPage(driver);
        productPage = new ProductsPage(driver);
    }

    @AfterMethod
    public void clearCookie(){
        driver.manage().deleteAllCookies();
    }


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
        assertEquals(shoppingCartPage.getProductDescription(productName),ITEM_DESCRIPTION_BACKPACK);
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
        assertEquals(productPage.getProductsCount(),EXPECTED_PRODUCT_COUNT);

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
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        int numberOfElements = driver.findElements(PRODUCT_PRICE_ANY_ITEM).size();
        assertEquals(numberOfElements, 0, "Element on page");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);


    }
}
