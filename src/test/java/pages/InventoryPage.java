package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pages.base.BasePage;

public class InventoryPage extends BasePage {
    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    String destinationURL = "https://www.saucedemo.com/inventory.html";

    public void landingInventory() {
        Assert.assertEquals(destinationURL, driver.getCurrentUrl());
    }
}
