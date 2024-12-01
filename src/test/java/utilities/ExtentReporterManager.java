package utilities;

import java.awt.Desktop;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testCases.BaseClass;

public class ExtentReporterManager implements ITestListener {

	public ExtentSparkReporter sparkreport;
	public ExtentReports report;
	public ExtentTest test;

	String repName;

	public void onStart(ITestContext testContext) {

		String timestamp = new SimpleDateFormat("YYYY.MM.DD.HH.MM.SS").format(new Date());

		repName = "Test-Report" + timestamp + ".html";

		sparkreport = new ExtentSparkReporter(".\\reports\\" + repName);

		sparkreport.config().setDocumentTitle("OpenCartAutomationTesting");
		sparkreport.config().setReportName("OpencartFunctionalTesting");
		sparkreport.config().setTheme(Theme.DARK);

		report = new ExtentReports();
		report.attachReporter(sparkreport);

		report.setSystemInfo("Application", "OpenCart");
		report.setSystemInfo("Module", "Admin");
		report.setSystemInfo("Submodule", "Customers");
		report.setSystemInfo("User Name", System.getProperty("user.name"));
		report.setSystemInfo("Environment", "QA");

		String os = testContext.getCurrentXmlTest().getParameter("os");
		report.setSystemInfo("OperatingSystem", os);

		String browser = testContext.getCurrentXmlTest().getParameter("browser");
		report.setSystemInfo("Browser", browser);

		List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
		if (!includedGroups.isEmpty()) {
			report.setSystemInfo("Groups", includedGroups.toString());
		}
	}

	public void onTestSuccess(ITestResult result) {

		test = report.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS, result.getName() + "got Successfully executed");
	}

	public void onTestFailure(ITestResult result) {

		test=report.createTest(result.getClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		
		test.log(Status.FAIL,result.getName()+"got Failed");
		test.log(Status.INFO,result.getThrowable().getMessage());
		
		try {
			String imgPath =new BaseClass().captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgPath);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void onTestSkipped(ITestResult result) {
		test=report.createTest(result.getClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP,result.getName()+"got skipped");
		test.log(Status.SKIP, result.getThrowable().getMessage());
	}
	
	public void onFinish(ITestContext testContext) {
		
		report.flush();
		
		String ReportPath=System.getProperty("user.dir")+"\\reports\\"+repName;
		File exportReport=new File(ReportPath);
		
		try {
			Desktop.getDesktop().browse(exportReport.toURI());
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
