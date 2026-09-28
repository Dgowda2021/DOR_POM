package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Login extends BasePage {

    private static final Logger logger = LogManager.getLogger(Login.class);

    @FindBy(xpath = "//input[@placeholder='you@company.com']")
    private WebElement txtUserId;

    @FindBy(xpath = "//input[@type='password']")
    private WebElement txtPassword;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement btnSignin;

    @FindBy(xpath = "//div[normalize-space() ='Invalid credentials']")
    private WebElement invalidCredentialsError;

    //div[contains(@class,'mat-mdc-snack-bar-label')]
    @FindBy(xpath = "//span[normalize-space()='Password is required']")
    private WebElement passwordRequiredError;

    @FindBy (xpath = "//span[normalize-space()='User ID']")
    private WebElement userIdLabel;



    public Login(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        logger.debug("Login page initialized successfully.");
    }

    public void setTxtUserId(String userId) {
        logger.info("Entering User ID.");

        try {
            enterText(txtUserId, userId);
            logger.debug("User ID entered successfully.");
        } catch (Exception e) {
            logger.error("Failed to enter User ID.", e);
            throw e;
        }
    }

    public void setTxtPassword(String password) {
        logger.info("Entering password.");

        try {
            enterText(txtPassword, password);
            logger.debug("Password entered successfully.");
        } catch (Exception e) {
            logger.error("Failed to enter password.", e);
            throw e;
        }
    }

    public void ClickBtnSignin() {
        logger.info("Clicking Sign In button.");

        try {
            clickElement(btnSignin);
            logger.debug("Sign In button clicked successfully.");
        } catch (Exception e) {
            logger.error("Failed to click Sign In button.", e);
            throw e;
        }
    }

    public String getInvalidCredentialsError() {
        logger.debug("Reading invalid credentials error message.");

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            wait.until(ExpectedConditions.visibilityOf(invalidCredentialsError));

            String errorMessage = invalidCredentialsError.getText();

            logger.debug("Invalid credentials error message: {}", errorMessage);

            return errorMessage;

        } catch (Exception e) {
            logger.error(
                    "Failed to read invalid credentials error message.",
                    e
            );
            throw e;
        }
    }

    public String getPasswordRequiredError() {
        logger.debug("Reading password required error message.");

        try {
            return passwordRequiredError.getText();
        } catch (Exception e) {
            logger.error(
                    "Failed to read password required error message.",
                    e
            );
            throw e;
        }
    }
    public boolean isSigninButtonEnabled() {
        logger.debug("Checking whether Sign In button is enabled.");

        try {
            boolean enabled = btnSignin.isEnabled();

            logger.debug("Sign In button enabled status: {}", enabled);

            return enabled;

        } catch (Exception e) {
            logger.error(
                    "Failed to check Sign In button enabled status.",
                    e
            );
            throw e;
        }
    }
    public void clickOutsidePasswordField() {
        logger.debug("Clicking outside password field to trigger validation.");

        try {
            userIdLabel.click();

            logger.debug("Successfully clicked outside password field.");

        } catch (Exception e) {
            logger.error(
                    "Failed to click outside password field.",
                    e
            );
            throw e;
        }
    }

}

