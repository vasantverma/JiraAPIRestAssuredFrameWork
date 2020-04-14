package com.jira.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


public class ExtentReportListener implements ITestListener
{
	//Report Constants  Declarations
	private static final String FILE_NAME = "AutomationReport.html";
	private static final String DOCUMENT_TITLE="Jira API Automation";
	private static final String REPORT_PATH=System.getProperty("user.dir")+"/TestAutomationReport/";
	
	//Extent Report Declarations
    public  static ExtentSparkReporter sparkReporter;
    private static ExtentReports extent = createInstance();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
	
    //Create an extent report instance
    public static ExtentReports createInstance() 
    {
        sparkReporter=new ExtentSparkReporter(REPORT_PATH+FILE_NAME);
    	sparkReporter.config().setDocumentTitle(DOCUMENT_TITLE);
    	sparkReporter.config().setReportName(FILE_NAME);
    	sparkReporter.config().setTheme(com.aventstack.extentreports.reporter.configuration.Theme.DARK);
    	extent = new ExtentReports();
    	extent.attachReporter(sparkReporter);
    	extent.setSystemInfo("Application Name","Jira API");
    	extent.setSystemInfo("Platform",System.getProperty("os.name"));
    	extent.setSystemInfo("Environment","QA");
 
        return extent;
    }

	
   //ITestListener overriden methods
    @Override
    public synchronized void onStart(ITestContext context) {
        System.out.println("Extent Reports Version 4.1.3 Jira API Test Suite started!");
    }
 
    @Override
    public synchronized void onFinish(ITestContext context) {
        System.out.println(("Extent Reports Version 4.1.3 Jira API Test Suite is ending!"));
        extent.flush();
    }
 
    @Override
    public synchronized void onTestStart(ITestResult result) {
        System.out.println((result.getMethod().getMethodName() + " started!"));
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName(),result.getMethod().getDescription());
        test.set(extentTest);
    }
 
    @Override
    public synchronized void onTestSuccess(ITestResult result) {
        System.out.println((result.getMethod().getMethodName() + " passed!"));
        test.get().pass("Test passed");
    }
 
    @Override
    public synchronized void onTestFailure(ITestResult result) {
        System.out.println((result.getMethod().getMethodName() + " failed!"));
        test.get().fail(result.getThrowable());
    }
 
    @Override
    public synchronized void onTestSkipped(ITestResult result) {
        System.out.println((result.getMethod().getMethodName() + " skipped!"));
        test.get().skip(result.getThrowable());
    }
 
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println(("onTestFailedButWithinSuccessPercentage for " + result.getMethod().getMethodName()));
    }
}
