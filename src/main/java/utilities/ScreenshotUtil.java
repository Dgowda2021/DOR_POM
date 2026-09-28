package utilities;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public final class ScreenshotUtil {

    private static final Logger logger = LogManager.getLogger(ScreenshotUtil.class);

    private static final String SCREENSHOT_PATH = System.getProperty("user.dir") + "/test-output/screenshots/";

    private static final DateTimeFormatter TIMESTAMP_FORMATTER = DateTimeFormatter.ofPattern("ddMMyyyy_HHmmss");

    private ScreenshotUtil() {
        // Prevent object creation
    }

    /**
     * Captures a screenshot and saves it to the configured screenshot directory.
     *
     * @param driver   WebDriver instance
     * @param testName Test name used for the screenshot file name
     * @return Absolute path of the captured screenshot
     */
    public static String takeScreenshot(WebDriver driver, String testName) {

        validateInput(driver, testName);

        String sanitizedTestName = sanitizeTestName(testName);

        String timestamp = LocalDateTime.now().format(TIMESTAMP_FORMATTER);

        String fileName = sanitizedTestName + "_" + timestamp + ".png";

        Path screenshotDirectory = Paths.get(SCREENSHOT_PATH);

        Path screenshotPath = screenshotDirectory.resolve(fileName);

        logger.info("Capturing screenshot for test: {}", testName);

        logger.debug("Screenshot path: {}", screenshotPath.toAbsolutePath());

        try {

            // Create screenshot directory if it does not exist
            Files.createDirectories(screenshotDirectory);

            // Capture screenshot
            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            // Copy screenshot to target location
            Files.copy(screenshotFile.toPath(), screenshotPath);

            logger.info(
                    "Screenshot captured successfully: {}",
                    screenshotPath.toAbsolutePath()
            );

            return screenshotPath.toAbsolutePath().toString();

        } catch (IOException e) {
            logger.error("Failed to save screenshot for test: {}", testName, e);
            throw new RuntimeException("Failed to capture screenshot for: " + testName, e);

        } catch (Exception e) {
            logger.error("Unexpected error while capturing screenshot for test: {}", testName, e);
            throw new RuntimeException("Unexpected error while capturing screenshot for: " + testName, e);
        }
    }

    /**
     * Validates screenshot utility input.
     */
    private static void validateInput(WebDriver driver, String testName) {

        if (driver == null) {
            throw new IllegalArgumentException("WebDriver cannot be null.");
        }

        if (!(driver instanceof TakesScreenshot)) {
            throw new IllegalArgumentException("WebDriver does not support screenshot capture.");
        }

        if (testName == null || testName.isBlank()) {
            throw new IllegalArgumentException("Test name cannot be null or empty.");
        }
    }

    /**
     * Removes characters that may cause invalid file names.
     */
    private static String sanitizeTestName(String testName) {

        return testName.trim().replaceAll("[\\\\/:*?\"<>|]", "_").replaceAll("\\s+", "_");
    }
}
