package common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

// This class is used to read properties from the application.properties file.
public final class ConfigProp {
    // Properties object to hold the properties from the file.
    static Properties prop = new Properties();

    // This method initializes the properties object and prints out the browser and platform properties.
    private static void init() {
        try {
            // Get the current thread's class loader.
            Thread currentThread = Thread.currentThread();
            ClassLoader contextClassLoader = currentThread.getContextClassLoader();

            // Load the properties file from the classpath.
            InputStream propertiesStream = contextClassLoader.getResourceAsStream("application.properties");
            prop.load(propertiesStream);

            // Get and print the browser property.
            String browserName = getProperty(prop, "browser", "");
            System.out.println("Target Browser "+ browserName);

            // Get and print the platform property.
            String platformType = getProperty(prop, "platform", "");
            System.out.println("Target Platform "+ platformType);
        } catch (IOException e) {
            // Print the stack trace if an exception occurs.
            e.printStackTrace();
        }
    }

    // This method returns the value of a given property.
    public static String getByKey(String key) {
        return prop.getProperty(key);
    }

    // This method initializes the properties and returns the browser property.
    public static String getBrowser() {
        init();
        return prop.getProperty("browser");
    }

    // This method returns the platform property.
    public static String getPlatform() {
        return prop.getProperty("platform");
    }

    // This method returns the value of a given property, or a default value if the property is not set.
    private static String getProperty(Properties props, String name, String defaultValue) {
        if (StringUtils.isNotEmpty(props.getProperty(name))) {
            return props.getProperty(name); // fall back to defaults
        } else {
            return defaultValue;
        }
    }
}

