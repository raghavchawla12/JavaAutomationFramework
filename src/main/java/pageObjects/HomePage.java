package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.AbstractMethods;

public class HomePage extends AbstractMethods {

	WebDriver driver;

	public HomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	// Wait Locators
	public By emailIdFieldWait = By.cssSelector("input#email");
	public By errorTextWait = By.cssSelector("div.message");

	// Locators
	@FindBy(css = "input#email")
	public WebElement emailIdField;
	
	@FindBy(css = "input#password")
	public WebElement passwordField;

	@FindBy(css = "button.btn.btn-primary")
	public WebElement loginButton;

	@FindBy(css = "div.message")
	private WebElement errorText;

	@FindBy(css="a.btn.btn-link")
	private WebElement forgotPasswordButton;
	
	// Action
	public void sendTextInEmailField(String email) {
		emailIdField.sendKeys(email);
	}
	
	public void sendTextInPasswordField(String password) {
		passwordField.sendKeys(password);
	}
	
	public void clickOnLoginButton() {
		loginButton.click();
		presenceOfElementWait(errorTextWait, 30);
	}
	
	public String getErrorText() {
		return errorText.getText().trim();
	}
	
	public ForgotPassword clickOnForgotPasswordButton() {
		forgotPasswordButton.click();
		return new ForgotPassword(driver);
	}
	
	
}
