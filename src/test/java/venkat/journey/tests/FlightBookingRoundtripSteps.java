package venkat.journey.tests;

import org.testng.annotations.Test;
import venkat.journey.generic.BaseTest;
import venkat.journey.pages.FlightsHomePage;
import venkat.journey.pages.FlightsSearchResultsPage;

public class FlightBookingRoundtripSteps extends BaseTest {
	@Test(priority=2)
	public void testRoundTripFlightBooking() 
	{
		FlightsHomePage homepage = new FlightsHomePage(driver);
		homepage.selectMenuCategory("Flights");
		homepage.selectTripType("Roundtrip");
		homepage.selectJourney("Pune", "Mumbai");
		homepage.selectRoundTripJourneyDate();
		homepage.selectAdultsCount(2);
		homepage.selectChildrenCount(2);
		homepage.clickOnSearchButton();
		
		FlightsSearchResultsPage searchresultspage = new FlightsSearchResultsPage(driver);
		searchresultspage.selectFilter("Duration (Shortest)");
		searchresultspage.verifyFlightsList();
		searchresultspage.verifyFlightsResultsPage("Pune", "Mumbai");
		searchresultspage.verifyRoundTrip();
	}

}
