package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {

	WebDriver driver;

	public MainPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "select:nth-child(1)")
	private WebElement departureDropdownLocator;

	public WebElement getDepartureDropdownLocator() {
		return departureDropdownLocator;
	}

	private By destinationDropdownLocator = By.cssSelector("select:nth-child(4)");

	public WebElement getDestinationDropdownLocator() {
		return driver.findElement(destinationDropdownLocator);
	}

	@FindBy(css = "input.btn.btn-primary")
	private WebElement findFlightsButton;

	public WebElement getFindFlightsButton() {
		return findFlightsButton;
	}

	@FindBy(css = "a:nth-child(3)")
	private WebElement homeButtonLink;

	public WebElement getHomeButtonLink() {
		return homeButtonLink;
	}

}
