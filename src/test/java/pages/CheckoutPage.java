package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.base.BasePage;

public class CheckoutPage extends BasePage {

    private final By firstNameInput = By.xpath("//input[@id='first-name']");
    private final By lastNameInput = By.xpath("//input[@id='last-name']");
    private final By postalCodeInput = By.xpath("//input[@id='postal-code']");
    private final By continueButton = By.xpath("//input[@data-test='continue']");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void fillCheckoutInformation(String randomData) {
        cleanText(firstNameInput);
        sendText(firstNameInput, randomData);
        cleanText(lastNameInput);
        sendText(lastNameInput, randomData);
        cleanText(postalCodeInput);
        sendText(postalCodeInput, randomData);
        util.allureCaptureScreenshotRe(driver);
    }

    public void clickOnContinueButtonOnCheckoutPage() {
        click(continueButton);
        waitForPageToBeLoaded();
        util.allureCaptureScreenshotRe(driver);
    }
}
