package utils;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExtentReportListener implements ITestListener, ISuiteListener {

    @Override
    public void onStart(ISuite suite){
        ExtentManager.getExtentReport();
    }

    @Override
    public void onTestStart(ITestResult result){
        String testName=result.getMethod().getMethodName();
        ExtentManager.createTest(testName);
        ExtentManager.getTest().info("Test execution started");
    }

    @Override
    public void onTestSuccess(ITestResult result){
        ExtentManager.getTest().pass("Test passed");
        ExtentManager.removeTest();
    }

    @Override
    public void onTestFailure(ITestResult result){
        ExtentManager.getTest().fail(result.getThrowable());
        ExtentManager.removeTest();
    }

    @Override
    public void onTestSkipped(ITestResult result){
        ExtentManager.getTest().skip("Test skipped");
        ExtentManager.removeTest();
    }

    @Override
    public void onFinish(ISuite suite){
        ExtentManager.getExtentReport().flush();
    }


}
