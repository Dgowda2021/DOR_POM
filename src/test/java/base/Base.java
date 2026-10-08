package base;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import utilities.ConfigReader;
import utilities.ExtentReportManager;
import utilities.ScreenshotUtil;

import static utilities.ConfigReader.getUrl;

public class Base {
    private static final Logger logger = LogManager.getLogger(Base.class);
    protected WebDriver driver;
    protected static final ExtentReports extent = ExtentReportManager.getReport();
    protected ExtentTest test;

    // TEST SETUP
    @BeforeClass
    public void setup() throws IOException {

        logger.info("===== Starting Test Setup =====");

        try {
            // Initialize Chrome browser
            logger.info("Initializing Chrome browser.");

            ChromeOptions options = new ChromeOptions();

            if (ConfigReader.isHeadless()) {

                logger.info("Headless mode is enabled.");

                options.addArguments("--headless=new");
                options.addArguments("--window-size=1920,1080");
                options.addArguments("--disable-gpu");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");

                logger.info("Chrome will run in HEADLESS mode.");

            } else {

                logger.info("Headless mode is disabled.");
                logger.info("Chrome will run in NORMAL mode.");
            }

            driver = new ChromeDriver(options);

            // Maximize browser window
            logger.info("Maximizing browser window.");
            driver.manage().window().maximize();

            // Open application
            String applicationUrl = getUrl();

            logger.info("Opening application URL.");
            logger.debug("Application URL: {}", applicationUrl);

            driver.get(applicationUrl);

            logger.info("Browser setup completed successfully.");
            logger.info("===== Test Setup Completed =====");

        } catch (Exception e) {

            logger.error("Failed during test setup.", e);

            // Make sure browser is closed if setup fails
            if (driver != null) {
                logger.debug("Closing browser because setup failed.");
                driver.quit();
                driver = null;
            }

            throw e;
        }
    }

    // TEST TEARDOWN
    @AfterMethod
    public void afterMethod(ITestResult result) throws IOException {
        logger.info("===== Starting Test Teardown =====");
        try {
            if (result == null) {
                logger.warn("Test result is null during teardown.");
                return;
            }
            String testName = result.getName();

            // TEST PASSED
            if (result.getStatus() == ITestResult.SUCCESS) {
                logger.info("Test passed: {}", testName);
                if (test != null) {
                    test.pass("Test Case Passed");
                }
            }

            // TEST FAILED
            else if (result.getStatus() == ITestResult.FAILURE) {
                logger.error("Test failed: {}", testName);
                if (result.getThrowable() != null) {
                    logger.error("Test failure reason: {}", result.getThrowable().getMessage());
                }

                if (test != null) {
                    test.fail("Test Case Failed");
                    if (result.getThrowable() != null) {
                        test.fail(result.getThrowable());
                    }

                    // Capture screenshot on failure
                    if (driver != null) {
                        logger.info("Capturing failure screenshot for test: {}", testName);
                        String screenshotPath = ScreenshotUtil.takeScreenshot(driver, testName);
                        test.addScreenCaptureFromPath(screenshotPath);
                        logger.info("Failure screenshot attached to Extent Report.");
                    }
                }
            }

            // TEST SKIPPED
            else if (result.getStatus() == ITestResult.SKIP) {
                logger.warn("Test skipped: {}", testName);
                if (test != null) {
                    test.skip("Test Case Skipped");
                    if (result.getThrowable() != null) {
                        test.skip(result.getThrowable());
                    }
                }
            }
        } catch (Exception e) {
            logger.error("Error occurred during test teardown.", e);
        }
    }


    // CLOSE BROWSER
    @AfterClass
    public void tearDown() {
        logger.info("===== Starting Browser Teardown =====");
        if (driver != null) {
            logger.info("Closing browser.");
            try {
                driver.quit();
            } catch (Exception e) {
                logger.error("Failed to close browser properly.", e);
            } finally {
                driver = null;
            }
        }

        logger.info("===== Test Teardown Completed =====");
    }


    // EXTENT REPORT FLUSH
    @AfterSuite
    public void flushReport() {
        logger.info("===== Flushing Extent Report =====");
        try {
            extent.flush();
            logger.info("Extent Report generated successfully.");
        } catch (Exception e) {
            logger.error("Failed to flush Extent Report.", e);
        }
    }
}
