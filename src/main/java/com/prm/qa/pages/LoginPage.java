package com.prm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.prm.qa.base.TestBase;

public class LoginPage extends TestBase{
	
	//Page Factory - OR:
	@FindBy(xpath="//*[text()='Login']")
	WebElement loginHome;
	
	@FindBy(id="email")
	WebElement email;
	
	@FindBy(id="password")
	WebElement password;
	
	@FindBy(xpath="//*[text()='Login']")
	WebElement loginBtn;


	//Initializing the Page Objects:
	public LoginPage(){
		PageFactory.initElements(driver, this);
	}
	
	//Actions:
	public String validateLoginPageTitle(){
		System.out.println(driver.getTitle());
		return driver.getTitle();
	}
	
	public DashboardPage login(String un, String pwd){
		email.sendKeys(un);
		password.sendKeys(pwd);
		loginBtn.click();
	  
			    	
		return new DashboardPage();
	}
	
}
