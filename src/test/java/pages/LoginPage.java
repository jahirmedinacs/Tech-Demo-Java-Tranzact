package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.base.BasePage;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    private final By userName = By.xpath("//input[@id='user-name']");
    private final By pass = By.xpath("//input[@id='password']");

    private final By logginButton = By.xpath("//input[@id='login-button']");
    private final By errorMessage = By.xpath("//div[@class='error-message-container error']/h3[normalize-space()]");

    public void loadLoginPage() {
        driver.get("https://www.saucedemo.com/");
        util.allureCaptureScreenshotRe(driver);
    }

    public void enterUsername(String user) {
        sendText(userName, user);
        util.allureCaptureScreenshotRe(driver);
    }

    public void enterPassword(String password) {
        sendText(pass, password);
        util.allureCaptureScreenshotRe(driver);
    }

    public void clickLoginButton() {
        click(logginButton);
        util.allureCaptureScreenshotRe(driver);
    }

    public void iShouldSeeAnErrorMessageIndicatingTheUserIsLocked(String message) {
        assertElementPresent(errorMessage);
        Assert.assertEquals(getText(errorMessage), message);
        util.allureCaptureScreenshotRe(driver);
    }

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
