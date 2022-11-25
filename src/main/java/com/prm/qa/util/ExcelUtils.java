package com.prm.qa.util;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.LineNumberReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.extractor.ExcelExtractor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.prm.qa.pages.DashboardPage;

    public class ExcelUtils {
    	
    	static String textReportFilePath = "./TestWrite/TestDMS.xlsx";
    	static String textExcelFilePath="./TestWrite/exceldata.txt";
    	DashboardPage dashboard=new DashboardPage();

    	@SuppressWarnings({ "resource", "deprecation" })
		//public static void main(String[] args) throws IOException {
           public void readExcel() throws IOException {  
    		StringBuilder sb = new StringBuilder();
            String excelpathDNB = "C:\\Users\\D&BMasterlistSequenceandSignage.xlsx";
         

            Map<String, Integer> requiredHeaders = new HashMap<>();
            FileInputStream file = new FileInputStream(new File(excelpathDNB));
            Workbook workbook = new XSSFWorkbook(file);
            DataFormatter formatter = new DataFormatter();
            Sheet sheet = workbook.getSheetAt(0);
            
            for (Cell cell : sheet.getRow(0)) {
                requiredHeaders.put(cell.getStringCellValue(), cell.getColumnIndex());
            }
            
            PrintStream out = new PrintStream(new FileOutputStream(textExcelFilePath));
            
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            	Row row = sheet.getRow(i);
            	String  sequenceValue=formatter.formatCellValue(row.getCell(requiredHeaders.get("Sequence")));
            	String  balanceValue=formatter.formatCellValue(row.getCell(requiredHeaders.get("Component")));
            	int valueSequence=Integer.parseInt(sequenceValue);
            	if((valueSequence != 0)&&(balanceValue.equals("BS")))
            	{
                String valuesFromExcel=formatter.formatCellValue(row.getCell(requiredHeaders.get("ChartAccount")));
                System.out.println(valuesFromExcel);
               if(valuesFromExcel.isEmpty())
               {
            	   continue;
               }
                out.println(valuesFromExcel);
               
               
                  }       
                	  }
            out.close();
            System.out.println("Number of lines in Text File for Excel is:" + " " +ExcelUtils.countTextLines(textExcelFilePath));
            
         
            	}

            	
    	public static boolean compareTextFiles (String filepath1 , String filePath2) throws FileNotFoundException, IOException{
    			
    			BufferedReader reader1 = new BufferedReader(new FileReader(filepath1));
    	         
    	        BufferedReader reader2 = new BufferedReader(new FileReader(filePath2));
    	         
    	        String line1 = reader1.readLine();
    	         
    	        String line2 = reader2.readLine();
    	         
    	        boolean areEqual = true;
    	         
    	        int lineNum = 1;
    	         
    	        while (line1 != null || line2 != null)
    	        {
    	            if(line1 == null || line2 == null)
    	            {
    	                areEqual = false;
    	                 
    	                break;
    	            }
    	            else if(! line1.equalsIgnoreCase(line2))
    	            {
    	                areEqual = false;
    	                 
    	                break;
    	            }
    	             
    	            line1 = reader1.readLine();
    	             
    	            line2 = reader2.readLine();
    	             
    	            lineNum++;
    	        }
    	         
    	        if(areEqual)
    	        {
    	          
    	        	System.out.println("Two files have same content.");
    	        }
    	        else
    	        {
    	            System.out.println("Two files have different content. They differ at line "+lineNum);
    	             
    	            System.out.println("File1 has "+line1+" and File2 has "+line2+" at line "+lineNum);
    	        }
    	
    	        
    	        reader1.close();
    	         
    	        reader2.close();
    	        
    	        return areEqual;
    	    }
				
    	
    	public static int countTextLines(String filename) throws IOException {
    		int lines = 0;

    		FileReader input = new FileReader(filename);
    		LineNumberReader count = new LineNumberReader(input);

    		String line = count.readLine();

    		if(count.ready())
    		{
    		    while(line != null) {
    		        lines = count.getLineNumber();
    		        line = count.readLine();
    		        
		                         
    		    }
    		    
    		    lines+=1;
    		}
    		    
    		count.close();

    		return lines;
    
    }
    	
		 	//public static void main(String[] args) throws IOException {
            public void readUsageReport() throws IOException {  
    		StringBuilder sb = new StringBuilder();
           // String excelpath = "C:\\Users\\D&BMasterlistSequenceandSignage.xlsx";
    		 String excelpath = "C:\\Users\\Evertz\\Documents\\TestWrite\\UsageReport.xlsx";

            Map<String, Integer> requiredHeaders = new HashMap<>();
            FileInputStream file = new FileInputStream(new File(excelpath));
            Workbook workbook = new XSSFWorkbook(file);
            DataFormatter formatter = new DataFormatter();
            Sheet sheet = workbook.getSheetAt(0);
            
           
        
            for (Cell cell : sheet.getRow(0)) {
                requiredHeaders.put(cell.getStringCellValue(), cell.getColumnIndex());
            }
            
            PrintStream out = new PrintStream(new FileOutputStream(textReportFilePath));
            
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            	Row row = sheet.getRow(i);
            	String valuesFromExcel=formatter.formatCellValue(row.getCell(requiredHeaders.get("DMS CODE")));
                System.out.println(valuesFromExcel);
               
                out.println(valuesFromExcel);
            	}
            out.close();
                       
         
             }
				            	
    
            	public static ArrayList<String> readDmsCodeFromExcel() throws Exception {
            		Thread.sleep(1000);
            		String valuesFromExcel="";
            		ArrayList<String> listValue=new ArrayList<String>();
            		StringBuilder sb = new StringBuilder();
            		String excelpath = "C:\\Users\\Evertz\\Documents\\TestWrite\\UsageReport.xlsx";
            		try {
            			Map<String, Integer> requiredHeaders = new HashMap<>();
            			FileInputStream file = new FileInputStream(new File(excelpath));
            			Workbook workbook = new XSSFWorkbook(file);
            			DataFormatter formatter = new DataFormatter();
            			Sheet sheet = workbook.getSheetAt(0);

            			for (Cell cell : sheet.getRow(0)) {
            				requiredHeaders.put(cell.getStringCellValue(), cell.getColumnIndex());
            			}

            			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            				Row row = sheet.getRow(i);
            				valuesFromExcel = formatter.formatCellValue(row.getCell(requiredHeaders.get("DMS CODE")));
            				System.out.println(valuesFromExcel);
            				listValue.add(valuesFromExcel);
            				
            				        		} 
            		}catch (Exception e) {
            			e.printStackTrace();
            				
            		}
            		return listValue;
    }
            	
            	public static String writeToTextFile(String value,String dmsPath) {
            				String fullPath="";
            		        BufferedWriter writer = null;
            		       
            		        try {
            		           
            		            String timeLog = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
            		            File logFile = new File(timeLog+dmsPath);
            		            fullPath=logFile.getCanonicalPath();
            		            System.out.println(fullPath);
            		            writer = new BufferedWriter(new FileWriter(logFile));
            		            if(value.isEmpty())
            		            {
            		            	Writer.nullWriter();
            		            }else {
            		            writer.write(value);
            		            }
            		        } catch (Exception e) {
            		            e.printStackTrace();
            		        } finally {
            		            try {
            		                
            		                writer.close();
            		            } catch (Exception e) {
            		            }
            		        }
							return fullPath;
							
            		    }
            	
            	
            	 public static void writeXLSXFile(int row, int col,Boolean result) throws IOException {
            	        try {
            	            FileInputStream file = new FileInputStream("C:\\Users\\Evertz\\Documents\\TestWrite\\UsageReport.xlsx");

            	            XSSFWorkbook workbook = new XSSFWorkbook(file);
            	            XSSFSheet sheet = workbook.getSheetAt(0);
            	            Cell cell = null;

            	            //Retrieve the row and check for null
            	            XSSFRow sheetrow = sheet.getRow(row);
            	            if(sheetrow == null){
            	                sheetrow = sheet.createRow(row);
            	            }
            	            //Update the value of cell
            	            cell = sheetrow.getCell(col);
            	            if(cell == null){
            	                cell = sheetrow.createCell(col);
            	            }
            	            cell.setCellValue(result);

            	            file.close();

            	            FileOutputStream outFile =new FileOutputStream(new File("C:\\Users\\Evertz\\Documents\\TestWrite\\UsageReport.xlsx"));
            	            workbook.write(outFile);
            	            outFile.close();

            	        } catch (FileNotFoundException e) {
            	            e.printStackTrace();
            	        } catch (IOException e) {
            	            e.printStackTrace();
            	        }
            	    }

//            	    public static void main(String[] args) throws IOException {
//            	        // TODO Auto-generated method stub
//            	        writeXLSXFile(3, 11);
//            	        System.out.println("terminado");
//            	    }

            	}        	
            		       		
            		         	
    