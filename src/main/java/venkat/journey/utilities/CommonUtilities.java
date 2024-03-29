package venkat.journey.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class CommonUtilities {
	public WebDriver driveer;
	
	public void captureScreenShot(WebDriver driver) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
		String now = sdf.format(new Date());
		TakesScreenshot ts =(TakesScreenshot)driver;
		File sfile =  ts.getScreenshotAs(OutputType.FILE);
		File dfile = new File("D:\results"+now+"+.png");
		//FileUtils.copyFile Venkat by Venkat
		
	}
	
	
	public void windowHandle(WebDriver driver) {
	
			Set<String> allWindowHandles = driver.getWindowHandles();
			for (String currentWindowHandle : allWindowHandles) {
				
				driver.switchTo().window(currentWindowHandle);
				driver.close();
			}
			
	}
	

	
}

