package pages;

import java.io.IOException;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.LogStatus;
import com.webdriver.health.core.KeyWords;
import com.webdriver.health.utils.Wait;

public class HomePage extends KeyWords{
	WebDriver driver;
	Wait wait = new Wait();
	
	public HomePage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void  totalSearchCategory()
	{
		int sizeOfList=SearchItems.size();
		System.out.println(sizeOfList);
		KeyWords.logger.log(LogStatus.PASS, "Total No of Search categories : " + sizeOfList);
	}
	
	public void scrollandClick() throws IOException
	{
		scrollToElement(Makeappointment);
		smartClick(Makeappointment);
	}

	@FindBys(@FindBy(xpath="//ul[@id='searchTb']/li"))
	List<WebElement> SearchItems;
	
	@FindBy(xpath="//a[contains(@href,'Rodger')][text()='Make an appointment']")
	WebElement Makeappointment;
	
	
}
