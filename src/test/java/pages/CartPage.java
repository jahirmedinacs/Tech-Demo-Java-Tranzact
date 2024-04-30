package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pages.base.BasePage;

import java.util.ArrayList;
import java.util.List;

// This CartPage class extends the BasePage class and provides methods specific to the cart page.
public class CartPage extends BasePage {

    // Locators for various elements on the cart page.
    private final String btnByNameOnCartPage = "//button[normalize-space()='%s']";
    private final String removeItemByNameFromCart = "//div[@class='inventory_item_name'][normalize-space()='%s']/../..//button[normalize-space()='Remove']";
    private final String quantityOfItemByName = "//div[@class='inventory_item_name'][normalize-space()='%s']/../../../div[@class='cart_quantity'][normalize-space()]";
    private final By numberOfItemsOnCart = By.xpath("//span[@class='shopping_cart_badge'][normalize-space()]");
    private final String itemPriceProductCartPage = "//div[@class='cart_item_label']//div[@class='inventory_item_name'][normalize-space()='%s']/../..//div[@class='inventory_item_price']";

    // List to store prices of products on the cart page.
    private List<String> priceFromProductsOnCartPage = new ArrayList<>();

    // Variables to store item quantity and cart value.
    private String itemQuantity = "";
    private String cartValue = "";

    // Constructor to initialize the WebDriver.
    public CartPage(WebDriver driver) {
        super(driver);
    }

    // Method to click a button on the cart page.
    public void clickOnButtonOnCartPage(String buttonName) {
        click(By.xpath(util.format(btnByNameOnCartPage, buttonName)));
        waitForPageToBeLoaded();
        util.allureCaptureScreenshotRe(driver);
    }

    // Method to remove a product by name from the cart page.
    public void removeProductByNameOnCartPage(String productName) {
        click(By.xpath(util.format(removeItemByNameFromCart, productName)));
        waitForPageToBeLoaded();
        util.allureCaptureScreenshotRe(driver);
    }

    // Method to capture the quantity of items added to the cart from a name on the cart page.
    public void captureTheQuantityOfItemsAddedToCartFromANameOnTheCartPage(String productName) {
        itemQuantity = getText(By.xpath(util.format(quantityOfItemByName, productName)));
        util.allureCaptureScreenshotRe(driver);
    }

    // Method to capture the value of the cart from the cart page.
    public void captureTheValueOfCartFromTheCartPage() {
        cartValue = getText(numberOfItemsOnCart);
        util.allureCaptureScreenshotRe(driver);
    }

    // Method to verify the quantity and value are the same as captured on the cart page.
    public void verifyQuantityAndValueAreTheSameAsCapturedOnCartPage() {
        Assert.assertEquals(itemQuantity, cartValue);
        util.allureCaptureScreenshotRe(driver);
    }

    // Method to save the price of a product by name from the cart page.
    public List<String> saveThePriceOfProductByNameFromCartPage(String productName) {
        By element = By.xpath(util.format(itemPriceProductCartPage, productName));
        assertElementPresent(element);
        String itemCartPage = getText(element);
        priceFromProductsOnCartPage.add(itemCartPage);
        System.out.println("Price from products on cart page: " + priceFromProductsOnCartPage);
        util.allureCaptureScreenshotRe(driver);
        return priceFromProductsOnCartPage;
    }

    // Method to verify the value from the cart is a specific number.
    public void verifyTheValueFromCartIs(String number) {
        WebElement numberOfItemsOnCartElement = driver.findElement(numberOfItemsOnCart);
        Assert.assertEquals(numberOfItemsOnCartElement.getText(), number);
        util.allureCaptureScreenshotRe(driver);
    }
}

