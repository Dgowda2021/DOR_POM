package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Registers {

    private final WebDriver driver;
    private final WebDriverWait wait;
    private static final Logger logger = LogManager.getLogger(Registers.class);


    // Constructor
    public Registers(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(120));

        PageFactory.initElements(driver, this);

        logger.debug("Registers page object initialized successfully.");
    }


    // Web Elements
    @FindBy(xpath = "//span[normalize-space()='Registers']")
    private WebElement clickRegisters;

    @FindBy(xpath = "//div[@class='ng-select-container ng-has-value']//span[@class='ng-arrow-wrapper']")
    private WebElement clickVerticalDropdown;

    @FindBy(xpath = "//ng-select[@placeholder='Select Site']//span[@class='ng-arrow-wrapper']")
    private WebElement clickSiteDropdown;


    // Page Actions
    //Navigate to Visitor Entry page.

    public void clickRegistersModule() {

        logger.info("Opening Registers module.");

        try {
            wait.until(ExpectedConditions.elementToBeClickable(clickRegisters)).click();
            logger.info("Registers module opened successfully.");
        } catch (Exception e) {
            logger.error("Failed to open Registers module.", e);
            throw e;
        }
    }

    public void clickVerticalDropdown(String verticalName) {

        logger.info("Selecting vertical: {}", verticalName);

        try {
            wait.until(ExpectedConditions.elementToBeClickable(clickVerticalDropdown)).click();

            By verticalOption = By.xpath(
                    "//span[@class='ng-option-label'][normalize-space()='"+ verticalName +"']");

            wait.until(ExpectedConditions.elementToBeClickable(verticalOption)).click();

            logger.info("Successfully selected vertical: {}", verticalName);

        } catch (Exception e) {
            logger.error("Failed to select vertical: {}", verticalName, e);
            throw e;
        }
    }

    public void selectSite(String siteName) {

        logger.info("Selecting site: {}", siteName);

        try {
            wait.until(ExpectedConditions.elementToBeClickable(clickSiteDropdown)).click();

            By siteOption = By.xpath("//span[@class='ng-option-label'][normalize-space()='" + siteName + "']");

            wait.until(ExpectedConditions.elementToBeClickable(siteOption)).click();

            logger.info("Successfully selected site: {}", siteName);

        } catch (Exception e) {
            logger.error("Failed to select site: {}", siteName, e);
            throw e;
        }
    }
}