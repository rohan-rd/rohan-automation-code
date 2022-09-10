package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Instant;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import launchBrowser.browserFactory;

public class WebDriverUtils {

	public WebDriver driver;


	/*
	 * Method to load the Property File
	 */
	public synchronized Properties loadPropertyFile(String fileName) throws IOException{
		StackTraceElement packagePath = null;
		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		for(int i=0;i<stackTrace.length;i++){
			if(stackTrace[i].toString().contains("")){
				packagePath = stackTrace[i];
				break;
			}
		}
		String [] arryPackageName = packagePath.toString().split("\\.");
		String packageName = arryPackageName[3];
		fileName = System.getProperty("user.dir") + File.separator+"src"+File.separator+"main"+File.separator+"resources"
				+File.separator+"properties"+File.separator+fileName;
		File file = new File(fileName);
		FileInputStream fileInput = new FileInputStream(file);
		Properties properties = new Properties();
		properties.load(fileInput);
		return properties;		
	}


	/*
	 * Method to start the Browser
	 */
	public WebDriver startBrowser(String browserName,String url) {
//		WebDriverManager.chromedriver().setup();
		if(browserName.equals("Chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}else if(browserName.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver =new FirefoxDriver();
		}
		driver.manage().window().maximize();
		driver.get(url);
		
		return driver;
	}
	

	/**
	 * Method to return page
	 */
	public void navigate(String url) throws Exception
	{
		driver.get(url);
	}	

}
