package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.ITestResult;

import static utilities.TestFramework_Initializer.utils;

public class Reporting_Util {

    private ExtentReports extentReport;
    private ExtentTest extentTest;

    // Leaving config utility in the constructor in case it is needed later on for multi-threading purposes
    public Reporting_Util(ExtentReports extentReport) {
        this.extentReport = extentReport;
        System.out.println("Reporting Util hit");
    }

    // Overloaded method for use with onTestStart ITestResult interface. Will provide test method name to the extentTest
    public void beginReport(ITestResult iTestResult) {
        this.extentTest = this.extentReport.createTest("Test: " + iTestResult.getName());
    }

    // Overloaded method for use with @BeforeMethod TestNG annotation. Will provide test method name to the extentTest
    public void beginReport(String methodName) {
        this.extentTest = this.extentReport.createTest("Test: " + methodName);
    }

    public void endReport() {
        // this.extentReport.endTest(this.extentTest);
        this.extentReport.flush();
    }

    public void fail(String fail) {
        extentTest.log(Status.FAIL, fail);
    }

    public void endReport_PASS() {
        this.extentTest.log(Status.PASS, "Test Passed");
        //this.extentReport.endTest(this.extentTest);
        this.extentReport.flush();
    }

    public void endReport_FAIL() {
        this.extentTest.log(Status.FAIL, "Test Failed");
        //this.extentReport.endTest(this.extentTest);
       // this.extentTest.fail("Test Step Failed", MediaEntityBuilder.createScreenCaptureFromPath("/src/test/java/resources/ssOutput/").build());
        this.extentReport.flush();
    }

    public void endReport_SKIP(ITestResult iTestResult) {
        this.extentTest.log(Status.SKIP, "Skipped test: " + iTestResult.getName());
        // this.extentReport.endTest(this.extentTest);
        this.extentReport.flush();
    }

    public void addScreenshot(String screenShotLocation) {
        //this.extentTest.log(Status.INFO, "Screenshot: " + this.extentTest.addScreenCaptureFromPath(screenShotLocation));
        this.extentTest.log(Status.FAIL, MediaEntityBuilder.createScreenCaptureFromPath(screenShotLocation).build());

    }

    public ExtentReports getInstance() {

        ExtentSparkReporter spark = new ExtentSparkReporter(utils.configReader().getExtentFilePath());
        TestFramework_Initializer testFramework_initializer = new TestFramework_Initializer();

        extentReport.attachReporter(spark);

        return extentReport;
    }

    public ExtentTest getActiveReport() {
        return this.extentTest;
    }
}
