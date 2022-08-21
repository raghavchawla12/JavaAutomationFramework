package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "input#email")
	private WebElement emailIdField;

	public WebElement getEmailIdField() {
		return emailIdField;
	}
	
	public By emailIdFieldWait = By.cssSelector("input#email");

	@FindBy(css = "input#password")
	private WebElement passwordField;

	public WebElement getPasswordField() {
		return passwordField;
	}

	@FindBy(css = "button.btn.btn-primary")
	private WebElement loginButton;

	public WebElement getLoginButton() {
		return loginButton;
	}

	@FindBy(css = "div.message")
	private WebElement errorText;

	public WebElement getErrorText() {
		return errorText;
	}
	
	public By errorTextWait = By.cssSelector("div.message");
	
	@FindBy(css="a.btn.btn-link")
	private WebElement forgotPasswordButton;
	
	public ForgotPassword getForgotPasswordButton() {
		forgotPasswordButton.click();
		// ForgotPassword fp = new ForgotPassword(driver);
		// return fp;
		return new ForgotPassword(driver);
	}
}
