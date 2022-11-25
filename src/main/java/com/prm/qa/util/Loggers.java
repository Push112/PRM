package com.prm.qa.util;

import org.apache.log4j.Logger;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.relevantcodes.extentreports.ExtentReports;

public class Loggers { 
	  private static final Loggers instance = new Loggers();
	  private static ExtentTest test;
	  private static ExtentReports extent;
	  private static Logger LOG;
	  private static final String REPORT_LOCATION = "test-output/reports.extent.html";

	  /**
	   * Returns an instance of {@link ExtentReports} object. If it doesn't exist creates a new instance and returns it
	   */
	  public static ExtentReports getLogger() {
	    if ( extent == null ) {
	      createInstance();
	    }
	    return extent;
	  }

	  /**
	   * Create ExtentReport and attaches htmlReporter to it
	   */
	  public static void createInstance() {
	    ExtentHtmlReporter htmlReporter = getHTMLReporter();
	    extent = new ExtentReports("testPRM.html" );
	    ((Object) extent).attachReporter( htmlReporter );
	  }

	  /**
	   * This method creates, configures and returns an instance of ExtentHtmlReporter
	   *
	   */
	  public static ExtentHtmlReporter getHTMLReporter() {
	    ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter( "testPRM.html" );
	     return htmlReporter;
	  }

	  /**
	   * This method logs a message with the INFO level for both instances of TestNG Logger and ExtentTest
	   */
	  public void info( String message ) {
	    LOG.info( message );
	    test.log( Status.INFO, message );
	  }
	  
	  public void onTestFailure( ITestResult testResult ) {
	      test.fail( testResult.getThrowable() );
	 }

}
