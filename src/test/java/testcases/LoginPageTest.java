package testcases;

import base.Base;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.Login;

import java.io.IOException;

import static utilities.ConfigReader.*;

public class LoginPageTest extends Base {

    private static final Logger logger = LogManager.getLogger(LoginPageTest.class);

    @Test(priority = 1)
    public void SuccessLoginTest() throws IOException {

        test = extent.createTest("Success Login Test");

        logger.info("===== Starting Success Login Test =====");
        test.info("===== Starting Success Login Test =====");

        try {
            // Initialize Login Page
            Login login = new Login(driver);
            logger.debug("Login page initialized successfully.");
            test.info("Login page initialized successfully.");

            // Enter User ID
            logger.info("Entering valid User ID.");
            test.info("Entering valid User ID.");
            login.setTxtUserId(getUserId());

            // Enter Password
            logger.info("Entering valid password.");
            test.info("Entering valid password.");
            login.setTxtPassword(getPassword());

            // Click Sign In
            logger.info("Clicking Sign In button.");
            test.info("Clicking Sign In button.");
            login.ClickBtnSignin();

            // Validate Dashboard Title
            String expectedTitle = "Digitization of Registers";
            String actualTitle = driver.getTitle();

            logger.info("Login completed. Validating dashboard title.");
            logger.info("Expected Title: {}", expectedTitle);
            logger.info("Actual Title: {}", actualTitle);

            test.info("Login completed. Validating dashboard title.");
            test.info("Expected Title: " + expectedTitle);
            test.info("Actual Title: " + actualTitle);

            Assert.assertEquals(
                    actualTitle,
                    expectedTitle,
                    "Login failed or dashboard title mismatch."
            );

            logger.info("Successful login validation completed.");
            logger.info("===== Success Login Test Passed =====");

            test.pass("Successful login validation completed.");
            test.pass("===== Success Login Test Passed =====");

        } catch (Exception e) {

            logger.error("Success Login Test failed due to an unexpected error.", e);
            test.fail("Success Login Test failed due to an unexpected error.");
            test.fail(e);

            throw e;
        }
    }

    @Test(priority = 2)
    public void failiedLoginTest() throws IOException {

        test = extent.createTest("Failed Login Test");

        logger.info("===== Starting Failed Login Test =====");
        test.info("===== Starting Failed Login Test =====");

        try {
            // Initialize Login Page
            Login login = new Login(driver);
            logger.debug("Login page initialized successfully.");
            test.info("Login page initialized successfully.");

            // Enter User ID
            logger.info("Entering valid User ID for failed login test.");
            test.info("Entering valid User ID for failed login test.");
            login.setTxtUserId(getUserId());

            // Enter incorrect password
            logger.info("Entering invalid password for negative login test.");
            test.info("Entering invalid password for negative login test.");
            login.setTxtPassword("Amzon@123");

            // Click Sign In
            logger.info("Clicking Sign In button.");
            test.info("Clicking Sign In button.");
            login.ClickBtnSignin();

            // Validate Error Message
            String expectedError = "Invalid credentials";

            logger.info("Validating invalid credentials error message.");
            test.info("Validating invalid credentials error message.");

            String actualError = login.getInvalidCredentialsError();

            logger.info("Expected Error: {}", expectedError);
            logger.info("Actual Error: {}", actualError);

            test.info("Expected Error: " + expectedError);
            test.info("Actual Error: " + actualError);

            Assert.assertEquals(
                    actualError,
                    expectedError,
                    "Error message mismatch for failed login."
            );

            logger.info("Failed login validation completed successfully.");
            logger.info("===== Failed Login Test Passed =====");

            test.pass("Failed login validation completed successfully.");
            test.pass("===== Failed Login Test Passed =====");

        } catch (Exception e) {

            logger.error(
                    "Failed Login Test encountered an unexpected error.",
                    e
            );

            test.fail("Failed Login Test encountered an unexpected error.");
            test.fail(e);

            throw e;
        }
    }

    @Test(priority = 3)
    public void blankPasswordTest() throws IOException {

        test = extent.createTest("Blank Password Test");

        logger.info("===== Starting Blank Password Test =====");
        test.info("===== Starting Blank Password Test =====");

        try {
            // Initialize Login Page
            Login login = new Login(driver);
            logger.debug("Login page initialized successfully.");
            test.info("Login page initialized successfully.");

            // Enter User ID
            logger.info("Entering valid User ID for blank password test.");
            test.info("Entering valid User ID for blank password test.");
            login.setTxtUserId(getUserId());

            // Leave password blank
            logger.info("Leaving password field blank.");
            test.info("Leaving password field blank.");
            login.setTxtPassword("");

            // Click outside password field to trigger validation
            logger.info("Clicking outside password field to trigger validation.");
            test.info("Clicking outside password field to trigger validation.");
            login.clickOutsidePasswordField();

            // Validate Error Message
            String expectedError = "Password is required";

            logger.info("Validating password required error message.");
            test.info("Validating password required error message.");

            String actualError = login.getPasswordRequiredError();

            logger.info("Expected Error: {}", expectedError);
            logger.info("Actual Error: {}", actualError);

            test.info("Expected Error: " + expectedError);
            test.info("Actual Error: " + actualError);

            Assert.assertEquals(
                    actualError,
                    expectedError,
                    "Error message mismatch for blank password."
            );

            // Validate Sign In button is disabled
            logger.info("Validating Sign In button is disabled.");
            test.info("Validating Sign In button is disabled.");

            boolean isSignInEnabled = login.isSigninButtonEnabled();

            logger.info(
                    "Sign In button enabled status: {}",
                    isSignInEnabled
            );

            test.info(
                    "Sign In button enabled status: " + isSignInEnabled
            );

            Assert.assertFalse(
                    isSignInEnabled,
                    "Sign In button should be disabled when password is blank."
            );

            logger.info("Blank password validation completed successfully.");
            logger.info("===== Blank Password Test Passed =====");

            test.pass("Blank password validation completed successfully.");
            test.pass("===== Blank Password Test Passed =====");

        } catch (Exception e) {

            logger.error(
                    "Blank Password Test encountered an unexpected error.",
                    e
            );

            test.fail("Blank Password Test encountered an unexpected error.");
            test.fail(e);

            throw e;
        }
    }
}