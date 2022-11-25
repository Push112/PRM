package com.prm.qa.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.prm.qa.base.TestBase;
import com.prm.qa.util.ExcelUtils;

public class DashboardPage extends TestBase {

	@FindBy(xpath = "//*[@class='navIcon prm-dashboard']")
	@CacheLookup
	WebElement dashboardLabel;

	@FindBy(xpath = "//*[@class='toolBtn']//*[@name='myfile']")
	WebElement uploadLink;

	@FindBy(xpath = "//*[@class='docTypeDropdown dropdown']")
	WebElement docDropDownMenu;

	@FindBy(xpath = "//*[@class='dropdown-item normal-font']")
	List<WebElement> optionDropDownMenu;

	@FindBy(xpath = "(//*[@placeholder='Search Entity...'])[1]")
	WebElement searchCustomer;

	@FindBy(xpath = "//*[@class='dropdown-item active']")
	WebElement selectCustomer;

	@FindBy(xpath = "//*[@name='template']")
	WebElement selectTemplateDropDown;

	@FindBy(xpath = "//*[@name='currency']")
	WebElement selectCurrencyDropDown;

	@FindBy(xpath = "//*[@name='language']")
	WebElement selectLanguageDropDown;

	@FindBy(xpath = "//*[@name='datatype']")
	WebElement selectAuditedDropDown;

	@FindBy(xpath = "//*[@class='cardActioBtn processBtn']")
	WebElement buttonProcess;

	@FindBy(xpath = "//*[@class='detail']")
	List<WebElement> details;
	
	@FindBy(xpath = "(//*[@class='detail'])[1]")
	WebElement currDetail;
	

	@FindBy(xpath = "//*[@class='docname inline bigger-font']")
	WebElement docName;

	@FindBy(xpath = "//*[contains(text(), 'Dmscode:')]")
	WebElement dmsCode;
	
	@FindBy(xpath = "//*[@class='labl templatelabel']")
	static
	List<WebElement> templateChartAccLabel;

	@FindBy(xpath = "//span[contains(text(),'Template_')]")
	static List<WebElement> dbTemplate;

	// @FindBy(xpath = "(//div[@class='cardActioBtn reviewBtn'])[1]")
	@FindBy(xpath = "(//div[text()=' Review now'])[1]")
	WebElement reviewMenuCurrentBtn;

	@FindBy(xpath = "//span[contains(text(),'Template_')]/following::div[text()=' Review now']")
	List<WebElement> reviewButton;
	
	@FindBy(xpath = "//span[@class='ng-arrow-wrapper']")
	WebElement notesArrow;
	
	@FindBy(xpath = "//div[@class='ng-option ng-option-marked']")
	WebElement notesOption;
	
	
//	@FindBy(xpath = "//*[text()='Processing']")
//	By buttonProcessing;

	@FindBy(xpath = "//div[@class='imgWrap']//i[@class='fal fa-search']")
	static WebElement topsearchButtonIcon;

	@FindBy(xpath = "//input[@placeholder='Search documents...']")
	static WebElement searchTextBox;

	@FindBy(xpath = "//*[@class='nameCont bigger-font']")
	static WebElement documentName;

	@FindBy(xpath = "//div[@tooltipClass='tooltipClass']/ancestor::div[@class='cardBtn cursor-pointer']")
	static WebElement reviewBtnType;
	
	@FindBy(xpath = "//div[@id='dashboardnavigation']")
	static WebElement dashboardNavBtn;
	

	static ReviewPage reviewPage = new ReviewPage();
	static ExcelUtils excelUtils = new ExcelUtils();
	static DashboardPage dashborad = new DashboardPage();
	static String textExcelFilePath = "C:\\Users\\Evertz\\Documents\\TestWrite\\exceldata.txt";
	static String textReviewFilePath = "C:\\Users\\Evertz\\Documents\\TestWrite\\myfile.txt";

	// Initializing the Page Objects:
	public DashboardPage() {
		PageFactory.initElements(driver, this);
	}

	public String verifyDashboardPageTitle() {
		return driver.getTitle();
	}

	public boolean verifyDashboardPage() {
		return dashboardLabel.isDisplayed();
	}

	public void uploadDocument() {

		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", uploadLink);
		StringSelection ss = new StringSelection(
				"C:\\Users\\Evertz\\Documents\\Practice\\PRM\\src\\main\\java\\com\\prm\\qa\\testdata\\FY2022MSFL.pdf");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
		Robot robot = null;
		try {
			robot = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		robot.delay(250);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);

	}

	public void chooseFileFromDropDown() throws InterruptedException {
//			JavascriptExecutor executor = (JavascriptExecutor)driver;
//			executor.executeScript("arguments[0].click();", docDropDownMenu);

		@SuppressWarnings("deprecation")
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement drpDownDoc = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='docTypeDropdown dropdown']")));
		drpDownDoc.click();
		Thread.sleep(1000);
		List<WebElement> fileList = optionDropDownMenu;
		Iterator<WebElement> it = fileList.iterator();
		while (it.hasNext()) {
			WebElement wb = it.next();
			if (wb.getText().equalsIgnoreCase("Financial Statements")) {
				wb.click();
				break;
			}

		}

	}

	public void searchAndAddCustomer() {
		searchCustomer.sendKeys("ABC");
		selectCustomer.click();

	}

	public void selectTemplateName() {
		Select selectTempalteName = new Select(selectTemplateDropDown);
		String templateName = "Template_Corpository";
		selectTempalteName.selectByVisibleText(templateName);

	}

	public void selectCurrency() {
		Select selectCurrencyValue = new Select(selectCurrencyDropDown);
		String currencyValue = "FJD";
		selectCurrencyValue.selectByVisibleText(currencyValue);

	}

	public void selectLanguage() {
		Select selectLanguageName = new Select(selectLanguageDropDown);
		String languageName = "English";
		selectLanguageName.selectByVisibleText(languageName);

	}

	public void selectAudited() {
		Select selectAuditedValue = new Select(selectAuditedDropDown);
		String auditedValue = "AUDITED";
		selectAuditedValue.selectByVisibleText(auditedValue);

	}
	
	public void selectNotes() {
		notesArrow.click();
		notesOption.click();
		
	}

	public void clickOnProcesButton() {
		buttonProcess.click();

	}

	public String clickOnDetails() {
		String dms="";
		String textDocuement = docName.getText();
		System.out.println(textDocuement);
		String reqFileName = "FY2022MSFL.pdf";
		Assert.assertEquals(textDocuement, reqFileName);
		if (textDocuement.equalsIgnoreCase(reqFileName)) {
			details.get(0).click();

			 String dmsText = dmsCode.getText();
			 dms=dmsText.replace("Dmscode:","");
			//String code = dms.replaceAll("[^0-9]", "");
			System.out.println(dms);

		}
		return dms;
	}

	/*
	 * public void getResponseJSON(){ String scriptToExecute =
	 * "var performance = window.performance || window.mozPerformance || window.msPerformance || window.webkitPerformance || {}; var network = performance.getEntries()|| {}; return JSON.stringify(network);;"
	 * ; //String scriptToExecute = "\"return window.performance.getEntries();\"";
	 * String netData =
	 * ((JavascriptExecutor)driver).executeScript(scriptToExecute).toString();
	 * System.out.println(netData);
	 */

//	        options.setCapability("goog:loggingPrefs", logPrefs);

	
	  public void clickOnReviewButton() throws InterruptedException {
	  
	  @SuppressWarnings({ "unused", "deprecation" }) Boolean bool = new	  WebDriverWait(driver,2000).until(ExpectedConditions.
	  invisibilityOfElementLocated(By.xpath("(//div[@class='cardActioBtn processingBtn'])[1]"))); 
	  	String dms="";
		String textDocuement = docName.getText();
		System.out.println(textDocuement);
		String reqFileName = "FY2022MSFL.pdf";
		Assert.assertEquals(textDocuement, reqFileName);
		if (textDocuement.equalsIgnoreCase(reqFileName)) {
			details.get(0).click();
		}
			 String dmsText = dmsCode.getText();
			 dms=dmsText.replace("Dmscode:","");
			//String code = dms.replaceAll("[^0-9]", "");
			System.out.println(dms);
	  		
			((JavascriptExecutor)driver).executeScript("arguments[0].click();", topsearchButtonIcon);
			searchTextBox.sendKeys(dms);
	
		String reviewBtn = reviewBtnType.getText();

		if (reviewBtn.contains("Review now")) {
			
			reviewBtnType.click();
	  
		}
	  }
	  
	  public void scrollToBottomInsideDiv(WebElement scrollArea) {
		    JavascriptExecutor js = ((JavascriptExecutor) driver);
		    js.executeScript("arguments[0].scrollTo(0, arguments[0].scrollHeight)", scrollArea);
		    
		}         
						
							
				
	public void clickOnReviewforExistingDoc() throws InterruptedException {
		this.scrollToBottomInsideDiv(driver.findElement(By.id("cardPanel")));
		String dbText = "Template_DNB";
		Actions action = new Actions(driver);   
		action.moveToElement(driver.findElement(By.xpath("(//span[contains(text(),'" + dbText + "')]/following::div[text()=' Review now'])[1]"))).click().build().perform();
		
					
			}
	public int findIndex(ArrayList<String> dmsValue, String name) {
	    
		for(String str : dmsValue) {
	        if ( str.equals(name)) {
	            return dmsValue.indexOf(str);
	        }
	    }
	    return -1;
	}
	
	
	public  void searchDmsCodeFromDashBoardPage() throws Exception {
				try {
				Thread.sleep(1000);
				ArrayList<String> dmsValue=excelUtils.readDmsCodeFromExcel();
				for(int j=0;j<dmsValue.size();j++)
				//for(String dms:dmsValue)
				{
					int excel_row=(this.findIndex(dmsValue,dmsValue.get(j)))+1;
					
					System.out.println("each......"+dmsValue.get(j));
									
					WebDriverWait wait = new WebDriverWait(driver, 10);
					WebElement searchIconEle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Search documents...']"))); 
					
					//searchIconEle.sendKeys(dms);
					searchIconEle.sendKeys(dmsValue.get(j));
					
				String docName = documentName.getText();
				//String templateName = dbTemplate.get(0).getText();
				
				String reviewBtn = reviewBtnType.getText();

				if (reviewBtn.contains("Review now")) {
					
					
					reviewBtnType.click();
					driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);			
					List<String> list = new ArrayList<String>();
					
					for (int i=0;i<templateChartAccLabel.size(); i++) {
				        list.add(((WebElement) templateChartAccLabel.get(i)).getText());
				    }
					  try {
					  
					  String listString = ""; 
					  String fullPath=ExcelUtils.writeToTextFile(listString, dmsValue.get(j));
					  for (String s : list	) 
					  {
						  if (list.size()==1){
							  listString += s; 
							 System.out.println("this is the last element in the arraylist");
						  }else {
						  listString += s + "\n" ; 
						  
						  
							}
						
						  
					  }
								
					  ExcelUtils.writeToTextFile(listString, dmsValue.get(j));
					 
					  System.out.println("Number of lines in Text File for Review Screen is:" + " "
							  + ExcelUtils.countTextLines(fullPath)); 
					  excelUtils.readExcel();
					 Boolean result=ExcelUtils.compareTextFiles(textExcelFilePath,fullPath);
					 ExcelUtils.writeXLSXFile(excel_row, 15, result);
					 
					  } catch (Exception e) 
					  { 
						  e.getStackTrace(); 
					  
					  }
					 
							  
				   
					dashboardNavBtn.click();
					((JavascriptExecutor)driver).executeScript("arguments[0].click();", topsearchButtonIcon);
					
				}
				
				else {
					System.out.println("templateName" + "|| " + docName + "||" + reviewBtn + "||" + "\n");
				
				}
					
				searchTextBox.clear();
				}
				
					}catch(Exception e) {
						e.printStackTrace();
					}
	}
	
	@SuppressWarnings("deprecation")
	public static void negScenarioExistingDoc() throws Exception {
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", topsearchButtonIcon);
		
				searchTextBox.sendKeys("3a59740adf6e482997a3f5e40786dd4f");
				//searchTextBox.sendKeys("619695c9e4ea462386f445fe205f629b");
				//searchTextBox.sendKeys("60ab44c315f54d72a3bb21916d326edb");//both white and red
				Thread.sleep(10000);		
			String reviewBtn = reviewBtnType.getText();

	if (reviewBtn.contains("Review now")) {
					
					reviewBtnType.click();
				//}
	}
}
}
