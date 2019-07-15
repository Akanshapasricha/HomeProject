package pages;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.webdriver.health.core.KeyWords;
import com.webdriver.health.utils.Wait;

/**
 * This page used to contains methods and elements of signUp
 * 
 * @author Chiku
 *
 */
public class Signup extends KeyWords {
	WebDriver driver;
	Wait wait = new Wait();

	public Signup(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void clickSignUpButton() throws InterruptedException, IOException {
		smartClick(signUplnk);
	}

	public void clickSignuptab() throws InterruptedException, IOException {
		smartClick(signUpTab);
	}

	public String getPageTitle() {
		return driver.getTitle();
	}

	public void fillRegistration(String firstName, String lastName, String email, String PhNo, String password, String repassword) 
	{
		smartSendKeys(firstNametxt, firstName);
		smartSendKeys(lastNametxt, lastName);
		smartSendKeys(EmailId, email);
		smartSendKeys(PhoneNo, PhNo);
		smartSendKeys(pass, password);
		smartSendKeys(repass, repassword);
	}
	public void clickpartnerWithUs() throws IOException
	{
		scrollToElement(lnkPartnerWithUs);
		smartClick(lnkPartnerWithUs);
	}

	/**
	 * Page Element.
	 */
	@FindBy(xpath = "//button[@class='login'][text()='Sign Up']")
	WebElement signUplnk;
	@FindBy(xpath = "//a[text()='Sign Up'][@data-toggle='tab']")
	WebElement signUpTab;
	@FindBy(xpath = "//input[@id='user-fname']")
	WebElement firstNametxt;
	@FindBy(xpath = "//input[@id='user-lname']")
	WebElement lastNametxt;
	@FindBy(xpath = "//input[@name='User[email]']")
	WebElement EmailId;
	@FindBy(xpath = "//input[@name='User[landline]']")
	WebElement PhoneNo;
	@FindBy(xpath = "//input[@name='User[password_hash]']")
	WebElement pass;
	@FindBy(xpath = "//input[@name='User[repassword]']")
	WebElement repass;
	@FindBy(xpath = "//a[text()='Partner Up With Us']")
	WebElement lnkPartnerWithUs;
}