package com.automation.test;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.automation.core.TestDriver;

public class HomeOwnerAssistance extends TestDriver  {
	

	@Test
	public void ContactUsOnline() throws Exception  {
		By HomeownerAssistance = By.xpath(".//*[@id='li6']/a/span");
		
		By HelpfulDocumentsDeedInLieu = By.id("DeedInLieuAgreement");
		
		By LossMitigation = By.id("Web95OptionAgreement");
		
		By ElectronicPaymentEnrollment = By.id("ElectronicPaymentEnrollmentForm");
		
		By CertificationOfTrusts = By.id("CertificationofTrusts");
		
		By ProbateGeneralInfo = By.id("GeneralInformation");
		
		By ExplanationOfHardship = By.id("ExplanationOfHardship");
		
		By NonBorrowingSpouse = By.id("NonBorrowingSpouse");
		
		try {
			driver.get("https://qa.championmortgage.com/");
			
			driver.findElement(HomeownerAssistance).click();
			
			Thread.sleep(6000);
			
			driver.findElement(HelpfulDocumentsDeedInLieu).click();
			
			
			driver.findElement(LossMitigation).click();
			
			
			driver.findElement(ElectronicPaymentEnrollment).click();
			
			
			driver.findElement(CertificationOfTrusts).click();
			
			
			driver.findElement(ProbateGeneralInfo).click();
			
			
			driver.findElement(ExplanationOfHardship).click();
			
			
			driver.findElement(NonBorrowingSpouse).click();
			
			
			
		}catch (Exception e) {
			recover(e.toString());
		}
		
			

}
}