package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class SauceDemo {
    protected WebDriver driver;

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://www.saucedemo.com/");

    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }


    @Test
    public void locatorsTest() {

        //by xpath
        WebElement errorMessage = driver.findElement(By.xpath("//h3[contains(text(),'Epic sadface:')]"));
        //by attribute
        WebElement inputUserName = driver.findElement(By.xpath("//input[@id='user-name']"));
        WebElement inputUserName2 = driver.findElement(By.xpath("//input[contains(@placeholder,'Username')]"));
        WebElement inputPassword = driver.findElement(By.xpath("//input[@name='password']"));
        WebElement inputPassword2 = driver.findElement(By.xpath("//input[contains(@data-test,'password')]"));
        WebElement buttonLogin = driver.findElement(By.xpath("//input[@id='login-button']"));
        //by attribute all "id"
        WebElement linkShoppingCart = driver.findElement(By.xpath("//*[@id='shopping_cart_container']"));
        //by text
        WebElement removeButtonInShoppingCart = driver.findElement(By.xpath("//button[text()='Remove')]"));
        //by following
        WebElement quantityItems = driver.findElement(By.xpath("//div[@id='cart_contents_container']//following::div[@class='cart_quantity']"));
        //by preceding-sibling
        WebElement infoPrice = driver.findElement(By.xpath("//button[contains(text(),'Remove')]/preceding-sibling::div[@class='inventory_item_price']"));
        //just try search by ancestor
        WebElement cartList = driver.findElement(By.xpath("//div[text()='Sauce Labs Backpack']/ancestor::div[@class='cart_list']"));
        //by partial text match
        WebElement itemDesc = driver.findElement(By.xpath("//div[contains(text(),'Get your testing')]"));
        //just try following-sibling
        WebElement buttonCheckout = driver.findElement(By.xpath("//button[@id='continue-shopping']/following-sibling::button[text()='Checkout']"));
        //by text
        WebElement buttonCheckout1 = driver.findElement(By.xpath("//button[text()='Checkout']"));
        //by parent
        WebElement navItemList = driver.findElement(By.xpath("//a[@id='reset_sidebar_link']//parent::nav"));
        //
        WebElement buttonBackToProducts = driver.findElement(By.xpath("//button[text()='Back to products']"));
        //by text()
        WebElement buttonOpenMenu = driver.findElement(By.xpath("//button[text()='Open Menu']"));
        WebElement bmListAllItems = driver.findElement(By.xpath("//a[text()='All Items']"));
        WebElement bmListAbout = driver.findElement(By.xpath("//a[text()='About']"));
        WebElement bmListLogout = driver.findElement(By.xpath("//a[text()='Logout']"));
        WebElement bmListReset = driver.findElement(By.xpath("//a[text()='Reset App State']"));
        WebElement buttonCloseMenu = driver.findElement(By.xpath("//button[text()='Close Menu']"));
        //filter list by following with partial match
        WebElement filterListAToZ = driver.findElement(By.xpath("//span[@class='active_option']//following::option[contains(text(),'Name (A to Z)')]"));
        WebElement filterListZToA = driver.findElement(By.xpath("//span[@class='active_option']//following::option[contains(text(),'Name (Z to A)')]"));
        WebElement filterListPriceLowToHigh = driver.findElement(By.xpath("//span[@class='active_option']//following::option[contains(text(),'Price (low to high)')]"));
        WebElement filterListPriceHighToLow = driver.findElement(By.xpath("//span[@class='active_option']//following::option[contains(text(),'Price (high to low)')]"));
        //
        //by full text (name item)
        WebElement nameItemBackpack = driver.findElement(By.xpath("//div[text()='Sauce Labs Backpack']"));
        WebElement nameItemBikeLight = driver.findElement(By.xpath("//div[text()='Sauce Labs Bike Light']"));
        WebElement nameItemBoltTShirt = driver.findElement(By.xpath("//div[text()='Sauce Labs Bolt T-Shirt']"));
        WebElement nameItemFirst = driver.findElement(By.xpath("//div[text()='Sauce Labs Fleece Jacket']"));
        WebElement nameItemFleeceJacket = driver.findElement(By.xpath("//div[text()='Sauce Labs Onesie']"));
        WebElement nameItemTestTShirt = driver.findElement(By.xpath("//div[text()='Test.allTheThings() T-Shirt (Red)']"));
        //imgItemBackpack not search but another imgItem is found
        WebElement imgItemBackpack = driver.findElement(By.xpath("//div[@class='inventory_list']//following::img[@alt='Sauce Labs Backpack']"));
        WebElement imgItemBikeLight = driver.findElement(By.xpath("//div[@class='inventory_list']//following::img[@alt='Sauce Labs Bike Light']"));
        WebElement imgItemBoltTShirt = driver.findElement(By.xpath("//div[@class='inventory_list']//following::img[@alt='Sauce Labs Bolt T-Shirt']"));
        WebElement imgItemFleeceJacket = driver.findElement(By.xpath("//div[@class='inventory_list']//following::img[@alt='Sauce Labs Fleece Jacket']"));
        WebElement imgItemLabsOnesie = driver.findElement(By.xpath("//div[@class='inventory_list']//following::img[@alt='Sauce Labs Onesie']"));
        WebElement imgItemTestTShirt = driver.findElement(By.xpath("//div[@class='inventory_list']//following::img[@alt='Test.allTheThings() T-Shirt (Red)']"));
        //item info
        WebElement infoItemBackpack = driver.findElement(By.xpath("//div[contains(text(),'carry.allTheThings()')]"));
        WebElement infoItemBikeLight = driver.findElement(By.xpath("//div[contains(text(),'A red light')]"));
        WebElement infoItemBoltTShirt = driver.findElement(By.xpath("//div[contains(text(),'Get your testing')]"));
        WebElement infoItemFleeceJacket = driver.findElement(By.xpath("//div[contains(text(),'not every day')]"));
        WebElement infoItemLabsOnesie = driver.findElement(By.xpath("//div[contains(text(),'Rib snap infant')]"));
        WebElement infoItemTestTShirt = driver.findElement(By.xpath("//div[contains(text(),'This classic Sauce')]"));

        //by css
        WebElement inputUserNameCss = driver.findElement(By.cssSelector("input[id='user-name']"));
        WebElement inputUserNameSecond = driver.findElement(By.cssSelector("#user-name")); //no sure
        WebElement inputPasswordByName = driver.findElement(By.name("password"));
        WebElement buttonLoginById = driver.findElement(By.id("login-button"));
        WebElement inventoryItem = driver.findElement(By.className("[class~='inventory_item']"));
        WebElement bmAllElements = driver.findElement(By.cssSelector("a[class^='bm']"));
        WebElement allElementsHatch = driver.findElement(By.cssSelector("a[href$='#']"));
        WebElement allElementsWhoContainsBtn = driver.findElement(By.cssSelector("button[class*='btn']"));


    }
}
