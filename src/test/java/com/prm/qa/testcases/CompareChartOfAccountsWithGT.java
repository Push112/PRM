package com.prm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.prm.qa.base.TestBase;
import com.prm.qa.pages.DashboardPage;
import com.prm.qa.pages.LoginPage;
import com.prm.qa.pages.ReviewPage;
import com.prm.qa.util.ExcelUtils;

public class CompareChartOfAccountsWithGT extends TestBase{
	LoginPage loginPage;
	DashboardPage dashboardPage;
	ReviewPage reviewPage;
	ExcelUtils excelUtils;
	
	String textExcelFilePath= "C:\\Users\\Evertz\\Documents\\TestWrite\\exceldata.txt";
	String textReviewFilePath = "C:\\Users\\Evertz\\Documents\\TestWrite\\sep29.txt";
	String textIsReviweFilePath= "C:\\Users\\Evertz\\Documents\\TestWrite\\incomeStatementReviewFile.txt";
	
	public CompareChartOfAccountsWithGT(){
		super();
	}
	
	@BeforeMethod
	public void setUp(){
		initialization();
		loginPage = new LoginPage();	
	}
	
	@Test(priority=1)
	public void loginPageTitleTest(){
		String title = loginPage.validateLoginPageTitle();
		Assert.assertEquals(title, "Accelerate!");
	}
	
	@Test(priority=2,description="Test case to validate data from D&B template for Chart Accounts on Income Statement sheet matches with the values on Balnace Screen")
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
		dashboardPage.selectNotes();
		dashboardPage.clickOnProcesButton();
		dashboardPage.clickOnReviewButton();
		reviewPage.getChartOfAccountsIS();
		excelUtils.readExcel();
		excelUtils.compareTextFiles(textExcelFilePath,textIsReviweFilePath);
		
	}
////	
//	
//	
//	@AfterMethod
//	public void tearDown(){
//		driver.quit();
//	}
	
	

	

}
