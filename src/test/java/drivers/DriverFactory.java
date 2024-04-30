package drivers;

import common.ConfigProp;
import org.openqa.selenium.WebDriver;

// This class is a factory for WebDriver instances.
public class DriverFactory {

    // The WebDriver instance.
    public WebDriver driver;

    // This method gets a WebDriver instance for the browser specified in the configuration properties.
    public void getDriver() {
        Driver drivers = new Driver();
        driver = drivers.getBrowser(ConfigProp.getBrowser());
    }

    // This method closes all active WebDriver instances.
    public void tearDown() {
        Driver.closeAllDrivers();
    }

    // This method returns the current WebDriver instance.
    public WebDriver driver() {
        return driver;
    }
}

