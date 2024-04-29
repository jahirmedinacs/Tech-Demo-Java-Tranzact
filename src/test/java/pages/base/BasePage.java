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

public class BasePage {
    protected WebDriver driver;
    protected Utilities util;

    protected final Logger LOGGER = LoggerFactory.getLogger(BasePage.class);

    protected final WebDriverWait wait;

    private String currentPage;

    public BasePage(WebDriver driver) {
        util = new Utilities();
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(90));
    }

    public void cleanText(By element) {
        waitWebElementVisibleBy(element);
        driver.findElement(element).clear();
    }

    public void sendText(By element, String text) {
        waitWebElementVisibleBy(element);
        driver.findElement(element).sendKeys(text);
    }

    public void pressEnter(By element) {
        waitWebElementVisibleBy(element);
        driver.findElement(element).sendKeys(Keys.ENTER);
    }

    public WebElement findElement(By locator) {
        waitWebElementVisibleBy(locator);
        return driver.findElement(locator);
    }

    public String getText(WebElement element) {
        return element.getText();
    }

    public String getText(By locator) {
        waitWebElementVisibleBy(locator);
        return driver.findElement(locator).getText();
    }

    public List<String> getTexts(By locator) {
        return findElements(locator).stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public boolean isVisible(By locator) {
        try {
            waitWebElementVisibleBy(locator);
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
        return true;
    }

    public void click(By element) {
        waitWebElementVisibleBy(element);
        waitWebElementClickableBy(element);
        driver.findElement(element).click();
    }

    public void clickJS(By element) {
        WebElement webElement = driver.findElement(element);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", webElement);
    }

    public void assertEquals(String elementActual, String txtError) {
        Assert.assertEquals(elementActual, txtError);
    }

    public boolean elementExists(By locator) {
        return !driver.findElements(locator).isEmpty();
    }

    public void moveToElement(By locator) {
        WebElement element = driver.findElement(locator);
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.perform();
    }

    public void scrollToElement(By locator) {
        WebElement element = driver.findElement(locator);
        Actions actions = new Actions(driver);
        actions.scrollToElement(element);
        actions.perform();
    }

    public void scrollToElement(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.perform();
    }

    public void moveToElement(String xpath) {
        WebElement element = driver.findElement(By.xpath(xpath));
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.perform();
    }

    public void scrollIntoView(By locator) {
        WebElement element = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        sleep(500L);
    }

    public void scrollIntoView(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        sleep(500L);
    }

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

    public boolean isNotDisplayed(By locator) {
        return driver.findElements(locator).isEmpty();
    }

    public boolean isDisplayed(By locator) {
        return !isNotDisplayed(locator);
    }

    public void click(String xpath) {
        waitWebElementVisibleBy(By.xpath(xpath));
        waitWebElementClickableBy(By.xpath(xpath));
        driver.findElement(By.xpath(xpath)).click();
    }

    public void mouseOver(By element) {
        waitWebElementVisibleBy(element);
        Actions builder = new Actions(driver);
        builder.moveToElement(driver.findElement(element)).perform();
    }

    public List<WebElement> findElements(By elements) {
        waitWebElementsVisibleBy(elements);
        return driver.findElements(elements);
    }

    public List<WebElement> findElementsIfExists(By elements) {
        try {
            waitWebElementsVisibleBy(elements);
        } catch (TimeoutException | NoSuchElementException e) {
            return new ArrayList<>();
        }
        return driver.findElements(elements);
    }

    public void waitWebElementClickableBy(By element) {
        wait.until(ExpectedConditions.presenceOfElementLocated(element));
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitWebElementVisibleBy(By element) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    public void waitWebElementsVisibleBy(By elements) {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(elements));
    }

    public void waitWebElementPresentBy(By element) {
        wait.until(ExpectedConditions.presenceOfElementLocated(element));
    }


    public void assertElementPresent(By element) {
        waitWebElementVisibleBy(element);
        Assert.assertTrue(driver.findElement(element).isDisplayed());
    }

    public void assertTextPresentJS(By element, String text) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(element));
        Assert.assertTrue(driver.findElement(element).getText().contains(text));
    }

    public void waitForPageToBeLoaded() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(webDriver ->
                (js).executeScript("return document.readyState;").equals("complete"));
        sleep(4);
    }

    public void sleep(int seconds) {
        sleep(seconds * 1000L);
    }

    public void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

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

    public void switchToNewTab() {
        currentPage = driver.getWindowHandle();
        for (String windowHandle : driver.getWindowHandles()) {
            if (!currentPage.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }

    public void switchToBaseTab() {
        driver.switchTo().window(currentPage);
    }

    public void closeCurrentTab() {
        driver.close();
    }

    public void resizeScreen(final int width, final int height) {
        driver.manage().window().setSize(new Dimension(width, height));
        util.allureCaptureScreenshotRe(driver);
    }

    public void back() {
        driver.navigate().back();
    }

    public void dragAndDrop(final WebElement from, final WebElement to) {
        Actions act = new Actions(driver);
        act.dragAndDrop(from, to).build().perform();
    }

    public boolean isWebElementDisplayed(By element) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        return driver.findElement(element).isDisplayed(); 
    }

    public void waitWebElementInvisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        try{
            wait.until(ExpectedConditions.invisibilityOf(element));
        } catch (ElementNotInteractableException ignored) {
            waitForPageToBeLoaded();
        }
    }
}
