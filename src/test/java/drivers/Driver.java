package drivers;

import common.ConfigProp;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

// This class is responsible for managing WebDriver instances.
public class Driver {

    // A map to store WebDriver instances.
    private static final Map<String, WebDriver> drivers = new HashMap<>();

    // This method returns a WebDriver instance for the specified browser.
    // It configures the browser settings and manages the WebDriver instances.
    public WebDriver getBrowser(String browserName) {
        // Define ChromeOptions for custom settings.
        ChromeOptions options = new ChromeOptions();
        options.addArguments("force-device-scale-factor=0.75");
        options.addArguments("--disable-web-security");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--allow-running-insecure-content");
        options.addArguments("disable-infobars");
        options.addArguments("lang=en-US");
        options.setAcceptInsecureCerts(true);
        options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.ACCEPT);

        WebDriver driver = null;

        // If the platform is local, use local drivers.
        if (ConfigProp.getPlatform().equals("local")) {
            System.out.println("Using local driver");
            switch (browserName.toLowerCase()) {
                case "firefox":
                    driver = drivers.get("firefox");
                    if (driver == null) {
                        WebDriverManager.firefoxdriver().setup();
                        driver = new FirefoxDriver();
                        drivers.put("firefox", driver);
                    }
                    break;
                case "edge":
                    driver = drivers.get("edge");
                    if (driver == null) {
                        WebDriverManager.edgedriver().setup();
                        driver = new EdgeDriver();
                        drivers.put("edge", driver);
                    }
                    break;
                case "chrome":
                    driver = drivers.get("chrome");
                    if (driver == null) {
                        driver = new ChromeDriver(options);
                        drivers.put("chrome", driver);
                    }
                    break;
            }
        } else {
            // If the platform is not local, use remote WebDriver.
            String remoteUrl = ConfigProp.getByKey("url.remote");
            System.out.println("Selenium grid url is: " + remoteUrl);
            try {
                driver = new RemoteWebDriver(new URL(remoteUrl), options);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

        // Set the default wait time for locating elements.
        Objects.requireNonNull(driver).manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        return driver;
    }

    // This method closes and quits all the WebDriver instances, and removes them from the drivers map.
    public static void closeAllDrivers() {
        for (String key : drivers.keySet()) {
            drivers.get(key).close();
            drivers.get(key).quit();
            drivers.remove(key);
        }
    }
}

