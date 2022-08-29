package launchBrowser;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class browserFactory {

	 static WebDriver driver;
	
	public static WebDriver startBrowser(String browserName,String url) {
	
		if(browserName.equals("Chrome")) {
			driver = new ChromeDriver();
		}else if(browserName.equals("firefox")) {
			driver =new FirefoxDriver();
		}
		driver.manage().window().maximize();
		driver.get(url);
		
		return driver;
	}
}
