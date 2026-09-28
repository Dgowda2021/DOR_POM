package utilities;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TimePicker {

    private static final Logger logger =
            LogManager.getLogger(TimePicker.class);

    private static final Duration DEFAULT_TIMEOUT =
            Duration.ofSeconds(20);

    // Time input locators
    private static final By HOUR_INPUT =
            By.xpath("(//input)[19]");

    private static final By MINUTE_INPUT =
            By.xpath("(//input)[20]");

    // Hour controls
    private static final By HOUR_UP_BUTTON =
            By.xpath("//button[@aria-label='Add a hour']//span");

    private static final By HOUR_DOWN_BUTTON =
            By.xpath("//button[@aria-label='Minus a hour']//span");

    // Minute controls
    private static final By MINUTE_UP_BUTTON =
            By.xpath("//button[@aria-label='Add a minute']//span");

    private static final By MINUTE_DOWN_BUTTON =
            By.xpath("//button[@aria-label='Minus a minute']//span");

    private final WebDriver driver;
    private final WebDriverWait wait;

    public TimePicker(WebDriver driver) {

        if (driver == null) {
            throw new IllegalArgumentException(
                    "WebDriver cannot be null."
            );
        }

        this.driver = driver;
        this.wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);

        logger.debug("TimePicker initialized successfully.");
    }

    /**
     * Selects the required hour and minute.
     *
     * @param targetHour   Target hour
     * @param targetMinute Target minute
     */
    public void selectTime(
            int targetHour,
            int targetMinute) {

        logger.info(
                "Selecting time: {}:{}",
                String.format("%02d", targetHour),
                String.format("%02d", targetMinute)
        );

        try {

            validateTime(targetHour, targetMinute);

            // ----------------------------------------------------
            // Read current hour
            // ----------------------------------------------------

            String hourText = wait
                    .until(ExpectedConditions.visibilityOfElementLocated(
                            HOUR_INPUT))
                    .getAttribute("value")
                    .trim();

            // ----------------------------------------------------
            // Read current minute
            // ----------------------------------------------------

            String minuteText = wait
                    .until(ExpectedConditions.visibilityOfElementLocated(
                            MINUTE_INPUT))
                    .getAttribute("value")
                    .trim();

            logger.debug(
                    "Current time values from picker - Hour: {}, Minute: {}",
                    hourText,
                    minuteText
            );

            // ----------------------------------------------------
            // Validate hour value
            // ----------------------------------------------------

            if (!hourText.matches("\\d+")) {

                throw new IllegalStateException(
                        "Unable to read hour value. Found: " + hourText
                );
            }

            // ----------------------------------------------------
            // Validate minute value
            // ----------------------------------------------------

            if (!minuteText.matches("\\d+")) {

                throw new IllegalStateException(
                        "Unable to read minute value. Found: " + minuteText
                );
            }

            int currentHour = Integer.parseInt(hourText);
            int currentMinute = Integer.parseInt(minuteText);

            logger.info(
                    "Current time: {}:{}",
                    String.format("%02d", currentHour),
                    String.format("%02d", currentMinute)
            );

            // ----------------------------------------------------
            // Adjust hour
            // ----------------------------------------------------

            adjustHour(currentHour, targetHour);

            // ----------------------------------------------------
            // Adjust minute
            // ----------------------------------------------------

            adjustMinute(currentMinute, targetMinute);

            logger.info(
                    "Time selected successfully: {}:{}",
                    String.format("%02d", targetHour),
                    String.format("%02d", targetMinute)
            );

        } catch (Exception e) {

            logger.error(
                    "Failed to select time: {}:{}",
                    String.format("%02d", targetHour),
                    String.format("%02d", targetMinute),
                    e
            );

            throw e;
        }
    }

    // ============================================================
    // ADJUST HOUR
    // ============================================================

    private void adjustHour(
            int currentHour,
            int targetHour) {

        if (currentHour == targetHour) {

            logger.debug(
                    "Hour is already set to: {}",
                    targetHour
            );

            return;
        }

        if (currentHour < targetHour) {

            int numberOfClicks =
                    targetHour - currentHour;

            logger.debug(
                    "Increasing hour by {} step(s).",
                    numberOfClicks
            );

            for (int i = 0; i < numberOfClicks; i++) {
                clickHourUp();
            }

        } else {

            int numberOfClicks =
                    currentHour - targetHour;

            logger.debug(
                    "Decreasing hour by {} step(s).",
                    numberOfClicks
            );

            for (int i = 0; i < numberOfClicks; i++) {
                clickHourDown();
            }
        }
    }

    // ============================================================
    // ADJUST MINUTE
    // ============================================================

    private void adjustMinute(
            int currentMinute,
            int targetMinute) {

        if (currentMinute == targetMinute) {

            logger.debug(
                    "Minute is already set to: {}",
                    targetMinute
            );

            return;
        }

        if (currentMinute < targetMinute) {

            int numberOfClicks =
                    targetMinute - currentMinute;

            logger.debug(
                    "Increasing minute by {} step(s).",
                    numberOfClicks
            );

            for (int i = 0; i < numberOfClicks; i++) {
                clickMinuteUp();
            }

        } else {

            int numberOfClicks =
                    currentMinute - targetMinute;

            logger.debug(
                    "Decreasing minute by {} step(s).",
                    numberOfClicks
            );

            for (int i = 0; i < numberOfClicks; i++) {
                clickMinuteDown();
            }
        }
    }

    // ============================================================
    // CLICK HOUR UP
    // ============================================================

    private void clickHourUp() {

        logger.debug("Clicking hour increase button.");

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        HOUR_UP_BUTTON
                )
        ).click();
    }

    // ============================================================
    // CLICK HOUR DOWN
    // ============================================================

    private void clickHourDown() {

        logger.debug("Clicking hour decrease button.");

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        HOUR_DOWN_BUTTON
                )
        ).click();
    }

    // ============================================================
    // CLICK MINUTE UP
    // ============================================================

    private void clickMinuteUp() {

        logger.debug("Clicking minute increase button.");

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        MINUTE_UP_BUTTON
                )
        ).click();
    }

    // ============================================================
    // CLICK MINUTE DOWN
    // ============================================================

    private void clickMinuteDown() {

        logger.debug("Clicking minute decrease button.");

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        MINUTE_DOWN_BUTTON
                )
        ).click();
    }

    // ============================================================
    // VALIDATE TIME
    // ============================================================

    private void validateTime(
            int targetHour,
            int targetMinute) {

        if (targetHour < 0 || targetHour > 23) {

            throw new IllegalArgumentException(
                    "Invalid target hour: " + targetHour +
                            ". Expected value between 0 and 23."
            );
        }

        if (targetMinute < 0 || targetMinute > 59) {

            throw new IllegalArgumentException(
                    "Invalid target minute: " + targetMinute +
                            ". Expected value between 0 and 59."
            );
        }
    }
}
