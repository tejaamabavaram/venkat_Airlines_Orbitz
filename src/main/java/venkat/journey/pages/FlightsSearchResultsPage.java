package venkat.journey.pages;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;
import venkat.journey.generic.BaseTest;

public class FlightsSearchResultsPage extends BaseTest {
	
	public FlightsSearchResultsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}
	
	@FindBy(xpath="//div[text()='PNQ - BOM']")
	private List<WebElement> flightsList;
	
	@FindBy(xpath="//select[@name='sort']")
	private WebElement priceDropdown;
	
	@FindBy(css="input[name='arrival-locations']")
	private WebElement roundtripToLoc;
	
	@FindBy(css="input[name='departure-locations']")
	private WebElement roundtripFromLoc;
	
	@FindBy(css="input[id='round-trip-flight']")
	private WebElement roundtripCheckBox;
	
	@FindBy(css="input[id='oneway-flight']")
	private WebElement onewayCheckBox;
	
	@FindBy(css="input[id='multi-dest-flight']")
	private WebElement multicityCheckBox;
	
	public WebElement getRoundtripToLoc() {
		return roundtripToLoc;
	}

	public WebElement getRoundtripFromLoc() {
		return roundtripFromLoc;
	}

	public WebElement getPriceDropdown() {
		return priceDropdown;
	}

	public List<WebElement> getFlightsList() {
		return flightsList;
	}
	
	// verify Results page displays flights availability from given source and destination
	public void verifyFlightsList()	{
		int count=0;
		for(int i=0;i<flightsList.size();i++) {
			Reporter.log("Flights List:"+flightsList.get(i).getText(),true);
			count++;
		}
			Reporter.log("Total Available flights:"+count,true);
	}

	// Select items based on Filters such as Price, Distance and Time  
	public void selectFilter(String option) {
		Select select = new Select(priceDropdown);
		select.selectByVisibleText(option);
		
	}
	
	//Verify results page display exact source and destination
	public void verifyFlightsResultsPage(String locationFrom,String locationTo) {
		Assert.assertEquals(locationFrom,"Pune");
		Assert.assertEquals(locationTo,"Mumbai");
		
	}
	
	// verify OneWayTrip checkBox in Search Results Page
	public void verifyOneWayTrip() {
	
		Assert.assertEquals(onewayCheckBox.isSelected(), true);
	}
	
	// verify RoundTrip checkBox in Search Results Page
	public void verifyRoundTrip() {
		Assert.assertEquals(roundtripCheckBox.isSelected(), true);
	}

}
