package Report;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import Utilities.CaptureScreenShot;

	public class BaseTest {
		WebDriver driver;
		ExtentReports reports;
		ExtentHtmlReporter htmlReporter;
		ExtentTest test;
		ExtentTest parentTest;
		ExtentTest childTest;
		
	@BeforeTest
	public void setUp() {
		reports = new ExtentReports();	
		htmlReporter = new ExtentHtmlReporter(new File(System.getProperty("user.dir")+"/AutomationReport.html"));
		htmlReporter.loadXMLConfig(new File(System.getProperty("user.dir")+"/extent-config.xml"));
		reports.attachReporter(htmlReporter);
		reports.setSystemInfo("QA-Tester", "Hitesh Kumar");
		reports.setSystemInfo("Environment", "Production");
		reports.setSystemInfo("Browser", "Mozilla Firefox");
	}
	
	@AfterMethod
	public void getResult(ITestResult result) throws IOException {
		
	String screenShot = CaptureScreenShot.captureScreen(driver, CaptureScreenShot.generateFileName(result)); 
		if(result.getStatus() == ITestResult.FAILURE) {	
			test.log(Status.FAIL, result.getName());
			test.log(Status.SKIP, result.getThrowable());
			test.fail("Screen Shot :" +test.addScreenCaptureFromPath(screenShot));	
		}else if(result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, result.getName());
		//	test.pass("Screen Shot :" +test.addScreenCaptureFromPath(screenShot));
		}else if(result.getStatus() == ITestResult.SKIP) {
			test.skip("Test Case:"+result.getName()+"has been Skipped");	
		}
	}
		
	@AfterTest
	public void tearDown() {
		reports.flush();
	}

}
	
