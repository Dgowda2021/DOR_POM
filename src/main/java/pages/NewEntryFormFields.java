package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.DatePicker;
import utilities.SignaturePad;
import utilities.TimePicker;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class NewEntryFormFields extends BasePage {

    private static final Logger logger =
            LogManager.getLogger(NewEntryFormFields.class);

    private final WebDriverWait wait;

    public NewEntryFormFields(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);

        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        logger.debug("NewEntryFormFields page initialized.");
    }

    // -------------------------------------------------------------------------
    // Navigation
    // -------------------------------------------------------------------------

    @FindBy(xpath = "//span[normalize-space()='Material Outward Register (Returnable)']")
    private WebElement materialOutwardReturnable;

    public void clickMaterialOutwardReturnable() {

        logger.info("Opening Material Outward Register (Returnable).");

        wait.until(ExpectedConditions.elementToBeClickable(materialOutwardReturnable));
        clickElement(materialOutwardReturnable);

        logger.info("Material Outward Register (Returnable) opened successfully.");
    }

   @FindBy(xpath="//*[normalize-space()='Material Outward Register (Returnable)']")
    private WebElement materialOutwardRegisterReturnable;
    public String formNameConfirmation(){
        logger.info("Waiting for form creation success message.");
        String message = wait.until(ExpectedConditions.visibilityOf(materialOutwardRegisterReturnable)).getText().trim();
        logger.info("Success message received: {}", message);
        return message;

    }

    @FindBy(xpath = "//span[normalize-space()='New Entry']")
    private WebElement newEntry;

    public void clickNewEntry() {

        logger.info("Clicking New Entry.");

        wait.until(ExpectedConditions.elementToBeClickable(newEntry));
        clickElement(newEntry);

        logger.info("New Entry form opened successfully.");
    }

    // -------------------------------------------------------------------------
    // Form Date
    // -------------------------------------------------------------------------

    @FindBy(xpath = "//input[@name='date']")
    private WebElement dateField;

    public void selectDate(String day,
            String month, String year) throws InterruptedException {
        logger.info("Selecting form date: {} {} {}", day, month, year);

        wait.until(ExpectedConditions.elementToBeClickable(dateField));
        clickElement(dateField);

        DatePicker datePicker = new DatePicker(driver);
        datePicker.selectDate(day, month, year);

        String expectedDate = getExpectedDate(day, month, year);

        wait.until(driver -> expectedDate.equals(dateField.getAttribute("value")));

        logger.info("Form date selected successfully. Value: {}", dateField.getAttribute("value"));
    }

    public void selectCurrentDate() throws InterruptedException {

        LocalDate today = LocalDate.now();

        String day = String.valueOf(today.getDayOfMonth());
        String month = today.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
        String year = String.valueOf(today.getYear());

        logger.info("Selecting current date: {} {} {}", day, month, year);

        selectDate(day, month, year);
    }

    // -------------------------------------------------------------------------
    // GP Number
    // -------------------------------------------------------------------------

    @FindBy(xpath = "//input[@placeholder='GP Number']")
    private WebElement txtGPNumber;

    public void setTxtGPNumber(String gpNumber) {

        logger.info("Entering GP Number: {}", gpNumber);

        waitForInput(txtGPNumber);

        enterText(txtGPNumber, gpNumber);

        wait.until(driver -> gpNumber.equals(txtGPNumber.getAttribute("value")));

        logger.info("GP Number entered successfully.");
    }

    // -------------------------------------------------------------------------
    // Date and Time
    // -------------------------------------------------------------------------

    @FindBy(xpath = "//input[@name='dateTime']")
    private WebElement txtDateTime;

    public void selectDateTime(String day, String month, String year, int hour, int minute) throws InterruptedException {

        logger.info("Selecting date and time: {} {} {} {}:{}", day, month, year, String.format("%02d", hour), String.format("%02d", minute));

        wait.until(ExpectedConditions.elementToBeClickable(txtDateTime));
        clickElement(txtDateTime);

        DatePicker datePicker = new DatePicker(driver);
        datePicker.selectDate(day, month, year);

        TimePicker timePicker = new TimePicker(driver);
        timePicker.selectTime(hour, minute);

        logger.info("Date and time selected successfully.");
    }

    @FindBy(xpath = "//span[normalize-space()='Set']")
    private WebElement btnSetDateTime;

    public void clickBtnSetDateTime() {

        logger.info("Clicking Set button for date and time.");

        wait.until(ExpectedConditions.elementToBeClickable(btnSetDateTime));
        clickElement(btnSetDateTime);

        // Wait until the next form field is ready.
        waitForInput(txtDescription);

        logger.info("Date and time set successfully.");
    }

    // -------------------------------------------------------------------------
    // Material Details
    // -------------------------------------------------------------------------

    @FindBy(xpath = "//textarea[@placeholder='Description of the Material']")
    private WebElement txtDescription;

    public void setTxtDescription(String description) {

        logger.info("Entering material description.");

        waitForInput(txtDescription);
        enterText(txtDescription, description);

        waitForValue(txtDescription, description);

        logger.info("Material description entered successfully.");
    }

    @FindBy(xpath = "//input[@placeholder='Denom']")
    private WebElement txtDenom;

    public void setTxtDenom(String denom) {

        logger.info("Entering material denomination.");

        waitForInput(txtDenom);
        enterText(txtDenom, denom);

        waitForValue(txtDenom, denom);

        logger.info("Material denomination entered successfully.");
    }

    @FindBy(xpath = "//input[@name='qty']")
    private WebElement txtQuantity;

    public void setTxtQuantity(String quantity) {

        logger.info("Entering material quantity.");

        waitForInput(txtQuantity);
        enterText(txtQuantity, quantity);

        waitForValue(txtQuantity, quantity);

        logger.info("Material quantity entered successfully.");
    }

    @FindBy(xpath = "//textarea[@placeholder='Material sent to address']")
    private WebElement txtMaterialSentToAddress;

    public void setTxtMaterialSentToAddress(String address) {

        logger.info("Entering material sent to address.");

        waitForInput(txtMaterialSentToAddress);
        enterText(txtMaterialSentToAddress, address);

        waitForValue(txtMaterialSentToAddress, address);

        logger.info("Material sent to address entered successfully.");
    }

    @FindBy(xpath = "//input[@name='authorisedBy']")
    private WebElement txtAuthorisedName;

    public void setTxtAuthorisedName(String name) {

        logger.info("Entering authorised person's name.");

        waitForInput(txtAuthorisedName);
        enterText(txtAuthorisedName, name);

        waitForValue(txtAuthorisedName, name);

        logger.info("Authorised person's name entered successfully.");
    }

    // -------------------------------------------------------------------------
    // Signature Pad 1
    // -------------------------------------------------------------------------

    @FindBy(xpath = "(//div[@class='forms-signature-box'])[1]")
    private WebElement signaturePadOpen1;

    public void signaturePadClick1() {

        logger.info("Opening Signature Pad 1.");

        wait.until(ExpectedConditions.elementToBeClickable(signaturePadOpen1));
        clickElement(signaturePadOpen1);

        waitForSignatureCanvas();

        logger.info("Signature Pad 1 opened successfully.");
    }

    @FindBy(xpath = "//div[@class='sig-canvas-wrapper']//canvas")
    private WebElement signaturePad;

    public void signOnPad() throws InterruptedException {

        logger.info("Waiting for signature canvas.");

        waitForSignatureCanvas();

        logger.info("Drawing signature.");

        SignaturePad signature = new SignaturePad(driver);
        signature.sign(signaturePad);

        logger.info("Signature drawn successfully.");

        // Make sure the Save Signature button is ready after drawing.
        wait.until(ExpectedConditions.elementToBeClickable(btnSaveSignature));

        logger.info("Save Signature button is ready.");
    }

    @FindBy(xpath = "//button[normalize-space()='Save Signature']")
    private WebElement btnSaveSignature;

    public void clickBtnSaveSignature() {

        logger.info("Saving signature.");

        wait.until(ExpectedConditions.elementToBeClickable(btnSaveSignature));
        clickElement(btnSaveSignature);

        /*
         * Important:
         * Do not immediately move to the next field.
         * Wait until the signature dialog/button disappears.
         */
        wait.until(ExpectedConditions.invisibilityOf(btnSaveSignature));

        logger.info("Signature saved and signature dialog closed successfully.");
    }

    // -------------------------------------------------------------------------
    // Purpose / In-security
    // -------------------------------------------------------------------------

    @FindBy(xpath = "//input[@placeholder='Purpose']")
    private WebElement txtPurpose;

    public void setTxtPurpose(String purpose) {

        logger.info("Entering purpose.");

        waitForInput(txtPurpose);
        enterText(txtPurpose, purpose);

        waitForValue(txtPurpose, purpose);

        logger.info("Purpose entered successfully.");
    }

    @FindBy(xpath = "//input[@name='insecurityName']")
    private WebElement txtInsecurityName;

    public void setTxtInsecurityName(String name) {

        logger.info("Entering in-security person's name.");

        waitForInput(txtInsecurityName);
        enterText(txtInsecurityName, name);

        waitForValue(txtInsecurityName, name);

        logger.info("In-security person's name entered successfully.");
    }

    // -------------------------------------------------------------------------
    // Signature Pad 2
    // -------------------------------------------------------------------------

    @FindBy(xpath = "(//div[@class='forms-signature-box'])[2]")
    private WebElement signaturePadOpen2;

    public void signaturePadClick2() {

        logger.info("Opening Signature Pad 2.");

        wait.until(ExpectedConditions.elementToBeClickable(signaturePadOpen2));
        clickElement(signaturePadOpen2);

        waitForSignatureCanvas();

        logger.info("Signature Pad 2 opened successfully.");
    }

    // -------------------------------------------------------------------------
    // Approximate Date
    // -------------------------------------------------------------------------

    @FindBy(xpath = "//input[@name='approxdate']")
    private WebElement txtApproxDate;

    public void selectApproximateDate(String day, String month, String year) throws InterruptedException {
        logger.info("Selecting approximate date: {} {} {}", day, month, year);

        wait.until(ExpectedConditions.elementToBeClickable(txtApproxDate));
        clickElement(txtApproxDate);

        DatePicker datePicker = new DatePicker(driver);
        datePicker.selectDate(day, month, year);

        String expectedDate = getExpectedDate(day, month, year);

        wait.until(driver -> expectedDate.equals(txtApproxDate.getAttribute("value")));

        logger.info("Approximate date selected successfully. Value: {}", txtApproxDate.getAttribute("value"));
    }

    // -------------------------------------------------------------------------
    // Material Carried
    // -------------------------------------------------------------------------

    @FindBy(xpath = "//input[@name='materialcarriedname']")
    private WebElement txtMaterialCarriedName;

    public void setTxtMaterialCarriedName(String name) {

        logger.info("Entering material carried person's name.");

        waitForInput(txtMaterialCarriedName);
        enterText(txtMaterialCarriedName, name);

        waitForValue(txtMaterialCarriedName, name);

        logger.info("Material carried person's name entered successfully.");
    }

    // -------------------------------------------------------------------------
    // Signature Pad 3
    // -------------------------------------------------------------------------

    @FindBy(xpath = "(//div[@class='forms-signature-box'])[3]")
    private WebElement signaturePadOpen3;

    public void signaturePadClick3() {

        logger.info("Opening Signature Pad 3.");

        wait.until(ExpectedConditions.elementToBeClickable(signaturePadOpen3));
        clickElement(signaturePadOpen3);

        waitForSignatureCanvas();

        logger.info("Signature Pad 3 opened successfully.");
    }

    // -------------------------------------------------------------------------
    // DC Number
    // -------------------------------------------------------------------------

    @FindBy(xpath = "//input[@placeholder='DC No']")
    private WebElement txtDCNo;

    public void setTxtDCNo(String dcNo) {

        logger.info("Entering DC Number.");

        waitForInput(txtDCNo);
        enterText(txtDCNo, dcNo);

        waitForValue(txtDCNo, dcNo);

        logger.info("DC Number entered successfully.");
    }

    // -------------------------------------------------------------------------
    // Material Return Details
    // -------------------------------------------------------------------------

    @FindBy(xpath = "//input[@name='mrdate']")
    private WebElement txtMaterialReturnDate;

    public void selectMaterialReturnDate(String day, String month, String year) throws InterruptedException {

        logger.info("Selecting material return date: {} {} {}", day, month, year);

        wait.until(ExpectedConditions.elementToBeClickable(txtMaterialReturnDate));
        clickElement(txtMaterialReturnDate);

        DatePicker datePicker = new DatePicker(driver);
        datePicker.selectDate(day, month, year);

        String expectedDate = getExpectedDate(day, month, year);

        wait.until(driver -> expectedDate.equals(txtMaterialReturnDate.getAttribute("value")));
        logger.info("Material return date selected successfully. Value: {}", txtMaterialReturnDate.getAttribute("value"));
    }


    @FindBy(xpath = "//input[@name='mrqty']")
    private WebElement txtMaterialReturnQuantity;

    public void setTxtMaterialReturnQuantity(String quantity) {

        logger.info("Entering material return quantity.");

        waitForInput(txtMaterialReturnQuantity);
        enterText(txtMaterialReturnQuantity, quantity);

        waitForValue(txtMaterialReturnQuantity, quantity);

        logger.info("Material return quantity entered successfully.");
    }

    @FindBy(xpath = "//input[@name='securityName']")
    private WebElement txtSecurityName;

    public void setTxtSecurityName(String name) {

        logger.info("Entering security person's name.");

        waitForInput(txtSecurityName);
        enterText(txtSecurityName, name);

        waitForValue(txtSecurityName, name);

        logger.info("Security person's name entered successfully.");
    }

    // -------------------------------------------------------------------------
    // Signature Pad 4
    // -------------------------------------------------------------------------

    @FindBy(xpath = "(//div[@class='forms-signature-box'])[4]")
    private WebElement signaturePadOpen4;

    public void signaturePadClick4() {

        logger.info("Opening Signature Pad 4.");

        wait.until(ExpectedConditions.elementToBeClickable(signaturePadOpen4));
        clickElement(signaturePadOpen4);

        waitForSignatureCanvas();

        logger.info("Signature Pad 4 opened successfully.");
    }

    // -------------------------------------------------------------------------
    // Remarks and Submit
    // -------------------------------------------------------------------------

    @FindBy(xpath = "//textarea[@placeholder='Remarks']")
    private WebElement txtRemarks;

    public void setTxtRemarks(String remarks) {

        logger.info("Entering remarks.");
        waitForInput(txtRemarks);
        txtRemarks.clear();
        enterText(txtRemarks, remarks);
        waitForValue(txtRemarks, remarks);
        logger.info("Remarks entered successfully.");
    }

    @FindBy(xpath = "//button[normalize-space()='Continue & Save']")
    private WebElement btnContinueSave;

    public void clickBtnContinueSave() {

        logger.info("Waiting for Continue & Save button.");

        wait.until(ExpectedConditions.elementToBeClickable(btnContinueSave));

        logger.info("Clicking Continue & Save.");

        clickElement(btnContinueSave);

        logger.info("Continue & Save clicked successfully.");
    }

    @FindBy(xpath = "//div[normalize-space()='Form created succesfully']")
    private WebElement successMessage;

    // -------------------------------------------------------------------------
    // Getters for Assertions
    // -------------------------------------------------------------------------

    public String getFormDate() {

        waitForInput(dateField);

        return dateField.getAttribute("value");
    }

    public String getGPNumber() {

        waitForInput(txtGPNumber);

        return txtGPNumber.getAttribute("value");
    }

    public String getDescription() {

        waitForInput(txtDescription);

        return txtDescription.getAttribute("value");
    }

    public String getDenom() {

        waitForInput(txtDenom);

        return txtDenom.getAttribute("value");
    }

    public String getQuantity() {

        waitForInput(txtQuantity);

        return txtQuantity.getAttribute("value");
    }

    public String getMaterialSentToAddress() {

        waitForInput(txtMaterialSentToAddress);

        return txtMaterialSentToAddress.getAttribute("value");
    }

    public String getAuthorisedName() {

        waitForInput(txtAuthorisedName);

        return txtAuthorisedName.getAttribute("value");
    }

    public String getPurpose() {

        waitForInput(txtPurpose);

        return txtPurpose.getAttribute("value");
    }

    public String getInsecurityName() {

        waitForInput(txtInsecurityName);

        return txtInsecurityName.getAttribute("value");
    }

    public String getApproximateDate() {

        waitForInput(txtApproxDate);

        return txtApproxDate.getAttribute("value");
    }

    public String getMaterialCarriedName() {

        waitForInput(txtMaterialCarriedName);

        return txtMaterialCarriedName.getAttribute("value");
    }

    public String getDCNo() {

        waitForInput(txtDCNo);

        return txtDCNo.getAttribute("value");
    }

    public String getMaterialReturnDate() {

        waitForInput(txtMaterialReturnDate);

        return txtMaterialReturnDate.getAttribute("value");
    }

    public String getSecurityName() {

        waitForInput(txtSecurityName);

        return txtSecurityName.getAttribute("value");
    }

    public String getMaterialReturnQuantity() {

        waitForInput(txtMaterialReturnQuantity);

        return txtMaterialReturnQuantity.getAttribute("value");
    }

    public String getRemarks() {

        waitForInput(txtRemarks);

        return txtRemarks.getAttribute("value");
    }

    public boolean isContinueSaveEnabled() {

        wait.until(ExpectedConditions.visibilityOf(btnContinueSave));

        return btnContinueSave.isEnabled();
    }

    public String getSuccessMessage() {

        logger.info("Waiting for form creation success message.");

        String message = wait.until(
                ExpectedConditions.visibilityOf(successMessage)
        ).getText().trim();

        logger.info("Success message received: {}", message);

        return message;
    }

    // -------------------------------------------------------------------------
    // Reusable Synchronization Methods
    // -------------------------------------------------------------------------

    /**
     * Wait until an input/textarea is visible and ready for interaction.
     */
    private void waitForInput(WebElement element) {

        wait.until(ExpectedConditions.visibilityOf(element));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Wait until a field contains the expected value.
     */
    private void waitForValue(WebElement element, String expectedValue) {

        wait.until(driver -> expectedValue.equals(element.getAttribute("value")));
    }

    /**
     * Wait until the signature canvas is visible and clickable.
     */
    private void waitForSignatureCanvas() {

        wait.until(ExpectedConditions.visibilityOf(signaturePad));
        wait.until(ExpectedConditions.elementToBeClickable(signaturePad));

        logger.debug("Signature canvas is visible and ready.");
    }

    /**
     * Converts the supplied month into the numeric date format
     * used by the application, for example:
     *
     * Aug -> 8/12/2026
     * Sep -> 9/17/2026
     * Nov -> 11/12/2026
     */
    private String getExpectedDate(
            String day,
            String month,
            String year) {

        int monthNumber;

        switch (month.trim().toLowerCase()) {

            case "jan":
            case "january":
                monthNumber = 1;
                break;

            case "feb":
            case "february":
                monthNumber = 2;
                break;

            case "mar":
            case "march":
                monthNumber = 3;
                break;

            case "apr":
            case "april":
                monthNumber = 4;
                break;

            case "may":
                monthNumber = 5;
                break;

            case "jun":
            case "june":
                monthNumber = 6;
                break;

            case "jul":
            case "july":
                monthNumber = 7;
                break;

            case "aug":
            case "august":
                monthNumber = 8;
                break;

            case "sep":
            case "september":
                monthNumber = 9;
                break;

            case "oct":
            case "october":
                monthNumber = 10;
                break;

            case "nov":
            case "november":
                monthNumber = 11;
                break;

            case "dec":
            case "december":
                monthNumber = 12;
                break;

            default:
                throw new IllegalArgumentException(
                        "Invalid month: " + month
                );
        }

        return monthNumber + "/" + day + "/" + year;
    }
}

