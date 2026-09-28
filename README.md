# DOR Automation – Selenium Java POM

## 1. Project Overview

This project contains automated test cases for the **Digitization of Registers (DOR)** application.

The automation framework is developed using **Selenium WebDriver with Java** and follows the **Page Object Model (POM)** design pattern.

The framework is designed to provide:

* Reusable page objects and utilities
* Maintainable and readable automation code
* Modular test cases
* Centralized configuration
* Automated test execution and reporting
* Logging using Log4j2
* Test reporting using Extent Reports

---

## 2. Technology Stack

| Technology         | Version / Details             |
| ------------------ | ----------------------------- |
| Java               | 17                            |
| Selenium WebDriver | 4.47.0                        |
| TestNG             | 7.12.0                        |
| Maven              | Project dependency management |
| Page Object Model  | Design pattern                |
| Log4j2             | Logging                       |
| Extent Reports     | Test reporting                |
| WebDriverManager   | Browser driver management     |
| IntelliJ IDEA      | Development IDE               |
| Chrome             | Test browser                  |

---

## 3. Prerequisites

Before running the automation project, make sure the following are installed:

1. Java JDK 17 or compatible version
2. Maven
3. Git
4. IntelliJ IDEA or another Java IDE
5. Google Chrome
6. Access to the DOR test environment

Verify Java installation:

```bash
java -version
```

Verify Maven installation:

```bash
mvn -version
```

Verify Git installation:

```bash
git --version
```

---

## 4. Project Structure

```text
DOR_POM/
│
├── Config/
│   └── config.properties
│
├── src/
│   ├── main/
│   │   └── java/
│   │       ├── base/
│   │       ├── pages/
│   │       └── utilities/
│   │
│   └── test/
│       └── java/
│           └── testcases/
│
├── test-output/
│   └── screenshots/
│
├── pom.xml
├── testng.xml
└── README.md
```

---

## 5. Configuration

The application configuration is maintained in:

```text
Config/config.properties
```

The configuration file contains environment-specific information such as:

```properties
url=<DOR application URL>
userId=<test user>
password=<test password>
```

**Important:** Do not commit real passwords, tokens, or other sensitive credentials to Git.

For shared repositories, use the organization's approved method for managing secrets and test credentials.

---

## 6. Framework Design

The project follows the Page Object Model (POM) design pattern.

### Base Classes

Common WebDriver setup and login functionality are maintained in the base classes.

### Page Objects

Application pages are represented as individual page classes.

Examples:

```text
Login
VisitorEntry
NewEntryFormFields
```

### Utilities

Reusable functionality is maintained separately in utility classes.

Examples:

```text
ConfigReader
DatePicker
TimePicker
SignaturePad
ScreenshotUtil
ExtentReportManager
```

### Test Classes

Test scenarios are maintained separately from page objects.

Examples:

```text
LoginPageTest
MaterialOutwardReturnable_FC_Test
```

---

## 7. How to Clone the Repository

Clone the repository using:

```bash
git clone <repository-url>
```

Navigate to the project:

```bash
cd DOR_POM
```

---

## 8. Install Dependencies

Run the following command from the project root:

```bash
mvn clean install
```

This downloads the required Maven dependencies and builds the project.

---

## 9. Run the Tests

To execute the tests using Maven:

```bash
mvn test
```

If the project uses a TestNG suite XML file:

```bash
mvn test -DsuiteXmlFile=testng.xml
```

Alternatively, tests can be executed from IntelliJ IDEA using the TestNG configuration.

---

## 10. Test Execution Flow

The automation framework follows this general execution flow:

```text
Test Class
    ↓
Base Setup
    ↓
Chrome Browser Launch
    ↓
DOR Application
    ↓
Login
    ↓
Page Object
    ↓
Test Actions
    ↓
Assertions
    ↓
Test Result
    ↓
Extent Report / Logs
    ↓
Browser Teardown
```

---

## 11. Reports

After test execution, the automation results can be found in the configured test-output directory.

Extent Report:

```text
test-output/AutomationReport.html
```

Screenshots:

```text
test-output/screenshots/
```

The report contains information such as:

* Test case name
* Test status
* Execution details
* Failure information
* Screenshots, where configured

---

## 12. Logging

The framework uses **Log4j2** for logging.

Logs are used to capture important execution information such as:

* Test setup
* Browser initialization
* Application launch
* Login actions
* Page interactions
* Test execution status
* Errors and exceptions

---

## 13. Coding Standards

The following practices should be followed when adding new automation code:

* Follow the Page Object Model structure.
* Keep test logic separate from page locators.
* Use reusable methods wherever possible.
* Avoid duplicate code.
* Use meaningful class, method, and variable names.
* Prefer explicit waits where required instead of unnecessary `Thread.sleep()`.
* Do not hard-code credentials.
* Add appropriate assertions to test cases.
* Add logging for important test execution steps.
* Keep utility functionality reusable.

---

## 14. Code Review Process

Automation changes should be reviewed before merging into the main branch.

The process is:

```text
Create Feature Branch
        ↓
Develop / Modify Automation
        ↓
Run Tests Locally
        ↓
Commit Changes
        ↓
Push Feature Branch
        ↓
Create Pull Request
        ↓
Developer / Senior Tester Review
        ↓
Address Review Comments
        ↓
Approval
        ↓
Merge to Main Branch
```

---

## 15. Troubleshooting

### Browser does not launch

Check:

* Chrome is installed.
* Selenium dependencies are available.
* The configured browser version is supported.
* WebDriverManager can access the required driver.

### Tests cannot access the application

Check:

* DOR application URL in `config.properties`.
* Test environment availability.
* Network/VPN access if required.
* Test credentials.

### Maven dependency issues

Run:

```bash
mvn clean install
```

If necessary, refresh Maven dependencies from the IDE.

---

## 16. Maintainer

**Dinesh**

DOR Automation – Selenium Java POM
