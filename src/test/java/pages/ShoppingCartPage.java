package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ShoppingCartPage extends BasePage {

    private final static By PRICE_LOCATOR = By.cssSelector(".inventory_item_price");
    private final static By REMOVE_FROM_CART_BUTTON = By.cssSelector("button[id^='remove-']");
    private final static By QUANTITY_ITEM = By.cssSelector(".cart_quantity");
    private final static By CHECKOUT_BUTTON = By.cssSelector("button[id='checkout']");
    private final static By CONTINUE_SHOPPING_BUTTON = By.cssSelector("button[id='continue-shopping']");
    private final static By DESCRIPTION = By.cssSelector(".inventory_item_desc");
    private final static By OPEN_MENU_BUTTON = By.cssSelector("button[id='react-burger-menu-btn']");


    public ShoppingCartPage(WebDriver driver) {
        super(driver);
    }
    public void clickToContinueShoppingButton(){
        driver.findElement(CONTINUE_SHOPPING_BUTTON).click();
    }

    public String quantityItemInCart(){
       return driver.findElement(QUANTITY_ITEM).getText();

    }

    public String getProductDescription() {
        return driver.findElement(DESCRIPTION).getText();
    }

    private WebElement getItemContainer(String productName) {
        return driver.findElement(By.xpath("//div[contains(text(), '" + productName + "')]/ancestor::div[@class='inventory_item']"));
    }

    public void clickRemoveButton() {
       driver.findElement(REMOVE_FROM_CART_BUTTON).click();
    }

    public String getProductPrice(String productName) {
        WebElement itemContainer = getItemContainer(productName);
        return itemContainer.findElement(PRICE_LOCATOR).getText();
    }
}
