package com.prm.qa.pages;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.stream.Collectors;

import org.apache.poi.util.SystemOutLogger;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.prm.qa.base.TestBase;
import com.prm.qa.util.CSVUtils;
import com.prm.qa.util.ExcelUtils;


public class ReviewPage extends TestBase {
	String textReviewFilePath = "C:\\Users\\Evertz\\Documents\\TestWrite\\myfile.txt";
	DashboardPage dashboardPage;
	ExcelUtils excelUtilsPage = new ExcelUtils();

	@FindBy(xpath = "//*[text()=' Balance Sheet']")
	@CacheLookup
	public static WebElement balanceSheetMenu;

	@FindBy(xpath = "//*[@class='accounts_header_label']")
	WebElement chartOfAccountsLabel;

	@FindBy(xpath = "//*[@class='labl templatelabel']")
	List<WebElement> templateChartAccLabel;

	@FindBy(xpath = "//div[@class='labels']/preceding::div[@class='period-header']")
	List<WebElement> leftColumnheaderBSData;

	@FindBy(xpath = "//div[@class='labels']//following::div[@class='period-header']")
	List<WebElement> rightColumnheaderBSData;

	@FindBy(xpath = "//div[@class='yearcontainer']//div[@class='period-header']")
	List<WebElement> leftHeaderColumnBSData;

	@FindBy(xpath = "//div[@class='yearcontainer templateyearheader']//div[@class='period-header']")
	List<WebElement> rightHeaderColumnBSData;

	@FindBy(xpath = "//div[@id='contentWrapper']//span[@container='body']")
	List<WebElement> leftColumnBSData;

	@FindBy(xpath = "///div[@id='contentWrapper']//div[@class='year']")
	List<WebElement> rightColumnBSData;

	@FindBy(xpath = "//div[text()[contains(.,'FY')]]")
	List<WebElement> HeaderColumnBSData;
	
	@FindBy(xpath = "//span[@class='yearcontainer' and contains(text(),'FY')]")
	List<WebElement> HeaderColumnSanityData;
	

	@FindBy(xpath = "//div[@id='contentWrapper']")
	WebElement reviewTable;
	
	@FindBy(xpath = "//div[text()=' Income Statement']")
	WebElement incomeStatement;
	
	@FindBy(xpath = "//div[text()='Sanity Check']")
	WebElement sanityCheckBtn;
	
	@FindBy(xpath = "//div[text()=' Income Statement']")
	WebElement incomeStatementBtn;	
	
	@FindBy(xpath = "//span[@class='yearcontainer' and text()[contains(.,'FY')]]")
	List<WebElement> HeaderColumnSanityCheckScreen;
	
	@FindBy(xpath = "//div[@class='noneditableinput']")
	List<WebElement> numberWhiteFieldItemSanityCheckScreen;
	
//	@FindBy(xpath = "//div[text()='BS']/preceding::i[@class='far fa-expand-arrows-alt expandBtn']")
//	WebElement expandBtnSanityScreen;
	
	@FindBy(xpath = "//div[@class='classnamecontainer']")
	List<WebElement> expandBtnSanityScreen;
			
	@FindBy(xpath = "//i[@class='fal fa-long-arrow-left']")
	WebElement sanityBSBtn;
	
	@FindBy(xpath = "//div[@class='label-container' and text()='IS']")
	WebElement sanityISBtn;
	
	
	@FindBy(xpath = "//div[@class='lineitem-container sourcelineitem']//following::i[@class='fas fa-plus']")
	List<WebElement> plusBSBtn;
	
	
	@FindBy(xpath = "//div[@class='calculated']")
	List<WebElement> numberGreenFieldItemSanityCheckScreen;
		
	@FindBy(xpath = "//span[text()='Equity']")
	WebElement cEquityRow;
	
	@FindBy(xpath = "//span[text()='Non Current Liabilities']")
	WebElement cCNonCurrentLiabilities;
	
	@FindBy(xpath = "//span[text()='Current Liabilities']")
	WebElement currentLiabilities;
	
	@FindBy(xpath = "//div[text()=' Total Liabilities ']")
	WebElement totalLiabilities;
	
	@FindBy(xpath = "//div[text()=' Total Liabilities and Equity ']")
	WebElement totalLiabilitiesAndEquity;
	
	@FindBy(xpath = "//span[text()='Non Current Assets']")
	WebElement cCNonCurrentAssets;
	
	@FindBy(xpath = "//span[text()='Current Assets']")
	WebElement cCCurrentAssets;
	
	@FindBy(xpath = "//div[text()=' Total Assets ']")
	WebElement totalAssets;
	
	@FindBy(xpath = "//span[text()='Operating Revenue']")
	WebElement operatingRevenue;
	
	@FindBy(xpath = "//span[text()='Other Operating Income']")
	WebElement otherOperatingIncome;
	
	@FindBy(xpath = "//div[text()=' Total Revenue ']")
	WebElement totalRevenue;
	
	@FindBy(xpath = "//span[text()='Cost of Goods Sold']")
	WebElement costOfGoodsSold;
	
	@FindBy(xpath = "//span[text()='Operating Expenses']")
	WebElement operatingExpenses;
	
	@FindBy(xpath = "//div[@class='className']//span[text()='Depreciation']")
	WebElement depreciation;
	
	@FindBy(xpath = "//div[@class='className']//span[text()[contains(.,'Finance')]]")
	WebElement financeCost;
	
	@FindBy(xpath = "//span[text()='Non Operating Expenses']")
	WebElement nonOperatingExpenses;
	
	@FindBy(xpath = "//div[text()=' Total Expenses ']")
	WebElement totalExpenses;
	
	@FindBy(xpath = "//div[@class='className']//span[text()[contains(.,'Other Non Operating')]]")
	WebElement otherNonOpIncome;
	
	@FindBy(xpath = "//div[text()=' Profit Before Exceptional and Extraordinary Items and Tax ']")
	WebElement profitBeforeExAndExtraItemsTax;
	
	@FindBy(xpath = "//span[text()='Exceptional Items']")
	WebElement exceptionalItems;
	
	@FindBy(xpath = "//span[text()='Extraordinary Items']")
	WebElement extraordinaryItems;
	
	@FindBy(xpath = "//span[text()='Prior Period Items']")
	WebElement priorPeriodItems;
	
	@FindBy(xpath = "//div[text()=' Profit Before Tax ']")
	WebElement profitBeforeTax;
	
	@FindBy(xpath = "//span[text()='Tax']")
	WebElement tax;
	
	@FindBy(xpath = "//div[text()=' Profit After Tax ']")
	WebElement profitAfterTax;
	
	@FindBy(xpath = "//span[text()='Profit from Associates']")
	WebElement profitFromAssociates;
	
	@FindBy(xpath = "//span[text()='Profit from Discontinued Operations']")
	WebElement ccDiscontinuedOp;
	
	@FindBy(xpath = "//div[text()=' Profit for the Year before Other Comprehensive Income ']")
	WebElement extractedPREOCIP;
	
	@FindBy(xpath = "//span[text()='Other Comprehensive Income']")
	WebElement otherComprehensiveIncome;
	
	@FindBy(xpath = "//div[text()=' Total Comprehensive Income ']")
	WebElement totalComprehensiveIncome;
	
	@FindBy(xpath = "//span[text()='Supplementary Information']")
	WebElement supplementaryInfo;	
	
	ExcelUtils excelUtils = new ExcelUtils();
	CSVUtils csvUtils = new CSVUtils();
	
	
	// Initializing the Page Objects:
	public ReviewPage() {
		PageFactory.initElements(driver, this);
	}

	public static String dmsCodeFromReviewUrl(String url) {
		String dmsReviewNumber = url.substring(url.lastIndexOf('/') + 1).split("-")[0];

		return dmsReviewNumber;
	}

	public void getChartOfAccounts() throws Exception {
//			 String actualColor = "Blue";
//			 String color = balanceSheetMenu.getCssValue("color");
//			 String hexColor = Color.fromString(color).asHex();
//			 System.out.println(hexColor);
//			 //Assert.assertEquals(false, false);
//			 ArrayList<String> valuesChartOfAcc = new  ArrayList<>();
////			 int sizecount=valuesChartOfAcc.size();
//			 String bsText=balanceSheetMenu.getText();
//			 String fileName = "ReviewFile.txt";
		try {
//			    	
//			   	 String dmsDoc=dmsCodeFromReviewUrl(driver.getCurrentUrl());
//			   	 System.out.println(dmsDoc);
//			   	 
			File file = new File("./TestWrite/sep29.txt");
//			   	 System.out.println(dmsDoc+bsText+fileName);
//			       
			FileWriter fw = new FileWriter(file.getAbsoluteFile());

			BufferedWriter bw = new BufferedWriter(fw);
//			     
			for (int i = 0; i < templateChartAccLabel.size(); i++) {
//			            		

				bw.write(((WebElement) templateChartAccLabel.get(i)).getText());
				
				if(templateChartAccLabel.isEmpty())
				{
					continue;
				
				}else {
					bw.newLine();
				}
				System.out.println(((WebElement) templateChartAccLabel.get(i)).getText());

			}
			// bw.flush();
			bw.close();
			
			System.out.println("Number of lines in Text File for Review Screen is:" + " "
					+ ExcelUtils.countTextLines("./TestWrite/sep29.txt"));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void getChartOfAccountsForEachDMS() throws Exception {
		String bsText = balanceSheetMenu.getText();
		String fileName = "ReviewFile.txt";
		try {
			
			String dmsDoc = dmsCodeFromReviewUrl(driver.getCurrentUrl());
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='labl templatelabel']")));
			/*
			 * System.out.println(dmsDoc); File file = new File(dmsDoc + bsText + fileName);
			 * System.out.println(dmsDoc + bsText + fileName);
			 * 
			 * FileWriter fw = new FileWriter(file.getAbsoluteFile());
			 * 
			 * BufferedWriter bw = new BufferedWriter(fw); Thread.sleep(5000);
			 */
			try {
				for (int i = 0; i < templateChartAccLabel.size(); i++) {

//				bw.write(((WebElement) templateChartAccLabel.get(i)).getText());
//				bw.newLine();

					System.out.println(((WebElement) templateChartAccLabel.get(i)).getText());

				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// bw.flush();
//			bw.close();
//			System.out.println("Number of lines in Text File for Review Screen is:" + " "
//					+ ExcelUtils.countTextLines(dmsDoc + bsText + fileName));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	@SuppressWarnings("static-access")
	public void validateLeftAndRightSummationColBS() throws java.text.ParseException, ParseException, IOException {
		boolean result = false;
		WebElement table = driver.findElement(By.id("contentWrapper"));
		List<WebElement> rows = table.findElements(By.xpath("//div[@class='row noMargin']"));
		List<WebElement> leftrows = table.findElements(By.xpath("//div[@style='padding-left: 1vw; width: 50%;']//div[@class='srcvalue']"));
		
		List<WebElement> rightrows = table.findElements(By.xpath("//div[@class='row noMargin']//div[@class='lineitem-container']"));
		List<WebElement> right_values=driver.findElements(By.xpath("//div[@class='row noMargin']//div[@class='lineitem-container']//div[@class='srcvalue']"));
		int right_size=right_values.size();
		int rowsize=rows.size();
		int rightrowsize=rightrows.size();
		System.out.println(rowsize);
		System.out.println(right_size);
		int leftHeader =leftColumnheaderBSData.size();
		int rightHeader=rightColumnheaderBSData.size();
		System.out.println("Left heade count .... "+leftHeader);
		
		
		 
		 
				
		 for(int k=0;k<leftHeader;k++)
			{
			 Double lsum = 0d; 
			 List<Double> lvalues = new ArrayList<Double>();
			//for(int i=1+k; i<(rowsize*leftHeader)-(leftHeader*leftHeader); i+=leftHeader)
			 for(int i=1+k; i<leftrows.size(); i+=leftHeader)
			{	
				
			String lCellValue = driver.findElement(By.xpath("(//div[@style='padding-left: 1vw; width: 50%;']//div[@class='srcvalue']//span)["+i+"]")).getText().toString();
			System.out.println("LEFT  "+i+"\t"+lCellValue);
			if(lCellValue == null || lCellValue.isEmpty()) 
			 {
				 lCellValue= "0.00";
			 }
			Double left_value=Double.parseDouble( lCellValue.replace(",","")) ;
			System.out.println("left col Values---   "+i+ "   \t  "+left_value);
			lvalues.add(left_value);
			}
							
			 		
			    for (Double x: lvalues) {
			        lsum +=x;
			    }
			    	System.out.println("Summation on review left column for white  items" +leftColumnheaderBSData.get(k).getText() + ":" + lsum);	
			 		}
			    for(int n=0;n<rightHeader;n++)
			    {
			    	Double rsum = 0d; 
			    	List<Double> rvalues = new ArrayList<Double>();
			    //for(int r=1+n; r<(right_size*rightHeader)-(rightHeader*rightHeader); r+=rightHeader)
			    	for(int r=1+n; r<right_size ; r+=rightHeader)
				{
			    	
				String rCellValue = driver.findElement(By.xpath("(//div[@style='padding-right: 1vw; width: 50%;']//div[@class='yearcontainer']//div[@class='srcvalue'])["+r+"]")).getText().toString();
				System.out.println("RIGHT  "+r+"\t"+rCellValue);
				if(rCellValue == null || rCellValue.isEmpty()) 
				 {
					 rCellValue= "0.00";
					 					 
				 }
				Double right_value=Double.parseDouble( rCellValue.replace(",","")) ;
				System.out.println("right col Values---   "+r+ "   \t  "+right_value);
				rvalues.add(right_value);
				
			    }
			    for (Double y: rvalues) {
			        rsum +=y;
			    }
			    
			    System.out.println("Summation on right column "+rightColumnheaderBSData.get(n).getText() + ":" + rsum);
			   
//			     if(lsum.equals(rsum))
//			    {
//			    	result=true;
//			    	System.out.println("Sum on left and right are equal"+ "\t"+rightColumnheaderBSData.get(n).getText()+lsum +"\t"+ rsum);
//			    }else
//			    {
//			    	System.out.println("Sum on left and right are NOT equal"+ "\t"+rightColumnheaderBSData.get(n).getText()+lsum +"\t"+ rsum);
//			    	
//			    }
			    }

			 		}	
					
			 
		public void getChartOfAccountsIS() throws Exception {
		 
		 incomeStatement.click();
		 
		 String fileName = "incomeStatementReviewFile.txt";
	try {
	   	 
		File file = new File("C:\\Users\\Evertz\\Downloads\\TestWrite\\incomeStatementReviewFile.txt");
		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		for (int i = 0; i < templateChartAccLabel.size(); i++) {
		bw.write(((WebElement) templateChartAccLabel.get(i)).getText());
		bw.newLine();
		System.out.println(((WebElement) templateChartAccLabel.get(i)).getText());
        }
		// bw.flush();
		bw.close();
		System.out.println("Number of lines in Text File for Review Screen is:" + " "
				+ ExcelUtils.countTextLines("C:\\Users\\Evertz\\Documents\\TestWrite\\incomeStatementReviewFile.txt"));

	} catch (FileNotFoundException ex) {
         
	}

}

	@SuppressWarnings("deprecation")
	public  void extractDataOnLeftColReviewScreenAndSanity() {
		WebElement table = driver.findElement(By.id("contentWrapper"));
		List<WebElement> rows = table.findElements(By.xpath("//div[@class='row noMargin']"));
		List<WebElement> leftrows = table.findElements(By.xpath("//div[@style='padding-left: 1vw; width: 50%;']//div[@class='srcvalue']"));
		
		int rowsize=rows.size();
		System.out.println(rowsize);
		int leftHeader =leftColumnheaderBSData.size();
		Double lsum = 0d;  
		boolean result = false;
		List<Double> lvalues = new ArrayList<Double>();
		 Double ssum = 0d; 
		 List<Double> svalues = new ArrayList<Double>();

//		 JavascriptExecutor js = (JavascriptExecutor)driver;
			/*
			 * for(int h=0;h<=plusBSBtn.size();h++) { System.out.println("expand---"+h);
			 * WebElement plus=driver.findElement(By.
			 * xpath("(//div[@class='lineitem-container sourcelineitem']//following::i[@class='fas fa-plus'])["
			 * +h+"]")); js.executeScript("arguments[0].click();",plus); }
			 */
		 for(int k=0;k<leftHeader;k++)
	 		{
			for(int i=1+k; i<leftrows.size(); i+=leftHeader)
			{	
				
				System.out.println("testing---"+i);
				String lCellValue = driver.findElement(By.xpath("(//div[@style='padding-left: 1vw; width: 50%;']//div[@class='srcvalue']//span)["+i+"]")).getText().toString();
				System.out.println(lCellValue);
				if(lCellValue == null || lCellValue.isEmpty()) 
				 {
					 lCellValue= "0.00";
					 					 
				 }
				Double left_value=Double.parseDouble( lCellValue.replace(",","")) ;
				System.out.println("left col Values---"+i+ "     "+left_value);
				lvalues.add(left_value);
				
			}
				
	    for (Double x: lvalues) {
	        lsum +=x;
	    }
			
				 System.out.println("Summation on review left column for white  items" +leftColumnheaderBSData.get(k).getText() + ":" + lsum);	
			   		
		
	 		}
			sanityCheckBtn.click();
		
			
			 for(int i=0;i<expandBtnSanityScreen.size();i++) 
			  { 
			  WebElement expand=driver.findElement(By.xpath("//i[@id='Balance Sheet"+i+"icon']"));
			  JavascriptExecutor executor = (JavascriptExecutor)driver; executor.executeScript("arguments[0].click();",expand);
			  }
			 WebElement sanitytable = driver.findElement(By.id("contentWrapper"));
			 List<WebElement> sanitywhiterows = sanitytable.findElements(By.xpath("//div[@class='col-12 noPadding']"));
			 int srows=sanitywhiterows.size();
			 List<WebElement> sHeader=driver.findElements(By.xpath("//span[@class='yearcontainer' and text()[contains(.,'FY')]]"));
			 int sanityHeader=sHeader.size();
			 	for(int l=0;l<sanityHeader;l++)
			 	{
					for(int j=1+l; j<= (srows*sanityHeader)-sanityHeader; j+=sanityHeader)
					{
						
						String sCellValue = driver.findElement(By.xpath("(//div[@class='col-12 noPadding']//div[@class='noneditableinput'])["+j+"]")).getText().toString();
						System.out.println("White Values--"+j+sCellValue);
						if(sCellValue == null || sCellValue.isEmpty()) 
						 {
							sCellValue= "00,000.00";
							 					 
						 }
						Double sanity_value=Double.parseDouble( sCellValue.replace(",","")) ;
						svalues.add(sanity_value);
						
						 
					}
			 		
	
		  for (Double y: svalues) {
		        ssum +=y;
		   }
	
			   System.out.println("Summation of items on review screen left column : " +sHeader.get(l).getText()+  ":" + ssum);
	 		
	 		}
		for(WebElement ele:leftColumnheaderBSData)
		{
		 Assert.assertTrue(lsum==ssum);
		 System.out.println("Both left cols and sanity screen cols summation are equal" +ele);
		}
	
	}

	
	public void extractWhiteItemColSanityScreenn() throws IOException, InterruptedException {
		sanityCheckBtn.click();
		String sanitytempCellText;
				
		
		  for(int i=0;i<=expandBtnSanityScreen.size();i++) { WebElement
		  expand=driver.findElement(By.xpath("//i[@id='Balance Sheet"+i+"icon']"));
		  System.out.println(expand); JavascriptExecutor executor =
		  (JavascriptExecutor)driver; executor.executeScript("arguments[0].click();",
		  expand); }
		 
				 
		WebElement table = driver.findElement(By.id("contentWrapper"));
		List<WebElement> rowsList = table.findElements(By.xpath("//div[@class='col-12 noPadding']"));
		int numRows = rowsList.size();
		int numCols = rowsList.get(0).findElements(By.xpath("//span[@class='yearcontainer' and contains(text(),'FY')]]")).size();

		System.out.println("Total number of rows= " + numRows);
		System.out.println("Total number of cols=" + numCols);

		String[][] arrTabledata = new String[numRows][numCols];
		List<WebElement> columnsList = null;
		int reviewScreenLeftCol=leftHeaderColumnBSData.size();
			System.out.println("-----------------------------------------------------------------------------");
			System.out.print(new WebDriverWait(driver, Duration.ofSeconds(10))
					.until(ExpectedConditions
							.visibilityOfAllElementsLocatedBy(By.xpath("//span[@class='yearcontainer' and text()[contains(.,'FY')]]")))
					.stream().map(element -> element.getText().toString() + "  :  ").collect(Collectors.toList()));
			System.out.println();
			System.out.println("-----------------------------------------------------------------------------");
			File file = new File("C:\\Users\\Evertz\\Documents\\TestWrite\\sanityWhiteItems.txt");
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			Thread.sleep(5000);
			for (int i = 0; i < numRows; i++) {
				System.out.println();
				columnsList = rowsList.get(i)
						.findElements(By.xpath("//div[@style='width: 25vw; display: flex;']//div[@class='numberfield']"));
				for (int j = 0; j < numCols; j++) {
					
					System.out.println(columnsList.get(j).getText().toString() + "   ");
					sanitytempCellText = columnsList.get(j).getText();
					bw.write(arrTabledata[i][j] = sanitytempCellText.toString());
					bw.newLine();				
				}
				
				
			}
			bw.close();
			System.out.println("Number of lines in Text File for Sanity Screen White Items Screen is:" + " "
					+ ExcelUtils.countTextLines("C:\\Users\\Evertz\\Documents\\TestWrite\\sanityWhiteItems.txt"));
		}
	

	public void validateColHeaderReviewScreenAndSanityScreen() {
		int reviewScreenLeftCol=leftHeaderColumnBSData.size();
		System.out.println("-----------------------------Review Screen------------------------------------------------");
		System.out.print(new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions
						.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='yearcontainer']//div[text()[contains(.,'FY')]]")))
				.stream().map(element -> element.getText().toString() + "  :  ").collect(Collectors.toList()));
		System.out.println();
		System.out.println("-----------------------------------------------------------------------------");
		sanityCheckBtn.click();
		int sanityscreenCol=HeaderColumnSanityCheckScreen.size();
		System.out.println("-----------------------------Sanity Screen------------------------------------------------");
		System.out.print(new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions
						.visibilityOfAllElementsLocatedBy(By.xpath("//span[@class='yearcontainer' and text()[contains(.,'FY')]]")))
				.stream().map(element -> element.getText().toString() + "  :  ").collect(Collectors.toList()));
		System.out.println();
		System.out.println("-----------------------------------------------------------------------------");
		if(reviewScreenLeftCol==sanityscreenCol)
		{
			System.out.println("Total amount columns on review screen and total on sanity screen are equal:");
		}else {
			System.out.println("Total amount columns on review screen and total on sanity screen are not equal equal:");
		}
		
			
}
	public void validateColHeaderReviewScreenAndIncomeStmt() {
		int reviewScreenLeftCol=leftHeaderColumnBSData.size();
		System.out.println("-----------------------------Review Screen------------------------------------------------");
		System.out.print(new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions
						.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='yearcontainer']//div[text()[contains(.,'FY')]]")))
				.stream().map(element -> element.getText().toString() + "  :  ").collect(Collectors.toList()));
		System.out.println();
		System.out.println("-----------------------------------------------------------------------------");
		incomeStatementBtn.click();
		int incomeStmtScreenCol=leftHeaderColumnBSData.size();
		System.out.println("-----------------------------Income Screen------------------------------------------------");
		System.out.print(new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions
						.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='yearcontainer' ]//div[ text()[contains(.,'FY')]]")))
				.stream().map(element -> element.getText().toString() + "  :  ").collect(Collectors.toList()));
		System.out.println();
		System.out.println("-----------------------------------------------------------------------------");
		if(reviewScreenLeftCol==incomeStmtScreenCol)
		{
			System.out.println("Total amount columns on review screen and total on income statement screen are equal:");
		}else {
			System.out.println("Total amount columns on review screen and total on income statement screen are not equal equal:");
		}
	
}
	public void validateSummationOfWhiteAndReadItemsOnSanityScreen() throws InterruptedException, IOException {
		sanityCheckBtn.click();
		boolean result = true;
		for(int i=0;i<expandBtnSanityScreen.size();i++) 
		  { 
		  WebElement expand=driver.findElement(By.xpath("//i[@id='Balance Sheet"+i+"icon']"));
		  JavascriptExecutor executor = (JavascriptExecutor)driver; executor.executeScript("arguments[0].click();",expand);
		  }
		 WebElement sanitytable = driver.findElement(By.id("contentWrapper"));
		 List<WebElement> sanityrows = sanitytable.findElements(By.xpath("//div[@class='col-12 noPadding']"));
		 List<WebElement> sanitygreenrows = sanitytable.findElements(By.xpath("//div[@class='col-12 noPadding classheader']"));
		 int srows=sanityrows.size();
		 int greenrows=sanitygreenrows.size();
		System.out.println(greenrows);
		
		List<WebElement> sHeader=driver.findElements(By.xpath("//span[@class='yearcontainer' and text()[contains(.,'FY')]]"));
		 int sanityHeader=sHeader.size();
		 System.out.println(sanityHeader);
		 	for(int k=0;k<sanityHeader;k++)
		 		{
		 		Double ssum = 0d; 
		 		Double greensum = 0d;
		 		List<Double> svalues = new ArrayList<Double>();
		 		List<Double> cvalues = new ArrayList<Double>();
				for(int i=1+k; i<= (srows*sanityHeader)-sanityHeader; i+=sanityHeader)
				{
					
					String sCellValue = driver.findElement(By.xpath("(//div[@class='col-12 noPadding']//div[@class='noneditableinput']//span)["+i+"]")).getText().toString();
					System.out.println("White Values"+sCellValue);
					if(sCellValue == null || sCellValue.isEmpty()) 
					 {
						sCellValue= "0.00";
					 }
					
					Double sanity_value=Double.parseDouble( sCellValue.replace(",","")) ;
					svalues.add(sanity_value);
					
			
				
				}
				for(int j=1+k;j<=(greenrows*sanityHeader)-sanityHeader;j+=sanityHeader)
				{
					
				String calCellValue = driver.findElement(By.xpath("(//div[@class='col-12 noPadding classheader']//div[@class='calculated'])["+j+"]")).getText().toString();
				System.out.println("green----" +calCellValue);
				if(calCellValue == null || calCellValue.isEmpty()) 
				 {
					calCellValue= "0.00";
				 }
				
				Double cal_value=Double.parseDouble( calCellValue.replace(",","")) ;
				cvalues.add(cal_value);
					}
					
			   for (Double y: svalues) {
			        ssum +=y;
			        
			   }
			   
			   for (Double c: cvalues) {
			        greensum +=c;
			        System.out.println("TEST....."+greensum);
			    
			   }
			   System.out.println("Sum on sanity column for white and red items" +sHeader.get(k).getText() + ":" + ssum);	
			   		System.out.println("Sum on sanity column for green items "+sHeader.get(k).getText() + ":" + greensum);
			   		  
			        
		
			   		
			   		//Assert.assertTrue("Sum on values in white and red is equal to sum of values in green on sanity screen : Output for : "+sHeader.get(k).getText(), ssum.equals(greensum));
			   	 // Assert.assertEquals("Sum on values in white and red is equal to sum of values in green on sanity screen : Output for : "+sHeader.get(k).getText(), ssum, greensum);	
		 		}
	}
	/* * returns random integer between minimum and maximum range */ 

	public static int getRandomInteger(int maximum, int minimum){ 

	return ((int) (Math.random()*(maximum - minimum))) + minimum; 

	}

	
	public void validateNumberOfLeftAndRightColBS() throws IOException, InterruptedException {
		  WebElement table = driver.findElement(By.id("contentWrapper"));
			List<WebElement> rows = driver.findElements(By.xpath("//div[@class='row noMargin']"));
			
			int rowsize=rows.size();
			int leftHeader =leftColumnheaderBSData.size();
			int rightHeader=rightColumnheaderBSData.size();
			int max=0;
				for(int i=1; i<=5; i++)
				{	
					int random=this.getRandomInteger(1,rowsize);
					System.out.println("Left Rows Random....."+random);
					List<WebElement> lCellValue = driver.findElements(By.xpath("//div[@id='"+random+"row']//div[@style='padding-left: 1vw; width: 50%;']//div[@class='srcvalue']//span"));
				
					int number_of_lCellValue=lCellValue.size();
				
						System.out.println("Left value size"+"\t" +number_of_lCellValue);  
				if(number_of_lCellValue==leftHeader)
				{
					System.out.println("Contents are available for the given left column for both periods");
					
				
				}else
				{
					System.out.println("Contents are not available for the given left column for both periods");
				}
				
				}
//				System.out.println("\t  Max Contents  available for the given left column\t" 
//                        + "\t" + "\t\t" + number_of_lCellValue.max);
				for(int i=1; i<=5; i++)
				{
					  
				int rightrandom=this.getRandomInteger(1,rowsize);
				System.out.println("Right Rows Random....."+rightrandom);		    	
				    	List<WebElement> rCellValue = driver.findElements(By.xpath("//div[@id='"+rightrandom+"row']//div[@style='padding-right: 1vw; width: 50%;']//div[@class='yearcontainer']//div[@class='srcvalue']"));

						int number_of_rCellValue=rCellValue.size();
						System.out.println("Right value size"+"\t" +number_of_rCellValue);  
						if(number_of_rCellValue==rightHeader)
						{
							System.out.println("Contents are available for the given right column for both periods");
						
						}else
						{
							System.out.println("Contents are not available for the given right column for both periods");
						}
										
						}	
	}
			 	
	    public void validateSummationOfAllBlocksOnSanityScreen() throws InterruptedException, IOException {
		sanityCheckBtn.click();
		boolean result=false;
		 WebElement sanitytable = driver.findElement(By.id("contentWrapper"));
		 List<WebElement> sanitygreenrows = sanitytable.findElements(By.xpath("//div[@class='col-12 noPadding classheader']"));
		 int greenrows=sanitygreenrows.size();
		 List<WebElement> sHeader=driver.findElements(By.xpath("//span[@class='yearcontainer' and text()[contains(.,'FY')]]"));
		 int sanityHeader=sHeader.size();
		 System.out.println(sanityHeader);
		 System.out.println(greenrows);
		Double ssum = 0d; 
		Double greensum = 0d;
		for(int k=0;k<sanityHeader;k++)
		{
			for(int i=1;i<=sanityHeader;i++)
			{
		String total_liabilities=driver.findElement(By.xpath("(//div[text()=' Total Liabilities ']//following::div[2]//div[@class='numberfield']//span)["+i+"]")).getText().toString();
		String cc_NonCurrentLiabilities=driver.findElement(By.xpath("(//span[text()='Non Current Liabilities']//following::div[2]//div[@class='calculated'])["+i+"]")).getText().toString();
		String cc_currentLiabilities=driver.findElement(By.xpath("(//span[text()='Current Liabilities']//following::div[2]//div[@class='calculated'])["+i+"]")).getText().toString();
		
		Double total_liabilities_value=Double.parseDouble( total_liabilities.replace(",","")) ;
		Double cc_NonCurrentLiabilities_value=Double.parseDouble( cc_NonCurrentLiabilities.replace(",","")) ;
		Double cc_currentLiabilities_value=Double.parseDouble( cc_currentLiabilities.replace(",","")) ;
		Double sumOfNonCurrAndCurrentLiabilities=cc_NonCurrentLiabilities_value+cc_currentLiabilities_value;
		 if(total_liabilities_value.equals(sumOfNonCurrAndCurrentLiabilities))
	        {
			 	result=true;
	            System.out.println("values of :" +totalLiabilities.getText() +"="+total_liabilities_value+" "+ "for" +HeaderColumnSanityData.get(k).getText()+"\t"+ "is equal to"+" " +cCNonCurrentLiabilities.getText()+ "+"+currentLiabilities.getText() +"\t"+ sumOfNonCurrAndCurrentLiabilities );
	        }
	        else
	        {
	            System.out.println("values of :" +totalLiabilities.getText() +"="+total_liabilities_value+" "+ "for" +HeaderColumnSanityData.get(k).getText()+"\t"+ "is NOT equal to"+" " +cCNonCurrentLiabilities.getText()+ "+"+currentLiabilities.getText() +"\t"+ sumOfNonCurrAndCurrentLiabilities );
	             
	        }
		String total_Liabilities_And_Equity=driver.findElement(By.xpath("(//div[text()=' Total Liabilities and Equity ']//following::div[2]//div[@class='numberfield']//span)["+i+"]")).getText().toString();
		String cc_EquityRow=driver.findElement(By.xpath("(//span[text()='Equity']//following::div[2]//div[@class='calculated'])["+i+"]")).getText().toString();
		
		Double total_Liabilities_And_Equity_value=Double.parseDouble( total_Liabilities_And_Equity.replace(",","")) ;
		Double cc_EquityRow_Value=Double.parseDouble( cc_EquityRow.replace(",","")) ;
		Double sumOfEquityRowNonCurrAndCurrentLiabilities=cc_NonCurrentLiabilities_value+cc_currentLiabilities_value+cc_EquityRow_Value;
		if(total_Liabilities_And_Equity_value.equals(sumOfEquityRowNonCurrAndCurrentLiabilities))
	        {
			 	result=true;
	            System.out.println("values of :" +totalLiabilitiesAndEquity.getText() +"="+total_Liabilities_And_Equity_value+" "+ "for" +HeaderColumnSanityData.get(k).getText()+"\t"+ "is equal to"+" " +cCNonCurrentAssets.getText()+ "+"+cCCurrentAssets.getText() +cEquityRow.getText() +"\t"+ sumOfEquityRowNonCurrAndCurrentLiabilities );
	        }
	        else
	        {
	            System.out.println("values of :" +totalLiabilitiesAndEquity.getText() +"="+total_Liabilities_And_Equity_value+" "+ "for" +HeaderColumnSanityData.get(k).getText()+"\t"+ "is NOT equal to"+" " +cCNonCurrentAssets.getText()+ "+"+cCCurrentAssets.getText() +cEquityRow.getText() +"\t"+ sumOfEquityRowNonCurrAndCurrentLiabilities );
	             
	        }
		
		
		String total_Assets=driver.findElement(By.xpath("(//div[text()=' Total Assets ']//following::div[2]//div[@class='numberfield']//span)["+i+"]")).getText().toString();
		String cC_NonCurrent_Assets=driver.findElement(By.xpath("(//span[text()='Non Current Assets']//following::div[2]//div[@class='calculated'])["+i+"]")).getText().toString();
		String cC_Current_Assets=driver.findElement(By.xpath("(//span[text()='Current Assets']//following::div[2]//div[@class='calculated'])["+i+"]")).getText().toString();
		
		Double total_Assets_value=Double.parseDouble( total_Assets.replace(",","")) ;
		Double cC_NonCurrent_Assets_Value=Double.parseDouble( cC_NonCurrent_Assets.replace(",","")) ;
		Double cC_Current_Assets_Value=Double.parseDouble( cC_Current_Assets.replace(",","")) ;
		Double sumOfNonCurrAndCurrentAssetsAndCurrentAssets=cC_NonCurrent_Assets_Value+cC_Current_Assets_Value;
			
		if(total_Assets_value.equals(sumOfNonCurrAndCurrentAssetsAndCurrentAssets))
        {
		 	result=true;
            System.out.println("values of :" +totalAssets.getText() +"="+total_Assets_value+" "+ "for" +HeaderColumnSanityData.get(k).getText()+"\t"+ "is equal to"+" " +cCNonCurrentAssets.getText()+ "+"+cCCurrentAssets.getText() +"\t"+ sumOfNonCurrAndCurrentAssetsAndCurrentAssets );
        }
        else
        {
        	 System.out.println("values of :" +totalAssets.getText() +"="+total_Assets_value+" "+ "for" +HeaderColumnSanityData.get(k).getText()+"\t"+ "is NOT equal to"+" " +cCNonCurrentAssets.getText()+ "+"+cCCurrentAssets.getText() +"\t"+ sumOfNonCurrAndCurrentAssetsAndCurrentAssets );
              
        }
			   	
			   		
			   		
			}
		}
			
		
		
}
	    

	    public void validateSummationOfAllBlocksOnIncomeStatement() throws InterruptedException, IOException {
	    	sanityISBtn.click();
			
			 List<WebElement> sHeader=driver.findElements(By.xpath("//span[@class='yearcontainer' and text()[contains(.,'FY')]]"));
			 int isHeader=sHeader.size();
			 			
			for(int k=0;k<isHeader;k++)
			{
				for(int i=1;i<=isHeader;i++)
				{
			String total_Revenue=driver.findElement(By.xpath("(//div[text()=' Total Revenue ']//following::div[2]//span)["+i+"]")).getText().toString();
			String cc_operatingRevenue=driver.findElement(By.xpath("(//span[text()='Operating Revenue']//following::div[2]//div[@class='calculated'])["+i+"]")).getText().toString();
			String cc_otherOperatingIncome=driver.findElement(By.xpath("(//span[text()='Other Operating Income']//following::div[2]//div[@class='calculated'])["+i+"]")).getText().toString();
			
			Double total_Revenue_value=Double.parseDouble( total_Revenue.replace(",","")) ;
			Double cc_revenue_value=Double.parseDouble( cc_operatingRevenue.replace(",","")) ;
			Double cc_other_inc=Double.parseDouble( cc_otherOperatingIncome.replace(",","")) ;
			Double sumOfOperatingREvenueAndOtherOpInc=cc_revenue_value+cc_other_inc;
			System.out.println("For the year"+HeaderColumnSanityData.get(k).getText()+"\t"+totalRevenue.getText() +total_Revenue_value);
			System.out.println("Sum on total_Revenue on income screen : Output for : "+HeaderColumnSanityData.get(k).getText()+" " +totalRevenue.getText()+"="+operatingRevenue.getText()+ "+"+otherOperatingIncome.getText() +"\t"+ sumOfOperatingREvenueAndOtherOpInc);
			System.out.println("********************************************************************************************");
			
			String total_Expenses=driver.findElement(By.xpath("(//div[text()=' Total Expenses ']//following::div[2]//div[@class='numberfield']//span)["+i+"]")).getText().toString();
			String cc_cogs=driver.findElement(By.xpath("(//span[text()='Cost of Goods Sold']//following::div[2]//div[@class='calculated'])["+i+"]")).getText().toString();
			String cc_operating_exp=driver.findElement(By.xpath("(//span[text()='Operating Expenses']//following::div[2]//div[@class='calculated'])["+i+"]")).getText().toString();
			String cc_depreciation=driver.findElement(By.xpath("(//div[@class='className']//span[text()='Depreciation']//following::div[2]//div[@class='calculated'])["+i+"]")).getText().toString();
			String cc_finance_cost=driver.findElement(By.xpath("(//div[@class='className']//span[text()[contains(.,'Finance')]]//following::div[2]//div[@class='calculated'])["+i+"]")).getText().toString();
			String cc_non_operating_exp=driver.findElement(By.xpath("(//span[text()='Non Operating Expenses']//following::div[2]//div[@class='calculated'])["+i+"]")).getText().toString();
						
			Double total_Expenses_value=Double.parseDouble( total_Expenses.replace(",","")) ;
			Double cc_cogs_value=Double.parseDouble( cc_cogs.replace(",","")) ;
			Double cc_operating_exp_value=Double.parseDouble( cc_operating_exp.replace(",","")) ;
			Double cc_depreciation_value=Double.parseDouble( cc_depreciation.replace(",","")) ;
			Double cc_finance_cost_value=Double.parseDouble( cc_finance_cost.replace(",","")) ;
			Double cc_non_operating_exp_value=Double.parseDouble( cc_non_operating_exp.replace(",","")) ;
			Double sumOfTotalExpenses=cc_cogs_value+cc_operating_exp_value+cc_depreciation_value+cc_finance_cost_value+cc_non_operating_exp_value;
			System.out.println("For the year"+HeaderColumnSanityData.get(k).getText()+"\t"+totalExpenses.getText() +total_Expenses_value);
			System.out.println("Sum on total_Expenses_value on income screen : Output for : "+HeaderColumnSanityData.get(k).getText()+" " +totalRevenue.getText()+"="+costOfGoodsSold.getText()+ "+"+operatingExpenses.getText() + "+"+depreciation.getText()+"+"+financeCost.getText()+"+"+nonOperatingExpenses.getText()+"+"+ "\t" + sumOfTotalExpenses);
			System.out.println("********************************************************************************************");
			String cc_other_non_op_Inc=driver.findElement(By.xpath("(//div[@class='className']//span[text()[contains(.,'Other Non Operating')]]//following::div[2]//div[@class='calculated'])["+i+"]")).getText().toString();
			String extracted_PBET=driver.findElement(By.xpath("(//div[text()=' Profit Before Exceptional and Extraordinary Items and Tax ']//following::div[2]//span)["+i+"]")).getText().toString();
			Double cc_other_non_op_Inc_value=Double.parseDouble( cc_other_non_op_Inc.replace(",","")) ;
			Double extracted_PBET_value=Double.parseDouble( extracted_PBET.replace(",","")) ;
			Double sumOfExtractedPBET=cc_revenue_value+cc_other_inc-cc_cogs_value-cc_operating_exp_value-cc_depreciation_value-cc_finance_cost_value-cc_non_operating_exp_value+cc_other_non_op_Inc_value;
			System.out.println("For the year"+HeaderColumnSanityData.get(k).getText()+"\t"+profitBeforeExAndExtraItemsTax.getText() +extracted_PBET_value);
			System.out.println("Sum on extracted_PBET_value on income screen : Output for : "+HeaderColumnSanityData.get(k).getText()+" " +profitBeforeExAndExtraItemsTax.getText()+"="+operatingRevenue.getText() +"+"+ otherOperatingIncome.getText() +"-"+ costOfGoodsSold.getText() +"-"+ operatingExpenses.getText()+"-"+ depreciation.getText()+"-"+ financeCost.getText()+"-"+ nonOperatingExpenses.getText()+"+" + otherNonOpIncome.getText()+"+"+ "\t" + sumOfExtractedPBET);
			System.out.println("********************************************************************************************");

			String extracted_PBT=driver.findElement(By.xpath("(//div[text()=' Profit Before Tax ']//following::div[2]//span)["+i+"]")).getText().toString();
			String cc_exceptional=driver.findElement(By.xpath("(//span[text()='Exceptional Items']//following::div[2]//div[@class='calculated'])["+i+"]")).getText().toString();
			String cc_extraordinary=driver.findElement(By.xpath("(//span[text()='Extraordinary Items']//following::div[2]//div[@class='calculated'])["+i+"]")).getText().toString();
			String cc_prior_period=driver.findElement(By.xpath("(//span[text()='Prior Period Items']//following::div[2]//div[@class='calculated'])["+i+"]")).getText().toString();
			
			Double extracted_PBT_value=Double.parseDouble( extracted_PBT.replace(",","")) ;
			Double cc_exceptional_value=Double.parseDouble( cc_exceptional.replace(",","")) ;
			Double cc_extraordinary_exp_value=Double.parseDouble( cc_extraordinary.replace(",","")) ;
			Double cc_prior_period_value=Double.parseDouble( cc_prior_period.replace(",","")) ;
			Double sumOfExtractedPBT=cc_revenue_value + cc_other_inc - cc_cogs_value - cc_operating_exp_value - cc_depreciation_value - cc_finance_cost_value - cc_non_operating_exp_value + cc_other_non_op_Inc_value - cc_exceptional_value - cc_extraordinary_exp_value - cc_prior_period_value;
			System.out.println("For the year"+HeaderColumnSanityData.get(k).getText()+"\t"+profitBeforeTax.getText() +extracted_PBT_value);
			System.out.println("Sum on total_Expenses_value on income screen : Output for : "+HeaderColumnSanityData.get(k).getText()+" " +profitBeforeTax.getText()+"="+costOfGoodsSold.getText()+ "+"+operatingExpenses.getText() + "+"+depreciation.getText()+"+"+financeCost.getText()+"+"+nonOperatingExpenses.getText()+"+"+ "\t" + sumOfExtractedPBT);
			System.out.println("********************************************************************************************");
			String cc_tax=driver.findElement(By.xpath("(//span[text()='Tax']//following::div[2]//div[@class='calculated'])["+i+"]")).getText().toString();
			String profit_AfterTax=driver.findElement(By.xpath("(//div[text()=' Profit After Tax ']//following::div[2]//div[@class='numberfield'])["+i+"]")).getText().toString();
			
			Double cc_tax_value=Double.parseDouble( cc_tax.replace(",","")) ;
			Double profitAfterTax_value=Double.parseDouble( profit_AfterTax.replace(",","")) ;
			Double sumOfExtractedPAT=cc_revenue_value + cc_other_inc - cc_cogs_value - cc_operating_exp_value - cc_depreciation_value - cc_finance_cost_value - cc_non_operating_exp_value + cc_other_non_op_Inc_value - cc_exceptional_value - cc_extraordinary_exp_value - cc_prior_period_value-cc_tax_value;
			System.out.println("For the year"+HeaderColumnSanityData.get(k).getText()+"\t"+profitAfterTax.getText() +profitAfterTax_value);
			System.out.println("Sum on total_Expenses_value on income screen : Output for : "+HeaderColumnSanityData.get(k).getText()+" " +profitAfterTax.getText()+"="+costOfGoodsSold.getText()+ "+"+operatingExpenses.getText() + "+"+depreciation.getText()+"+"+financeCost.getText()+"+"+nonOperatingExpenses.getText()+"+"+"- "+tax.getText()+"\t" + sumOfExtractedPAT);
			System.out.println("********************************************************************************************");
			
			String cc_assoc_profit=driver.findElement(By.xpath("(//span[text()='Profit from Associates']//following::div[2]//div[@class='numberfield'])["+i+"]")).getText().toString();
			String cc_discont_profit=driver.findElement(By.xpath("(//span[text()='Profit from Discontinued Operations']//following::div[2]//div[@class='calculated'])["+i+"]")).getText().toString();
			String extracted_PREOCIP=driver.findElement(By.xpath("(//div[text()=' Profit for the Year before Other Comprehensive Income ']//following::div[2]//div[@class='numberfield'])["+i+"]")).getText().toString();
			
			Double cc_assoc_profit_value=Double.parseDouble( cc_assoc_profit.replace(",","")) ;
			Double cc_discont_profit_value=Double.parseDouble( cc_discont_profit.replace(",","")) ;
			Double extracted_PREOCIP_value=Double.parseDouble( extracted_PREOCIP.replace(",","")) ;
			Double sumOfExtractedPREOCIP=cc_revenue_value + cc_other_inc - cc_cogs_value - cc_operating_exp_value - cc_depreciation_value - cc_finance_cost_value - cc_non_operating_exp_value + cc_other_non_op_Inc_value - cc_exceptional_value - cc_extraordinary_exp_value - cc_prior_period_value-cc_tax_value+cc_assoc_profit_value+cc_discont_profit_value;
			System.out.println("For the year"+HeaderColumnSanityData.get(k).getText()+"\t"+extractedPREOCIP.getText() +extracted_PREOCIP_value);
			System.out.println("Sum on extracted_PREOCIP_value on income screen : Output for : "+HeaderColumnSanityData.get(k).getText()+" " +extractedPREOCIP.getText()+"="+costOfGoodsSold.getText()+ "+"+operatingExpenses.getText() + "+"+depreciation.getText()+"+"+financeCost.getText()+"+"+nonOperatingExpenses.getText()+"+"+"- "+tax.getText()+"+"+profitFromAssociates.getText()+"+"+ccDiscontinuedOp.getText()+"\t" + sumOfExtractedPREOCIP);
			System.out.println("********************************************************************************************");
			
			String cc_othercom_income=driver.findElement(By.xpath("(//span[text()='Other Comprehensive Income']//following::div[2]//div[@class='numberfield'])["+i+"]")).getText().toString();
			String extracted_POSTOCIP=driver.findElement(By.xpath("(//div[text()=' Total Comprehensive Income ']//following::div[2]//div[@class='numberfield'])["+i+"]")).getText().toString();
			String supplementary_info=driver.findElement(By.xpath("(//span[text()='Supplementary Information']//following::div[2]//div[@class='numberfield'])["+i+"]")).getText().toString();
			
			Double cc_othercom_income_value=Double.parseDouble( cc_othercom_income.replace(",","")) ;
			Double extracted_POSTOCIP_value=Double.parseDouble( extracted_POSTOCIP.replace(",","")) ;
			Double supplementary_info_value=Double.parseDouble( supplementary_info.replace(",","")) ;
			Double sumOfTotalComInc=cc_revenue_value + cc_other_inc - cc_cogs_value - cc_operating_exp_value - cc_depreciation_value - cc_finance_cost_value - cc_non_operating_exp_value + cc_other_non_op_Inc_value - cc_exceptional_value - cc_extraordinary_exp_value - cc_prior_period_value-cc_tax_value+cc_assoc_profit_value+cc_discont_profit_value+cc_othercom_income_value;
			System.out.println("For the year"+HeaderColumnSanityData.get(k).getText()+"\t"+totalComprehensiveIncome.getText() +extracted_POSTOCIP_value);
			System.out.println("Sum on extracted_POSTOCIP_value on income screen : Output for : "+HeaderColumnSanityData.get(k).getText()+" " +totalComprehensiveIncome.getText()+"="+costOfGoodsSold.getText()+ "+"+operatingExpenses.getText() + "+"+depreciation.getText()+"+"+financeCost.getText()+"+"+nonOperatingExpenses.getText()+"+"+"- "+tax.getText()+"+"+profitFromAssociates.getText()+"+"+ccDiscontinuedOp.getText()+"+"+otherComprehensiveIncome.getText()+"\t" + sumOfTotalComInc);
			System.out.println("********************************************************************************************");
			
			
			//			try {
			
//			Assert.assertTrue(total_liabilities_value==sumOfNonCurrAndCurrentLiabilities,"Sum on total_liabilities_value on sanity screen : Output for : " +totalLiabilities.getText()+"="+cCNonCurrentLiabilities.getText()+ "+"+currentLiabilities.getText());
//			
//		       	Assert.assertEquals("Sum on total_liabilities_value on sanity screen : Output for : " +totalLiabilities.getText()+"="+cCNonCurrentLiabilities.getText()+ "+"+currentLiabilities.getText(),total_liabilities_value==sumOfNonCurrAndCurrentLiabilities);
//				Assert.assertEquals("Sum on totalLiabilitiesAndEquity on sanity screen : Output for : " +totalLiabilitiesAndEquity.getText()+"="+cCNonCurrentLiabilities.getText()+ "+"+currentLiabilities.getText()+"+"+cEquityRow.getText() ,total_Liabilities_And_Equity_value==sumOfEquityRowNonCurrAndCurrentLiabilities);
//		       	Assert.assertEquals("Sum on totalAssets on sanity screen : Output for : " +totalAssets.getText()+"="+cCNonCurrentAssets.getText()+ "+"+cCCurrentAssets.getText() ,total_Assets_value==sumOfNonCurrAndCurrentAssetsAndCurrentAssets);
//			  	
//				} catch (Throwable e) {
//				   e.printStackTrace();
//				    Assert.fail();
//				   		
//				   	}
				}
				   	
				   		
				   		
		
			}
			
	}

}






		
		
		   