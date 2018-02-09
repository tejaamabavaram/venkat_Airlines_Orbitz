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
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
//@Listeners(CustomListener.class)
@Test(retryAnalyzer=venkat.journey.generic.RetryAnalyzer.class)
public class BaseTest implements InterfaceConstants {
	public WebDriver driver;
	public static ExtentReports report;
	@BeforeSuite
	public void createReport()
	{
		report = new ExtentReports();
		ExtentHtmlReporter htmlReport = new ExtentHtmlReporter(REPORT_PATH);
		report.attachReporter(htmlReport);
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
		driver.quit();
		String testName = result.getName();
		ExtentTest test = report.createTest(testName);
		if(result.getStatus()==1)
		{
			test.pass("Test Pass");
		}
		else {
			test.fail("Test Fail");
		}
	}

}
