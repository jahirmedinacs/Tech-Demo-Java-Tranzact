package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.base.BasePage;

import java.util.List;

// This CheckoutOverviewPage class extends the BasePage class and provides methods specific to the checkout overview page.
public class CheckoutOverviewPage extends BasePage {

    // Locators for various elements on the checkout overview page.
    private final By finishButton = By.xpath("//button[@id='finish']");
    private final By thankYouMessage = By.xpath("//h2[@class='complete-header'][normalize-space()]");
    private final By itemTotalValue = By.xpath("//div[@class='summary_subtotal_label'][normalize-space()]");

    // Constructor to initialize the WebDriver.
    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
    }

    // Method to click the finish button on the checkout overview page.
    public void clickOnFinishOnCheckoutOverviewPage() {
        click(finishButton);
        waitForPageToBeLoaded();
        util.allureCaptureScreenshotRe(driver);
    }

    // Method to capture a message from the checkout complete page.
    public void captureAMessageFromCheckoutCompletePage(String message) {
        String actualMessage = getText(thankYouMessage);
        assertElementPresent(thankYouMessage);
        Assert.assertEquals(actualMessage, message);
        util.allureCaptureScreenshotRe(driver);
    }

    // Method to verify that the item total is the same as the first product price captured.
    public void verifyItemTotalIsSameFirstProductPriceCaptured(List<String> productPrices) {
        String totalValue = getText(itemTotalValue);
        String[] totalValueSplit = totalValue.split("total: ");
        String itemTotal = totalValueSplit[1];
        Assert.assertEquals(productPrices.get(0), itemTotal);
        util.allureCaptureScreenshotRe(driver);
    }
}

