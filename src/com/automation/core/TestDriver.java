
package com.automation.core;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.LinkedList;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import com.automation.util.BrowserUtilities;
import com.automation.util.CommonUtilities;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class TestDriver extends CommonUtilities {

	public static LinkedHashMap<String, String> parameters;
	public static LinkedList<LinkedHashMap<String, String>> results = new LinkedList<LinkedHashMap<String, String>>();
	public static WebDriver driver;
	public ExtentReports extent;
	String Path = "./reports/htmlreports/ExtentReport.html";
	
	public static ExtentTest test;

	@BeforeMethod()
	public void InitializeTestIteration() {
		driver = BrowserUtilities.getBrowser("Chrome");
		Capabilities cap = ((RemoteWebDriver) TestDriver.driver).getCapabilities();
		System.out.println(Path);	
		extent = new ExtentReports(Path, true);
		File config = new File("./extentreports_config.xml");
		extent.loadConfig(config);
		//parameters.put("browser_version", cap.getBrowserName().toString() + " " + cap.getVersion().toString());
		test = extent.startTest("SampleTestMethod",
				"[" + "IE" + "] " + "SampleTestMethod");
		System.out.println("before method op");
	}

	@DataProvider(name = "data-provider")
	public static Object[][] dataProvider(ITestContext context) {
		String[] browsersArray = context.getCurrentXmlTest().getParameter("browsers").split(",");
		Object[][] obj = new Object[browsersArray.length][1];
		for (int i = 0; i < browsersArray.length; i++) {
			obj[i][0] = browsersArray[i];
		}
		System.out.println("data provider op");	
		return obj;
	}		
	
	@AfterMethod
	public void tearDown() {

		if (driver != null)
			driver.quit();
		extent.endTest(test);
		System.out.println("after method op");
	}

	@AfterSuite
	public void flush() {
		extent.flush();
		extent.close();
		System.out.println("after suite op");
	}

}
