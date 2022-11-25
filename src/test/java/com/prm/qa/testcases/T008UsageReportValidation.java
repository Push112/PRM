package com.prm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.prm.qa.base.TestBase;
import com.prm.qa.pages.DashboardPage;
import com.prm.qa.pages.LoginPage;

public class T008UsageReportValidation extends TestBase{
	LoginPage loginPage;
	DashboardPage dashboardPage;
	

	
	public T008UsageReportValidation(){
		super();
	}
	
	@BeforeMethod
	public void setUp(){
		initialization();
		loginPage = new LoginPage();	
	}
	
	
	@SuppressWarnings("static-access")
	@Test(priority=1,description="Test case to validate data usage report")
	public void loginTest() throws Exception {
		
		dashboardPage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		String dashboardPageTitle = dashboardPage.verifyDashboardPageTitle();
		Assert.assertEquals(dashboardPageTitle, "Accelerate!");
		dashboardPage.searchDmsCodeFromDashBoardPage();
		
		
	}
	

	

}
