package com.automation.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginFactory {
	
	WebDriver driver;
	@FindBy(id="ContentPlaceHolder1_ContentPlaceHolder_Admin_siteLogin_UserName")
	WebElement username;
	
	@FindBy(id="ContentPlaceHolder1_ContentPlaceHolder_Admin_siteLogin_Password")
	WebElement password;
	
	@FindBy(id="ContentPlaceHolder1_ContentPlaceHolder_Admin_siteLogin_LoginButton")
	WebElement login;
	
	public LoginFactory(WebDriver driver){
//		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	public WebElement username(){
		return username;
	}
	
	public WebElement password(){
		return password;
	}
	
	public WebElement submit(){
		return login;
	}

}
