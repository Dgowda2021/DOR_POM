package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    private static final Logger logger = LogManager.getLogger(BasePage.class);
    private static final By CDK_OVERLAY = By.cssSelector("div.cdk-overlay-backdrop.cdk-overlay-backdrop-showing");

    public BasePage(WebDriver driver) {

        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        logger.debug("BasePage initialized.");
    }

    // -------------------------------------------------------------------------
    // Common Waits
    // -------------------------------------------------------------------------

    protected void waitForOverlayToDisappear() {

        wait.until(driver -> driver.findElements(CDK_OVERLAY).stream().noneMatch(WebElement::isDisplayed));
        logger.debug("CDK overlay disappeared.");
    }

    protected void waitForVisibility(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitForClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void waitForInvisibility(WebElement element) {

        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    // -------------------------------------------------------------------------
    // Common Actions
    // -------------------------------------------------------------------------

    protected void clickElement(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        logger.debug("Element clicked successfully.");
    }

    protected void clickElementAfterOverlayDisappears(WebElement element) {
        waitForOverlayToDisappear();
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        logger.debug("Element clicked successfully after overlay disappeared.");
    }

    protected void enterText(WebElement element, String text) {

        wait.until(ExpectedConditions.visibilityOf(element));
        wait.until(ExpectedConditions.elementToBeClickable(element));

        element.clear();
        element.sendKeys(text);

        logger.debug("Text entered successfully.");
    }

    protected void enterTextAfterOverlayDisappears(
            WebElement element,
            String text) {

        waitForOverlayToDisappear();

        wait.until(ExpectedConditions.visibilityOf(element));
        wait.until(ExpectedConditions.elementToBeClickable(element));

        element.clear();
        element.sendKeys(text);

        logger.debug("Text entered successfully after overlay disappeared.");
    }
    protected void waitForVisible(WebElement element) {

        wait.until(
                ExpectedConditions.visibilityOf(element)
        );
    }

    protected boolean isDisplayed(WebElement element) {

        try {
            return wait.until(
                    ExpectedConditions.visibilityOf(element)
            ).isDisplayed();

        } catch (Exception e) {
            return false;
        }
    }
}
