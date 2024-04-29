package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.base.BasePage;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    private final By userName = By.xpath("//input[@id='user-name']");
    private final By pass = By.xpath("//input[@id='password']");

    private final By logginButton = By.xpath("//input[@id='login-button']");

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




}
