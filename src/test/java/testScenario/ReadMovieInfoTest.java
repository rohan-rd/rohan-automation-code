package testScenario;

import org.testng.annotations.Test;


import helper.MovieHelper;
import helper.StartUpHelper;

import java.io.IOException;
import java.text.ParseException;
import java.util.Properties;

import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;
import launchBrowser.browserFactory;
import pages.ImdbPage;
import pages.WikiPage;

import util.WebDriverUtils;

public class ReadMovieInfoTest {
	SoftAssertions softAssertions;
	public WebDriver driver;
	public Properties properties;

	ImdbPage objPage;
	WikiPage wikiPage;
	MovieHelper helper;
	StartUpHelper startup;

	@BeforeClass(alwaysRun = true)
	@Parameters({"runParallel","enviroment","browser","hubURL"})
	public void setUp(String runParallel,String enviroment, String browser, String hubURL) 
			throws Exception
	{
	
		WebDriverUtils webDriverUtils  = new WebDriverUtils();
		properties = webDriverUtils.loadPropertyFile(enviroment);
				
		driver = webDriverUtils.startBrowser("Chrome", properties.getProperty("imdbUrl"));
	
		helper = new MovieHelper(driver);

	}

	@Test(description = "Movie Information", groups = {"Smoke"}, testName = "TC-1.1", priority=1)
	public void imdbMovie() throws Exception, ParseException {
		try {
			softAssertions = new SoftAssertions();
			
			helper.imdbSearchMovie(properties.getProperty("movieName"));
			
			driver.get(properties.getProperty("wikiUrl"));
			helper.wikiSearchMovie(properties.getProperty("movieName"));
			
			softAssertions.assertThat(helper.countryComparision());
			
			softAssertions.assertThat(helper.YearComparision());
			
			softAssertions.assertAll();
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}

	}
	
//	@Test(description = "WIKI", groups = {"Smoke"}, testName = "TC-1.2",priority =2, enabled=false)
//	public void wikiMovie() throws Exception {
//		try {
//			
////			driver.get(properties.getProperty("wikiUrl"));
////			helper.wikiSearchMovie(properties.getProperty("movieName"));
//			
//		}catch(Exception e){
//			System.out.println(e.getMessage());
//		}
//		System.out.println("------------------------->"+properties.getProperty("wikiUrl"));
//		//startup.navigateToPage(properties.getProperty("wikiUrl"), driver);
//		
//	}
	
	@AfterClass(alwaysRun = true)
	public void tearDown()  throws IOException, InterruptedException
	{
		driver.quit();
	}

}
