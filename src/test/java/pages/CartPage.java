package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pages.base.BasePage;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePage {

    private final String btnByNameOnCartPage = "//button[normalize-space()='%s']";
    private final String removeItemByNameFromCart = "//div[@class='inventory_item_name'][normalize-space()='%s']/../..//button[normalize-space()='Remove']";
    private final String quantityOfItemByName = "//div[@class='inventory_item_name'][normalize-space()='%s']/../../../div[@class='cart_quantity'][normalize-space()]";
    private String itemQuantity = "";
    private String cartValue = "";
    private final By numberOfItemsOnCart = By.xpath("//span[@class='shopping_cart_badge'][normalize-space()]");
    private final String itemPriceProductCartPage = "//div[@class='cart_item_label']//div[@class='inventory_item_name'][normalize-space()='%s']/../..//div[@class='inventory_item_price']";
    private List<String> priceFromProductsOnCartPage = new ArrayList<>();

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void clickOnButtonOnCartPage(String buttonName) {
        click(By.xpath(util.format(btnByNameOnCartPage, buttonName)));
        waitForPageToBeLoaded();
        util.allureCaptureScreenshotRe(driver);
    }

    public void removeProductByNameOnCartPage(String productName) {
        click(By.xpath(util.format(removeItemByNameFromCart, productName)));
        waitForPageToBeLoaded();
        util.allureCaptureScreenshotRe(driver);
    }

    public void captureTheQuantityOfItemsAddedToCartFromANameOnTheCartPage(String productName) {
        itemQuantity = getText(By.xpath(util.format(quantityOfItemByName, productName)));
        util.allureCaptureScreenshotRe(driver);
    }

    public void captureTheValueOfCartFromTheCartPage() {
        cartValue = getText(numberOfItemsOnCart);
        util.allureCaptureScreenshotRe(driver);
    }

    public void verifyQuantityAndValueAreTheSameAsCapturedOnCartPage() {
        Assert.assertEquals(itemQuantity, cartValue);
        util.allureCaptureScreenshotRe(driver);
    }

    public List<String> saveThePriceOfProductByNameFromCartPage(String productName) {
        By element = By.xpath(util.format(itemPriceProductCartPage, productName));
        assertElementPresent(element);
        String itemCartPage = getText(element);
        priceFromProductsOnCartPage.add(itemCartPage);
        System.out.println("Price from products on cart page: " + priceFromProductsOnCartPage);
        util.allureCaptureScreenshotRe(driver);
        return priceFromProductsOnCartPage;
    }

    public void verifyTheValueFromCartIs(String number) {
        WebElement numberOfItemsOnCartElement = driver.findElement(numberOfItemsOnCart);
        Assert.assertEquals(numberOfItemsOnCartElement.getText(), number);
        util.allureCaptureScreenshotRe(driver);
    }
}
