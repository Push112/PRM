package com.prm.qa.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Check {
	public static void main(String[] args) throws IOException
    {
		String url = "https://dev.xlrt.ai/fs/review/26f43472c08349748e84c01d7b179058";
//		Pattern regex = Pattern.compile("^.+/.*(?:\\D|^)(\\d+)"); 
//		Matcher tagmatch = regex.matcher( url );
//		//System.out.println(tagmatch.group(1));
//		tagmatch.find();
		System.out.println(url.substring(url.lastIndexOf('/')+1).split("-")[0]);
		//System.out.println(tagmatch.group(1));
    }
    }