package com.prm.qa.testcases;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.json.JSONException;
import org.json.JSONObject;
import com.prm.qa.pages.LoginPage;
import com.prm.qa.util.TestUtil;
import com.prm.qa.util.WebEventListener;

public class GTWithoutNotesTest {
	
	public static WebDriver driver;
	public static Properties prop;
	
	public GTWithoutNotesTest(){
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+ "/src/main/java/com/prm"
					+ "/qa/config/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@SuppressWarnings({ "deprecation", "unchecked" })
	public static void initialization(){
		String browserName = prop.getProperty("browser");
	   
	    
	
		 // specify the path of the chromdriver binary that you have downloaded (see point 2)
		if(browserName.equals("chrome")){
			System.setProperty("webdriver.chrome.driver", "C://Users//Evertz//Documents//104chromedriver_win32//chromedriver.exe");	
			driver = new ChromeDriver(); 
		}
		else if(browserName.equals("FF")){
			System.setProperty("webdriver.gecko.driver", "C://Users//Evertz//Downloads//gekodriver//gekodriver.exe");	
			driver = new FirefoxDriver(); 
		}
		
	
		driver.get(prop.getProperty("url"));

		driver.findElement(By.xpath("//*[@id='email']")).sendKeys("Pushpanjali");
		driver.findElement(By.xpath("//*[@id='password']")).sendKeys("Password0922");
		driver.findElement(By.xpath("//*[text()='Login']")).click();
	
				
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0, 250)", "");
			String docName=driver.findElement(By.xpath( "//*[text()='FY2021 MSFL.pdf']")).getText();
			List<WebElement> reviewButton = (List<WebElement>) driver.findElement(By.xpath("//*[text()=' Review now']"));
			
			Iterator<WebElement> itReview = reviewButton.iterator();
			
			while (itReview.hasNext()) {
			    WebElement review  = itReview.next();
			    
			    if(docName.equals("FY2021 MSFL.pdf")) {
			    	new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable((By) review));
					
			    	System.out.println(driver.getTitle());
			        break;
			    }

		
		
//		String scriptToExecute = "var performance = window.performance || window.mozPerformance || window.msPerformance || window.webkitPerformance || {}; getElementInfo('payload')|| {}; return JSON.stringify(network);;";
//		//String scriptToExecute = "var performance = window.performance || window.mozPerformance || window.msPerformance || window.webkitPerformance || {}; var network = performance.getRequest()|| {}; return JSON.stringify(network);;";
//		//String scriptToExecute = "\"return window.performance.getEntries();\"";
//        String netData = ((JavascriptExecutor)driver).executeScript(scriptToExecute).toString();
//        System.out.println(netData);
//	
	}
	}
}
		
		
		
		
		
	
	
	
	

