package tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.ProductsPage;
import pages.ShoppingCartPage;
import utils.AllureUtils;


public class ProductsPageTest extends BaseProductsTest{

    private static final String OPTION_PRICE_LOW_TO_HIGH = "Price (low to high)";

    protected ProductsPage productPage;
    private String productName;


    @BeforeClass(alwaysRun = true)
    public void setProductTests() {
        productPage = new ProductsPage(driver);
        shoppingCartPage = new ShoppingCartPage(driver);
    }

    @AfterMethod
    public void clearCookie() {
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();

    }

    @Test(description = "verify count item on product page",groups = {"Regression"})
    public void verifyCountItemOnProductPage() {
        loginPage.login(USERNAME, PASSWORD);
        Assert.assertEquals(productPage.getProductsCount(), 6);
    }

    @Test(description = "Add item in shopping cart",dataProvider = "Add item in shopping cart",groups = {"Smoke"})
    public void addItemToCart(String username, String password,String productName) {
        loginPage.login(username, password);
        productPage.clickAddToCartButton(productName);
        productPage.clickToCartLink();
        AllureUtils.attachScreenshot(driver);
        Assert.assertEquals(shoppingCartPage.getProductName(productName), productName);
    }

    @DataProvider(name = "Add item in shopping cart")
    public Object[][] getAddItemInCart() {
        return new Object[][]{
                {"standard_user", "secret_sauce", "Sauce Labs Backpack"},
                {"standard_user", "secret_sauce", "Sauce Labs Bolt T-Shirt"},
                {"standard_user", "secret_sauce","Sauce Labs Bike Light"},
                {"standard_user", "secret_sauce","Sauce Labs Fleece Jacket"},
                {"standard_user", "secret_sauce","Sauce Labs Onesie"},
                {"standard_user", "secret_sauce","Test.allTheThings() T-Shirt (Red)"}
        };
    }
}
