package utilities;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DatePicker {

    WebDriver driver;
    WebDriverWait wait;

    public DatePicker(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }
    public void selectDate(String day, String month, String year)
            throws InterruptedException {

        // GET CURRENT YEAR AND CURRENT MONTH
        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();
        String currentMonth = currentDate.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);

        // Convert selected year to int
        int selectedYear = Integer.parseInt(year);
        System.out.println("========================================");
        System.out.println("Current Year  : " + currentYear);
        System.out.println("Current Month : " + currentMonth);
        System.out.println("Selected Year : " + year);
        System.out.println("Selected Month: " + month);
        System.out.println("========================================");


        // CASE 1:
        // SELECTED YEAR = CURRENT YEAR
        if (selectedYear == currentYear) {
            System.out.println("Selected year is current year: " + year);

            // CASE 1A:
            // SELECTED MONTH = CURRENT MONTH
            if (month.equalsIgnoreCase(currentMonth) || getMonthNumber(month) == getMonthNumber(currentMonth)) {
                System.out.println("Selected month is current month: " + month);
                System.out.println("Selecting date directly...");
                selectDay(day);
            }

            // CASE 1B:
            // SAME YEAR BUT DIFFERENT MONTH
            else {
                System.out.println("Same year but different month.");
                selectMonthUsingArrow(currentMonth, month);
                // Select date after reaching required month
                selectDay(day);
            }
        }

        // CASE 2:
        // SELECTED YEAR != CURRENT YEAR
        else {
            System.out.println("Selected year is different from current year: " + year);
            System.out.println("Selecting Year -> Month -> Date");


            // 1. Click Month-Year dropdown
            By monthYearButton = By.xpath("//button[@aria-label='Choose month and year']");
            wait.until(ExpectedConditions.elementToBeClickable(monthYearButton)).click();
            Thread.sleep(500);

            // 2. Select Year
            By yearLocator = By.xpath("//span[normalize-space()='" + year + "']");
            wait.until(ExpectedConditions.visibilityOfElementLocated(yearLocator));
            wait.until(ExpectedConditions.elementToBeClickable(yearLocator));
            Thread.sleep(500);
            driver.findElement(yearLocator).click();
            // Wait for month view
            Thread.sleep(800);

            // 3. Select Month
            By monthLocator = By.xpath("//span[normalize-space()='" + month + "']");
            wait.until(ExpectedConditions.visibilityOfElementLocated(monthLocator));
            wait.until(ExpectedConditions.elementToBeClickable(monthLocator));
            Thread.sleep(500);
            driver.findElement(monthLocator).click();
            // Wait for date view
            Thread.sleep(800);

            // 4. Select Date
            selectDay(day);
        }
    }

    // SELECT MONTH USING LEFT / RIGHT ARROW
    private void selectMonthUsingArrow(String currentMonth, String selectedMonth) throws InterruptedException {

        // Convert month names to month numbers
        int currentMonthNumber = getMonthNumber(currentMonth);

        int selectedMonthNumber = getMonthNumber(selectedMonth);

        // Calculate difference
        int monthDifference = selectedMonthNumber - currentMonthNumber;
        System.out.println("Month Difference: " + monthDifference);


        // SELECT PREVIOUS MONTH
        if (monthDifference < 0) {
            int numberOfClicks = Math.abs(monthDifference);
            System.out.println("Moving LEFT " + numberOfClicks + " time(s)");
            for (int i = 0; i < numberOfClicks; i++) {
                clickPreviousMonth();
                Thread.sleep(500);
            }
        }

        // SELECT NEXT MONTH
        else {
            int numberOfClicks = monthDifference;
            System.out.println("Moving RIGHT " + numberOfClicks + " time(s)");
            for (int i = 0; i < numberOfClicks; i++) {
                clickNextMonth();
                Thread.sleep(500);
            }
        }
    }

    // CLICK LEFT / PREVIOUS MONTH
    private void clickPreviousMonth() {
        By previousMonthButton = By.xpath("//button[@aria-label='Previous month']//span");
        wait.until(ExpectedConditions.elementToBeClickable(previousMonthButton)).click();
    }

    // CLICK RIGHT / NEXT MONTH
    private void clickNextMonth() {
        By nextMonthButton = By.xpath("//button[@aria-label='Next month']//span");
        wait.until(ExpectedConditions.elementToBeClickable(nextMonthButton)).click();
    }

    // SELECT DATE
    private void selectDay(String day)
            throws InterruptedException {
        By dateLocator = By.xpath("//span[normalize-space()='" + day + "']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(dateLocator));
        wait.until(ExpectedConditions.elementToBeClickable(dateLocator));
        Thread.sleep(500);
        driver.findElement(dateLocator).click();
        Thread.sleep(500);
    }

    //GET MONTH NUMBER
    private int getMonthNumber(String month) {

        switch (month.trim().toLowerCase()) {

            case "jan":
            case "january":
                return 1;

            case "feb":
            case "february":
                return 2;

            case "mar":
            case "march":
                return 3;

            case "apr":
            case "april":
                return 4;

            case "may":
                return 5;

            case "jun":
            case "june":
                return 6;

            case "jul":
            case "july":
                return 7;

            case "aug":
            case "august":
                return 8;

            case "sep":
            case "september":
                return 9;

            case "oct":
            case "october":
                return 10;

            case "nov":
            case "november":
                return 11;

            case "dec":
            case "december":
                return 12;

            default:
                throw new IllegalArgumentException(
                        "Invalid month: " + month
                );
        }
    }
}