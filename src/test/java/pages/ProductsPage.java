package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class ProductsPage extends BasePage{

    private final static By FILTER = By.cssSelector("select[class='product_sort_container']");
    private final static By OPTIONS_DROPDOWN = By.tagName("select");
    private final static By PRICE_LOCATOR = By.cssSelector(".inventory_item_price");
    private final static By DESCRIPTION = By.cssSelector(".inventory_item_desc");
    private final static By ADD_TO_CART_BUTTON = By.cssSelector("button[name*='add-to-cart-']");
    private final static By CART_LINK = By.cssSelector("a[class='shopping_cart_link']");
    private static final By MENU = By.id("react-burger-menu-btn");
    private static final By LOGOUT_LINK = By.id("logout_sidebar_link");
    private final static String PRODUCT_PAGE_URL = "https://www.saucedemo.com/inventory.html";

    @Override
    public boolean isPageOpened() {
        return false;
    }

    @Override
    public BasePage open() {
        return null;
    }



    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public List<WebElement> openFilter() {
        driver.findElement(FILTER).click();
        Select dropdownFilter = new Select(driver.findElement(OPTIONS_DROPDOWN));
        return dropdownFilter.getOptions();


    }

    public String openProductDetails(String productName) {
        WebElement itemContainer = getItemContainer(productName);
        return itemContainer.findElement(DESCRIPTION).getText();

    }

    public String getProductPrice(String productName) {
        WebElement itemContainer = getItemContainer(productName);
        return itemContainer.findElement(PRICE_LOCATOR).getText();
    }

    public String getProductDetails(String productName) {
        WebElement itemContainer = getItemContainer(productName);
        return itemContainer.findElement(DESCRIPTION).getText();
    }
    @Step("Click button 'add to cart'")
    public void clickAddToCartButton(String productName) {
        WebElement itemContainer = getItemContainer(productName);
        itemContainer.findElement(ADD_TO_CART_BUTTON).click();
    }
    @Step("Click to cart link")
    public void clickToCartLink() {
        driver.findElement(CART_LINK).click();
    }
    @Step("Get products count on product page")
    public int getProductsCount() {
        List<WebElement> countItem = driver.findElements(By.cssSelector("div[class='inventory_item']"));
        int count = countItem.size();
        return count;
    }

    private WebElement getItemContainer(String productName) {
        return driver.findElement(By.xpath("//div[contains(text(), '" + productName + "')]/ancestor::div[@class='inventory_item']"));
    }

    public void logout(){
        driver.findElement(MENU).click();
        driver.findElement(LOGOUT_LINK).click();
    }

}
