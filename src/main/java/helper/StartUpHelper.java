package helper;

import org.openqa.selenium.WebDriver;

import pages.StartupPage;
import util.CommonUtils;

public class StartUpHelper {

	StartupPage startUpPage;

	CommonUtils ngHelper;


	public StartUpHelper(WebDriver driver) {
		startUpPage = new StartupPage(driver);
		ngHelper = new CommonUtils(driver);
	}

	/**
	 * Method to Kill Driver
	 */
	public void killDriver() throws InterruptedException
	{
		startUpPage.killDriver();
	}

	
}
