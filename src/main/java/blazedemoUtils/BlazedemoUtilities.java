package blazedemoUtils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import pageObjects.FlightOptionsPage;
import pageObjects.MainPage;
import resources.BaseClass;

public class BlazedemoUtilities extends BaseClass {

	MainPage mp;
	FlightOptionsPage fop;
	private static Logger log = LogManager.getLogger(BlazedemoUtilities.class.getName());

	public void objectCreation(WebDriver driver) {
		mp = new MainPage(driver);
		fop = new FlightOptionsPage(driver);
	}

	public void openChooseThisFlightPage(int departureIndex, int destinationIndex) {
		mp.selectDepartureDropdown(departureIndex);
		mp.selectDestinationDropdown(destinationIndex);
		mp.clickOnFindFlightsButton();
	}

}
