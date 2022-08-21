package Frameworks;

import java.io.IOException;
import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.ForgotPassword;
import pageObjects.HomePage;
import pageObjects.MainPage;
import resources.BaseClass;

public class BlazeDemoLoginTest extends BaseClass {
	public WebDriver driver;
	MainPage mp; HomePage hp;
	private static Logger log = LogManager.getLogger(BlazeDemoLoginTest.class.getName());

	@BeforeTest
	public void initializeDriverAndBrowser() throws IOException {
		driver = initializeDriver();
		driver.get(prop.getProperty("url"));
		log.debug("Driver is Initialized");
		mp = new MainPage(driver);
		hp = new HomePage(driver);
	}

	@BeforeMethod
	public void reloadWebPage() {
		driver.get(prop.getProperty("url"));
	}

	@Test(dataProvider = "getData")
	public void verifyApplicationDisplaysPageExpiredUponLoggingIn(String emailId, String password,
			String expectedError) {
		mp.getHomeButtonLink().click();
		presenceOfElementWait(driver, hp.emailIdFieldWait, 30);
		hp.getEmailIdField().sendKeys(emailId);
		hp.getPasswordField().sendKeys(password);
		hp.getLoginButton().click();
		presenceOfElementWait(driver, hp.errorTextWait, 30);
		if (hp.getErrorText().getText().equalsIgnoreCase(expectedError)) {
			Assert.assertTrue(true);
			log.info("Test Case Passed");
		} else {
			log.error("Test Case Failed");
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void verifyForgotPasswordPageIsOpeningUponClickingOnForgotPasswordButton() throws InterruptedException {

		mp.getHomeButtonLink().click();
		presenceOfElementWait(driver, hp.emailIdFieldWait, 30);
		ForgotPassword fp = hp.getForgotPasswordButton();
		forceWait(5000);
		Assert.assertEquals(fp.getSendLinkButton().getText().trim(), "Send Password Reset Link");
		log.info("Test Case Passed");
	}

	@AfterTest
	public void tearDown() {
		driver.close();
	}

	@DataProvider
	public Object[][] getData() {
		Object[][] data = new Object[1][3];
		data[0][0] = "abc@test.com";
		data[0][1] = "1234";
		data[0][2] = "Page Expired";
		return data;
	}
}
