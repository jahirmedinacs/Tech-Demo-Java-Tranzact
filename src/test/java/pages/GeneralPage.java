package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.base.BasePage;

// This GeneralPage class extends the BasePage class and provides methods for general actions available across different pages.
public class GeneralPage extends BasePage {

    // Locators for various elements on the page.
    private final By menuIcon = By.xpath("//button[@id='react-burger-menu-btn']");
    private final String buttonOptionByNameOnMenu = "//div[@class='bm-menu']//a[normalize-space()='%s']";

    // Constructor to initialize the WebDriver.
    public GeneralPage(WebDriver driver) {
        super(driver);
    }

    // Method to click on the menu icon on the top left of the header.
    public void clickOnMenuIconOnTopLeftOfTheHeader() {
        click(menuIcon);
        waitForPageToBeLoaded();
        util.allureCaptureScreenshotRe(driver);
    }

    // Method to click on a button by name on the displayed menu.
    public void clickOnButtonByNameOnTheDisplayedMenu(String buttonName) {
        click(By.xpath(util.format(buttonOptionByNameOnMenu, buttonName)));
        waitForPageToBeLoaded();
        util.allureCaptureScreenshotRe(driver);
    }
}

