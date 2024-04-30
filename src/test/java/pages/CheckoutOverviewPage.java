package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.base.BasePage;

import java.util.List;

public class CheckoutOverviewPage extends BasePage {

    private final By finishButton = By.xpath("//button[@id='finish']");
    private final By thankYouMessage = By.xpath("//h2[@class='complete-header'][normalize-space()]");
    private final By itemTotalValue = By.xpath("//div[@class='summary_subtotal_label'][normalize-space()]");

    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
    }

    public void clickOnFinishOnCheckoutOverviewPage() {
        click(finishButton);
        waitForPageToBeLoaded();
        util.allureCaptureScreenshotRe(driver);
    }

    public void captureAMessageFromCheckoutCompletePage(String message) {
        String actualMessage = getText(thankYouMessage);
        assertElementPresent(thankYouMessage);
        Assert.assertEquals(actualMessage, message);
        util.allureCaptureScreenshotRe(driver);
    }

    public void verifyItemTotalIsSameFirstProductPriceCaptured(List<String> productPrices) {
        String totalValue = getText(itemTotalValue);
        String[] totalValueSplit = totalValue.split("total: ");
        String itemTotal = totalValueSplit[1];
        Assert.assertEquals(productPrices.get(0), itemTotal);
        util.allureCaptureScreenshotRe(driver);
    }
}
