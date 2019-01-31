package com.prestashop.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.github.javafaker.Faker;
import com.prestashop.pages.*;
import com.prestashop.tests.functional_tests.cart.ErrorMessageValidation;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;


import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public abstract class TestBase {
    public WebDriver driver;

    //for reporting purpose
    protected static ExtentReports report;
    protected static ExtentHtmlReporter htmlReporter;
    protected static ExtentTest extentLogger;

    protected Pages pages;
    public FakeData faker;
    protected Actions action;
    public Random rndm;
    public SoftAssert softAssert;
    @BeforeMethod
    public void setUp()
    {

  softAssert=new SoftAssert();
    pages=new Pages();
        driver=Driver.getDriver();
        driver.get(ConfigurationReader.getProperty("url"));
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        action= new Actions(driver);
        rndm=new Random();
        faker=new FakeData();
    }
    @BeforeTest(alwaysRun = true)
    public void setUpTest() {
        //initialize the extendReport
        report = new ExtentReports();
        //setting the path for the report
        //report will be generated in the current project inside folder test-output
        //report file name is report.html
        String filePath = System.getProperty("user.dir") + "/test-output/report.html";
        //initialize the html reporter with the path to the report
        htmlReporter = new ExtentHtmlReporter(filePath);
        // We attach the html report to our report
        report.attachReporter(htmlReporter);

        //add some information optional
        report.setSystemInfo("Environment", "Staging");
        report.setSystemInfo("Browser", ConfigurationReader.getProperty("browser"));
        report.setSystemInfo("OS", System.getProperty("os.name"));
        report.setSystemInfo("Tester","Ozturk61");
        htmlReporter.config().setDocumentTitle("Prestashop Reports");
        htmlReporter.config().setReportName("Prestashop Automated Test Reports");
//        htmlReporter.config().setTheme(Theme.DARK);

    }

    @AfterTest(alwaysRun = true)
    public void tearDownTest() {
        report.flush();

    }
    @AfterMethod(alwaysRun = true)
    public void tearDownMethod(ITestResult result) throws IOException {

        //if any test fails, it detects it and add screenshot to your report
        if (result.getStatus() == ITestResult.FAILURE) {
            String screenshotLocation = BrowserUtils.getScreenshot(result.getName());
            extentLogger.fail(result.getName());
            extentLogger.addScreenCaptureFromPath(screenshotLocation);
            extentLogger.fail(result.getThrowable());

        } else if (result.getStatus() == ITestResult.SKIP) {
            extentLogger.skip("Test Case Skipped: " + result.getName());
        }
        Driver.closeDriver();
    }

}
