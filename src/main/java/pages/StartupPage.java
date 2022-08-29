package pages;

import org.openqa.selenium.WebDriver;

public class StartupPage {

	public WebDriver driver;

	public StartupPage(WebDriver driver){
		this.driver = driver;		
	}

	/**
	 * Method to Kill Driver Instance
	 */
	public void killDriver(){
		if(driver!=null){
			driver.quit();
		}
	}
	
	/**
	 * Method to CLose Driver Instance
	 */
	public void closeDriver(){
		if(driver!=null){
			driver.close();
		}
	}
}
