package com.automation.test;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.automation.core.TestDriver;

public class FAQ extends TestDriver {
	By FAQ = By.xpath(".//*[@id='li3']/a/span");
	
	By expandAll = By.id("expandAll");
	
	By collapseAll =By.id("collapseAll");
	
	By viewFeeLink = By.id("FeeScheduleHyperlink");
	
	@Test
	public void Frequently() throws Exception {

		try {
			driver.get("https://qa.championmortgage.com/");
			
			driver.findElement(FAQ).click();
			
			
			
			driver.findElement(expandAll).click();
			
			Thread.sleep(5000);
			
			driver.findElement(viewFeeLink).click();
			
			Thread.sleep(5000);
			
			driver.findElement(collapseAll).click();
			
		
			}catch (Exception e) {
				
			recover(e.toString());
		}

}
}
