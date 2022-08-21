package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FlightOptionsPage {

	WebDriver driver;

	public FlightOptionsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// @FindBy(css="tbody tr")
	// WebElement flightOptionsCount;

	private By flightOptionsCount = By.cssSelector("tbody tr");

	public List<WebElement> getFlightOptionsCount() {
		return driver.findElements(flightOptionsCount);
	}

	@FindBy(css = "td:nth-child(2) > input")
	private WebElement chooseThisFlightButton;
	
	public By chooseThisFlightButtonWait = By.cssSelector("td:nth-child(2) > input");

	public WebElement getChooseThisFlightButton() {
		return chooseThisFlightButton;
	}
	

}
