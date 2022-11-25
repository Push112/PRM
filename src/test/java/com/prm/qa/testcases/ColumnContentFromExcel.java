package com.prm.qa.testcases;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ColumnContentFromExcel {
	
	@Test(dataProvider = "getExcelData")
    public void testSheet(String firstName, String lastName, String personalEmail) {
    System.out.println(firstName+"  "+lastName+" "+personalEmail);
}
@DataProvider
public Object[][] getExcelData(){
    String excelSheetPath = System.getProperty("user.dir")+"/data.xls";
    String sheetName = "Sheet1";
    return getExcelData(excelSheetPath, sheetName);
}
public Object[][] getExcelData(String excelSheetPath, String sheetName) {
    Object[][] arrayExcelData = null;
    try (
            FileInputStream fileStream = new FileInputStream(excelSheetPath)
    ) {
        XSSFWorkbook workbook = new XSSFWorkbook(fileStream);
        XSSFSheet sheet = workbook.getSheet(sheetName);
        Row row = sheet.getRow(0);
        int lastRowIndex = sheet.getLastRowNum() + 1;
        System.out.println("Last row index :" + lastRowIndex);
        int totalNoOfCols = row.getLastCellNum() - 1;
        System.out.println("Total columns :" + totalNoOfCols);
        arrayExcelData = new Object[totalNoOfCols][lastRowIndex];
        DataFormatter df = new DataFormatter();
        for (int i = 1; i <= totalNoOfCols ; i++) {
            for (int j = 0; j < lastRowIndex; j++) {
                row = sheet.getRow(j);
                Cell c = row.getCell(i);
                String cellData = df.formatCellValue(c);
                System.out.println(cellData);
                arrayExcelData[i-1][j] = cellData;
            }
            System.out.println("-----------");
        }
    } catch (Exception e) {
        e.printStackTrace();
        System.out.println(e.getMessage());
    }
    return arrayExcelData;
}
}