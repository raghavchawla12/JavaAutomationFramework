package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.AbstractMethods;

public class FlightOptionsPage extends AbstractMethods{

	WebDriver driver;

	public FlightOptionsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	// Wait Locators

	public By chooseThisFlightButtonWait = By.cssSelector("td:nth-child(2) > input");

	// Locators
	
	@FindBy(css="tbody tr")
	public List<WebElement> flightOptionsCount;

	@FindBy(css = "td:nth-child(2) > input")
	public WebElement chooseThisFlightButton;
	
	public WebElement getChooseThisFlightButton() {
		return chooseThisFlightButton;
	}
	
	@FindBy(tagName= "h3")
	private WebElement headingText;
	
	// Actions
	
	public int getSizeOfFlightOptions() {
		return flightOptionsCount.size();
	}
	
	public String getHeadingText() {
		String text = headingText.getText().trim();
		return text;
	}

}
