package base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pages.Login;

import java.io.IOException;

import static utilities.ConfigReader.getPassword;
import static utilities.ConfigReader.getUserId;

public class BaseLogin extends Base {

    private static final Logger logger = LogManager.getLogger(BaseLogin.class);

    // DOR LOGIN
    @BeforeClass
    public void DORLogin() throws IOException {
        logger.info("===== Starting DOR Login =====");
        try {
            // Initialize Login Page
            Login login = new Login(driver);

            logger.debug("Login page initialized successfully.");

            // Enter User ID
            logger.info("Entering DOR User ID.");
            login.setTxtUserId(getUserId());

            // Enter Password
            logger.info("Entering DOR password.");
            login.setTxtPassword(getPassword());

            // Click Sign In
            logger.info("Clicking DOR Sign In button.");
            login.ClickBtnSignin();

            logger.info("DOR login completed successfully.");
            logger.info("===== DOR Login Completed =====");

        } catch (Exception e) {

            logger.error("DOR login failed.", e);

            throw e;
        }
    }
}
