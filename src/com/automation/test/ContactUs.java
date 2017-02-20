package com.automation.test;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.automation.core.TestDriver;

public class ContactUs extends TestDriver  {
	

	@Test
	public void ContactUsOnline() throws Exception {
		
		By contactUs = By.xpath(".//*[@id='li5']/a/span");
		
		By contactOnline = By.xpath(".//*[@id='contact_us_form_table']/tbody/tr/td/ul/li[2]/strong/a");
		
		By name = By.id("Name");
		
		By loanNumber = By.id("LoanNumber");
		
		By phoneNumber = By.id("Phone");
		
		By reason = By.id("ReasonID");
		
		By submit = By.id("btnSubmit");
		

		try {
		driver.get("https://qa.championmortgage.com/");
		
		driver.findElement(contactUs).click();
		
		driver.findElement(contactOnline).click();
		
		driver.findElement(name).sendKeys("Tru*****");
		
		driver.findElement(loanNumber).sendKeys("751821");
		
		driver.findElement(phoneNumber).sendKeys("7834566644");
		
		driver.findElement(reason).click();
		
		Select reason1 = new Select(driver.findElement(By.id("ReasonID")));
		
		reason1.selectByValue("3");
		
		Thread.sleep(5000);
		
		driver.findElement(submit).click();
		
		
			
		}catch (Exception e) {
			recover(e.toString());

}
	}
}
	
