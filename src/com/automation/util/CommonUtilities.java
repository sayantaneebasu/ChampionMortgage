package com.automation.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.automation.core.TestDriver;
import com.relevantcodes.extentreports.LogStatus;

public class CommonUtilities {
	
	//create screen shots
	public String getScreenshot(WebDriver driver, String Status) throws Exception {
		 File srcfile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		 File destfile = new File(("./reports/screenshots/screen.png"));
		 FileUtils.copyFile(srcfile,destfile);
		 return destfile.getAbsolutePath();
	}
	
	//type in input field
	//accepts element, text to type, clearing the field before typing true/false 
	public static void sendKeys(WebElement element, String text, boolean clear){
		if(clear){
			element.clear();
		}
			element.sendKeys(text);
	}
	
	//clear input field
	public static void clear(WebElement element){
		element.clear();
	}
	
	//get element text
	public static String getText(WebElement element){
		return element.getText();
	}
	
	
	//click on element
	public static void click(WebElement element){
		element.click();
	}
	
	//check if element is present by size
	public static boolean isElementPresent(WebElement element){
		try {
			if(element.getSize() != null )
				return true;
			else
				return false;
		} catch (NoSuchElementException e){
			return false;
		}
	}
	
	//check Element present by By
	public static boolean isElementPresent(WebDriver driver,By by){
		try {
			driver.findElement(by); 
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
	
	//wait for page to load
	public static void pageLoadTimeout(WebDriver driver,int seconds){
		driver.manage().timeouts().pageLoadTimeout(3, TimeUnit.SECONDS);
	}
	
	public void report(String Status, String Description, String ExpectedResult, String ActualResult, String Exception, String browser, boolean isScreenshotNeeded) throws Exception{
		LinkedHashMap<String, String> resultStep = new LinkedHashMap<String,String>();
		resultStep.put("Status", Status.toUpperCase());
		resultStep.put("Description", Description);
		resultStep.put("ExpectedResult", ExpectedResult);
		resultStep.put("ActualResult", ActualResult);
		resultStep.put("Exception", Exception);
		resultStep.put("Browser", browser);
		TestDriver.results.add(resultStep);
		if (isScreenshotNeeded) {
			getScreenshot(TestDriver.driver, Status.toUpperCase());
		}
		
		switch (Status.toUpperCase()) {
			case "PASS": TestDriver.test.log(LogStatus.PASS, Description); break;
			case "FAIL": TestDriver.test.log(LogStatus.FAIL, Description); break;
			case "WARNING": TestDriver.test.log(LogStatus.WARNING, Description); break;
			case "INFO": TestDriver.test.log(LogStatus.INFO, Description); break;
			case "ERROR": TestDriver.test.log(LogStatus.ERROR, Description); break;
			default: TestDriver.test.log(LogStatus.UNKNOWN, Description); break;
		}
		
	}
	
	public void recover(String Exception) throws Exception{
		LinkedHashMap<String, String> resultStep = new LinkedHashMap<String,String>();
		resultStep.put("Status", "ERROR");
		resultStep.put("Description", "An error occured during the execution");
		resultStep.put("Exception", Exception);
		TestDriver.results.add(resultStep);
		getScreenshot(TestDriver.driver, "ERROR");
		TestDriver.test.log(LogStatus.ERROR, Exception);		
		Assert.fail(Exception);
	}
	
	public static String clickAndSort(WebDriver driver, String columnName,String divId, boolean isAscendingOrder) throws Exception {
		String status;
		List<String> columnData = new ArrayList<String>();
		List<String> sortData = new ArrayList<String>();
		int colNum = findColumnNum(driver, columnName,divId);
		int lastPageNum = Integer
				.parseInt(driver.findElement(By.xpath("//div[@id='"+divId+"']//a[@title='Go to the last page']"))
						.getAttribute("data-page"));
		WebElement currentPage=driver.findElement(By.xpath("//div[@id='"+divId+"']//span[contains(@class,'selected')]"));
		String selectedPage=currentPage.getText();
		if (lastPageNum > 1) {
			if(Integer.parseInt(selectedPage)!=1){
				clickAndWait(driver,"//div[@id='"+divId+"']//a[@title='Go to the first page']",
						"//div[@id='"+divId+"']//a[@title='Go to the first page' and contains(@class,'disabled')]");
			}
		}
		WebElement element = driver.findElement(By.xpath("//div[@id='"+divId+"']//a[contains(text(),'" + columnName + "')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
		
		for (int j = 1; j <= lastPageNum; j++) {
			int RowNum = driver.findElements(By.xpath("//div[@id='"+divId+"']//table/tbody/tr")).size();
			for (int i = 1; i <= RowNum; i++) {
				if (isElementPresent(driver,
						By.xpath("//div[@id='"+divId+"']//table/tbody/tr[" + i + "]/td[" + colNum + "]"))) {
					WebElement column = driver.findElement(
							By.xpath("//div[@id='"+divId+"']//table/tbody/tr[" + i + "]/td[" + colNum + "]"));
					String columnValues = (String) ((JavascriptExecutor) driver)
							.executeScript("return arguments[0].innerHTML;", column);
					columnData.add(columnValues);
				}
			}
			if (j < lastPageNum) {
				WebElement next = driver.findElement(By.xpath("//div[@id='"+divId+"']//a[@title='Go to the next page']"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", next);
			}
		}
		sortData.addAll(columnData);
		if (isAscendingOrder) {
			Collections.sort(sortData);
		} else {
			Collections.sort(sortData, Collections.reverseOrder());
		}
		if (columnData.equals(sortData))
			status = "pass";
		else
			status = "fail";
		return status;
	}

	public static String pagination(WebDriver driver,String divId) throws InterruptedException {
		String status = "Pass";
		WebElement currentPage=driver.findElement(By.xpath("//div[@id='"+divId+"']//span[contains(@class,'selected')]"));
		String selectedPage=currentPage.getText();
		int lastPageNum = Integer
				.parseInt(driver.findElement(By.xpath("//div[@id='"+divId+"']//a[@title='Go to the last page']"))
						.getAttribute("data-page"));
		if (lastPageNum > 1) {
			if(Integer.parseInt(selectedPage)!=1){
				clickAndWait(driver,"//div[@id='"+divId+"']//a[@title='Go to the first page']",
						"//div[@id='"+divId+"']//a[@title='Go to the first page' and contains(@class,'disabled')]");
			}
			for(int i=2;i<=lastPageNum;i++){
				WebElement Page = driver.findElement(By.xpath("//div[@id='"+divId+"']//a[contains(text(),'"+i+"')]"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", Page);
				if (!driver.findElement(By.xpath("//div[@id='"+divId+"']//span[contains(@class,'selected')]")).getText().equals(String.valueOf(i))){
					status = "Fail";
				}
			}
			clickAndWait(driver,"//div[@id='"+divId+"']//a[@title='Go to the first page']",
					"//div[@id='"+divId+"']//a[@title='Go to the first page' and contains(@class,'disabled')]");
			if (!driver.findElement(By.xpath("//div[@id='"+divId+"']//span[contains(@class,'selected')]")).getText().equals("1")
					|| !driver
							.findElement(
									By.xpath("//div[@id='"+divId+"']//a[@title='Go to the previous page' and contains(@class,'disabled')]"))
							.isDisplayed()){
				status = "Fail";
			}
			clickAndWait(driver,"//div[@id='"+divId+"']//a[@title='Go to the last page']",
					"//div[@id='"+divId+"']//a[@title='Go to the last page' and contains(@class,'disabled')]");
			if (!driver.findElement(By.xpath("//div[@id='"+divId+"']//span[contains(@class,'selected')]")).getText().equals(String.valueOf(lastPageNum))
					|| !driver
							.findElement(By
									.xpath("//div[@id='"+divId+"']//a[@title='Go to the next page' and contains(@class,'disabled')]"))
							.isDisplayed()){
				status = "Fail";
			}
			WebElement previousPage = driver
					.findElement(By.xpath("//div[@id='"+divId+"']//a[@title='Go to the previous page']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", previousPage);
			if (!driver.findElement(By.xpath("//div[@id='"+divId+"']//span[contains(@class,'selected')]")).getText()
					.equals(String.valueOf(lastPageNum - 1))){
				status = "Fail";
			}
			clickAndWait(driver,"//div[@id='"+divId+"']//a[@title='Go to the next page']",
					"//div[@id='"+divId+"']//a[@title='Go to the last page' and contains(@class,'disabled')]");
			if (!driver.findElement(By.xpath("//div[@id='"+divId+"']//span[contains(@class,'selected')]")).getText()
					.equals(String.valueOf(lastPageNum))){
				status = "Fail";
			}
		}
		return status;
	}
	
	public static void clickAndWait(WebDriver driver,String elementXpath,String waitCondionXpath){
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement element=driver.findElement(By.xpath(elementXpath));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
         wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(waitCondionXpath))));
	}

	public static int findColumnNum(WebDriver driver, String columnName,String divId) {
		int colSize = driver.findElements(By.xpath("//div[@id='"+divId+"']//table/thead/tr/th")).size();
		int i;
		for (i = 1; i <= colSize; i++) {
			String colName = driver.findElement(By.xpath("//div[@id='"+divId+"']//table/thead/tr/th[" + i + "]")).getText();
			if (colName.equalsIgnoreCase(columnName)) {
				break;
			}
		}
		return i;
	}
	public static boolean assertValues(WebDriver driver, LinkedHashMap<String, String> hm) throws InterruptedException {

		List<WebElement> totalheaders = driver.findElements(By.xpath(hm.get("Tableid") + "/th"));
		boolean b = false;
		for (int i = 0; i <= totalheaders.size() - 1; i++) {
			Thread.sleep(2000);
			for (Entry<String, String> entry : hm.entrySet()) {
				if (totalheaders.get(i).getText().equalsIgnoreCase(entry.getKey())) {
					int totalrows = driver.findElements(By.xpath(hm.get("Tableid") + "/tr")).size();
					for (int j = 1; j <= totalrows - 1; j++) {
						if (driver.findElement(By.xpath(hm.get("Tableid") + "/tr[" + j + "]/td[" + (i + 1) + "]"))
								.getText().equalsIgnoreCase((String) entry.getValue())) {
						
							b = true;

						} else {
							b = false;
						}

					}
				}
			}
		}

		return b;
	}
	
}



