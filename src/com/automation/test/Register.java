package com.automation.test;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.automation.core.TestDriver;

public class Register extends TestDriver 
{
	By register = By.xpath("//span[contains(text(), 'REGISTER')]");
	
	By championLoanNumber = By.id("LoanNumber");
	
	By SSN = By.id("SSN");
	
	By propertyZipcode = By.id("ZipCode");
	
	By lastName = By.id("Lastname");
	
	By emailAddress = By.id("Email");
	
	By password = By.id("Password");
	
	By confirmPassword = By.id("ConfirmPwd");
	
	By securityQuestion1 = By.id("SecurityQuestionID1");
	
	By securityanswer1 = By.id("SecurityAnswer1");
	
	By securityQuestion2 = By.id("SecurityQuestionID2");
	
	By securityanswer2 = By.id("SecurityAnswer2");
	
	By securityQuestion3 = By.id("SecurityQuestionID3");
	
	By securityanswer3 = By.id("SecurityAnswer3");
	
	By showAnswer2 = By.id("ChkSecAns2");
	
	By termsOfUse = By.id("AgreedToTermsOfUse");
	
	By registerButton = By.id("btnSubmit");

	
	/* Register New Loan with Active Loan Number */
	
	@Test()
	public void RegisterNewLoan() throws Exception {

		try {
			driver.get("https://qa.championmortgage.com/");
			
		    driver.findElement(register).click();
		    
		    Thread.sleep(6000);
		    
		    driver.findElement(championLoanNumber).sendKeys("1025603");
		    
		    Thread.sleep(6000);
		    
		    driver.findElement(SSN).sendKeys("7100");
		    
		    driver.findElement(propertyZipcode).sendKeys("98365");
		    
		    driver.findElement(lastName).sendKeys("Mag*****");
		    
		    driver.findElement(emailAddress).sendKeys("qatest1025603@gmail.com");
		    
		    Thread.sleep(6000);
		    
		    driver.findElement(password).sendKeys("Champion@1");
		    
		    Thread.sleep(6000);
		    
		    driver.findElement(confirmPassword).sendKeys("Champion@1");
		    
		    Thread.sleep(6000);
		    
		    driver.findElement(securityQuestion1).click();
		    
		    Select securityQues1 = new Select(driver.findElement(By.id("SecurityQuestionID1")));
		    
		    securityQues1.selectByValue("11");
		    
			driver.findElement(securityanswer1).sendKeys("Chennai");
			
			driver.findElement(securityQuestion2).click();
			
			Select securityQues2 = new Select(driver.findElement(By.id("SecurityQuestionID2")));
			
			securityQues2.selectByValue("14");
			
			driver.findElement(securityanswer2).sendKeys("Nationstar");
			
			driver.findElement(showAnswer2).click();
			
			driver.findElement(securityQuestion3).click();
			
			Select securityQues3 = new Select(driver.findElement(By.id("SecurityQuestionID3")));
			
			securityQues3.selectByValue("13");
			
			driver.findElement(securityanswer3).sendKeys("Indian");
			
			Thread.sleep(6000);
			
			driver.findElement(termsOfUse).click();
			
			Thread.sleep(6000);
			
			driver.findElement(registerButton).click();
			
			Thread.sleep(6000);
			
}catch (Exception e) {
			recover(e.toString());
		}
			
		}

}
