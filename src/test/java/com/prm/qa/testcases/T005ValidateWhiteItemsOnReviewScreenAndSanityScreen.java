package com.prm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.prm.qa.base.TestBase;
import com.prm.qa.pages.DashboardPage;
import com.prm.qa.pages.LoginPage;
import com.prm.qa.pages.ReviewPage;
import com.prm.qa.util.ExcelUtils;

public class T005ValidateWhiteItemsOnReviewScreenAndSanityScreen extends TestBase{
	LoginPage loginPage;
	DashboardPage dashboardPage;
	ReviewPage reviewPage;
	ExcelUtils excelUtils;

	String leftTextReviewScreen="C:\\Users\\Evertz\\Documents\\TestWrite\\reviewLeftData.txt";
	String whiteItemsSanityScreen="C:\\Users\\Evertz\\Documents\\TestWrite\\sanityWhiteItems.txt";
			
	
	public T005ValidateWhiteItemsOnReviewScreenAndSanityScreen(){
		super();
	}
	
	@BeforeMethod
	public void setUp(){
		initialization();
		loginPage = new LoginPage();	
	}
	
	
	@Test(priority=1,description="Total amount items on review screen and total on sanity screen.")
	public void loginTest() throws Exception {
		
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
		reviewPage.extractDataOnLeftColReviewScreenAndSanity();
		
	
		
		

	}

}
