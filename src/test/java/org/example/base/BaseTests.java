package org.example.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.example.pages.DemosPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.*;

/**
 * Sets up project base settings and implements before/after tests annotations
 */
public class BaseTests {
    private static final String CHROME_DRIVER_PATH = "resources/drivers/chromedriver.exe";
    private static final String BASE_URI = "https://jqueryui.com/demos";
    private static final String REPORT_PATH = "/target/extent-reports/Report.html";
    protected ExtentReports report;
    protected ExtentSparkReporter sparkReporter;
    protected ExtentTest test;
    protected DemosPage demosPage;
    private WebDriver driver;

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);

        /* Disable infobar "Браузером управляет автоматизированное ПО" */
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("excludeSwitches",
                new String[]{ "enable-automation" });

        driver = new ChromeDriver(chromeOptions);

        driver.manage().window().maximize();
        driver.get(BASE_URI);

        demosPage = new DemosPage(driver);
        initializeReport();
    }

    public void initializeReport() {
        sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + REPORT_PATH);
        report = new ExtentReports();
        report.attachReporter(sparkReporter);

        /* Configure reporter */
        sparkReporter.config().setDocumentTitle("Test task report");
        sparkReporter.config().setReportName("Test task report");
        sparkReporter.config().setTheme(Theme.STANDARD);
        sparkReporter.config().setTimeStampFormat("EEEE, dd MMMM, yyyy, hh:mm a '('zzz')'");
    }

    @BeforeMethod
    public void goToDemosPage() {
        driver.navigate().to(BASE_URI);
    }

    @AfterMethod
    public void logTestResult(ITestResult result) {
        if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS,
                     MarkupHelper.createLabel(result.getName() + " PASSED ", ExtentColor.GREEN));
        }
        else if (result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL,
                     MarkupHelper.createLabel(result.getName() + " FAILED ", ExtentColor.RED));
            test.fail(result.getThrowable());
        }
        else {
            test.log(Status.SKIP,
                     MarkupHelper.createLabel(result.getName() + " SKIPPED ", ExtentColor.ORANGE));
            test.skip(result.getThrowable());
        }
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    @AfterSuite
    public void GenerateReport() {
        report.flush();
    }
}
