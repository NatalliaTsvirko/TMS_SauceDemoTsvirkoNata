package tests;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.ProductsPage;
import pages.ShoppingCartPage;
import java.util.List;



public class ProductsPageTest extends BaseProductsTest {

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

    }

    @Test
    public void verifyCountItemOnProductPage() {
        //login
        loginPage.login(USERNAME, PASSWORD);
        //count product on page
        Assert.assertEquals(productPage.getProductsCount(), "");

    }

//С этим тестом я запуталась и мне нужна твоя помощь
    @Test
    public void filterOnProductPage() {
        //login
        loginPage.login(USERNAME, PASSWORD);
        //make object dropdownFilter and find element
        List<WebElement> dropdownFilter =  productPage.openFilter();
        //make variable "options"
        //String options = "lohi";
        Assert.assertEquals(dropdownFilter.get(2),OPTION_PRICE_LOW_TO_HIGH);
    }

    @Test
    public void addItemToCart() {
        //login
        loginPage.login(USERNAME, PASSWORD);
        //assign value to variable
        productName = "Sauce Labs Bolt T-Shirt";
        //add to cart
        productPage.clickAddToCartButton(productName);
        //go to cart
        productPage.clickToCartLink();
        //check quantity in cart
        Assert.assertEquals(shoppingCartPage.quantityItemInCart(), "1");
    }
}
