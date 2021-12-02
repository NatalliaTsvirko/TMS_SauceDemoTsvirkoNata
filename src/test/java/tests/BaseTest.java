package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;
import pages.BasePage;
import pages.LoginPage;
import pages.ProductsPage;
import pages.ShoppingCartPage;

import java.util.concurrent.TimeUnit;

@Listeners(TestListener.class)
public abstract class BaseTest  {
    final static String USERNAME = "standard_user";
    final static String PASSWORD = "secret_sauce";
    final static String PRODUCT_PAGE_URL = "https://www.saucedemo.com/inventory.html";

    protected WebDriver driver;
    protected LoginPage loginPage;
    protected ProductsPage productPage;
    protected ShoppingCartPage shoppingCartPage;


    @Parameters({"browser"})
    @BeforeClass(alwaysRun = true)
    public void setUp(@Optional("chrome") String browser) {
        driver = DriverFactory.getDriver(browser);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
        loginPage = new LoginPage(driver);
        productPage = new ProductsPage(driver);
        shoppingCartPage = new ShoppingCartPage(driver);
        driver.get("https://www.saucedemo.com/");
    }


    @AfterClass(alwaysRun = true)
    public void tearDown() {
        driver.quit();

    }

}
