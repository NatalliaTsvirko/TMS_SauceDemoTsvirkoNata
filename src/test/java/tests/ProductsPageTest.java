package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.ProductsPage;

import java.util.List;

public class ProductsPageTest extends BaseProductsTest {
    private final static int EXPECTED_PRODUCT_COUNT = 6;
    private final static By FILTER = By.cssSelector("select[class='product_sort_container']");
    private String productName;
    private ProductsPage productPage;


    @BeforeClass(alwaysRun = true)
    public void setProductTests() {
        productPage = new ProductsPage(driver);
    }

    @AfterMethod
    public void clearCookie() {
        driver.manage().deleteAllCookies();
        loginPage.logout();
    }

    @Test
    public void verifyCountItemOnProductPage() {
        //login
        loginPage.login(USERNAME, PASSWORD);
        //count product on page
        Assert.assertEquals(productPage.getProductsCount(), 6);

    }

    @Test
    public void filterOnProductPage() {
        //login
        loginPage.login(USERNAME, PASSWORD);
        //make object dropdownFilter and find element
        Select dropdownFilter = new Select(driver.findElement(FILTER));
        //make variable "options"
        String options = "lohi";
        //select options by value
        dropdownFilter.selectByValue(options);
        //I don't know how to check
        //Assert.assertEquals("Price (low to high)");

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
