package Frameworks.tests;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import blazedemoUtils.BlazedemoUtilities;
import pageObjects.FlightOptionsPage;
import pageObjects.MainPage;
import resources.BaseClass;
import TestComponents.Retry;

public class BlazeDemoTest extends BaseClass {
	public WebDriver driver;
	MainPage mp;
	FlightOptionsPage fop;
	BlazedemoUtilities bdu;
	private static Logger log = LogManager.getLogger(BlazeDemoTest.class.getName());

	@BeforeMethod
	public void initializeDriverAndBrowser() throws IOException {
		driver = initializeDriver();
		driver.get(prop.getProperty("url"));
		mp = new MainPage(driver);
		fop = new FlightOptionsPage(driver);
		bdu = new BlazedemoUtilities();
		bdu.objectCreation(driver);
	}

	@Test(retryAnalyzer = Retry.class)
	public void verifyTotalFlightOptionsAreFive() {
		mp.selectDepartureDropdown(2);
		mp.selectDestinationDropdown(1);
		mp.clickOnFindFlightsButton();
		Assert.assertEquals(fop.getSizeOfFlightOptions(), 5);
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

	@Test(retryAnalyzer = Retry.class)
	public void verifyThatApplicationDisplaysCorrectDepartureAndDropdownInFlightOptionsPage() {
		String expectedText = "Flights from Paris to Buenos Aires:";
		bdu.openChooseThisFlightPage(2, 1);
		Assert.assertEquals(fop.getHeadingText(), expectedText);
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
