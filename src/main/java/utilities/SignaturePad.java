package utilities;

import java.time.Duration;
import java.util.logging.Logger;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignaturePad {
    protected WebDriverWait wait;
    WebDriver driver;
    Actions actions;


    public SignaturePad(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));

    }

    public void sign(WebElement signaturePad)
            throws InterruptedException {

        actions.moveToElement(signaturePad, 20, 20)
                .clickAndHold()
                .moveByOffset(40, 20)
                .moveByOffset(30, -20)
                .moveByOffset(40, 25)
                .moveByOffset(30, -15)
                .release()
                .perform();


    }
}

