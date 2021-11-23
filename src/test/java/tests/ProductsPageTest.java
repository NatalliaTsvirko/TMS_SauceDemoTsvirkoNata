package tests;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.ProductsPage;
import pages.ShoppingCartPage;

public class ProductsPageTest extends BaseProductsTest {

    protected ProductsPage productPage;

    @BeforeClass(alwaysRun = true)
    public void setProductTests() {
        productPage = new ProductsPage(driver);
        shoppingCartPage = new ShoppingCartPage(driver);
    }

    @AfterMethod
    public void clearCookie() {
        driver.manage().deleteAllCookies();
    }

    //Using LoadablePage pattern
    @Test
    public void loginPageIsOpened() {
        loginPage.open();
        boolean isLoginPageOpened = loginPage.isPageOpened();
        Assert.assertTrue(isLoginPageOpened, "Login page should be opened");
        loginPage.login(USERNAME, PASSWORD);
        Assert.assertTrue(productPage.isPageOpened(), "Product page should be opened");
    }
}
