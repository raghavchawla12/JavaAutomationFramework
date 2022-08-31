package Frameworks.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.ForgotPassword;
import pageObjects.HomePage;
import pageObjects.MainPage;
import resources.BaseClass;
import TestComponents.Retry;

public class BlazeDemoLoginTest extends BaseClass {
	public WebDriver driver;
	MainPage mp;
	HomePage hp;
	private static Logger log = LogManager.getLogger(BlazeDemoLoginTest.class.getName());

	@BeforeMethod(alwaysRun = true)
	public void initializeDriverAndBrowser() throws IOException {
		driver = initializeDriver();
		driver.get(prop.getProperty("url"));
		mp = new MainPage(driver);
		hp = new HomePage(driver);
	}

	@Test(dataProvider = "getData", retryAnalyzer=Retry.class)
	public void verifyApplicationDisplaysPageExpiredUponLoggingIn(String emailId, String password,
			String expectedError) {
		mp.clickOnHomeButton();
		hp.sendTextInEmailField(emailId);
		hp.sendTextInPasswordField(password);
		hp.clickOnLoginButton();
		if (hp.getErrorText().equalsIgnoreCase(expectedError)) {
			log.info("Test Case Passed with Assertion Pass");
			Assert.assertTrue(true);
		} else {
			log.error("Test Case Failed with Assertion Fail");
			Assert.assertTrue(false);
		}
	}

	@Test(retryAnalyzer=Retry.class)
	public void verifyForgotPasswordPageIsOpeningUponClickingOnForgotPasswordButton() throws InterruptedException {

		mp.clickOnHomeButton();
		ForgotPassword fp = hp.clickOnForgotPasswordButton();
		forceWait(5000);
		Assert.assertEquals(fp.getSendLinkButtonText(), "Send Password Reset Link");
	}

	@Test(dataProvider = "getDataJson")
	public void verifyJsonTestDataTestCase(HashMap<String, String> input) {
		mp.clickOnHomeButton();
		hp.sendTextInEmailField(input.get("email"));
		hp.sendTextInPasswordField(input.get("password"));
		hp.clickOnLoginButton();
		if (hp.getErrorText().equalsIgnoreCase(input.get("text"))) {
			log.info("Test Case Passed with Assertion Pass");
			Assert.assertTrue(true);
		} else {
			log.error("Test Case Failed with Assertion Fail");
			Assert.assertTrue(false);
		}
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.quit();
	}

	@DataProvider
	public Object[][] getData() {
		Object[][] data = new Object[1][3];
		data[0][0] = "abc@test.com";
		data[0][1] = "1234";
		data[0][2] = "Page Expired";
		return data;
	}

	@DataProvider
	public Object[][] getDataJson() throws IOException {
		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "//src//test//java//Frameworks//TestData//LoginTestData.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };

// 		@DataProvider
//		public Object[][] getData()
//		  {
//		    return new Object[][]  {{"abc@test.com","1234","Page Expired"}, 
		// {"abc@abc.com","abcd","Page Expired" } };
//		    
//		  }
//		HashMap<String,String> map = new HashMap<String,String>();
//		map.put("email", "abc@test.com");
//		map.put("password", "1234");
//		map.put("text", "Page Expired");
		//
//		HashMap<String,String> map1 = new HashMap<String,String>();
//		map1.put("email", "abc@abc.com");
//		map1.put("password", "abcd");
//		map1.put("text", "Page Expired");
	}
}
