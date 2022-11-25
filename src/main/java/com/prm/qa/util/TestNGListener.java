package com.prm.qa.util;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.IInvokedMethodListener;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Listeners;

public class TestNGListener implements ITestListener, ISuiteListener, IInvokedMethodListener {

    protected Logger logger = LogManager.getLogger(this.getClass());

    @Override
    public void onTestStart(ITestResult iTestResult) {

    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {

        logger.info("Test successfully executed.");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {

        logger.error("Test Failed with message: " + iTestResult.getThrowable().getMessage());
    }
}

@Listeners(TestNGListener.class)
public abstract class AbstractTest {
}