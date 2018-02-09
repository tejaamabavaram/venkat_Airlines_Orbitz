package venkat.journey.tests;

import org.testng.annotations.Test;
import venkat.journey.generic.BaseTest;
import venkat.journey.pages.FlightsHomePage;
import venkat.journey.pages.FlightsSearchResultsPage;
import venkat.journey.utilities.DataDriven;
//@Test(retryAnalyzer=venkat.journey.generic.RetryAnalyzer.class)
public class FlightBookingRoundtripSteps extends BaseTest {
	@Test(priority=2 )
	public void testRoundTripFlightBooking() 
	{

		int rc= DataDriven.getRowCount(XLPATH, "journey");
		for(int i=1;i<rc;i++) {
			
		String source = DataDriven.getCellValue(XLPATH, "journey", i, 0);
		System.out.println(source);
		String destination = DataDriven.getCellValue(XLPATH, "journey", i, 1);
		System.out.println(destination);
		FlightsHomePage homepage = new FlightsHomePage(driver);
		homepage.selectMenuCategory("Flights");
		homepage.selectTripType("Roundtrip");
		homepage.selectJourney(source,destination);
		homepage.selectRoundTripJourneyDate();
		homepage.selectAdultsCount(2);
		homepage.selectChildrenCount(2);
		homepage.clickOnSearchButton();
		
		FlightsSearchResultsPage searchresultspage = new FlightsSearchResultsPage(driver);
		searchresultspage.selectFilter("Duration (Shortest)");
		searchresultspage.verifyFlightsList();
		//searchresultspage.verifyFlightsResultsPage(source, destination);
		searchresultspage.verifyRoundTrip();
	}

}
}
