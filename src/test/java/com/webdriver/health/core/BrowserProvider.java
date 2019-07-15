package com.webdriver.health.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import org.testng.annotations.DataProvider;

/**
 * This Class contains data provider for web operations.
 * 
 * @author souradeepg
 *
 */
public class BrowserProvider {
	@DataProvider(name = "local")
	public static Object[][] localTestRun() throws FileNotFoundException {
		Scanner s = new Scanner(new File("browsers"));
		ArrayList<String> list = new ArrayList<String>();
		while (s.hasNext()) {
			list.add(s.next());
		}
		String[][] browsers = new String[list.size()][1];
		// return browsers;
		for (int i = 0; i < list.size(); i++) {
			browsers[i][0] = list.get(i);
		}
		return (browsers);
	}

	/**
	 * Browsers that tests will be executed on Grid.
	 * 
	 * @return
	 */
	@DataProvider(name = "remote")
	public static Object[][] GridTestRun() {
		return new Object[][] { { "GridChrome" } };
	}
}
