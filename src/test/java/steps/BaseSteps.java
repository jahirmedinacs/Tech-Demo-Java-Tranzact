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

public class BaseSteps {
    public static final Logger LOGGER = LoggerFactory.getLogger(BaseSteps.class);
    public static WebDriver driver;

    private final Utilities util = new Utilities();
    private DriverFactory driverFactory;

    @Step("Setup")
    @Before()
    public void setup() {
        driverFactory = new DriverFactory();
        driverFactory.getDriver();
        driver = driverFactory.driver();
        LOGGER.info("@BeforeTest - Setup");
    }

    @Step("Tear Down")
    @After(order = 0)
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed() || scenario.getStatus().equals(Status.SKIPPED)) {
            util.allureCaptureScreenshotRe(driver);
        }
        driverFactory.tearDown();
        LOGGER.info("@AfterTest - Tear Down");
    }
}
