package com.webdriver.health.core;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;

/**
 * This Class is used to perform the browser operation.
 * 
 * @author souradeepg
 *
 */
public class Browser {
	public static WebDriver driver;

	/**
	 * This Method is used to set browser for tests.
	 * 
	 * @param browser
	 * @param testname
	 * @return
	 * @throws IOException
	 */
	public static WebDriver setDriver(String browser, String testname) throws IOException {

		switch (browser) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "\\ExeFiles\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
		}
		return driver;
	}

	/**
	 * Get Current Driver Instance.
	 * 
	 * @return
	 */
	public static WebDriver get() {
		return driver;
	}

	/**
	 * TearDown
	 * 
	 * @param currentDriver
	 * @param result
	 */
	public void tearDown(WebDriver currentDriver, ITestResult result) {
		try {
			currentDriver = get();
			currentDriver.quit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
