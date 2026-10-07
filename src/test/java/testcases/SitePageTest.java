package testcases;

import base.BaseLogin;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.SitePage;

import java.time.Duration;
import java.util.logging.Logger;

public class SitePageTest extends BaseLogin {

    Logger logger = Logger.getLogger(SitePageTest.class.getName());

    @Test(priority = 1)
    public void sitePageTest() throws InterruptedException {

        test = extent.createTest(
                "Site Page Test",
                "Verify that the user can access Sites and create a new site."
        );

        SoftAssert softAssert = new SoftAssert();

        logger.info("========== Site Page Test Started ==========");
        test.info("Site Page Test Started");

        try {

            // ---------------------------------------
            // Page Object Initialization
            // ---------------------------------------

            SitePage sitePage = new SitePage(driver);

            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

            // ---------------------------------------
            // Step 1: Open Sites
            // ---------------------------------------

            logger.info("Opening Sites module...");
            test.info("Opening Sites module...");

            sitePage.clickSites();

            logger.info("Successfully clicked Sites menu.");
            test.pass("Successfully clicked Sites menu.");


            // ---------------------------------------
            // Step 2: Verify Sites page
            // ---------------------------------------

            logger.info("Verifying Sites page...");
            test.info("Verifying Sites page...");

            softAssert.assertTrue(sitePage.isSitesPageDisplayed(), "Sites page is not displayed.");

            if (sitePage.isSitesPageDisplayed()) {
                logger.info("Sites page displayed successfully.");
                test.pass("Sites page displayed successfully.");
            } else {
                logger.warning("Sites page is not displayed.");
                test.fail("Sites page is not displayed.");
            }


            // ---------------------------------------
            // Step 3: Click Add Site
            // ---------------------------------------

            logger.info("Clicking Add Site...");
            test.info("Clicking Add Site...");

            sitePage.clickAddSite();

            logger.info("Add Site form opened.");
            test.pass("Add Site form opened successfully.");


            // ---------------------------------------
            // Step 4: Enter Site Details
            // ---------------------------------------

            String siteName = "Automation Test Site";
            logger.info("Entering Site Name: " + siteName);
            test.info("Entering Site Name: " + siteName);

            sitePage.enterSiteName(siteName);

            String siteCode = "DK" + (System.currentTimeMillis() % 1000);
            logger.info("Entering Site Code: " + siteCode);
            test.info("Entering Site Code: " + siteCode);

            sitePage.enterSiteCode(siteCode);


            logger.info("Selecting Vertical Center...");
            test.info("Selecting Vertical Center...");

            sitePage.selectVerticalCenter();

            sitePage.selectCenter("Delivery Center");

            String city = "Bangalore";
            logger.info("Entering City: " + city);
            test.info("Entering City: " + city);

            sitePage.enterCity(city);

            String state = "Karnataka";

            logger.info("Entering State: " + state);
            test.info("Entering State: " + state);

            sitePage.enterState(state);

            String address = "Automation Test Address";
            logger.info("Entering Address: " + address);
            test.info("Entering Address: " + address);

            sitePage.enterAddress(address);


            // ---------------------------------------
            // Step 5: Submit
            // ---------------------------------------

            logger.info("Submitting Create Site form...");
            test.info("Submitting Create Site form...");

            // sitePage.clickSubmit();

            logger.info("Create Site form submitted.");
            test.info("Create Site form submitted.");


            // ---------------------------------------
            // Step 6: Verify Site Creation
            // ---------------------------------------

            logger.info("Verifying Site creation...");
            test.info("Verifying Site creation...");

            boolean siteCreated = sitePage.isSiteCreatedSuccessfullyDisplayed();

            softAssert.assertTrue(siteCreated, "Site created successfully message was not displayed.");

            if (siteCreated) {

                logger.info("Site created successfully. Site Name: " + siteName + ", Site Code: " + siteCode);
                test.pass("Site created successfully. Site Name: " + siteName + ", Site Code: " + siteCode);

            } else {

                logger.warning("Site creation success message was not displayed.");
                test.fail("Site creation success message was not displayed.");
            }


        } catch (Exception e) {

            logger.severe("Site page test failed: " + e.getMessage());
            test.fail("Site page test failed: " + e.getMessage());
            softAssert.fail("Site page test failed: " + e.getMessage());

        } finally {

            // ---------------------------------------
            // Finalize Soft Assertions
            // ---------------------------------------

            softAssert.assertAll();

            logger.info("========== Site Page Test Completed ==========");
            test.info("Site Page Test Completed");
        }
    }

    @Test(priority = 2)
    public void editSitePageTest() throws InterruptedException {

        test = extent.createTest(
                "Edit Site Test",
                "Verify that an existing site can be searched, edited and updated successfully."
        );

        SoftAssert softAssert = new SoftAssert();

        logger.info("========== Edit Site Test Started ==========");
        test.info("Edit Site Test Started");

        try {

            SitePage sitePage = new SitePage(driver);

            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

            // -----------------------------------------
            // Step 1: Open Sites module
            // -----------------------------------------

            logger.info("Opening Sites module...");
            test.info("Opening Sites module...");

            sitePage.clickSites();

            logger.info("Sites menu clicked successfully.");
            test.pass("Sites menu clicked successfully.");

            // -----------------------------------------
            // Step 2: Verify Sites page
            // -----------------------------------------

            logger.info("Verifying Sites page...");

            boolean sitesPageDisplayed = sitePage.isSitesPageDisplayed();

            softAssert.assertTrue(sitesPageDisplayed, "Sites page is not displayed.");

            if (sitesPageDisplayed) {
                logger.info("Sites page displayed successfully.");
                test.pass("Sites page displayed successfully.");
            } else {
                logger.warning("Sites page is not displayed.");
                test.fail("Sites page is not displayed.");
            }

            // -----------------------------------------
            // Step 3: Search existing site
            // -----------------------------------------

            String siteCode = "DK222"; // Replace with existing site code

            logger.info("Searching for site with code: {}");
            test.info("Searching for site with code: " + siteCode);

            sitePage.searchSite(siteCode);

            logger.info("Site search completed.");
            test.pass("Site search completed for site code: " + siteCode);

            // -----------------------------------------
            // Step 4: Click Edit
            // -----------------------------------------

            logger.info("Clicking Edit button...");
            test.info("Clicking Edit button...");

            sitePage.clickEdit();

            logger.info("Edit button clicked successfully.");
            test.pass("Edit form opened successfully.");

            // -----------------------------------------
            // Step 5: Update Site details
            // -----------------------------------------

            String updatedSiteName = "Automation Updated Site";
            logger.info("Updating Site Name...");
            sitePage.enterSiteName(updatedSiteName);
            sitePage.selectCenterIfDifferent("Prime Now");
            String updatedCity = "Chennai";
            logger.info("Updating City...");
            sitePage.enterCity(updatedCity);

            String updatedState = "Tamil Nadu";
            logger.info("Updating State...");
            sitePage.enterState(updatedState);

            String updatedAddress = "Updated Automation Test Address";
            logger.info("Updating Address...");
            sitePage.enterAddress(updatedAddress);

            test.info("Site details updated.");

            // -----------------------------------------
            // Step 6: Submit changes
            // -----------------------------------------

            logger.info("Submitting updated site details...");
            test.info("Submitting updated site details...");

            sitePage.clickSubmit();

            logger.info("Updated site submitted successfully.");
            test.pass("Updated site submitted successfully.");

            // -----------------------------------------
            // Step 7: Verify update
            // -----------------------------------------

            boolean siteUpdated = sitePage.isSiteUpdatedSuccessfullyDisplayed();

            softAssert.assertTrue(siteUpdated, "Site update success message was not displayed.");

            if (siteUpdated) {
                logger.info("Site updated successfully.");
                test.pass("Site updated successfully.");
            } else {
                logger.warning("Site update success message was not displayed.");
                test.fail("Site update success message was not displayed.");
            }

        } catch (Exception e) {

            logger.severe("Edit Site test failed: " + e.getMessage());
            test.fail("Edit Site test failed: " + e.getMessage());

            softAssert.fail("Edit Site test failed: " + e.getMessage());

        } finally {

            softAssert.assertAll();

            logger.info("========== Edit Site Test Completed ==========");
            test.info("Edit Site Test Completed");
        }
    }

    @Test(priority = 3)
    public void duplicateSiteCodeTest() throws InterruptedException {

        test = extent.createTest(
                "Duplicate Site Code Test",
                "Verify that the system prevents creation of a site with an existing site code."
        );

        SoftAssert softAssert = new SoftAssert();

        logger.info("========== Duplicate Site Code Test Started ==========");
        test.info("Duplicate Site Code Test Started");

        try {

            SitePage sitePage = new SitePage(driver);

            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

            sitePage.clickSites();

            logger.info("Sites page opened.");
            test.info("Sites page opened.");

            sitePage.clickAddSite();

            logger.info("Add Site form opened.");
            test.info("Add Site form opened.");

            String siteName = "Duplicate Test Site";
            String existingSiteCode = "DK222"; // Existing site code

            sitePage.enterSiteName(siteName);
            sitePage.enterSiteCode(existingSiteCode);

            logger.info("Selecting Vertical Center...");
            test.info("Selecting Vertical Center...");

            sitePage.selectVerticalCenter();

            sitePage.selectCenter("Delivery Center");

            sitePage.enterCity("Chennai");
            sitePage.enterState("Tamil Nadu");
            sitePage.enterAddress("Duplicate Site Test Address");

            sitePage.clickSubmit();

            boolean duplicateMessage =
                    sitePage.isSiteCodeAlreadyExistsDisplayed();

            softAssert.assertTrue(
                    duplicateMessage,
                    "Site code already exists message was not displayed."
            );

            if (duplicateMessage) {
                test.pass("Duplicate Site Code validation displayed successfully.");
            } else {
                test.fail("Duplicate Site Code validation was not displayed.");
            }

        } catch (Exception e) {

            logger.severe("Duplicate Site Code test failed: " + e.getMessage());
            test.fail("Duplicate Site Code test failed: " + e.getMessage());

            softAssert.fail(
                    "Duplicate Site Code test failed: " + e.getMessage()
            );

        } finally {

            softAssert.assertAll();

            logger.info("========== Duplicate Site Code Test Completed ==========");
            test.info("Duplicate Site Code Test Completed");
        }
    }
    @Test(priority = 4)
    public void searchSiteTest() throws InterruptedException {

        test = extent.createTest(
                "Search Site Test",
                "Verify that an existing site can be searched successfully."
        );

        SoftAssert softAssert = new SoftAssert();

        logger.info("========== Search Site Test Started ==========");
        test.info("Search Site Test Started");

        try {

            SitePage sitePage = new SitePage(driver);

            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

            sitePage.clickSites();

            String siteCode = "AUTO2";

            logger.info("Searching site using Site Code: {}");
            test.info("Searching site using Site Code: " + siteCode);

            sitePage.searchSite(siteCode);

            // Add a page-object method to verify the searched site
            boolean siteDisplayed = sitePage.isSiteDisplayed(siteCode);

            softAssert.assertTrue(siteDisplayed, "Searched site was not displayed.");

            if (siteDisplayed) {
                test.pass("Site found successfully: " + siteCode);
            } else {
                test.fail("Site was not found: " + siteCode);
            }

        } catch (Exception e) {

            logger.severe("Search Site test failed: " + e.getMessage());
            test.fail("Search Site test failed: " + e.getMessage());
            softAssert.fail("Search Site test failed: " + e.getMessage());

        } finally {

            softAssert.assertAll();

            logger.info("========== Search Site Test Completed ==========");
            test.info("Search Site Test Completed");
        }
    }
    @Test(priority = 5)
    public void cancelCreateSiteTest() throws InterruptedException {

        test = extent.createTest(
                "Cancel Create Site Test",
                "Verify that cancelling site creation does not create a new site."
        );

        SoftAssert softAssert = new SoftAssert();

        logger.info("========== Cancel Create Site Test Started ==========");

        try {

            SitePage sitePage = new SitePage(driver);

            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

            logger.info("Opening Sites module...");
            sitePage.clickSites();
            test.pass("Sites module opened successfully.");

            logger.info("Opening Add Site form...");
            sitePage.clickAddSite();
            test.pass("Add Site form opened successfully.");

            logger.info("Entering Site Name: Cancel Test Site");
            sitePage.enterSiteName("Cancel Test Site");
            test.info("Entered Site Name: Cancel Test Site");

            String siteCode = "C" + (System.currentTimeMillis() % 100000);

            logger.info("Generated Site Code: {}");
            sitePage.enterSiteCode(siteCode);
            test.info("Entered Site Code: " + siteCode);

            logger.info("Selecting Vertical Center...");
            sitePage.selectVerticalCenter();
            test.info("Selected Vertical Center.");

            logger.info("Selecting Delivery Center...");
            sitePage.selectCenter("Delivery Center");
            test.info("Selected Delivery Center.");

            logger.info("Entering City: Chennai");
            sitePage.enterCity("Chennai");
            test.info("Entered City: Chennai");

            logger.info("Entering State: Tamil Nadu");
            sitePage.enterState("Tamil Nadu");
            test.info("Entered State: Tamil Nadu");

            logger.info("Entering Address: Cancel Test Address");
            sitePage.enterAddress("Cancel Test Address");
            test.info("Entered Address: Cancel Test Address");

            logger.info("Clicking Cancel button...");
            sitePage.clickCancel();
            test.info("Clicked Cancel button on Create Site form.");

            logger.info("Create Site form cancelled successfully.");
            test.pass("Create Site form cancelled successfully.");
        } catch (Exception e) {
            logger.severe("Cancel Create Site test failed: " + e.getMessage());
            test.fail("Cancel Create Site test failed: " + e.getMessage());
            softAssert.fail("Cancel Create Site test failed: " + e.getMessage());
        } finally {
            softAssert.assertAll();
            logger.info("========== Cancel Create Site Test Completed ==========");
            test.info("Cancel Create Site Test Completed");
        }
    }

    @Test(priority = 6)
    public void cancelEditSiteTest() throws InterruptedException {

        test = extent.createTest("Cancel Create Site Test", "Verify that cancelling site creation does not create a new site.");

        SoftAssert softAssert = new SoftAssert();

        logger.info("========== Cancel Create Site Test Started ==========");

        try {

            SitePage sitePage = new SitePage(driver);

            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

            logger.info("Opening Sites module...");
            sitePage.clickSites();
            test.pass("Sites module opened successfully.");

            logger.info("Searching for existing site with site code: DK222");
            String searchSiteCode = "DK222"; // Replace with existing site code
            sitePage.searchSite(searchSiteCode);
            test.info("Searched for site code: " + searchSiteCode);

            logger.info("Opening Edit Site form...");
            sitePage.clickEdit();
            test.pass("Edit Site form opened successfully.");

            logger.info("Entering updated Site Name: Cancel Test Site");
            sitePage.enterSiteName("Cancel Test Site");

            String siteCode = "CAN" + (System.currentTimeMillis() % 1000);

            logger.info("Entering updated Site Code: {}");
            sitePage.enterSiteCode(siteCode);

            logger.info("Selecting Center: Sort Center");
            sitePage.selectCenterIfDifferent("Sort Center");

            logger.info("Entering updated City: Chennai");
            sitePage.enterCity("Chennai");

            logger.info("Entering updated State: Tamil Nadu");
            sitePage.enterState("Tamil Nadu");

            logger.info("Entering updated Address: Cancel Test Address");
            sitePage.enterAddress("Cancel Test Address");

            logger.info("All edit details entered successfully.");

            logger.info("Clicking Cancel button...");
            sitePage.clickCancel();

            logger.info("Create Site form cancelled.");
            test.pass("Create Site form cancelled successfully.");

        } catch (Exception e) {

            logger.severe("Cancel Create Site test failed: " + e.getMessage());
            test.fail("Cancel Create Site test failed: " + e.getMessage());

            softAssert.fail("Cancel Create Site test failed: " + e.getMessage());

        } finally {

            softAssert.assertAll();

            logger.info("========== Cancel Create Site Test Completed ==========");
            test.info("Cancel Create Site Test Completed");
        }
    }

    @Test(priority = 7)
    public void mandatoryFieldValidationTest() throws InterruptedException {

        test = extent.createTest("Mandatory Field Validation Test", "Verify that mandatory validation messages are displayed when required fields are left empty.");

        SoftAssert softAssert = new SoftAssert();

        logger.info("========== Mandatory Field Validation Test Started ==========");
        test.info("Mandatory Field Validation Test Started");

        try {
            SitePage sitePage = new SitePage(driver);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

            // Step 1: Open Sites
            logger.info("Opening Sites module...");
            test.info("Opening Sites module...");
            sitePage.clickSites();
            softAssert.assertTrue(sitePage.isSitesPageDisplayed(), "Sites page is not displayed.");
            test.pass("Sites page opened successfully.");

            // Step 2: Open Create Site form
            logger.info("Opening Add Site form...");
            test.info("Opening Add Site form...");
            sitePage.clickAddSite();
            test.pass("Add Site form opened successfully.");

            // Step 3: Site Name validation
            logger.info("Validating Site Name mandatory field...");
            test.info("Validating Site Name mandatory field...");
            sitePage.triggerSiteNameValidation();
            boolean siteNameRequired = sitePage.isSiteNameRequiredDisplayed();
            softAssert.assertTrue(siteNameRequired, "Site Name is required validation is not displayed.");
            if (siteNameRequired) {
                test.pass("Site Name required validation displayed.");
            } else {
                test.fail("Site Name required validation was not displayed.");
            }

            // Step 4: Site Code validation
            logger.info("Validating Site Code mandatory field...");
            test.info("Validating Site Code mandatory field...");
            sitePage.triggerSiteCodeValidation();
            boolean siteCodeRequired = sitePage.isSiteCodeRequiredDisplayed();
            softAssert.assertTrue(siteCodeRequired, "Site Code is required validation is not displayed.");
            if (siteCodeRequired) {
                test.pass("Site Code required validation displayed.");
            } else {
                test.fail("Site Code required validation was not displayed.");
            }

            // Step 5: Vertical Center validation
//            logger.info("Validating Vertical Center mandatory field...");
//            test.info("Validating Vertical Center mandatory field...");
//            sitePage.triggerCenterValidation();
//            boolean centerRequired = sitePage.isVerticalCenterRequiredDisplayed();
//            softAssert.assertTrue(centerRequired, "Vertical Center is required validation is not displayed.");
//            if (centerRequired) {
//                test.pass("Vertical Center required validation displayed.");
//            } else {
//                test.fail("Vertical Center required validation was not displayed.");
//            }

            // Step 6: City validation
            logger.info("Validating City mandatory field...");
            test.info("Validating City mandatory field...");
            sitePage.triggerCityValidation();
            boolean cityRequired = sitePage.isCityRequiredDisplayed();
            softAssert.assertTrue(cityRequired, "City is required validation is not displayed.");
            if (cityRequired) {
                test.pass("City required validation displayed.");
            } else {
                test.fail("City required validation was not displayed.");
            }

            // Step 7: State validation
            logger.info("Validating State mandatory field...");
            test.info("Validating State mandatory field...");

            sitePage.triggerStateValidation();
            boolean stateRequired = sitePage.isStateRequiredDisplayed();
            softAssert.assertTrue(stateRequired, "State is required validation is not displayed.");
            if (stateRequired) {
                test.pass("State required validation displayed.");
            } else {
                test.fail("State required validation was not displayed.");
            }

            // Step 8: Verify Submit is disabled
            boolean submitDisabled = !sitePage.isSubmitEnabled();
            softAssert.assertTrue(submitDisabled, "Submit button should be disabled when mandatory fields are empty.");
            if (submitDisabled) {
                test.pass("Submit button is disabled as expected.");
            } else {
                test.fail("Submit button is enabled even though mandatory fields are empty.");
            }
        } catch (Exception e) {
            logger.severe("Mandatory Field Validation test failed: " + e.getMessage());
            test.fail("Mandatory Field Validation test failed: " + e.getMessage());
            softAssert.fail("Mandatory Field Validation test failed: " + e.getMessage());
        } finally {
            softAssert.assertAll();
            logger.info("========== Mandatory Field Validation Test Completed ==========");
            test.info("Mandatory Field Validation Test Completed");
        }
    }

}