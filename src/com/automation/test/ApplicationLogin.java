package com.automation.test;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

import com.automation.core.TestDriver;

public class ApplicationLogin extends TestDriver {

	@Test
	public void SampleTestMethod() throws Exception {

		try {
			driver.get("https://www.google.co.in/?gfe_rd=cr&ei=y5IFWPSmDbHT8gf-6LyYDg&gws_rd=ssl");

			driver.findElement(By.name("q")).sendKeys("abc");
			driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
			report("Pass", "Gateway Login", "The login action should be passed", "The login is successfully passed",
					"", "Chrome", true);

		} catch (Exception e) {
			recover(e.toString());
		}
	}

}
