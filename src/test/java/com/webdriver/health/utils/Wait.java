package com.webdriver.health.utils;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.webdriver.health.core.Browser;

public class Wait  implements WaitSetup{

	public int timeout = 0;
	WebDriver  driver;

	/**
	 * Implicit Wait.Time is configurable here . This will be read from the wait
	 * properties file.
	 */
	@Override
	public void implicitWait(WebDriver driver, int timeout) {
	driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.MILLISECONDS);
	}

	/**
	 * Explicit wait.Time is configurable here . This will be read from the wait
	 * properties file.
	 */
	@Override
	public void explicitWait(WebDriver driver, int time, WebElement element) {
		try {
			waitForPageToLoad();
		} catch(WebDriverException e) {
			//do nothing in catch block, for Mobile app, waitForPageToLoad doesn't work and throws WebDriverException: Not yet implemented
		}
		timeout = time / 1000;
		new WebDriverWait(driver, timeout).until(ExpectedConditions.elementToBeClickable(element));
	}
	/**
	 * This wait is used to Check the element visibility. This will be read from
	 * the wait properties file.
	 */
	@Override
	public void waitForElementToVisible(WebDriver driver, int time, WebElement element) {
		int timeout = time / 1000;
		try {
			new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOf(element));
		} catch (Exception ex) {
			new WebDriverWait(driver, timeout).until(ExpectedConditions.elementToBeClickable(element));
		}
	}

	/**
	 * This wait is used to Check the element visibility. This will be read from
	 * the wait properties file.
	 */
	@Override
	public void waitForElementToBeInvisible(WebDriver driver, int time, WebElement element) {
		int timeout = time / 1000;
		new WebDriverWait(driver, timeout).until(ExpectedConditions.invisibilityOf(element));
	}

	/**
	 * This wait is used to Check the element visibility using Fluent wait, polling every 10ms
	 */
	public void waitForElementToBeInvisibleFastAndFluent(WebDriver driver, int timeout, WebElement element) {
		FluentWait<WebDriver>  fwait = new FluentWait<WebDriver> (driver)
				.withTimeout(timeout, TimeUnit.MILLISECONDS)
				.pollingEvery(10, TimeUnit.MILLISECONDS)
				.ignoring(NoSuchElementException.class, StaleElementReferenceException.class);
		fwait.until(ExpectedConditions.invisibilityOf(element));
	}

	/**
	 * This wait is used to Check the element visibility using Fluent wait, polling every 10ms
	 */
	public void waitForElementToBeVisibleFastAndFluent(WebDriver driver, int timeout, WebElement element) {
		FluentWait<WebDriver> fwait = new FluentWait<WebDriver>(driver)
				.withTimeout(timeout, TimeUnit.MILLISECONDS)
				.pollingEvery(10, TimeUnit.MILLISECONDS)
				.ignoring(NoSuchElementException.class,
						StaleElementReferenceException.class);
		fwait.until(ExpectedConditions.visibilityOf(element));
	}

	/**
	 * This Wait is used to wait until the element text visible
	 * @author sghosh
	 */
	@Override
	public void waitForElementTextToBeVisible(WebDriver driver, int time,WebElement element,String text) {
		int timeout=time/1000;
		new WebDriverWait(driver, timeout).until(ExpectedConditions.textToBePresentInElementValue(element, text));
	}

	/**
	 * Specify a condition to wait for. Poll every 10 ms. TimeUnit in MS.
	 * 
	 * @param driver
	 * @param timeout
	 * @param element
	 */
	public void fluenttWait(WebDriver driver, int timeout, WebElement element) {
		FluentWait<WebDriver> fwait = new FluentWait<WebDriver>(driver)
				.withTimeout(timeout, TimeUnit.MILLISECONDS)
				.pollingEvery(50, TimeUnit.MILLISECONDS)
				.ignoring(NoSuchElementException.class, StaleElementReferenceException.class);
		fwait.until(ExpectedConditions.elementToBeClickable(element));
	}

	@Override
	public void fluenttWait() {
		// TODO Auto-generated method stub

	}

	/**
	 * Add the waitForPageToLoad Method to wait for the ready state of the
	 * window Waits for the ready state of the window
	 * 
	 * @author ckaur
	 */
	public static void waitForPageToLoad() {
		WebDriver Driver = Browser.get();
		JavascriptExecutor js = (JavascriptExecutor) Driver;
		String pageLoadStatus = null;
		do {
			pageLoadStatus = (String) js.executeScript("return document.readyState");
		} while (!pageLoadStatus.equals("complete"));
	}

	/**
	 * This wait is used to Check that the string is present in URL This will be
	 * read from the wait properties file.
	 */
	public void waitStringPresentInURL(WebDriver driver, int time, String strURL) {
		int timeout = time / 1000;
		new WebDriverWait(driver, timeout).until(ExpectedConditions.urlContains(strURL));
	}

	/**
	 * Wait till the number of Browser Windows matches the desired count, used in switchControlWindow
	 * @param driver
	 * @param numberOfWindows
	 * @param timeInSeconds
	 * @author skancharla
	 */
	public void waitForNumberOfWindowsToEqual(WebDriver driver, final int numberOfWindows) {
		new WebDriverWait(driver, timeout) {
		}.until(new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {                        
				return (driver.getWindowHandles().size() == numberOfWindows);
			}
		}
	);
	}
}
