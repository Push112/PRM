package com.prm.qa.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.format.CellFormat;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.w3c.dom.css.Counter;

public class CSVUtils {

	
	 private static final char DEFAULT_SEPARATOR = ',';
	  
	  public static void writeLine(Writer w, List<String> values) throws
	  IOException { writeLine(w, values, DEFAULT_SEPARATOR, ' '); }
	  
	  public static void writeLine(Writer w, List<String> values, char separators)
	  throws IOException { writeLine(w, values, separators, ' '); }
	  
	  
	  private static String followCVSformat(String value) {
	  
	  String result = value; if (result.contains("\"")) { result =
	  result.replace("\"", "\"\""); } return result;
	  
	  }
	  
	  public static void writeLine(Writer w, List<String> values, char separators,
	  char customQuote) throws IOException {
	  
	  boolean first = true;
	  
	  
	  
	  if (separators == ' ') { separators = DEFAULT_SEPARATOR; }
	  
	  StringBuilder sb = new StringBuilder(); for (String value : values) { if
	  (!first) { sb.append(separators); } if (customQuote == ' ') {
	  sb.append(followCVSformat(value)); } else {
	  sb.append(customQuote).append(followCVSformat(value)).append(customQuote); }
	  
	  first = false; } sb.append("\n"); w.append(sb.toString());
	  
	  
	  } 
//	  public static void main(String[] args) throws Exception {
//	  
//	  String csvFile = "C:\\Users\\Evertz\\Documents\\TestWrite\\abc.csv";
//	  FileWriter writer = new FileWriter(csvFile);
//	  
//	  CSVUtils.writeLine(writer, Arrays.asList("a", "b", "c", "test"));
//	  
//	  //custom separator + quote CSVUtils.writeLine(writer, Arrays.asList("aaa",
//	  "bb,b", "cc,c"), ',', '"');
//	  
//	  // //custom separator + quote // CSVUtils.writeLine(writer,
//	  Arrays.asList("aaa", "bbb", "cc,c"), '|', '\''); // // //double-quotes //
//	  CSVUtils.writeLine(writer, Arrays.asList("aaa", "bbb", "cc\"c"));
//	  
//	 
//	  writer.flush(); writer.close();
	  
	  public void addColumn(String path,String fileName) throws IOException{
		    BufferedReader br=null;
		    BufferedWriter bw=null;
		    final String lineSep=System.getProperty("line.separator");

		    try {
		        File file = new File(path, fileName);
		        File file2 = new File(path, fileName+".1");//so the
		                    //names don't conflict or just use different folders

		        br = new BufferedReader(new InputStreamReader(new FileInputStream(file))) ;
		        bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file2)));
		        String line = null;
		                    int i=0;
		        for ( line = br.readLine(); line != null; line = br.readLine(),i++)
		        {               

		            //String addedColumn = String.valueOf(data.get(i));
		           // bw.write(line+addedColumn+lineSep);
		    }

		    }catch(Exception e){
		        System.out.println(e);
		    }finally  {
		        if(br!=null)
		            br.close();
		        if(bw!=null)
		            bw.close();
		    }

		}
	  
	  }
	 
