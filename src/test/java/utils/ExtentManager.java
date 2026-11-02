package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


public class ExtentManager {

    private static ExtentReports extentReport;
    private static ThreadLocal<ExtentTest> test=new ThreadLocal<>();

    public static ExtentReports getExtentReport(){
        if(extentReport==null){
           String currentDateTime= DateUtils.getCurrentDateTime();
            ExtentSparkReporter sparkReporter=new ExtentSparkReporter("test-output/extentReports/Report_"+currentDateTime+".html");
            sparkReporter.config().setDocumentTitle("EventHub Application Report");
            sparkReporter.config().setReportName("Automation Test Results");

            extentReport=new ExtentReports();
            extentReport.attachReporter(sparkReporter);
        }
        return extentReport;
    }

    // To create a separate report entry for each test case
    public static void createTest(String testName){
        test.set(getExtentReport().createTest(testName));
    }

    public static ExtentTest getTest(){
        return test.get();
    }

    public static void removeTest(){
        test.remove();
    }


}
