import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


public class SauceDemoTest {
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
    public void loginAndAddItemTest() {

        WebElement inputUserName = driver.findElement(By.xpath("//input[@id='user-name']"));
        inputUserName.sendKeys("standard_user");
        WebElement inputPassword = driver.findElement(By.cssSelector("input[name='password']"));
        inputPassword.sendKeys("secret_sauce");
        WebElement buttonLogin = driver.findElement(By.cssSelector("input[id='login-button']"));
        buttonLogin.click();
        WebElement buttonAddToCart = driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-backpack']"));
        buttonAddToCart.click();
        WebElement buttonCart = driver.findElement(By.cssSelector("a[class='shopping_cart_link']"));
        buttonCart.click();
        WebElement priceItem = driver.findElement(By.xpath("//div[text()='29.99']"));
        WebElement nameItem = driver.findElement(By.xpath("//div[text()='Sauce Labs Backpack']"));
        Assert.assertEquals(priceItem.getText(), "$29.99");
        Assert.assertEquals(nameItem.getText(), "Sauce Labs Backpack");

    }

}
