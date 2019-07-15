package com.webdriver.health.core;

import java.io.File;
import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.relevantcodes.extentreports.LogStatus;
import com.webdriver.health.utils.Wait;

public class KeyWords extends ExtentReporter {
	public WebDriver driver;
	Wait wait = new Wait();
	/**
	 * Page constructor
	 * 
	 * @param driver
	 */
	public KeyWords(WebDriver driver) {
		this.driver = driver;
	}
	/**
	 * This keyWord is used to click on the particular elements
	 * 
	 * @param element
	 * @throws IOException
	 */
	public void smartClick(WebElement element) throws IOException {
		wait.explicitWait(driver, 30000, element);
		element.click();
		logger.log(LogStatus.PASS, "Succesfully Clicked on :" + element);
	}
	/**
	 * This keyWord is used to enter value in the text box.
	 * 
	 * @param element
	 * @param valueToEnter
	 */
	public void smartSendKeys(WebElement element, String valueToEnter) 
	{
		wait.explicitWait(driver, 30000, element);
		element.clear();
		element.sendKeys(valueToEnter);
		logger.log(LogStatus.PASS, "Succesfully Entered the value :" + valueToEnter);
	}
	public void smartDropDownByVisibleText(WebElement element, String valueToEnter)
	{
		Select drpDown = new Select(element);
		drpDown.selectByVisibleText(valueToEnter);
		logger.log(LogStatus.PASS, "DropDown Successfully Selected by the Visible Text: " + valueToEnter);
	}
	public void smartDropDownByIndex(WebElement element, int valueToEnter)
	{
		Select drpDown = new Select(element);
		drpDown.selectByIndex(valueToEnter);
		logger.log(LogStatus.PASS, "DropDown Successfully Selected by the Index:" + valueToEnter);

	}
	public void smartDropDownByValue(WebElement element, String valueToEnter)
	{
		Select drpDown = new Select(element);
		drpDown.selectByValue(valueToEnter);
		logger.log(LogStatus.PASS, "DropDown Successfully Selected by the Value: " + valueToEnter);
	}
	/**
	 * This method is used to scroll to element
	 * 
	 * @param element
	 */
	public void scrollToElement(WebElement element)
	{
		wait.explicitWait(driver, 30000, element);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		logger.log(LogStatus.PASS, "Succesfully scroll to the element :" + element);
	}
	public void navigateToURL(String url)
	{
		driver.get(url);
		logger.log(LogStatus.PASS, "Succesfully Navigated to the URL :" + url);
	}
}
