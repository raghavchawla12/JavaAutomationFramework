package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import resources.AbstractMethods;

public class MainPage extends AbstractMethods {

	WebDriver driver;
	FlightOptionsPage fop = new FlightOptionsPage(driver);
	HomePage hp = new HomePage(driver);

	public MainPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/*
	 * @FindBy(css = "select:nth-child(1)") private WebElement
	 * departureDropdownLocator;
	 * 
	 * public WebElement getDepartureDropdownLocator() { return
	 * departureDropdownLocator; }
	 * 
	 * private By destinationDropdownLocator =
	 * By.cssSelector("select:nth-child(4)");
	 * 
	 * public WebElement getDestinationDropdownLocator() { return
	 * driver.findElement(destinationDropdownLocator); }
	 */

	// Locators
	@FindBy(css = "select:nth-child(1)")
	public WebElement departureDropdownLocator;

	@FindBy(css = "select:nth-child(4)")
	public WebElement destinationDropdownLocator;

	@FindBy(css = "input.btn.btn-primary")
	public WebElement findFlightsButton;

	@FindBy(css = "a:nth-child(3)")
	public WebElement homeButtonLink;

	// Actions
	public void selectDepartureDropdown(int index) {
		Select departureDropdown = new Select(departureDropdownLocator);
		departureDropdown.selectByIndex(index);
	}

	public void selectDestinationDropdown(int index) {
		Select destinationDropdown = new Select(destinationDropdownLocator);
		destinationDropdown.selectByIndex(index);
	}

	public void clickOnFindFlightsButton() {
		findFlightsButton.click();
		elementToClickableWait(fop.chooseThisFlightButtonWait, 30);
	}

	public void clickOnHomeButton() {
		homeButtonLink.click();
		presenceOfElementWait(hp.emailIdFieldWait, 30);
	}

}
