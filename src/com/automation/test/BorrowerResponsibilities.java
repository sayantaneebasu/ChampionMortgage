package com.automation.test;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.automation.core.TestDriver;

public class BorrowerResponsibilities extends TestDriver  {
	
	By borrowerResponsibilities = By.xpath(".//*[@id='li4']/a/span");
	
	@Test
	public void Borr() throws Exception {

		try {
			driver.get("https://qa.championmortgage.com/");
			
			driver.findElement(borrowerResponsibilities).click();
			
		}catch (Exception e) {
			recover(e.toString());
		}

}
}
