package common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;


public final class ConfigProp {
    static Properties prop = new Properties();
    private static void init() {
        try {
			Thread currentThread = Thread.currentThread();
			ClassLoader contextClassLoader = currentThread.getContextClassLoader();
			InputStream propertiesStream = contextClassLoader.getResourceAsStream("application.properties");
			prop.load(propertiesStream);
			String browserName = getProperty(prop, "browser", "");
			System.out.println("Target Browser "+ browserName);

            String platformType = getProperty(prop, "platform", "");
            System.out.println("Target Platform "+ platformType);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getByKey(String key) {
        return prop.getProperty(key);
    }

    public static String getBrowser() {
        init();
        return prop.getProperty("browser");
    }

    public static String getPlatform() {
        return prop.getProperty("platform");
    }

    private static String getProperty(Properties props, String name, String defaultValue) {
		if (StringUtils.isNotEmpty(props.getProperty(name))) {
			return props.getProperty(name); // fall back to defaults
		} else {
			return defaultValue;
		}
	}
}
