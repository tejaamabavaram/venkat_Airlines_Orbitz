package venkat.journey.generic;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

@Listeners(CustomListener.class)

public class BaseTest implements InterfaceConstants {
	public WebDriver driver;
	public ExtentTest test ;
	public  ExtentReports report;
	@BeforeSuite
	public void createReport()
	{
		report = new ExtentReports(null);
		ExtentHtmlReporter htmlReport = new ExtentHtmlReporter(REPORT_PATH);
	/*	report.attachReporter(htmlReport);
		report.setSystemInfo("Host Name", "venkat");
		report.setSystemInfo("User name", "reddy");
		report.setSystemInfo("Environment", "QA");*/
	}
	@AfterSuite
	public void flush()
	{
		report.flush();
		
	}
	static
	{
		System.setProperty(GECKO_KEY, GECKO_VALUE);
		System.setProperty(CHROME_KEY, CHROME_VALUE);
	}
	
	@Parameters({"browser"})
	@BeforeMethod(alwaysRun=true)
	public void openApplication(String browser)	{
		
		 if(browser.equalsIgnoreCase("firefox")) {
			 
			  driver = new FirefoxDriver();
			
		 } else if (browser.equalsIgnoreCase("chrome")) { 
			 
			 driver = new ChromeDriver();
			
		 }
		 	driver.get("https://www.orbitz.com");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			
	}
	
	@AfterMethod
	public void closeApplication(ITestResult result) {
		if(result.getStatus()==ITestResult.FAILURE)
		{
		test.log(LogStatus.FAIL, "TEst case failed is:"+result.getName());
		
		//test.log(LogStatus.FAIL, "TEst case failed is:"+result.getName()); //to aadd name in extent report
		test.log(LogStatus.FAIL, "TEst case failed is:"+result.getThrowable());// add exception in extent
		String screenPath = ExtentReportsWithScreenShot.getScreenShotPhoto(driver, result.getName());
		test.log(LogStatus.FAIL, test.addScreenCapture(screenPath));//to add screen shot in extent reports 
		test.log(LogStatus.FAIL, test.addScreenCapture(screenPath));
		} else if(result.getStatus() == ITestResult.SKIP)
		{
			test.log(LogStatus.SKIP, "Skipped test is:"+result.getName());
		}
		else if(result.getStatus()==ITestResult.SUCCESS)
		{
			test.log(LogStatus.PASS, "Test case passed is:"+result.getName());
		}
		report.endTest(test);// end test and ends current test and prepare html report
		driver.quit();
		/*String testName = result.getName();
		ExtentTest test = report.createTest(testName);
		if(result.getStatus()==1)
		{
			test.pass("Test Pass");
		}
		else {
			test.fail("Test Fail");
		}*/
	}

}
