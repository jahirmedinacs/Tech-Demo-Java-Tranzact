package drivers;

import common.ConfigProp;
import org.openqa.selenium.WebDriver;

public class DriverFactory {

    public WebDriver driver;

    public void getDriver() {
        Driver drivers = new Driver();
        driver = drivers.getBrowser(ConfigProp.getBrowser());
    }

    public void tearDown() {
        Driver.closeAllDrivers();
    }

    public WebDriver driver() {
        return driver;
    }
}
