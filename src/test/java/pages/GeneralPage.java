package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.base.BasePage;

public class GeneralPage extends BasePage {

    private final By menuIcon = By.xpath("//button[@id='react-burger-menu-btn']");
    private final String buttonOptionByNameOnMenu = "//div[@class='bm-menu']//a[normalize-space()='%s']";

    public GeneralPage(WebDriver driver) {
        super(driver);
    }

    public void clickOnMenuIconOnTopLeftOfTheHeader() {
        click(menuIcon);
        waitForPageToBeLoaded();
        util.allureCaptureScreenshotRe(driver);
    }

    public void clickOnButtonByNameOnTheDisplayedMenu(String buttonName) {
        click(By.xpath(util.format(buttonOptionByNameOnMenu, buttonName)));
        waitForPageToBeLoaded();
        util.allureCaptureScreenshotRe(driver);
    }
}
