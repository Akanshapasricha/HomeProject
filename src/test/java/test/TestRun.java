 package test;
  

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.webdriver.health.core.BaseSetup;
import com.webdriver.health.core.Browser;
import com.webdriver.health.core.BrowserProvider;
import com.webdriver.health.core.ExtentReporter;
import com.webdriver.health.core.KeyWords;
import com.webdriver.health.core.UIDataProvider;

import pages.HomePage;
import pages.Signup;

public class TestRun extends BaseSetup {
	/**
	 * This Test used to test the SignUp registration.
	 * 
	 * @param browser
	 * @param firstName
	 * @param lastName
	 * @throws InterruptedException
	 * @throws IOException
	 */
	ExtentReports reports;
	ExtentTest logger;
	KeyWords keywrd;

	@Test(dataProvider = "signUpRegistrationAndBrowser", dataProviderClass = UIDataProvider.class)
	public void testVerifySignUp(String browser, String firstName, String lastName, String email, String phNo, String passwrd, String repasswrd)
			throws InterruptedException, IOException {
		ExtentReporter.createReport("Sign Up registration", "Test used to verify registration");
		driver = Browser.setDriver(browser, "Verify the Sign Up Registration" + " on " + browser);
		Signup signUp = new Signup(driver);
		keywrd = new KeyWords(driver);
		keywrd.navigateToURL(config.getPropertyValue("./url.properties", "URL"));
		signUp.clickpartnerWithUs();
		signUp.clickSignUpButton();
		signUp.clickSignuptab();
		signUp.fillRegistration(firstName, lastName, email, phNo, passwrd, repasswrd);
		Assert.assertTrue(false);
	}
	
	@Test(dataProvider = "local", dataProviderClass = BrowserProvider.class)
	public void totalsearchCategory(String browser) throws IOException
	{
		ExtentReporter.createReport("Count of Search category", "Test used to Count the saecrh category");
		driver = Browser.setDriver(browser, "Count the Total search Category" + " on " + browser);
		HomePage HmPageObj = new HomePage(driver);
		keywrd = new KeyWords(driver);
		keywrd.navigateToURL(config.getPropertyValue("./url.properties", "URL"));
		HmPageObj.totalSearchCategory();
		HmPageObj.scrollandClick();
	}
}
