package com.automation.test;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.automation.core.TestDriver;

public class MyProfile extends TestDriver {
	
	By login = By.xpath("//span[contains(text(), 'LOGIN')]");
	
	By username = By.id("input22");
	
	By password = By.id("input29");
	
	By signin = By.xpath(".//*[@id='form15']/div[2]/input");
	
	By changeLogin = By.xpath(".//*[@id='main_master_content']/div/div[2]/div[5]/div[2]/ul/li[3]/div[1]/a");
	
	By newLogin = By.id("NewEmail");
	
	By confirmNewLogin = By.id("ConfirmNewEmail");
	
	By newLoginSaveChanges = By.id("btnSubmit");
	
	By changeLoginCancel = By.xpath(".//*[@id='main_master_content']/div/form/div/div[4]/a");
	
	By changePassword = By.xpath(".//*[@id='main_master_content']/div/div[2]/div[5]/div[2]/ul/li[3]/div[2]/a");
	
	By currentPassword = By.id("CurrentPwd");
	
	By newPassword = By.id("NewPwd");
	
	By confirmNewPassword = By.id("ConfirmNewPwd");
	
	By newPasswordSaveChanges = By.xpath(".//*[@id='main_master_content']/div/form/div/div[4]/input");
	
	By changePasswordCancel = By.xpath(".//*[@id='main_master_content']/div/form/div/div[4]/a");
	
	By updateContactInformation = By.xpath(".//*[@id='main_master_content']/div/div[2]/div[5]/div[2]/ul/li[3]/div[3]/a");
	
	By addWorkPhone = By.id("WorkPhone");
	
	By reasonForAddressChange = By.id("ReasonForAddressChange");
	
	By contactInfoSaveChanges = By.id("btnSubmit");
	
	By contactInfoCancel = By.xpath(".//*[@id='main_master_content']/div/form/div/div[10]/a");
	
	By changeSecurityQA = By.xpath(".//*[@id='main_master_content']/div/div[2]/div[5]/div[2]/ul/li[3]/div[4]/a");
	
	By logout = By.xpath(".//*[@id='header']/table/tbody/tr/td[2]/div/a/span");
	
	@Test
	public void LoginDetails() throws Exception 
	{
		try {
			
			/* Login with user credentials */
			
			LoginChamp loginChamp = new LoginChamp();
			
			loginChamp.Login();
			driver.findElement(changeLogin).click();
		    
		    //driver.findElement(newLogin).sendKeys("loan105745369@gmail.com");
		    
		    //driver.findElement(confirmNewLogin).sendKeys("loan105745369@gmail.com");
		    
		    //driver.findElement(newLoginSaveChanges).click();
		    
		    driver.findElement(changeLoginCancel).click();
		    
		    driver.findElement(changePassword).click();
   		    
    	    //driver.findElement(currentPassword).sendKeys("Championmort@1");
		    
    	    //driver.findElement(newPassword).sendKeys("Champion@1");
		    
    	    //driver.findElement(confirmNewPassword).sendKeys("Champion@1");
		    
    	    //driver.findElement(newPasswordSaveChanges).click();
		   
    	    driver.findElement(changePasswordCancel).click();
    	    
    	    driver.findElement(updateContactInformation).click();
    	    
      	   driver.findElement(addWorkPhone).sendKeys("7889990990");
      	   
     	   Select reasonAddrChange = new Select(driver.findElement(By.id("ReasonForAddressChange")));
   	        
		       reasonAddrChange.selectByValue("Moved");
		       
		       driver.findElement(contactInfoSaveChanges).click();
		       
		       driver.findElement(contactInfoCancel).click();
		       
		       driver.findElement(changeSecurityQA).click();
	   
			} catch (Exception e) {

		}
	}
	
	
	/*@Test(priority=2)
	public void changeLogin() throws Exception 
	{
			try {
		    
		    Clicking on change login link and making changes
				
				driver.findElement(changeLogin).click();
			    
			    //driver.findElement(newLogin).sendKeys("loan105745369@gmail.com");
			    
			    //driver.findElement(confirmNewLogin).sendKeys("loan105745369@gmail.com");
			    
			    //driver.findElement(newLoginSaveChanges).click();
			    
			    driver.findElement(changeLoginCancel).click();
		   
		    } 
			catch (Exception e) {
		    	
		    }
		}
	
	@Test(priority=3)
	public void changePassword() throws Exception 
	{
           try {
        	   Clicking on change password link and changing the password 
        	   
        	   driver.findElement(changePassword).click();
   		    
        	    //driver.findElement(currentPassword).sendKeys("Championmort@1");
   		    
        	    //driver.findElement(newPassword).sendKeys("Champion@1");
   		    
        	    //driver.findElement(confirmNewPassword).sendKeys("Champion@1");
   		    
        	    //driver.findElement(newPasswordSaveChanges).click();
   		   
        	    driver.findElement(changePasswordCancel).click();
        	    
			
		}  catch (Exception e) {
	    	
	    }
		
	}
	@Test
	public void updateContactInfo() throws Exception 
	{
           try {
        	   Clicking on update contact information link 
        	   
        	   driver.findElement(updateContactInformation).click();
        	    
         	   driver.findElement(addWorkPhone).sendKeys("7889990990");
         	   
        	   Select reasonAddrChange = new Select(driver.findElement(By.id("ReasonForAddressChange")));
      	        
   		       reasonAddrChange.selectByValue("Moved");
   		       
   		       driver.findElement(contactInfoSaveChanges).click();
   		       
   		       driver.findElement(contactInfoCancel).click();
        	    
        	   
        	   
           }catch (Exception e) {
   	    	
   	    }
	}
	@Test(priority=4)
	public void updateQA() throws Exception
	{
		try {
			driver.findElement(changeSecurityQA).click();
		}catch (Exception e) {
   	    	
   	    }
	}*/
	}
		
	 
   


