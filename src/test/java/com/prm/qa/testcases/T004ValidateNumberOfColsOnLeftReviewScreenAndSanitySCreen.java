package com.prm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.prm.qa.base.TestBase;
import com.prm.qa.pages.DashboardPage;
import com.prm.qa.pages.LoginPage;
import com.prm.qa.pages.ReviewPage;
import com.prm.qa.util.ExcelUtils;

public class T004ValidateNumberOfColsOnLeftReviewScreenAndSanitySCreen extends TestBase{
	LoginPage loginPage;
	DashboardPage dashboardPage;
	ReviewPage reviewPage;
	ExcelUtils excelUtils;
	
	String textExcelFilePath= "C:\\Users\\Evertz\\Documents\\TestWrite\\exceldata.txt";
	String textReviewFilePath = "C:\\Users\\Evertz\\Documents\\TestWrite\\sep29.txt";
	String leftText="C:\\Users\\Evertz\\Documents\\TestWrite\\left.txt";
	String rightText="C:\\Users\\Evertz\\Documents\\TestWrite\\right.txt";
			
	
	public T004ValidateNumberOfColsOnLeftReviewScreenAndSanitySCreen(){
		super();
	}
	
	@BeforeMethod
	public void setUp(){
		initialization();
		loginPage = new LoginPage();	
	}
	
	
	@Test(priority=1,description="Total amount 0f columns on review screen and total on sanity screen.")
	public void validateColHeaderReviewScreenSanityScreen() throws Exception {
		
		dashboardPage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		reviewPage=new ReviewPage();
		excelUtils=new ExcelUtils();
		String dashboardPageTitle = dashboardPage.verifyDashboardPageTitle();
		Assert.assertEquals(dashboardPageTitle, "Accelerate!");
		dashboardPage.uploadDocument();
		Thread.sleep(3000);
		dashboardPage.chooseFileFromDropDown();
		dashboardPage.searchAndAddCustomer();
		dashboardPage.selectTemplateName();
		dashboardPage.selectCurrency();
		dashboardPage.selectLanguage();
		dashboardPage.selectAudited();
		dashboardPage.clickOnProcesButton();
		//dashboardPage.clickOnReviewforExistingDoc();
		reviewPage.validateColHeaderReviewScreenAndSanityScreen();
		
	
		
		
		

	}


	

}
