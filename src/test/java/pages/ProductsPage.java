package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProductsPage extends BasePage {
    private final static By PRICE_LOCATOR = By.cssSelector(".inventory_item_price");
    private final static By DESCRIPTION = By.cssSelector(".inventory_item_desc");
    private final static By ADD_TO_CART_BUTTON = By.cssSelector("button[name*='add-to-cart-']");
    private final static By CART_LINK = By.cssSelector("a[class='shopping_cart_link']");
    private final static By FILTER = By.cssSelector("select[class='product_sort_container']");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public void openFilter() {
        driver.findElement(FILTER).click();
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

    public void clickAddToCartButton(String productName) {
        WebElement itemContainer = getItemContainer(productName);
        itemContainer.findElement(ADD_TO_CART_BUTTON).click();
    }

    public void clickToCartLink() {
        driver.findElement(CART_LINK).click();
    }

    public int getProductsCount() {
        List<WebElement> countItem = driver.findElements(By.cssSelector("div[class='inventory_item']"));
        int count = countItem.size();
        return countItem.size();
    }

    private WebElement getItemContainer(String productName) {
        return driver.findElement(By.xpath("//div[contains(text(), '" + productName + "')]/ancestor::div[@class='inventory_item']"));
    }

}

