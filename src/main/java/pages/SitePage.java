package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static java.util.regex.Pattern.quote;

public class SitePage extends BasePage {

    private static final Logger logger = LogManager.getLogger(SitePage.class);

    public SitePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        logger.info("SitePage initialized.");
    }

    // Sites Page and Action
    @FindBy(xpath = "//span[normalize-space()='Sites']")
    private WebElement sites;
    public void clickSites() {
        wait.until(ExpectedConditions.elementToBeClickable(sites)).click();
        logger.info("Clicked on Sites menu.");
    }
    @FindBy(xpath = "//div[@class='sl-header']//button[1]")
    private WebElement addSite;
    public void clickAddSite() {
        wait.until(ExpectedConditions.elementToBeClickable(addSite)).click();
        logger.info("Clicked on Add Site button.");
    }

    // Create Site Form
    @FindBy(xpath = "//input[@placeholder='Site Name']")
    private WebElement siteName;
    public void enterSiteName(String name) {
        wait.until(ExpectedConditions.visibilityOf(siteName));
        siteName.clear();
        siteName.sendKeys(name);
        logger.info("Entered Site Name: {}", name);
    }

    @FindBy(xpath = "//input[@placeholder='Site Code']")
    private WebElement siteCode;

    public void enterSiteCode(String code) {
        wait.until(ExpectedConditions.visibilityOf(siteCode));
        siteCode.clear();
        siteCode.sendKeys(code);
        logger.info("Entered Site Code: {}", code);
    }

    @FindBy(xpath = "//span[normalize-space()='Vertical Center']")
    private WebElement verticalCenter;
    public void selectVerticalCenter() {
        wait.until(ExpectedConditions.elementToBeClickable(verticalCenter)).click();
        logger.info("Selected Vertical Center.");
    }

    public void selectCenter(String centerName) {
        By center = By.xpath("//span[normalize-space()='" + centerName + "']");
        wait.until(ExpectedConditions.elementToBeClickable(center)).click();
        logger.info("Selected Center: {}", centerName);
    }
    @FindBy(xpath = "//input[@placeholder='City']")
    private WebElement city;

    public void enterCity(String cityName) {
        wait.until(ExpectedConditions.visibilityOf(city));
        city.clear();
        city.sendKeys(cityName);
        logger.info("Entered City: {}", cityName);
    }
    @FindBy(xpath = "//input[@placeholder='State']")
    private WebElement state;
    public void enterState(String stateName) {
        wait.until(ExpectedConditions.visibilityOf(state));
        state.clear();
        state.sendKeys(stateName);
        logger.info("Entered State: {}", stateName);
    }
    @FindBy(xpath = "//input[@placeholder='Address']")
    private WebElement address;

    public void enterAddress(String addressText) {
        wait.until(ExpectedConditions.visibilityOf(address));
        address.clear();
        address.sendKeys(addressText);
        logger.info("Entered Address: {}", addressText);
    }
    @FindBy(xpath = "//button[normalize-space()='Submit']")
    private WebElement submit;
    public void clickSubmit() {
        wait.until(ExpectedConditions.elementToBeClickable(submit)).click();
        logger.info("Clicked Submit button.");
    }
    @FindBy(xpath = "//button[normalize-space()='Cancel']")
    private WebElement cancel;
    public void clickCancel() {
        wait.until(ExpectedConditions.elementToBeClickable(cancel)).click();
        logger.info("Clicked Cancel button.");
    }

    // Validation Messages
        @FindBy(xpath = "//div[normalize-space()='Site code already exists']")
    private WebElement siteCodeAlreadyExists;
    public boolean isSiteCodeAlreadyExistsDisplayed() {
        try {
            boolean displayed = wait.until(
                    ExpectedConditions.visibilityOf(siteCodeAlreadyExists)
            ).isDisplayed();

            logger.info("Site code already exists message displayed: {}", displayed);
            return displayed;

        } catch (Exception e) {
            logger.warn("Site code already exists message was not displayed.");
            return false;
        }
    }

    // Application currently contains "succesfully"
    @FindBy(xpath = "//div[normalize-space()='Site created succesfully']")
    private WebElement siteCreatedSuccessfully;
    public boolean isSiteCreatedSuccessfullyDisplayed() {
        try {
            boolean displayed = wait.until(
                    ExpectedConditions.visibilityOf(siteCreatedSuccessfully)
            ).isDisplayed();

            logger.info("Site created successfully message displayed: {}", displayed);
            return displayed;

        } catch (Exception e) {
            logger.warn("Site created successfully message was not displayed.");
            return false;
        }
    }

    @FindBy(xpath = "//div[normalize-space()='Site updated succesfully']")
    private WebElement siteUpdatedSuccessfully;
    public boolean isSiteUpdatedSuccessfullyDisplayed() {
        try {
            boolean displayed = wait.until(
                    ExpectedConditions.visibilityOf(siteUpdatedSuccessfully)
            ).isDisplayed();

            logger.info("Site created successfully message displayed: {}", displayed);
            return displayed;

        } catch (Exception e) {
            logger.warn("Site created successfully message was not displayed.");
            return false;
        }
    }

    // Search / Site Actions
    @FindBy(xpath = "//input[@placeholder='Search by site code, city...']")
    private WebElement searchBySiteCodeOrCity;


    public void searchSite(String searchText) {
        wait.until(ExpectedConditions.visibilityOf(searchBySiteCodeOrCity));
        searchBySiteCodeOrCity.clear();
        searchBySiteCodeOrCity.sendKeys(searchText);
        logger.info("Searched site using: {}", searchText);
    }

    // Edit
    @FindBy(xpath = "(//span[contains(text(),'edit')])[1]")
    private WebElement edit;
    public void clickEdit() {
        wait.until(ExpectedConditions.elementToBeClickable(edit)).click();
        logger.info("Clicked Edit button.");
    }
    @FindBy(xpath="//h1[normalize-space()='Sites']") private WebElement sitesPageHeader;
    public boolean isSitesPageDisplayed() {
        try {
            boolean displayed = wait.until(ExpectedConditions.visibilityOf(sitesPageHeader)).isDisplayed();

            logger.info("Sites page displayed: {}", displayed);
            return displayed;

        } catch (Exception e) {
            logger.warn("Sites page is not displayed.");
            return false;
        }
    }

    public boolean isSiteDisplayed(String siteCode) {

        By site = By.xpath("//span[normalize-space()='" + siteCode + "']");
        try {
            boolean displayed = wait.until(ExpectedConditions.visibilityOfElementLocated(site)).isDisplayed();
            logger.info("Site {} displayed: {}", siteCode, displayed);
            return displayed;
        } catch (Exception e) {
            logger.warn("Site {} was not displayed.", siteCode);
            return false;
        }
    }


    @FindBy(xpath = "//div[@class='mat-mdc-select-value']")
    private WebElement centerDropdown;

    public String getSelectedCenter() {

        try {
            String selectedCenter = wait.until(ExpectedConditions.visibilityOf(centerDropdown)).getText().trim();
            logger.info("Currently selected center: {}", selectedCenter);
            return selectedCenter;

        } catch (Exception e) {
            logger.warn("Unable to get currently selected center: {}", e.getMessage());
            return "";
        }
    }


    public void selectCenterIfDifferent(String requiredCenter) {
        String currentCenter = getSelectedCenter();
        // Center is already selected
        if (currentCenter.equalsIgnoreCase(requiredCenter)) {
            logger.info("Center '{}' is already selected. No change required.", requiredCenter);
            return;
        }

        logger.info("Current center is '{}'. Changing to '{}'.", currentCenter, requiredCenter);
        // Open the dropdown first
        wait.until(ExpectedConditions.elementToBeClickable(centerDropdown)).click();

        logger.info("Center dropdown opened.");
        // Select required option
        By centerOption = By.xpath("//mat-option//span[normalize-space()='" + requiredCenter + "']");
        wait.until(ExpectedConditions.elementToBeClickable(centerOption)).click();
        logger.info("Center changed from '{}' to '{}'.", currentCenter, requiredCenter);
    }


    @FindBy(xpath = "//span[normalize-space()='Site Name is required']")
    private WebElement siteNameIsRequired;
    public boolean isSiteNameRequiredDisplayed() {
        return isValidationMessageDisplayed(siteNameIsRequired, "Site Name is required");
    }
    @FindBy(xpath = "//span[normalize-space()='Site Code is required']")
    private WebElement siteCodeIsRequired;
    public boolean isSiteCodeRequiredDisplayed() {
        return isValidationMessageDisplayed(siteCodeIsRequired, "Site Code is required");
    }
    @FindBy(xpath = "//span[normalize-space()='Vertical Center is required']")
    private WebElement verticalCenterIsRequired;
    public boolean isVerticalCenterRequiredDisplayed() {
        return isValidationMessageDisplayed(
                verticalCenterIsRequired,
                "Vertical Center is required"
        );
    }
    @FindBy(xpath = "//span[normalize-space()='City is required']")
    private WebElement cityIsRequired;
    public boolean isCityRequiredDisplayed() {
        return isValidationMessageDisplayed(cityIsRequired, "City is required");
    }

    @FindBy(xpath = "//span[normalize-space()='State is required']")
    private WebElement stateIsRequired;
    public boolean isStateRequiredDisplayed() {
        return isValidationMessageDisplayed(stateIsRequired, "State is required");
    }
    @FindBy(xpath = "//span[normalize-space()='Address is required']")
    private WebElement addressIsRequired;
    public boolean isAddressRequiredDisplayed() {
        return isValidationMessageDisplayed(
                addressIsRequired,
                "Address is required"
        );
    }

    private boolean isValidationMessageDisplayed(
            WebElement validationMessage,
            String message
    ) {
        try {
            boolean displayed = wait.until(ExpectedConditions.visibilityOf(validationMessage)).isDisplayed();
            logger.info("{} validation displayed: {}", message, displayed);
            return displayed;

        } catch (Exception e) {
            logger.warn("{} validation message was not displayed.", message);
            return false;
        }
    }

    public void triggerSiteNameValidation() {

        wait.until(ExpectedConditions.elementToBeClickable(siteName)).click();
        wait.until(ExpectedConditions.elementToBeClickable(siteCode)).click();
        logger.info("Triggered Site Name validation.");
    }
    public void triggerSiteCodeValidation() {

        wait.until(ExpectedConditions.elementToBeClickable(siteCode)).click();
        wait.until(ExpectedConditions.elementToBeClickable(city)).click();
        logger.info("Triggered Site Code validation.");
    }
    public void triggerCenterValidation() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(centerDropdown)).click();
        Thread.sleep(1000);
        wait.until(ExpectedConditions.elementToBeClickable(city)).click();
        wait.until(ExpectedConditions.elementToBeClickable(city)).click();
        logger.info("Triggered Vertical Center validation.");
    }
    public void triggerCityValidation() {
        wait.until(ExpectedConditions.elementToBeClickable(city)).click();
        wait.until(ExpectedConditions.elementToBeClickable(state)).click();
        logger.info("Triggered City validation.");
    }
    public void triggerStateValidation() {
        wait.until(ExpectedConditions.elementToBeClickable(state)).click();
        wait.until(ExpectedConditions.elementToBeClickable(address)).click();
        logger.info("Triggered State validation.");
    }
    public void triggerAddressValidation() {
        wait.until(ExpectedConditions.elementToBeClickable(address)).click();
        wait.until(ExpectedConditions.elementToBeClickable(addSite)).click();
        logger.info("Triggered Address validation.");
    }

    public boolean isSubmitEnabled() {
        boolean enabled = submit.isEnabled();
        logger.info("Submit button enabled: {}", enabled);
        return enabled;
    }
}



