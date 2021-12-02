package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.ProductsPage;
import pages.ShoppingCartPage;

import java.util.concurrent.TimeUnit;

import static org.testng.AssertJUnit.assertEquals;
import static tests.BaseTest.PASSWORD;
import static tests.BaseTest.USERNAME;

public class ShoppingCartTest extends BaseProductsTest{

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
        driver.navigate().refresh();

    }


    @Test(dataProvider = "Negative test on description items",groups = {"Negative"})
    public void verifyDescriptionItemInCart(String productName, String itemDescription) {
        loginPage.login(USERNAME,PASSWORD);
        productPage.clickAddToCartButton(productName);
        productPage.clickToCartLink();
        assertEquals(shoppingCartPage.getProductDescription(productName),itemDescription);
    }

    @DataProvider(name = "Negative test on description items")
    public Object[][] getDescriptionItem() {
        return new Object[][]{
                { "Sauce Labs Backpack","carry.allTheThings() with the sleek, " +
                        "streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection."},
                {"Sauce Labs Bolt T-Shirt","carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising" +
                        " style with unequaled laptop and tablet protection."},
                {"Sauce Labs Bike Light","Get your testing superhero on with the Sauce Labs bolt T-shirt. " +
                        "From American Apparel, 100% ringspun combed cotton, heather gray with red bolt."},
                {"Sauce Labs Fleece Jacket","A red light isn't the desired state in testing but it sure helps when riding your bike at night." +
                        " Water-resistant with 3 lighting modes, 1 AAA battery included."},
                {"Sauce Labs Onesie","Rib snap infant onesie for the junior automation engineer in development." +
                        " Reinforced 3-snap bottom closure, two-needle hemmed sleeved and bottom won't unravel."},
                {"Test.allTheThings() T-Shirt (Red)",""}
        };
    }

    @Test(groups = {"Regression"})
    public void backToContinueShopping(){
        productName = "Sauce Labs Backpack";
        loginPage.login(USERNAME, PASSWORD);
        productPage.clickAddToCartButton(productName);
        productPage.clickToCartLink();
        shoppingCartPage.clickToContinueShoppingButton();
        assertEquals(productPage.getProductsCount(),EXPECTED_PRODUCT_COUNT);

    }

    @Test(retryAnalyzer = ReTry.class,groups = {"Smoke"})
    public void removeItemFromCart(){
        productName = "Sauce Labs Onesie";
        loginPage.login(USERNAME, PASSWORD);
        productPage.clickAddToCartButton(productName);
        productPage.clickToCartLink();
        shoppingCartPage.clickRemoveButton();
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        int numberOfElements = driver.findElements(PRODUCT_PRICE_ANY_ITEM).size();
        Assert.assertEquals(numberOfElements, 0, "Element on page");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    }
}
