package com.health.core;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Properties;
import java.util.stream.Stream;

public class ReadConfig {

	// Utility util = new Utility();

	/**
	 * Read Values from a Property File
	 * 
	 * @param fileName
	 * @return
	 */

	public Properties readPropertyValues(String fileName) {
		Properties properties = new Properties();
		try {
			String current = new java.io.File(".").getCanonicalPath();
			fileName = fileName.replace("./", "/");
			InputStream inputStream = new FileInputStream(current + fileName);
			properties.load(inputStream);
		} catch (IOException e) {
			// Logger.getTest().log(LogStatus.ERROR, "IOException : " + e);
		}
		return properties;
	}

	public String getPropertyValue(String fileName, String propertyName) {
		Properties properties = new Properties();
		try {
			String current = new java.io.File(".").getCanonicalPath();
			fileName = fileName.replace("./", "/");
			InputStream inputStream = new FileInputStream(current + fileName);
			properties.load(inputStream);
		} catch (IOException e) {
			// Logger.getTest().log(LogStatus.ERROR, "IOException : " + e);
		}
		return properties.getProperty(propertyName);
	}

	/**
	 * Fetch value given the relative path.
	 */

	public String getValue(String fileName, String propertyName) {
		Properties properties = new Properties();
		try {
			String current = new java.io.File(".").getCanonicalPath();
			String fName = "./data/" + fileName + ".properties";
			fName = fName.replace("./", "/");
			InputStream inputStream = new FileInputStream(current + fName);
			properties.load(inputStream);
		} catch (IOException e) {
			// Logger.getTest().log(LogStatus.ERROR, "IOException : " + e);
		}
		return properties.getProperty(propertyName);
	}

	/**
	 * Read Contents of File.
	 * 
	 * @return Iterator<String>
	 */

	@SuppressWarnings("resource")
	public Iterator<String> readFileContents(String fileName) {
		Iterator<String> itr = null;
		Stream<String> stream = null;
		try {
			String current = new java.io.File(".").getCanonicalPath();
			fileName = fileName.replace("./", "/");
			stream = Files.lines(Paths.get(current + fileName));
			itr = stream.iterator();
		} catch (Exception e) {
			// Logger.getTest().log(LogStatus.ERROR, "IOException : " + e);
		}

		return itr;
	}
}
