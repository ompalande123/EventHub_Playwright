package base;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.BrowserFactory;
import utils.ExtentManager;
import utils.ScreenshotUtils;

import static utils.BrowserFactory.browser;
import static utils.BrowserFactory.playwright;


public class BaseTest {

    protected BrowserContext context;
    protected Page page;


    @BeforeClass
    public void setupBrowser() {
        BrowserFactory.initializeBrowser();
    }

    @BeforeMethod
    public void setupContextPage() {

        context = browser.newContext();
        page = context.newPage();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            String testName = result.getMethod().getMethodName();
            if (page != null && !page.isClosed()) {
                String screenshotPath = ScreenshotUtils.captureScreenshot(page, testName);
                if (screenshotPath != null && ExtentManager.getTest() != null) {
                    try {
                        ExtentManager.getTest().addScreenCaptureFromPath(screenshotPath);
                    } catch (Exception e) {
                        System.out.println("Unable to attach screenshot - " + e.getMessage());
                    }
                }
            }
        }
        if (context != null) {
            context.close();
        }

    }


    @AfterClass
    public void closeBrowser() {
        BrowserFactory.closeBrowser();
    }

    @AfterSuite
    public void closePlaywright(){
        if (playwright!=null){
            playwright.close();
        }
    }

}
