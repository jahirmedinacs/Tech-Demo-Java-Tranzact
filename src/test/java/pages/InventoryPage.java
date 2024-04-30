package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pages.base.BasePage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class InventoryPage extends BasePage {

    private final String addProductByNameButton = "//div[@class='inventory_item_name '][normalize-space()='%s']/ancestor::div[@class='inventory_item_description']//button[normalize-space()='Add to cart']";
    private final By cartIcon = By.xpath("//a[@data-test='shopping-cart-link']");
    private final By sortTypeButton = By.xpath("//select[@data-test='product-sort-container']");
    private final String sortTypeByName = "//select/option[normalize-space()='%s']";
    private final By currentSortType = By.xpath("//span[@class='active_option']");
    private final By itemPrices = By.xpath("//div[@class='inventory_item_price']");
    private final String removeProductByNameButton = "//div[@class='inventory_item_name '][normalize-space()='%s']/ancestor::div[@class='inventory_item_description']//button[normalize-space()='Remove']";
    private final String itemPriceProductName = "//div[@class='inventory_item_name '][normalize-space()='%s']/ancestor::div[@class='inventory_item_description']//div[@data-test='inventory-item-price']";
    private List<String> priceFromProductsOnProductsPage = new ArrayList<>();

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    String destinationURL = "https://www.saucedemo.com/inventory.html";

    public void landingInventory() {
        Assert.assertEquals(destinationURL, driver.getCurrentUrl());
    }

    public void addProductByNameToCart(String productName) {
        click(By.xpath(util.format(addProductByNameButton, productName)));
        waitForPageToBeLoaded();
        util.allureCaptureScreenshotRe(driver);
    }

    public void clickOnCartIcon() {
        click(cartIcon);
        waitForPageToBeLoaded();
        util.allureCaptureScreenshotRe(driver);
    }

    public void clickOnSortTypeButtonOnProductsPage() {
        click(sortTypeButton);
        waitForPageToBeLoaded();
        util.allureCaptureScreenshotRe(driver);
    }

    public void changeProductSortByType(String sortType) {
        click(By.xpath(util.format(sortTypeByName, sortType)));
        waitForPageToBeLoaded();
        util.allureCaptureScreenshotRe(driver);
    }

    public void verifyTheDisplayedNameOnTheSortFilterIs(String sortType) {
        WebElement sortTypeElement = driver.findElement(currentSortType);
        Assert.assertEquals(sortTypeElement.getText().trim(), sortType);
        util.allureCaptureScreenshotRe(driver);
    }

    public void verifyPricesFromProductsAreInAscendingOrder() {
        List<String> itemPricesString = getTexts(itemPrices);
        List<Double> itemPricesDouble = itemPricesString.stream()
                .map(price -> Double.parseDouble(price.replace("$", "")))
                .toList();
        boolean isSorted = IntStream.range(1, itemPricesDouble.size())
                .allMatch(i -> itemPricesDouble.get(i - 1) <= itemPricesDouble.get(i));
        Assert.assertTrue(isSorted, "Prices are not in ascending order");
        util.allureCaptureScreenshotRe(driver);
    }

    public void verifyTheRemoveButtonIsEnabledForProductByName(String productName) {
        assertElementPresent(By.xpath(util.format(removeProductByNameButton, productName)));
        util.allureCaptureScreenshotRe(driver);
    }

    public List<String> saveThePriceOfProductNameFromProductPage(String productName) {
        By element = By.xpath(util.format(itemPriceProductName, productName));
        assertElementPresent(element);
        String itemProductPage = getText(element);
        priceFromProductsOnProductsPage.add(itemProductPage);
        System.out.println("Price from products on products page: " + priceFromProductsOnProductsPage);
        util.allureCaptureScreenshotRe(driver);
        return priceFromProductsOnProductsPage;
    }
}
