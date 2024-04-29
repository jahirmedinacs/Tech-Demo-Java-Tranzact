package pages;

import org.openqa.selenium.WebDriver;
import pages.base.BasePage;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void loadLoginPage() {
        driver.get("https://www.saucedemo.com/");
    }
}
