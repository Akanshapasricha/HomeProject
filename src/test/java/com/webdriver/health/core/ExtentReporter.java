package com.webdriver.health.core;

import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class ExtentReporter {
	public static ExtentReports report;
	public static ExtentTest logger;
	public static String reportPath = System.getProperty("user.dir") + "\\Reports\\AutomationReport.html";
@BeforeSuite
	public static void reporter() {
		report = new ExtentReports(reportPath);
		
	}

	public static  void createReport(String testName, String testDesc) {

		logger = report.startTest(testName, testDesc);
		//return logger;
	}
}
