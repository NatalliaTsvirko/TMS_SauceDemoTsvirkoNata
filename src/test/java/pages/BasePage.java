package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {
    protected final static String URL = "https://www.saucedemo.com/";

    WebDriver driver;
    WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    //add loadablePage pattern
    public abstract boolean isPageOpened();

    public abstract BasePage open();

    protected boolean isElementPresent(By locator) {

        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException exception) {
            System.out.println(exception.getMessage());
            return false;
        }
    }

    public String getCurrentPageUrl() {
        return driver.getCurrentUrl();
    }

    public void waitUntilElementNotDisabled(WebElement element) {
        wait.until(ExpectedConditions.attributeToBeNotEmpty(element, "disabled"));
    }

    public void waitUntilElementNotDisabled1(WebElement element) {
        wait.until(ExpectedConditions.not(ExpectedConditions.attributeToBe(element, "style", "color:red")));
    }

    public void waitUntilElementVisible(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitUntilClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
}
