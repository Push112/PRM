package com.prm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.prm.qa.base.TestBase;
import com.prm.qa.pages.DashboardPage;
import com.prm.qa.pages.LoginPage;
import com.prm.qa.pages.ReviewPage;
import com.prm.qa.util.ExcelUtils;

public class T001CompareChartAccount extends TestBase{
	LoginPage loginPage;
	DashboardPage dashboardPage;
	ReviewPage reviewPage;
	ExcelUtils excelUtils;
	
	String textExcelFilePath= "./TestWrite/exceldata.txt";
	String textReviewFilePath = "./TestWrite/sep29.txt";
	String textIsReviweFilePath= "C:\\Users\\Evertz\\Documents\\TestWrite\\incomeStatementReviewFile.txt";
	
	public T001CompareChartAccount(){
		super();
	}
	
	@BeforeMethod
	public void setUp(){
		initialization();
		loginPage = new LoginPage();	
	}

	@Test(priority=1,description="Test case to validate data from D&B template for Chart Accounts on Balance sheet matches with the values on Balnace Screen")
	public void compareTextFiles_ChartAccount_BS() throws Exception {
		
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
		//dashboardPage.clickOnDetails();
		dashboardPage.clickOnReviewButton();
		//dashboardPage.clickOnReviewforExistingDoc();
		reviewPage.getChartOfAccounts();
		excelUtils.readExcel();
		ExcelUtils.compareTextFiles(textExcelFilePath,textReviewFilePath);
		
	}
	@Test(priority=2,description="Test case to validate data from D&B template for Chart Accounts on Income Statement sheet matches with the values on Balnace Screen")
	public void compareTextFiles_ChartAccount_IS() throws Exception {
		
		dashboardPage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		reviewPage=new ReviewPage();
		excelUtils=new ExcelUtils();
		String dashboardPageTitle = dashboardPage.verifyDashboardPageTitle();
		Assert.assertEquals(dashboardPageTitle, "Accelerate!");
//		dashboardPage.uploadDocument();
//		Thread.sleep(3000);
//		dashboardPage.chooseFileFromDropDown();
//		dashboardPage.searchAndAddCustomer();
//		dashboardPage.selectTemplateName();
//		dashboardPage.selectCurrency();
//		dashboardPage.selectLanguage();
//		dashboardPage.selectAudited();
//		dashboardPage.selectNotes();
//		dashboardPage.clickOnProcesButton();
//		dashboardPage.clickOnReviewButton();
		dashboardPage.clickOnReviewforExistingDoc();
		reviewPage.getChartOfAccountsIS();
		excelUtils.readExcel();
		ExcelUtils.compareTextFiles(textExcelFilePath,textIsReviweFilePath);
		
	}
	
	

	

}
