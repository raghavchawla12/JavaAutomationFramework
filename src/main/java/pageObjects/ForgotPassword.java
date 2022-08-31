package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.AbstractMethods;

public class ForgotPassword extends AbstractMethods {

	WebDriver driver;

	public ForgotPassword(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	// Locators
	@FindBy(css="button.btn,btn-primary")
	public WebElement sendLinkButton;
	
	// Actions
	public String getSendLinkButtonText() {
		return sendLinkButton.getText().trim();
	}
}
