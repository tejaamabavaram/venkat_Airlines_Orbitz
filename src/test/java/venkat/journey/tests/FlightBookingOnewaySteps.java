package venkat.journey.tests;

import org.testng.annotations.Test;

import venkat.journey.generic.BaseTest;
import venkat.journey.pages.FlightsHomePage;
import venkat.journey.pages.FlightsSearchResultsPage;
import venkat.journey.utilities.DataDriven;

//@Test(retryAnalyzer=venkat.journey.generic.RetryAnalyzer.class)
public class FlightBookingOnewaySteps extends BaseTest {
	@Test(priority=1,retryAnalyzer =venkat.journey.generic.RetryAnalyzer.class)
	public void testOnewayBookingSteps() 
	{
		//Data Driven
		int rc= DataDriven.getRowCount(XLPATH, "journey");
		for(int i=1;i<=rc;i++) {
			
		String source = DataDriven.getCellValue(XLPATH, "journey", i, 0);
		String destination = DataDriven.getCellValue(XLPATH, "journey", i, 1);
		FlightsHomePage homepage = new FlightsHomePage(driver);
		homepage.selectMenuCategory("Flights");
		homepage.selectTripType("One way");
		homepage.selectJourney(source,destination);
		homepage.selectJourneyDate();
		homepage.selectAdultsCount(2);
		homepage.selectChildrenCount(2);
		homepage.clickOnSearchButton();
		
		FlightsSearchResultsPage resultspage = new FlightsSearchResultsPage(driver);
		resultspage.selectFilter("Duration (Longest)");
		resultspage.verifyFlightsList();
		//resultspage.verifyFlightsResultsPage(source,destination);
		resultspage.verifyOneWayTrip();
		
		
	}

}
}