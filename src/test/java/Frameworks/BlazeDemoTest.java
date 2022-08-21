package Frameworks;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import blazedemoUtils.BlazedemoUtilities;
import pageObjects.FlightOptionsPage;
import pageObjects.MainPage;
import resources.BaseClass;

public class BlazeDemoTest extends BaseClass {
	public WebDriver driver;
	MainPage mp;
	FlightOptionsPage fop;
	private static Logger log = LogManager.getLogger(BlazeDemoTest.class.getName());

	@BeforeTest
	public void initializeDriverAndBrowser() throws IOException {
		driver = initializeDriver();
		driver.get(prop.getProperty("url"));
		mp = new MainPage(driver);
		fop = new FlightOptionsPage(driver);
		log.debug("Driver is Initialized");
	}

	@BeforeMethod
	public void reloadWebPage() {
		driver.get(prop.getProperty("url"));
	}

	@Test
	public void verifyTotalFlightOptionsAreFive() {
		WebElement departureDropdownLocator = mp.getDepartureDropdownLocator();
		Select departureDropdown = new Select(departureDropdownLocator);
		departureDropdown.selectByIndex(2);
		log.debug("Selected Departure Dropdown City");
		WebElement destinationDropdownLocator = mp.getDestinationDropdownLocator();
		Select destinationDropdown = new Select(destinationDropdownLocator);
		destinationDropdown.selectByIndex(1);
		log.debug("Selected Destination Dropdown City");
		mp.getFindFlightsButton().click();
		elementToClickableWait(driver, fop.chooseThisFlightButtonWait, 30);
		Assert.assertEquals(fop.getFlightOptionsCount().size(), 5);
		log.info("Test Case Passed");
	}

	/*
	 * @Test public void deleteMe() {
	 * driver.get("https://the-internet.herokuapp.com/dynamic_loading");
	 * driver.findElement(By.cssSelector("a:nth-child(5)")).click();
	 * driver.findElement(By.xpath("//button[text() = 'Start']")).click(); //
	 * WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30)); //
	 * wait.until(ExpectedConditions.textToBe(By.cssSelector( // "div#finish h4"),
	 * "Hello World!")); By elem = By.cssSelector("div#finish h4");
	 * textMatchesWait(elem, 30, "Hello World!");
	 * Assert.assertEquals(driver.findElement(By.cssSelector("div#finish h4")).
	 * getText(), "Hello World!"); }
	 */
	
	@Test
	public void verifyThatApplicationDisplaysCorrectDepartureAndDropdownInFlightOptionsPage() {
		String expectedText = "Flights from Paris to Buenos Aires:";
		BlazedemoUtilities bdu = new BlazedemoUtilities();
		bdu.objectCreation(driver);
		bdu.openChooseThisFlightPage(driver, 0, 0);
		Assert.assertEquals(fop.getHeadingText().getText().trim(), expectedText);		
	}

	@AfterTest
	public void tearDown() {
		driver.close();
	}

}
