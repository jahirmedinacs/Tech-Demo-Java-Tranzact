package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.base.BasePage;

// This LoginPage class extends the BasePage class and provides methods specific to the login page.
public class LoginPage extends BasePage {

    // Constructor to initialize the WebDriver.
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // Locators for the username and password input fields.
    private final By userName = By.xpath("//input[@id='user-name']");
    private final By pass = By.xpath("//input[@id='password']");

    // Locator for the login button.
    private final By logginButton = By.xpath("//input[@id='login-button']");

    // Locator for the error message.
    private final By errorMessage = By.xpath("//div[@class='error-message-container error']/h3[normalize-space()]");

    // Method to load the login page.
    public void loadLoginPage() {
        driver.get("https://www.saucedemo.com/");
        util.allureCaptureScreenshotRe(driver);
    }

    // Method to enter a username.
    public void enterUsername(String user) {
        sendText(userName, user);
        util.allureCaptureScreenshotRe(driver);
    }

    // Method to enter a password.
    public void enterPassword(String password) {
        sendText(pass, password);
        util.allureCaptureScreenshotRe(driver);
    }

    // Method to click the login button.
    public void clickLoginButton() {
        click(logginButton);
        util.allureCaptureScreenshotRe(driver);
    }

    // Method to check if an error message indicating the user is locked is displayed.
    public void iShouldSeeAnErrorMessageIndicatingTheUserIsLocked(String message) {
        assertElementPresent(errorMessage);
        Assert.assertEquals(getText(errorMessage), message);
        util.allureCaptureScreenshotRe(driver);
    }

    // Method to check if the user is redirected to the home page or an error message is displayed.
    public void iShouldSee(String expected) {
        if (expected.equals("redirected to the home page")) {
            Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
        } else if (expected.equals("error message indicating the user is locked")) {
            Assert.assertEquals(getText(errorMessage), "Epic sadface: Sorry, this user has been locked out.");
        } else if (expected.equals("error message indicating invalid credentials")){
            Assert.assertEquals(getText(errorMessage), "Epic sadface: Username and password do not match any user in this service");
        }
        util.allureCaptureScreenshotRe(driver);
    }
}

