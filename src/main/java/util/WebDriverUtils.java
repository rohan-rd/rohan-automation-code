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
	public ThreadLocal<RemoteWebDriver> threadDriver = null;
	static DesiredCapabilities capabilities;
	public String runParallel = "false";
	public boolean setCookie;

	String driverPath = File.separator + "src" + File.separator + "main" + File.separator + "resources"
			+ File.separator + "driver" + File.separator;

	public String getRunParallel()
	{
		return runParallel;
	}

	public void setRunParallel(String runParallel)
	{
		this.runParallel = runParallel;
	}	

	public String getDriverPath(){
		return driverPath;
	}

	public void setDriver(ThreadLocal <RemoteWebDriver> threadDriver){
		this.threadDriver = threadDriver;
	}

	public void setDriver(WebDriver driver){
		this.driver = driver;
	}

	/**
	 * Method to Get the Driver
	 */
	public WebDriver getDriver()
	{
		if(threadDriver == null)
		{
			return driver;
		}
		else
		{
			return threadDriver.get();
		}			
	}

	/**
	 * Method to Set Driver Path based on the Browser Name.
	 */
	public void setDriverPath(String browserName) {
		switch (browserName) {
		case "Chrome" :
			if(System.getProperty("os.name").toLowerCase().contains("mac")) {
				this.driverPath = driverPath +"mac"+ File.separator+ "chromedriver";		
			} else if(System.getProperty("os.name").toLowerCase().contains("windows")){
				this.driverPath = driverPath +"window"+ File.separator+"chromedriver.exe";
			}else{
				this.driverPath = driverPath +"ubuntu"+ File.separator+ "chromedriver";
			}
			break;
		case "Firefox" :
			if(System.getProperty("os.name").toLowerCase().contains("mac")) {
				this.driverPath = driverPath + "geckodriver";		
			} else {
				this.driverPath = driverPath + "geckodriver.exe";
			}
			break;
		case "IE" :
			if(System.getProperty("os.name").toLowerCase().contains("mac")) {
				this.driverPath = driverPath + "IEDriverServer";		
			} else {
				this.driverPath = driverPath + "IEDriverServer.exe";
			}
			break;
		case "Edge" :
			if(System.getProperty("os.name").toLowerCase().contains("mac")) {
				this.driverPath = driverPath + "msedgedriver";		
			} else {
				this.driverPath = driverPath + "msedgedriver.exe";
			}
			break;
		}
	}	

	/**
	 * Method to Initialize Browser and corresponding Driver.
	 */
	public void initializeDriver(String browserName, String hubURL,String mode){
		if(!runParallel.equalsIgnoreCase("true") && mode.equalsIgnoreCase("normal")){
			switch (browserName){
			case "chrome" :
				setDriverPath("Chrome");
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+ getDriverPath());
				setDriver(new ChromeDriver());
				break;
			case "Firefox" :
				setDriverPath("Firefox");
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+ getDriverPath());
				setDriver(new FirefoxDriver());
				break;
			case "IE" :
				setDriverPath("IE");
				System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+ getDriverPath());
				setDriver(new InternetExplorerDriver());
				break;
			case "Edge" :
				setDriverPath("Edge");
				System.setProperty("webdriver.edge.driver", System.getProperty("user.dir")+ getDriverPath());
				setDriver(new EdgeDriver());
				break;
			case "Safari" :
				setDriver(new SafariDriver());
				break;
			default:
				System.out.println("Initializing Chrome Browser by Default");
				setDriverPath("Chrome");
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+ getDriverPath());
				setDriver(new ChromeDriver());
				break;	
			}
		}
		else if(!runParallel.equalsIgnoreCase("true") && mode.equalsIgnoreCase("headless")) {
			switch (browserName){
			case "chrome" :
				setDriverPath("Chrome");
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+ getDriverPath());
				setDriver(new ChromeDriver());
				break;
			case "Firefox" :
				setDriverPath("Firefox");
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+ getDriverPath());
				setDriver(new FirefoxDriver());
				break;
			case "IE" :
				setDriverPath("IE");
				System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+ getDriverPath());
				setDriver(new InternetExplorerDriver());
				break;
			case "Edge" :
				setDriverPath("Edge");
				System.setProperty("webdriver.edge.driver", System.getProperty("user.dir")+ getDriverPath());
				setDriver(new EdgeDriver());
				break;
			case "Safari" :
				setDriver(new SafariDriver());
				break;
			default:
				setDriverPath("Chrome");
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+ getDriverPath());
				ChromeOptions option = new ChromeOptions();
				option.addArguments("--window-size=1920,1080");
				option.addArguments("--start-maximized");
				option.addArguments("--headless");
				
				//option.addArguments("disable-infobars"); // disabling infobars
				option.addArguments("--disable-extensions"); // disabling extensions
				option.addArguments("--disable-gpu"); // applicable to windows os only
				option.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
				option.addArguments("--no-sandbox"); // Bypass OS security model
				setDriver(new ChromeDriver(option));
				break;	
			}
		}
	}

	/**
	 * Method to Load Properties FIle
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

	/**
	 * Method to generate Random Number
	 */
	public static long getRandomNumber()
	{
		return Instant.now().toEpochMilli();
	}

	/**
	 * Method to Wait till Page Load Completely.
	 */
	public void waitForPageToCompleteState() throws InterruptedException 
	{
		int counter = 0;
		int maxNoOfRetries = 10;
		while (maxNoOfRetries > 0 && (counter != maxNoOfRetries)) {
			Thread.sleep(2000);
			try {
				final JavascriptExecutor js = (JavascriptExecutor) driver;
				if (js.executeScript("return document.readyState").toString().equals("complete")) {
					Thread.sleep(2000);
					break;
				}
			} catch (Exception e) {

			}
			counter++;
		}
	}
	
	public WebDriver startBrowser(String browserName,String url) {
		WebDriverManager.chromedriver().setup();
		if(browserName.equals("Chrome")) {
			driver = new ChromeDriver();
		}else if(browserName.equals("firefox")) {
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
