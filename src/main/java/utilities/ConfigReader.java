package utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static final Logger logger = LogManager.getLogger(ConfigReader.class);
    private static final String CONFIG_FILE_PATH = "Config/config.properties";

    private ConfigReader() {
        // Prevent object creation
    }

    /**
     * Loads properties from the configuration file.
     *
     * @return loaded Properties object
     * @throws IOException if the configuration file cannot be read
     */
    private static Properties getLoadedPropertiesObject() throws IOException {

        logger.debug("Loading configuration file: {}", CONFIG_FILE_PATH);

        Properties properties = new Properties();

        try (FileInputStream fis = new FileInputStream(CONFIG_FILE_PATH)) {

            properties.load(fis);

            logger.debug(
                    "Configuration file loaded successfully. Properties count: {}",
                    properties.size()
            );

            return properties;

        } catch (IOException e) {

            logger.error(
                    "Failed to load configuration file: {}",
                    CONFIG_FILE_PATH,
                    e
            );

            throw e;
        }
    }

    /**
     * Returns application URL.
     */
    public static String getUrl() throws IOException {

        logger.debug("Reading application URL from configuration.");

        String url = getLoadedPropertiesObject().getProperty("url");

        if (url == null || url.isBlank()) {
            logger.error("Application URL is missing or empty in configuration.");
            throw new IllegalStateException(
                    "Application URL is missing or empty in config.properties."
            );
        }

        logger.debug("Application URL loaded successfully.");

        return url;
    }

    /**
     * Returns application User ID.
     */
    public static String getUserId() throws IOException {

        logger.debug("Reading User ID from configuration.");

        String userId = getLoadedPropertiesObject().getProperty("userId");

        if (userId == null || userId.isBlank()) {
            logger.error("User ID is missing or empty in configuration.");
            throw new IllegalStateException(
                    "User ID is missing or empty in config.properties."
            );
        }

        logger.debug("User ID loaded successfully.");

        return userId;
    }

    /**
     * Returns application password.
     * Password value is intentionally not logged.
     */
    public static String getPassword() throws IOException {

        logger.debug("Reading password from configuration.");

        String password = getLoadedPropertiesObject().getProperty("password");

        if (password == null || password.isBlank()) {
            logger.error("Password is missing or empty in configuration.");
            throw new IllegalStateException(
                    "Password is missing or empty in config.properties."
            );
        }

        logger.debug("Password loaded successfully.");

        return password;
    }

    /**
     * Returns whether Selenium should run Chrome in headless mode.
     *
     * System property takes priority over config.properties.
     *
     * Example:
     * mvn test -Dheadless=true
     */
    public static boolean isHeadless() throws IOException {

        logger.debug("Reading headless mode configuration.");

        String headless = System.getProperty("headless");

        if (headless == null || headless.isBlank()) {

            headless = getLoadedPropertiesObject()
                    .getProperty("headless", "false");
        }

        boolean headlessMode = Boolean.parseBoolean(headless);

        logger.info("Headless mode: {}", headlessMode);

        return headlessMode;
    }
}