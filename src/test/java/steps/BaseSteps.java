package steps;

import common.Utilities;
import drivers.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.Status;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// This is the base class for the step definitions in Cucumber.
public class BaseSteps {
    // Define a Logger for logging.
    public static final Logger LOGGER = LoggerFactory.getLogger(BaseSteps.class);
    // Define a WebDriver for interacting with the web page.
    public static WebDriver driver;

    // Define a Utilities object for utility functions.
    private final Utilities util = new Utilities();
    // Define a DriverFactory object for managing WebDriver.
    private DriverFactory driverFactory;

    // This method will be run before each scenario.
    @Step("Setup")
    @Before()
    public void setup() {
        // Initialize the DriverFactory.
        driverFactory = new DriverFactory();
        // Get the WebDriver from the DriverFactory.
        driverFactory.getDriver();
        // Assign the WebDriver to the driver variable.
        driver = driverFactory.driver();
        // Log the setup process.
        LOGGER.info("@BeforeTest - Setup");
    }

    // This method will be run after each scenario.
    @Step("Tear Down")
    @After(order = 0)
    public void tearDown(Scenario scenario) {
        // If the scenario fails or is skipped, take a screenshot.
        if (scenario.isFailed() || scenario.getStatus().equals(Status.SKIPPED)) {
            util.allureCaptureScreenshotRe(driver);
        }
        // Tear down the WebDriver.
        driverFactory.tearDown();
        // Log the tear down process.
        LOGGER.info("@AfterTest - Tear Down");
    }
}

