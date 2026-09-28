package testcases;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.BaseLogin;
import pages.NewEntryFormFields;
import pages.Registers;
import pages.ViewListFormFields;

public class MaterialOutwardReturnable_FC_Test extends BaseLogin {

    private static final Logger logger =
            LogManager.getLogger(MaterialOutwardReturnable_FC_Test.class);

    // CREATE TEST
    @Test(priority = 1)
    public void NewEntryFormCreateTest() throws InterruptedException {

        test = extent.createTest("Material Outward Returnable", "Verify that a user can successfully create a Material Outward Returnable form in Fulfillment Center.");

        SoftAssert softAssert = new SoftAssert();

        logger.info("=================================================");
        logger.info("Starting Material Outward Returnable form creation test.");
        logger.info("=================================================");

        test.info("Starting Material Outward Returnable form creation test.");

        try {
            // REGISTERS
            Registers registers = new Registers(driver);

            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

            logger.info("Opening Register module.");
            test.info("Opening Register module.");

            registers.clickRegistersModule();
            Thread.sleep(1000);

            logger.info("Register module opened successfully.");
            test.info("Successfully opened Register module.");

            // SELECT VERTICAL
            String vertical = "Fulfillment Center";
            logger.info("Selecting vertical: {}", vertical);
            test.info("Selecting vertical: " + vertical);
            registers.clickVerticalDropdown(vertical);
            Thread.sleep(1000);

            // SELECT SITE
            String site = "BOM7";
            logger.info("Selecting site: {}", site);

            test.info("Selecting site: " + site);

            registers.selectSite(site);

            // MATERIAL OUTWARD RETURNABLE
            NewEntryFormFields newEntry = new NewEntryFormFields(driver);

            logger.info("Opening Material Outward Register (Returnable).");
            test.info("Opening Material Outward Returnable module.");
            newEntry.clickMaterialOutwardReturnable();

            logger.info("Selected form name confirmation");
            test.info("Confirm the selected form");

            String actualFormName = "Material Outward Register (Returnable)";
            String expextedFormName = newEntry.formNameConfirmation();

            // NEW ENTRY
            logger.info("Opening New Entry form.");
            test.info("Clicking New Entry.");
            newEntry.clickNewEntry();

            logger.info("Selected form name confirmation Inside the selected form");
            test.info("Confirm the selected form name Inside the selected form");

            String actualFormNameInside = "Material Outward Register (Returnable)";
            String expextedFormNameInside = newEntry.formNameConfirmation();

            // FORM DATE
            String formDateDay = "12";   // TEST DATA
            String formDateMonth = "Aug";   // TEST DATA
            String formDateYear = "2026";   // TEST DATA

            DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MMM", Locale.ENGLISH);
            int month = Month.from(monthFormatter.parse(formDateMonth)).getValue();
            String expectedformDate = month + "/" + formDateDay + "/" + formDateYear;
            System.out.println("expectedformDate :" + expectedformDate);
            test.info("Selecting form Date: " + expectedformDate);
            newEntry.selectDate(formDateDay, formDateMonth, formDateYear);
            test.info("form Date selected successfully.");
            String actualformDate = newEntry.getFormDate();
            System.out.println("actualformDate :" + actualformDate);
            softAssert.assertEquals(actualformDate, expectedformDate, "Form Date mismatch");

//        LocalDate today = LocalDate.now();
//        String expectedDate = today.getMonthValue()+"/"+ today.getDayOfMonth() + "/" + today.getYear();
//        System.out.println("Expected date : " + expectedDate);
//        test.info("Selecting current date");
//        mor.selectCurrentDate();
//        test.info("Current date selected successfully");
//        softAssert.assertEquals(mor.getFormDate(), expectedDate, "Form date mismatch");


            // GP NUMBER
            String gpNumber = "12345";
            logger.info("Entering GP Number.");
            test.info("Entering GP Number: " + gpNumber);
            newEntry.setTxtGPNumber(gpNumber);
            softAssert.assertEquals(newEntry.getGPNumber(), gpNumber, "GP Number mismatch");


            // DATE & TIME
            String dateTimeDay = "12";  // TEST DATA
            String dateTimeMonth = "Oct";  // TEST DATA
            String dateTimeYear = "2026";  // TEST DATA
            int hour = 18;  // TEST DATA
            int minute = 30;  // TEST DATA

            test.info("Selecting Date and Time.");
            newEntry.selectDateTime(dateTimeDay, dateTimeMonth, dateTimeYear, hour, minute);
            test.info("Date and Time selected successfully.");

            // SET DATE & TIME
            newEntry.clickBtnSetDateTime();
            test.info("Clicked Set Date & Time.");


            // DESCRIPTION
            String description = "Test Description";
            newEntry.setTxtDescription(description);
            softAssert.assertEquals(newEntry.getDescription(), description, "Description mismatch");


            // DENOM
            String denom = "Test Denom";
            newEntry.setTxtDenom(denom);
            softAssert.assertEquals(newEntry.getDenom(), denom, "Denom mismatch");


            // QUANTITY
            String quantity = "10";

            newEntry.setTxtQuantity(quantity);
            softAssert.assertEquals(newEntry.getQuantity(), quantity, "Quantity mismatch");


            // MATERIAL SENT TO ADDRESS
            String materialAddress = "Test Address";
            newEntry.setTxtMaterialSentToAddress(materialAddress);
            softAssert.assertEquals(newEntry.getMaterialSentToAddress(), materialAddress, "Material Sent To Address mismatch");


            // AUTHORISED NAME
            String authorisedName = "Test Authorised Name";
            newEntry.setTxtAuthorisedName(authorisedName);
            softAssert.assertEquals(newEntry.getAuthorisedName(), authorisedName, "Authorised Name mismatch");


            // SIGNATURE 1
            logger.info("Opening Signature Pad 1.");
            test.info("Opening Signature Pad 1.");
            newEntry.signaturePadClick1();
            newEntry.signOnPad();
            newEntry.clickBtnSaveSignature();
//            Thread.sleep(1000);


            // PURPOSE
            String purpose = "Test Purpose";
            newEntry.setTxtPurpose(purpose);
            softAssert.assertEquals(newEntry.getPurpose(), purpose, "Purpose mismatch");


            // INSECURITY NAME
            String insecurityName = "Test Insecurity Name";
            newEntry.setTxtInsecurityName(insecurityName);
            softAssert.assertEquals(newEntry.getInsecurityName(), insecurityName, "Insecurity Name mismatch");


            // SIGNATURE 2
            logger.info("Opening Signature Pad 2.");
            test.info("Opening Signature Pad 2.");
            newEntry.signaturePadClick2();
            newEntry.signOnPad();
            newEntry.clickBtnSaveSignature();
//            Thread.sleep(1000);

            // APPROXIMATE DATE
            String approximateDateDay = "12";  // TEST DATA
            String approximateDateMonth = "Nov";  // TEST DATA
            String approximateDateYear = "2026";  // TEST DATA

            DateTimeFormatter monthFormatter1 = DateTimeFormatter.ofPattern("MMM", Locale.ENGLISH);
            int month1 = Month.from(monthFormatter1.parse(approximateDateMonth)).getValue();
            String expectedApproximateDate = month1 + "/" + approximateDateDay + "/" + approximateDateYear;
            test.info("Selecting Approximate Date: " + expectedApproximateDate);
            newEntry.selectApproximateDate(approximateDateDay, approximateDateMonth, approximateDateYear);
            test.info("Approximate Date selected successfully.");
            String actualApproximateDate = newEntry.getApproximateDate();
            softAssert.assertEquals(actualApproximateDate, expectedApproximateDate, "Approximate Date mismatch");


            // MATERIAL CARRIED NAME
            String materialCarriedName = "Test Material Carried Name";
            newEntry.setTxtMaterialCarriedName(materialCarriedName);
            softAssert.assertEquals(newEntry.getMaterialCarriedName(), materialCarriedName, "Material Carried Name mismatch");


            // SIGNATURE 3
            logger.info("Opening Signature Pad 3.");
            test.info("Opening Signature Pad 3.");
            newEntry.signaturePadClick3();
            logger.info("Drawing Signature 3.");
            test.info("Drawing Signature on Signature Pad 3.");
            newEntry.signOnPad();
            logger.info("Saving Signature 3.");
            test.info("Saving Signature 3.");
            newEntry.clickBtnSaveSignature();
            logger.info("Signature 3 saved successfully.");
            test.pass("Signature 3 saved successfully.");
//            Thread.sleep(1000);


            // DC NUMBER
            String dcNumber = "12345";
            newEntry.setTxtDCNo(dcNumber);
            softAssert.assertEquals(newEntry.getDCNo(), dcNumber, "DC Number mismatch");


            // MATERIAL RETURN DATE
            String materialReturnDateDay = "17";
            String materialReturnDateMonth = "Sep";
            String materialReturnDateYear = "2026";

            String expectedMaterialReturnDate = getExpectedDate(materialReturnDateDay, materialReturnDateMonth, materialReturnDateYear);
            logger.info("Selecting material return date: {}", expectedMaterialReturnDate);
            newEntry.selectMaterialReturnDate(materialReturnDateDay, materialReturnDateMonth, materialReturnDateYear);
            String actualMaterialReturnDate = newEntry.getMaterialReturnDate();
            softAssert.assertEquals(actualMaterialReturnDate, expectedMaterialReturnDate, "Material Return Date mismatch");


            // MATERIAL RETURN QUANTITY
            String materialReturnQuantity = "5";
            newEntry.setTxtMaterialReturnQuantity(materialReturnQuantity);
            softAssert.assertEquals(newEntry.getMaterialReturnQuantity(), materialReturnQuantity, "Material Return Quantity mismatch");


            // SECURITY NAME
            String securityName = "Test Security Name";
            newEntry.setTxtSecurityName(securityName);
            softAssert.assertEquals(newEntry.getSecurityName(), securityName, "Security Name mismatch");


            // SIGNATURE 4
            logger.info("Opening Signature Pad 4.");
            test.info("Opening Signature Pad 4.");
            newEntry.signaturePadClick4();
            newEntry.signOnPad();
            newEntry.clickBtnSaveSignature();
//            Thread.sleep(1000);

            // REMARKS
            String remarks = "Test Remarks";
            newEntry.setTxtRemarks(remarks);
            softAssert.assertEquals(newEntry.getRemarks(), remarks, "Remarks mismatch");


            // CONTINUE & SAVE
            logger.info("Submitting Material Outward Returnable form.");
            test.info("Clicking Continue & Save.");

            newEntry.clickBtnContinueSave();


            // FINAL ASSERTION
            String expectedMessage = "Form created succesfully";
            String actualMessage = newEntry.getSuccessMessage();

            logger.info("Validating form creation message. Expected: {}, Actual: {}", expectedMessage, actualMessage);

            softAssert.assertEquals(actualMessage, expectedMessage, "Material Outward Returnable form was not created successfully.");
            softAssert.assertAll();
            logger.info("Material Outward Returnable form created successfully.");
            test.pass("Material Outward Returnable form created successfully.");

        } catch (Exception e) {
            logger.error("Material Outward Returnable form creation test failed.", e);
            test.fail("Material Outward Returnable form creation test failed: " + e.getMessage());

            throw e;
        }
    }

    @Test(priority = 2)
    public void verifyCreatedFormDateTest() throws InterruptedException {
        test = extent.createTest("Material Outward Returnable Update\", \"Verify that a user can successfully update a Material Outward Returnable form in Fulfillment Center.");

        SoftAssert softAssert = new SoftAssert();

        logger.info("=================================================");
        logger.info("Starting Material Outward Returnable form Update test.");
        logger.info("=================================================");

        test.info("Starting Material Outward Returnable form Update test.");

        try {
            Registers registers = new Registers(driver);

            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

            logger.info("Opening Register module.");
            test.info("Opening Register module.");

            registers.clickRegistersModule();
            Thread.sleep(1000);

            logger.info("Register module opened successfully.");
            test.info("Successfully opened Register module.");

            // SELECT VERTICAL
            String vertical = "Fulfillment Center";
            logger.info("Selecting vertical: {}", vertical);
            test.info("Selecting vertical: " + vertical);
            registers.clickVerticalDropdown(vertical);
            Thread.sleep(1000);

            // SELECT SITE
            String site = "BOM7";
            logger.info("Selecting site: {}", site);

            test.info("Selecting site: " + site);

            registers.selectSite(site);

            // MATERIAL OUTWARD RETURNABLE
            NewEntryFormFields newEntry = new NewEntryFormFields(driver);
            ViewListFormFields viewList = new ViewListFormFields(driver);

            logger.info("Opening Material Outward Register (Returnable).");
            test.info("Opening Material Outward Returnable module.");
            newEntry.clickMaterialOutwardReturnable();

            // View List
            logger.info("Opening View List form.");
            test.info("Clicking View List.");
            viewList.clickViewList();

            String actualDateTime = viewList.getCreatedDateTime();
            String todayDate = viewList.getCreatedDateTime();

            LocalDate today = LocalDate.now();
            String todayDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd MMM yyyy"));

            logger.info("Actual Date/Time: {}", actualDateTime);
            logger.info("Expected Date: {}", todayDate);

            Assert.assertEquals(actualDateTime, todayDateTime, "Created date/time does not match.");
            // FINAL ASSERTION

            logger.info("All created form details verified successfully in View List.");

            test.pass("All created form details are present and verified successfully in View List.");
            softAssert.assertAll();

        } catch (Exception e) {
            logger.error("Material Outward Returnable update test failed.", e);
            test.fail("Material Outward Returnable update test failed: " + e.getMessage());
            throw e;
        }
    }


    // =========================================================
    // UPDATE TEST
    // =========================================================

    @Test(priority = 3)
    public void ListFormVerifyTest() throws InterruptedException {

        test = extent.createTest("Material Outward Returnable Update\", \"Verify that a user can successfully update a Material Outward Returnable form in Fulfillment Center.");

        SoftAssert softAssert = new SoftAssert();

        logger.info("=================================================");
        logger.info("Starting Material Outward Returnable form Update test.");
        logger.info("=================================================");

        test.info("Starting Material Outward Returnable form Update test.");

        try {
            // REGISTERS
            Registers registers = new Registers(driver);

            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

            logger.info("Opening Register module.");
            test.info("Opening Register module.");

            registers.clickRegistersModule();
            Thread.sleep(1000);

            logger.info("Register module opened successfully.");
            test.info("Successfully opened Register module.");

            // SELECT VERTICAL
            String vertical = "Fulfillment Center";
            logger.info("Selecting vertical: {}", vertical);
            test.info("Selecting vertical: " + vertical);
            registers.clickVerticalDropdown(vertical);
            Thread.sleep(1000);

            // SELECT SITE
            String site = "BOM7";
            logger.info("Selecting site: {}", site);

            test.info("Selecting site: " + site);

            registers.selectSite(site);

            // MATERIAL OUTWARD RETURNABLE
            NewEntryFormFields newEntry = new NewEntryFormFields(driver);
            ViewListFormFields viewList = new ViewListFormFields(driver);

            logger.info("Opening Material Outward Register (Returnable).");
            test.info("Opening Material Outward Returnable module.");
            newEntry.clickMaterialOutwardReturnable();

            // View List
            logger.info("Opening View List form.");
            test.info("Clicking View List.");
            viewList.clickViewList();

            String actualFormName = "Material Outward Register (Returnable)";
            String expextedFormName = newEntry.formNameConfirmation();


            //Click Edit form
            logger.info("Clicking edit action");
            test.info("Click edit");
            viewList.clickEdit();
            Thread.sleep(2000);
            // VERIFY CREATED FORM DETAILS FROM VIEW LIST

            String actualFormNameInside = "Material Outward Register (Returnable)";
            String expextedFormNameInside = newEntry.formNameConfirmation();

            logger.info("Starting verification of created form in View List.");
            test.info("Starting verification of created form in View List.");

            // FORM DATE
            String expectedFormDate = "8/12/2026";
            softAssert.assertEquals(viewList.getFormDate(), expectedFormDate, "Form Date mismatch");

            // GP NUMBER
            String gpNumber = "12345";
            softAssert.assertEquals(viewList.getGPNumber(), gpNumber, "GP Number mismatch");


            //Date & Time
            String expectedDateTime = "10/12/2026, 6:30 PM";
            softAssert.assertEquals(viewList.getDateTime(), expectedDateTime, "Date & Time mismatch");

            // DESCRIPTION
            String description = "Test Description";
            softAssert.assertEquals(viewList.getDescription(), description, "Description mismatch");

            // DENOM
            String denom = "Test Denom";
            softAssert.assertEquals(viewList.getDenom(), denom, "Denom mismatch");

            // QUANTITY
            String quantity = "10";
            softAssert.assertEquals(viewList.getQuantity(), quantity, "Quantity mismatch");

            // MATERIAL SENT TO ADDRESS
            String materialAddress = "Test Address";
            softAssert.assertEquals(viewList.getMaterialSentToAddress(), materialAddress, "Material Sent To Address mismatch");

            // AUTHORISED NAME
            String authorisedName = "Test Authorised Name";
            softAssert.assertEquals(viewList.getAuthorisedName(), authorisedName, "Authorised Name mismatch");

            // PURPOSE
            String purpose = "Test Purpose";
            softAssert.assertEquals(viewList.getPurpose(), purpose, "Purpose mismatch");

            // INSECURITY NAME
            String insecurityName = "Test Insecurity Name";
            softAssert.assertEquals(viewList.getInsecurityName(), insecurityName, "Insecurity Name mismatch");

            // APPROXIMATE DATE
            String expectedApproximateDate = "11/12/2026";
            softAssert.assertEquals(viewList.getApproximateDate(), expectedApproximateDate, "Approximate Date mismatch");

            // MATERIAL CARRIED NAME
            String materialCarriedName = "Test Material Carried Name";
            softAssert.assertEquals(viewList.getMaterialCarriedName(), materialCarriedName, "Material Carried Name mismatch");

            // DC NUMBER
            String dcNumber = "12345";
            softAssert.assertEquals(viewList.getDCNo(), dcNumber, "DC Number mismatch");

            // MATERIAL RETURN DATE
            String expectedMaterialReturnDate = "9/17/2026";
            softAssert.assertEquals(viewList.getMaterialReturnDate(), expectedMaterialReturnDate, "Material Return Date mismatch");

            // MATERIAL RETURN QUANTITY
            String materialReturnQuantity = "5";
            softAssert.assertEquals(viewList.getMaterialReturnQuantity(), materialReturnQuantity, "Material Return Quantity mismatch");

            // SECURITY NAME
            String securityName = "Test Security Name";
            softAssert.assertEquals(viewList.getSecurityName(), securityName, "Security Name mismatch");

            // REMARKS
            String remarks = "Test Remarks";
            softAssert.assertEquals(viewList.getRemarks(), remarks, "Remarks mismatch");


            logger.info("Updating and Submitting Material Outward Returnable form.");
            test.info("Clicking Continue & Save.");

            viewList.clickBtnContinueAndSave();

            // FINAL ASSERTION
            String expectedMessage = "Form updated succesfully";
            String actualMessage = viewList.getSuccessUpdateMessage();

            logger.info("Validating form creation message. Expected: {}, Actual: {}", expectedMessage, actualMessage);

            softAssert.assertEquals(actualMessage, expectedMessage, "Material Outward Returnable form was not created successfully.");
            softAssert.assertAll();
            logger.info("Material Outward Returnable form created successfully.");
            test.pass("Material Outward Returnable form created successfully.");


            // FINAL ASSERTION
            softAssert.assertAll();

            logger.info("All created form details verified successfully in View List.");

            test.pass("All created form details are present and verified successfully in View List.");


//                // FINAL ASSERTION
//                String expectedMessage = "Form updated succesfully";
//                String actualMessage = newEntry.getSuccessMessage();
//
//                logger.info("Validating form updation message. Expected: {}, Actual: {}", expectedMessage, actualMessage);
//
//                softAssert.assertEquals(actualMessage, expectedMessage, "Material Outward Returnable form was not updated successfully.");
//                softAssert.assertAll();
//                logger.info("Material Outward Returnable form updated successfully.");
//                test.pass("Material Outward Returnable form updated successfully.");

        } catch (Exception e) {
            logger.error("Material Outward Returnable update test failed.", e);
            test.fail("Material Outward Returnable update test failed: " + e.getMessage());
            throw e;
        }
    }

    @Test(priority = 4)
    public void ListFormUpdateTest() throws InterruptedException {

        test = extent.createTest("Material Outward Returnable Update\", \"Verify that a user can successfully update a Material Outward Returnable form in Fulfillment Center.");

        SoftAssert softAssert = new SoftAssert();

        logger.info("=================================================");
        logger.info("Starting Material Outward Returnable form Update test.");
        logger.info("=================================================");

        test.info("Starting Material Outward Returnable form Update test.");

        try {
            // REGISTERS
            Registers registers = new Registers(driver);

            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

            logger.info("Opening Register module.");
            test.info("Opening Register module.");

            registers.clickRegistersModule();
            Thread.sleep(1000);

            logger.info("Register module opened successfully.");
            test.info("Successfully opened Register module.");

            // SELECT VERTICAL
            String vertical = "Fulfillment Center";
            logger.info("Selecting vertical: {}", vertical);
            test.info("Selecting vertical: " + vertical);
            registers.clickVerticalDropdown(vertical);
            Thread.sleep(1000);

            // SELECT SITE
            String site = "BOM7";
            logger.info("Selecting site: {}", site);

            test.info("Selecting site: " + site);

            registers.selectSite(site);

            // MATERIAL OUTWARD RETURNABLE
            NewEntryFormFields newEntry = new NewEntryFormFields(driver);
            ViewListFormFields viewList = new ViewListFormFields(driver);

            logger.info("Opening Material Outward Register (Returnable).");
            test.info("Opening Material Outward Returnable module.");
            newEntry.clickMaterialOutwardReturnable();

            // View List
            logger.info("Opening View List form.");
            test.info("Clicking View List.");
            viewList.clickViewList();

            String actualFormName = "Material Outward Register (Returnable)";
            String expextedFormName = newEntry.formNameConfirmation();


            //Click Edit form
            logger.info("Clicking edit action");
            test.info("Click edit");
            viewList.clickEdit();
            Thread.sleep(2000);
            // VERIFY CREATED FORM DETAILS FROM VIEW LIST

            String actualFormNameInside = "Material Outward Register (Returnable)";
            String expextedFormNameInside = newEntry.formNameConfirmation();

            logger.info("Starting verification of created form in View List.");
            test.info("Starting verification of created form in View List.");

            // REMARKS
            String remarks = "Test Remarks updated";
            newEntry.setTxtRemarks(remarks);
            softAssert.assertEquals(newEntry.getRemarks(), remarks, "Remarks mismatch");


            logger.info("Updating and Submitting Material Outward Returnable form.");
            test.info("Clicking Continue & Save.");

            viewList.clickBtnContinueAndSave();

            // FINAL ASSERTION
            String expectedMessage = "Form updated succesfully";
            String actualMessage = viewList.getSuccessUpdateMessage();

            logger.info("Validating form creation message. Expected: {}, Actual: {}", expectedMessage, actualMessage);

            softAssert.assertEquals(actualMessage, expectedMessage, "Material Outward Returnable form was not created successfully.");
            softAssert.assertAll();
            logger.info("Material Outward Returnable form created successfully.");
            test.pass("Material Outward Returnable form created successfully.");


            // FINAL ASSERTION
            softAssert.assertAll();

            logger.info("Form update verified successfully in View List.");

            test.pass("Details are Updated in View List.");


//                // FINAL ASSERTION
//                String expectedMessage = "Form updated succesfully";
//                String actualMessage = newEntry.getSuccessMessage();
//
//                logger.info("Validating form updation message. Expected: {}, Actual: {}", expectedMessage, actualMessage);
//
//                softAssert.assertEquals(actualMessage, expectedMessage, "Material Outward Returnable form was not updated successfully.");
//                softAssert.assertAll();
//                logger.info("Material Outward Returnable form updated successfully.");
//                test.pass("Material Outward Returnable form updated successfully.");

        } catch (Exception e) {
            logger.error("Material Outward Returnable update test failed.", e);
            test.fail("Material Outward Returnable update test failed: " + e.getMessage());
            throw e;
        }
    }



    // =========================================================
    // COMMON DATE UTILITY
    // =========================================================

    private String getExpectedDate(String day, String month, String year) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM", Locale.ENGLISH);
        int monthNumber = Month.from(formatter.parse(month)).getValue();
        return monthNumber + "/" + day + "/" + year;
    }



}

