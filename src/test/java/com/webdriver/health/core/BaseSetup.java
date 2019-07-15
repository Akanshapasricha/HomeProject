package com.webdriver.health.core;

import java.io.File;
import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;

import com.health.core.ReadConfig;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class BaseSetup extends ExtentReporter {
	public static WebDriver driver;
	protected Browser b = new Browser();
	protected ReadConfig config = new ReadConfig();
	String screenshotFolder = System.getProperty("user.dir") + "\\screenShots\\";
	String screenshotBaseurl = "http://awsfrontend02.ds1.ltcorp.tree:8000/screenshots/";

	/**
	 * Cleanup close down any open sessions.
	 * 
	 * @throws Exception
	 * 
	 * @throws IOException
	 */
	@AfterMethod
	public void tearDown(ITestResult result) {
		Date date = new Date();
		Format formatter = new SimpleDateFormat("MM-dd-YYYY_hh-mm-ss");
		try {
			if (result.getStatus() == ITestResult.FAILURE) {
				// Logger.getTest().log(LogStatus.ERROR, "Test Failed", "Take
				// Screenshot");
				File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				try {
					String screenshotfileName = result.getName() + "-" + formatter.format(date) + ".png";
					FileUtils.copyFile(scrFile, new File(screenshotFolder + screenshotfileName));
					String addScreenCapture = logger.addScreenCapture(screenshotFolder + screenshotfileName);
					logger.log(LogStatus.FAIL, "hello", addScreenCapture);
				} catch (IOException e) {
					// Logger.getTest().log(LogStatus.ERROR, "File copy to
					// folder failed in tearDown method");
				}
			}
			driver = Browser.get();
			driver.quit();
		} catch (NullPointerException | NoSuchSessionException e) {
			e.printStackTrace(); // noticed sometimes Basesetup throws
			// nullpointerexception on browser.get that
			// skips/fails the whole suite so adding
			// this here
		}
	}

	@AfterSuite
	public void tearDown() {
		report.endTest(logger);
		report.flush();

	}
}
