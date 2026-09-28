package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class ExtentReportManager {

    private static final Logger logger =
            LogManager.getLogger(ExtentReportManager.class);

    private static ExtentReports extent;

    private static final String REPORT_PATH =
            System.getProperty("user.dir")
                    + "/test-output/AutomationReport.html";

    private static final String REPORT_NAME =
            "Register Forms Automation Report";

    private static final String DOCUMENT_TITLE =
            "DOR Automation Test Report";

    private ExtentReportManager() {
        // Prevent object creation
    }

    /**
     * Returns the ExtentReports instance.
     * Creates and configures the report if it does not already exist.
     *
     * @return ExtentReports instance
     */
    public static synchronized ExtentReports getReport() {

        if (extent == null) {

            logger.info("Initializing Extent Report.");
            logger.debug("Extent Report path: {}", REPORT_PATH);

            try {

                ExtentSparkReporter sparkReporter =
                        new ExtentSparkReporter(REPORT_PATH);

                // Report configuration
                sparkReporter.config()
                        .setReportName(REPORT_NAME);

                sparkReporter.config()
                        .setDocumentTitle(DOCUMENT_TITLE);

                // Create ExtentReports instance
                extent = new ExtentReports();

                // Attach Spark Reporter
                extent.attachReporter(sparkReporter);

                // System information
                extent.setSystemInfo(
                        "Framework",
                        "Selenium + Java + TestNG + POM"
                );

                extent.setSystemInfo(
                        "Automation",
                        "Selenium WebDriver"
                );

                extent.setSystemInfo(
                        "Tester",
                        "Dinesh"
                );

                extent.setSystemInfo(
                        "Environment",
                        "QA"
                );

                extent.setSystemInfo(
                        "Browser",
                        "Chrome"
                );

                logger.info(
                        "Extent Report initialized successfully."
                );

            } catch (Exception e) {

                logger.error(
                        "Failed to initialize Extent Report.",
                        e
                );

                throw e;
            }
        }

        return extent;
    }
}
