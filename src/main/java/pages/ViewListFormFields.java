package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static java.awt.SystemColor.text;

public class ViewListFormFields extends BasePage {

    private static final Logger logger =
            LogManager.getLogger(ViewListFormFields.class);

    public ViewListFormFields(WebDriver driver) {

        super(driver);
        PageFactory.initElements(driver, this);

        logger.debug("View List Form Fields page initialized.");
    }

    // -------------------------------------------------------------------------
    // Navigation
    // -------------------------------------------------------------------------

    @FindBy(xpath = "//span[normalize-space()='View List']")
    private WebElement viewList;
    public void clickViewList() {
        logger.info("Clicking View List.");
        wait.until(ExpectedConditions.elementToBeClickable(viewList));
        clickElement(viewList);
        logger.info("View List opened successfully.");
    }

    @FindBy(xpath = "(//span[@class='lf-date-badge'])[1]")
    private WebElement createdDateTime;

    public String getCreatedDateTime() {

        waitForVisible(createdDateTime);

        String text = createdDateTime.getText().trim();

        logger.info("Created form date/time: {}", text);

        return text.split("\\n")[1].trim();
    }

    // -------------------------------------------------------------------------
    // EDIT / VIEW ACTION
    // -------------------------------------------------------------------------

    @FindBy(xpath = "(//button[@mattooltip='Edit entry'])[1]")
    private WebElement edit;
    public void clickEdit() {
        logger.info("Clicking Edit Action.");
        wait.until(ExpectedConditions.elementToBeClickable(edit));
        clickElement(edit);
        logger.info("Edit button clicked successfully.");
    }

    // -------------------------------------------------------------------------
    // FORM DATE
    // -------------------------------------------------------------------------

    @FindBy(xpath = "//input[@name='date']")
    private WebElement dateField;
    public String getFormDate() {
        waitForField(dateField);
        String value = dateField.getAttribute("value");
        logger.info("View List - Form Date: {}", value);
        return value;
    }

    // -------------------------------------------------------------------------
    // GP NUMBER
    // -------------------------------------------------------------------------

    @FindBy(xpath = "//input[@placeholder='GP Number']")
    private WebElement txtGPNumber;

    public String getGPNumber() {
        waitForField(txtGPNumber);
        String value = txtGPNumber.getAttribute("value");
        logger.info("View List - GP Number: {}", value);
        return value;
    }

    // -------------------------------------------------------------------------
    // DATE AND TIME
    // -------------------------------------------------------------------------

    @FindBy(xpath = "//input[@name='dateTime']")
    private WebElement txtDateTime;

    public String getDateTime() {
        waitForField(txtDateTime);
        String value = txtDateTime.getAttribute("value");
        logger.info("View List - Date and Time: {}", value);
        return value;
    }

    // -------------------------------------------------------------------------
    // DESCRIPTION
    // -------------------------------------------------------------------------

    @FindBy(xpath = "//textarea[@placeholder='Description of the Material']")
    private WebElement txtDescription;

    public String getDescription() {

        waitForField(txtDescription);
        String value = txtDescription.getAttribute("value");
        logger.info("View List - Description: {}", value);
        return value;
    }

    // -------------------------------------------------------------------------
    // DENOM
    // -------------------------------------------------------------------------

    @FindBy(xpath = "//input[@placeholder='Denom']")
    private WebElement txtDenom;

    public String getDenom() {
        waitForField(txtDenom);
        String value = txtDenom.getAttribute("value");
        logger.info("View List - Denom: {}", value);
        return value;
    }

    // -------------------------------------------------------------------------
    // QUANTITY
    // -------------------------------------------------------------------------

    @FindBy(xpath = "//input[@name='qty']")
    private WebElement txtQuantity;

    public String getQuantity() {
        waitForField(txtQuantity);
        String value = txtQuantity.getAttribute("value");
        logger.info("View List - Quantity: {}", value);
        return value;
    }

    // -------------------------------------------------------------------------
    // MATERIAL SENT TO ADDRESS
    // -------------------------------------------------------------------------

    @FindBy(xpath = "//textarea[@placeholder='Material sent to address']")
    private WebElement txtMaterialSentToAddress;

    public String getMaterialSentToAddress() {

        waitForField(txtMaterialSentToAddress);
        String value = txtMaterialSentToAddress.getAttribute("value");
        logger.info("View List - Material Sent To Address: {}", value);
        return value;
    }

    // -------------------------------------------------------------------------
    // AUTHORISED NAME
    // -------------------------------------------------------------------------

    @FindBy(xpath = "//input[@name='authorisedBy']")
    private WebElement txtAuthorisedName;
    public String getAuthorisedName() {
        waitForField(txtAuthorisedName);
        String value = txtAuthorisedName.getAttribute("value");
        logger.info("View List - Authorised Name: {}", value);
        return value;
    }

    // -------------------------------------------------------------------------
    // PURPOSE
    // -------------------------------------------------------------------------

    @FindBy(xpath = "//input[@placeholder='Purpose']")
    private WebElement txtPurpose;
    public String getPurpose() {
        waitForField(txtPurpose);
        String value = txtPurpose.getAttribute("value");
        logger.info("View List - Purpose: {}", value);
        return value;
    }

    // -------------------------------------------------------------------------
    // INSECURITY NAME
    // -------------------------------------------------------------------------

    @FindBy(xpath = "//input[@name='insecurityName']")
    private WebElement txtInsecurityName;
    public String getInsecurityName() {
        waitForField(txtInsecurityName);
        String value = txtInsecurityName.getAttribute("value");
        logger.info("View List - In-security Name: {}", value);
        return value;
    }

    // -------------------------------------------------------------------------
    // APPROXIMATE DATE
    // -------------------------------------------------------------------------

    @FindBy(xpath = "//input[@name='approxdate']")
    private WebElement txtApproxDate;
    public String getApproximateDate() {
        waitForField(txtApproxDate);
        String value = txtApproxDate.getAttribute("value");
        logger.info("View List - Approximate Date: {}", value);
        return value;
    }

    // -------------------------------------------------------------------------
    // MATERIAL CARRIED NAME
    // -------------------------------------------------------------------------

    @FindBy(xpath = "//input[@name='materialcarriedname']")
    private WebElement txtMaterialCarriedName;

    public String getMaterialCarriedName() {
        waitForField(txtMaterialCarriedName);
        String value = txtMaterialCarriedName.getAttribute("value");
        logger.info("View List - Material Carried Name: {}", value);
        return value;
    }

    // -------------------------------------------------------------------------
    // DC NUMBER
    // -------------------------------------------------------------------------

    @FindBy(xpath = "//input[@placeholder='DC No']")
    private WebElement txtDCNo;

    public String getDCNo() {
        waitForField(txtDCNo);
        String value = txtDCNo.getAttribute("value");
        logger.info("View List - DC Number: {}", value);
        return value;
    }

    // -------------------------------------------------------------------------
    // MATERIAL RETURN DATE
    // -------------------------------------------------------------------------

    @FindBy(xpath = "//input[@name='mrdate']")
    private WebElement txtMaterialReturnDate;
    public String getMaterialReturnDate() {
        waitForField(txtMaterialReturnDate);
        String value = txtMaterialReturnDate.getAttribute("value");
        logger.info("View List - Material Return Date: {}", value);
        return value;
    }

    // -------------------------------------------------------------------------
    // MATERIAL RETURN QUANTITY
    // -------------------------------------------------------------------------

    @FindBy(xpath = "//input[@name='mrqty']")
    private WebElement txtMaterialReturnQuantity;
    public String getMaterialReturnQuantity() {
        waitForField(txtMaterialReturnQuantity);
        String value = txtMaterialReturnQuantity.getAttribute("value");
        logger.info("View List - Material Return Quantity: {}", value);
        return value;
    }

    // -------------------------------------------------------------------------
    // SECURITY NAME
    // -------------------------------------------------------------------------

    @FindBy(xpath = "//input[@name='securityName']")
    private WebElement txtSecurityName;

    public String getSecurityName() {
        waitForField(txtSecurityName);
        String value = txtSecurityName.getAttribute("value");
        logger.info("View List - Security Name: {}", value);
        return value;
    }

    // -------------------------------------------------------------------------
    // REMARKS
    // -------------------------------------------------------------------------

    @FindBy(xpath = "//textarea[@placeholder='Remarks']")
    private WebElement txtRemarks;

    public String getRemarks() {
        waitForField(txtRemarks);
        String value = txtRemarks.getAttribute("value");
        logger.info("View List - Remarks: {}", value);
        return value;
    }

    @FindBy(xpath="//button[normalize-space()='Continue & Save']") private WebElement continueAndSave;
    public void clickBtnContinueAndSave() {

        logger.info("Waiting for Continue & Save button.");

        wait.until(ExpectedConditions.elementToBeClickable(continueAndSave));

        logger.info("Clicking Continue & Save.");

        clickElement(continueAndSave);

        logger.info("Continue & Save clicked successfully.");
    }

    @FindBy(xpath = "//div[normalize-space()='Form updated succesfully']")
    private WebElement successupdateMessage;
    public String getSuccessUpdateMessage() {

        logger.info("Waiting for form creation success message.");

        String message = wait.until(
                ExpectedConditions.visibilityOf(successupdateMessage)
        ).getText().trim();

        logger.info("Success message received: {}", message);

        return message;
    }


    // -------------------------------------------------------------------------
    // REUSABLE WAIT
    // -------------------------------------------------------------------------

    private void waitForField(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }
}
