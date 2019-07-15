package com.webdriver.health.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import org.testng.annotations.DataProvider;

public class UIDataProvider {

	/**
	 * This Method is used to read the data from the file
	 * 
	 * @param filePath
	 * @return
	 * @throws FileNotFoundException
	 */
	public static Object[][] readDataProviderFile(String filePath) throws FileNotFoundException {
		Scanner s = new Scanner(new File(filePath));
		ArrayList<String> list = new ArrayList<String>();
		while (s.hasNext()) 
		{
			list.add(s.next());
		}
		String[] value = list.get(0).trim().split(",");
		String[][] UIdata = new String[list.size()][value.length];
		for (int i = 0; i < list.size(); i++) {
			String[] colValue = list.get(i).trim().split(",");
			List<String> colList = Arrays.asList(colValue);
			for (int j = 0; j < colList.size(); j++) {
				UIdata[i][j] = colList.get(j).trim();
			}
		}
		return (UIdata);
	}

	/**
	 * DataProvider for Browser
	 * 
	 * @return
	 * @throws FileNotFoundException
	 */
	@DataProvider(name = "local")
	public static Object[][] browser() throws FileNotFoundException {
		return BrowserProvider.localTestRun();
	}

	/**
	 * Returns the combined data providers
	 * 
	 * @param provider1
	 * @param provider2
	 * @return
	 */
	public static Object[][] combine(Object[][] provider1, Object[][] provider2) {
		List<Object[]> objectCodesList = new LinkedList<Object[]>();
		for (Object[] o : provider1) {
			for (Object[] o2 : provider2) {
				objectCodesList.add(concatAll(o, o2));
			}
		}
		return objectCodesList.toArray(new Object[0][0]);
	}

	/**
	 * Concat the objects.
	 * 
	 * @param first
	 * @param rest
	 * @return
	 */
	@SafeVarargs
	public static <T> T[] concatAll(T[] first, T[]... rest) {
		int totalLength = first.length;
		for (T[] array : rest) {
			totalLength += array.length;
		}
		T[] result = Arrays.copyOf(first, totalLength);
		int offset = first.length;
		for (T[] array : rest) {
			System.arraycopy(array, 0, result, offset, array.length);
			offset += array.length;
		}
		return result;
	}

	/**
	 * DataProvider for SignUp Registration
	 * 
	 * @author Chiku
	 * @return
	 * @throws FileNotFoundException
	 */
	@DataProvider(name = "signUpRegistration")
	public static Object[][] registrationTestRun() throws FileNotFoundException {
		return readDataProviderFile("./data/signUpRegistration");
	}

	/**
	 * Combined Data Provider for SignUp Registration.
	 * 
	 * @author Chiku
	 * @return
	 * @throws FileNotFoundException
	 */
	@DataProvider(name = "signUpRegistrationAndBrowser")
	public static Object[][] registrationSignUpAndBrowser() throws FileNotFoundException {
		return combine(browser(), registrationTestRun());
	}
}
