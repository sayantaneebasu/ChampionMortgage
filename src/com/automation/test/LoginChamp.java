package com.automation.test;


import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.automation.core.TestDriver;

public class LoginChamp extends TestDriver
{
    
	By login = By.xpath("//span[contains(text(), 'LOGIN')]");
	
	By username = By.id("input22");
	
	By password = By.id("input29");
	
	By signin = By.xpath(".//*[@id='form15']/div[2]/input");
	
	By changeLogin = By.xpath(".//*[@id='main_master_content']/div/div[2]/div[5]/div[2]/ul/li[3]/div[1]/a");
	
	By newLogin = By.id("NewEmail");
	
	By confirmNewLogin = By.id("ConfirmNewEmail");
	
	By saveChanges = By.id("btnSubmit");
	
	@Test
	public void Login() throws Exception {
		try {
			/* Opening the website */
			driver.get("https://qa.championmortgage.com");
			
			/* Clicking on login button and entering credentials*/
			driver.findElement(login).click();
			
			driver.findElement(username).sendKeys("qatest1025603@gmail.com");
			
		    driver.findElement(password).sendKeys("Champion@1");
		    
		    driver.findElement(signin).click();
		    
		    Thread.sleep(9000);
		   
			
			} catch (Exception e) {

		}
		
	

	}
}

	
	
