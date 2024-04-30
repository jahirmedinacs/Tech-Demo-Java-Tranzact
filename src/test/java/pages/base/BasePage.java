package pages.base;

import common.Utilities;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// This BasePage class provides common methods to interact with web elements.
public class BasePage {
    // WebDriver instance.
    protected WebDriver driver;
    // Utilities instance.
    protected Utilities util;

    // Logger instance for logging.
    protected final Logger LOGGER = LoggerFactory.getLogger(BasePage.class);

    // WebDriverWait instance for explicit waits.
    protected final WebDriverWait wait;

    // Current page URL.
    private String currentPage;

    // Constructor to initialize the WebDriver and WebDriverWait.
    public BasePage(WebDriver driver) {
        util = new Utilities();
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(90));
    }

    // Method to clear text from a web element.
    public void cleanText(By element) {
        waitWebElementVisibleBy(element);
        driver.findElement(element).clear();
    }

    // Method to send text to a web element.
    public void sendText(By element, String text) {
        waitWebElementVisibleBy(element);
        driver.findElement(element).sendKeys(text);
    }

    // Method to press Enter key on a web element.
    public void pressEnter(By element) {
        waitWebElementVisibleBy(element);
        driver.findElement(element).sendKeys(Keys.ENTER);
    }

    // Method to find a web element.
    public WebElement findElement(By locator) {
        waitWebElementVisibleBy(locator);
        return driver.findElement(locator);
    }

    // Method to get text from a web element.
    public String getText(WebElement element) {
        return element.getText();
    }

    // Overloaded method to get text from a web element located by a locator.
    public String getText(By locator) {
        waitWebElementVisibleBy(locator);
        return driver.findElement(locator).getText();
    }

    // Method to get texts from multiple web elements located by a locator.
    public List<String> getTexts(By locator) {
        return findElements(locator).stream().map(WebElement::getText).collect(Collectors.toList());
    }

    // Method to check if a web element is visible.
    public boolean isVisible(By locator) {
        try {
            waitWebElementVisibleBy(locator);
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
        return true;
    }

    // Method to click on a web element.
    public void click(By element) {
        waitWebElementVisibleBy(element);
        waitWebElementClickableBy(element);
        driver.findElement(element).click();
    }

    // Method to click on a web element using JavaScript.
    public void clickJS(By element) {
        WebElement webElement = driver.findElement(element);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", webElement);
    }

    // Method to assert if two strings are equal.
    public void assertEquals(String elementActual, String txtError) {
        Assert.assertEquals(elementActual, txtError);
    }

    // Method to check if a web element exists.
    public boolean elementExists(By locator) {
        return !driver.findElements(locator).isEmpty();
    }

    // Method to move the cursor to a web element.
    public void moveToElement(By locator) {
        WebElement element = driver.findElement(locator);
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.perform();
    }

    // Method to scroll to a web element.
    public void scrollToElement(By locator) {
        WebElement element = driver.findElement(locator);
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.perform();
    }

    // Overloaded method to scroll to a web element.
    public void scrollToElement(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.perform();
    }

    // Method to move the cursor to a web element located by an XPath.
    public void moveToElement(String xpath) {
        WebElement element = driver.findElement(By.xpath(xpath));
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.perform();
    }

    // Method to scroll into view of a web element.
    public void scrollIntoView(By locator) {
        WebElement element = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        sleep(500L);
    }

    // Overloaded method to scroll into view of a web element.
    public void scrollIntoView(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        sleep(500L);
    }

    // Method to scroll into view of a web element with retry count.
    public void scrollIntoViewWithRetryCount(final By locator, final int count) {
        int i = 1;
        while (i < count) {
            scrollIntoView(locator);
            if (isWebElementClickableBy(locator) && i >= (count / 2)) {
                break;
            }
            waitForPageToBeLoaded();
            i++;
        }
    }

    // Method to check if a web element is not displayed.
    public boolean isNotDisplayed(By locator) {
        return driver.findElements(locator).isEmpty();
    }

    // Method to check if a web element is displayed.
    public boolean isDisplayed(By locator) {
        return !isNotDisplayed(locator);
    }

    // Method to click on a web element located by an XPath.
    public void click(String xpath) {
        waitWebElementVisibleBy(By.xpath(xpath));
        waitWebElementClickableBy(By.xpath(xpath));
        driver.findElement(By.xpath(xpath)).click();
    }

    // Method to move the cursor over a web element.
    public void mouseOver(By element) {
        waitWebElementVisibleBy(element);
        Actions builder = new Actions(driver);
        builder.moveToElement(driver.findElement(element)).perform();
    }

    // Method to find multiple web elements.
    public List<WebElement> findElements(By elements) {
        waitWebElementsVisibleBy(elements);
        return driver.findElements(elements);
    }

    // Method to find multiple web elements if they exist.
    public List<WebElement> findElementsIfExists(By elements) {
        try {
            waitWebElementsVisibleBy(elements);
        } catch (TimeoutException | NoSuchElementException e) {
            return new ArrayList<>();
        }
        return driver.findElements(elements);
    }

    // Method to wait until a web element is clickable.
    public void waitWebElementClickableBy(By element) {
        wait.until(ExpectedConditions.presenceOfElementLocated(element));
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    // Method to wait until a web element is visible.
    public void waitWebElementVisibleBy(By element) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    // Method to wait until multiple web elements are visible.
    public void waitWebElementsVisibleBy(By elements) {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(elements));
    }

    // Method to wait until a web element is present.
    public void waitWebElementPresentBy(By element) {
        wait.until(ExpectedConditions.presenceOfElementLocated(element));
    }

    // Method to assert if a web element is present.
    public void assertElementPresent(By element) {
        waitWebElementVisibleBy(element);
        Assert.assertTrue(driver.findElement(element).isDisplayed());
    }

    // Method to assert if a text is present in a web element using JavaScript.
    public void assertTextPresentJS(By element, String text) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(element));
        Assert.assertTrue(driver.findElement(element).getText().contains(text));
    }

    // Method to wait until the page is loaded.
    public void waitForPageToBeLoaded() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(webDriver ->
                (js).executeScript("return document.readyState;").equals("complete"));
        sleep(4);
    }

    // Method to pause the execution for a specified number of seconds.
    public void sleep(int seconds) {
        sleep(seconds * 1000L);
    }

    // Overloaded method to pause the execution for a specified number of milliseconds.
    public void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    // Method to check if a web element is clickable.
    public boolean isWebElementClickableBy(final By element) {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(element));
            wait.until(ExpectedConditions.visibilityOfElementLocated(element));
            wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (TimeoutException | ElementClickInterceptedException e) {
            return false;
        }
        return true;
    }

    // Method to switch to a new tab.
    public void switchToNewTab() {
        currentPage = driver.getWindowHandle();
        for (String windowHandle : driver.getWindowHandles()) {
            if (!currentPage.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }

    // Method to switch to the base tab.
    public void switchToBaseTab() {
        driver.switchTo().window(currentPage);
    }

    // Method to close the current tab.
    public void closeCurrentTab() {
        driver.close();
    }

    // Method to resize the screen.
    public void resizeScreen(final int width, final int height) {
        driver.manage().window().setSize(new Dimension(width, height));
        util.allureCaptureScreenshotRe(driver);
    }

    // Method to navigate back.
    public void back() {
        driver.navigate().back();
    }

    // Method to drag and drop a web element.
    public void dragAndDrop(final WebElement from, final WebElement to) {
        Actions act = new Actions(driver);
        act.dragAndDrop(from, to).build().perform();
    }

    // Method to check if a web element is displayed.
    public boolean isWebElementDisplayed(By element) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        return driver.findElement(element).isDisplayed();
    }

    // Method to wait until a web element is invisible.
    public void waitWebElementInvisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        try{
            wait.until(ExpectedConditions.invisibilityOf(element));
        } catch (ElementNotInteractableException ignored) {
            waitForPageToBeLoaded();
        }
    }
}

