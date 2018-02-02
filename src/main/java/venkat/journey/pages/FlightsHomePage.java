package venkat.journey.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class FlightsHomePage  {
	public WebDriver driver;
	public FlightsHomePage(WebDriver driver) {
		this.driver=driver;
	}
	
	//Select the category such as Flights or Hotels or Cars etc..
	public void selectMenuCategory(String menu)	{
		driver.findElement(By.xpath("//span[text()='"+menu+"']")).click();
	}
	
	//Select Type of Trip such as Oneway or RoundTrip Or Multi_city
	public void selectTripType(String tripType)	{
		 WebElement trip = driver.findElement(By.xpath("//span[text()='"+tripType+"']"));
		 JavascriptExecutor js = (JavascriptExecutor)driver;
		 js.executeScript("arguments[0].click();",trip);
	}
	
	//Enter source and destination values
	public void selectJourney(String source , String destination) {
		driver.findElement(By.id("flight-origin")).sendKeys(source);
		driver.findElement(By.id("aria-option-0")).click();
		driver.findElement(By.id("flight-destination")).sendKeys(destination);
		driver.findElement(By.id("aria-option-0")).click();
	}
	
	// Select Date of journey for OnewayTrip
	public void selectJourneyDate()	{
		driver.findElement(By.id("flight-departing")).click();
		List<WebElement> activeDeparttureDateList = driver.findElements(By.xpath(
				"//div[@id='flight-departing-wrapper']/div/div/div[2]/table/tbody//tr//td/button[@class != 'datepicker-cal-date disabled']"));

		activeDeparttureDateList.get(0).click();
		/*WebElement checkbox = driver.findElement(By.id("flexibleDate-label"));
		if(!checkbox.isSelected())
		{
			checkbox.click();
		}*/
	}
	// Select Date of journey for RoundTrip
	public void selectRoundTripJourneyDate() {
		driver.findElement(By.id("flight-departing")).click();
		List<WebElement> activeDeparttureDateList = driver.findElements(By.xpath(
				"//div[@id='flight-departing-wrapper']/div/div/div[2]/table/tbody//tr//td/button[@class != 'datepicker-cal-date disabled']"));
	
		activeDeparttureDateList.get(0).click();
		
		driver.findElement(By.id("flight-returning")).click();
		List<WebElement> activeReturnDateList = driver.findElements(By.xpath(
				"//div[@id='flight-returning-wrapper']/div/div/div[3]/table/tbody//tr//td/button[@class != 'datepicker-cal-date disabled']"));
		activeReturnDateList.get(1).click();
		WebElement checkbox = driver.findElement(By.cssSelector("label[id='flexibleDate-label']"));
		
		if(!checkbox.isSelected()) {
			checkbox.click();
		}
	}
	
	// Enter Number of Adult passengers 
	public void selectAdultsCount(int value) {
	
		Select select = new Select(driver.findElement(By.id("flight-adults")));
		select.selectByIndex(value);
		
	}
	
	// Enter Number of Children passengers and their Age
	public void selectChildrenCount(int value)
	{
		Select select1 = new Select(driver.findElement(By.id("flight-children")));
		select1.selectByIndex(value);
		Select select2 = new Select(driver.findElement(By.id("flight-age-select-1")));
		select2.selectByIndex(10);
		Select select3 = new Select(driver.findElement(By.id("flight-age-select-2")));
		select3.selectByIndex(8);
	}
	
	//Click on Search Button
	public void clickOnSearchButton() {
		WebElement searchBtn = driver.findElement(By.xpath("//span[text()='Search']"));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();",searchBtn);
	}
	
}
