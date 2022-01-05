package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ShoppingCartPage extends BasePage {
    private final static By PRICE_LOCATOR = By.cssSelector(".inventory_item_price");
    private final static By REMOVE_FROM_CART_BUTTON = By.cssSelector("button[id^='remove-']");
    private final static By QUANTITY_ITEM = By.cssSelector(".cart_quantity");
    private final static By CHECKOUT_BUTTON = By.cssSelector("button[id='checkout']");
    private final static By CONTINUE_SHOPPING_BUTTON = By.cssSelector("button[id='continue-shopping']");
    private final static By DESCRIPTION = By.cssSelector(".inventory_item_desc");
    private final static By OPEN_MENU_BUTTON = By.cssSelector("button[id='react-burger-menu-btn']");
    private static final By LOGOUT_LINK = By.id("logout_sidebar_link");
    private final static String PRODUCT_PAGE_URL = "https://www.saucedemo.com/inventory.html";
    private final static By PRODUCT_NAME_LOCATOR = By.cssSelector(".inventory_item_name");



    public ShoppingCartPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageOpened() {
        return false;
    }

    @Override
    public BasePage open() {
        return null;
    }

    public void clickToContinueShoppingButton(){
        driver.findElement(CONTINUE_SHOPPING_BUTTON).click();
    }

    public String quantityItemInCart(){
        return driver.findElement(QUANTITY_ITEM).getText();

    }
    public boolean isProductNameDisplayed(String productName) {
        WebElement itemContainer = getItemContainer(productName);
        return itemContainer.findElement(PRODUCT_NAME_LOCATOR).isDisplayed();
    }

    @Step("Get product description")
    public String getProductDescription(String productName) {
        WebElement itemContainer = getItemContainer(productName);
        return itemContainer.findElement(DESCRIPTION).getText();

    }
    @Step("Get product name")
    public String getProductName(String productName) {
        WebElement itemContainer = getItemContainer(productName);
        return itemContainer.findElement(PRODUCT_NAME_LOCATOR).getText();

    }

    private WebElement getItemContainer(String productName) {
        return driver.findElement(By.xpath("//div[contains(text(), '" + productName + "')]/ancestor::div[@class='cart_item_label']"));
    }
    @Step("Click button 'remove' ")
    public void clickRemoveButton() {
        driver.findElement(REMOVE_FROM_CART_BUTTON).click();
    }

    public String getProductPrice(String productName) {
        WebElement itemContainer = getItemContainer(productName);
        return itemContainer.findElement(PRICE_LOCATOR).getText();
    }

    public void logout(){
        driver.findElement(OPEN_MENU_BUTTON).click();
        driver.findElement(LOGOUT_LINK).click();
    }

    public int getProductsCount() {
        List<WebElement> itemCount = driver.findElements(By.cssSelector(".cart_item"));
        int count = itemCount.size();
        return count;
    }

    public void waitVerifyThatItemDontHaveOnPage(){
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }
}
