package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.base.BasePage;

// This CheckoutPage class extends the BasePage class and provides methods specific to the checkout page.
public class CheckoutPage extends BasePage {

    // Locators for various elements on the checkout page.
    private final By firstNameInput = By.xpath("//input[@id='first-name']");
    private final By lastNameInput = By.xpath("//input[@id='last-name']");
    private final By postalCodeInput = By.xpath("//input[@id='postal-code']");
    private final By continueButton = By.xpath("//input[@data-test='continue']");

    // Constructor to initialize the WebDriver.
    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    // Method to fill the checkout information fields with random data.
    public void fillCheckoutInformation(String randomData) {
        cleanText(firstNameInput);
        sendText(firstNameInput, randomData);
        cleanText(lastNameInput);
        sendText(lastNameInput, randomData);
        cleanText(postalCodeInput);
        sendText(postalCodeInput, randomData);
        util.allureCaptureScreenshotRe(driver);
    }

    // Method to click on the continue button on the checkout page.
    public void clickOnContinueButtonOnCheckoutPage() {
        click(continueButton);
        waitForPageToBeLoaded();
        util.allureCaptureScreenshotRe(driver);
    }
}

