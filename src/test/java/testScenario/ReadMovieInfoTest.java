package testScenario;

import org.testng.annotations.Test;


import helper.MovieHelper;
import helper.StartUpHelper;

import java.io.IOException;
import java.text.ParseException;
import java.util.Properties;

import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

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
	
	WebDriverUtils webDriverUtils  = new WebDriverUtils();

	/**
	 * This is used to initialize the chrome browser and to open imdb page
	 */
	@BeforeClass(alwaysRun = true)
	@Parameters({"enviroment","browser"})
	public void setUp(String enviroment, String browser) 
			throws Exception
	{
		
		properties = webDriverUtils.loadPropertyFile(enviroment);
				
		driver = webDriverUtils.startBrowser(browser, properties.getProperty("imdbUrl"));
	
		helper = new MovieHelper(driver);

	}
	

	/**
	 * This is used to verify release date and county of the movie
	 */
	@Test(description = "Movie Information", groups = {"Smoke"}, testName = "TC-1.1", enabled=false)
	public void imdbMovie() throws Exception, ParseException {
		try {
			softAssertions = new SoftAssertions();
			
			helper.imdbSearchMovie(properties.getProperty("movieName"));
			
			webDriverUtils.navigate(properties.getProperty("wikiUrl"));
			
			helper.wikiSearchMovie(properties.getProperty("movieName"));
			
			softAssertions.assertThat(helper.countryComparision());
			
			softAssertions.assertThat(helper.YearComparision());
			
			softAssertions.assertAll();
			
		}catch(Exception e) {
			System.out.println("Exception caught:"+e.getMessage());
		}

	}
	
	@Test(description = "Movie Information", groups = {"Smoke"})
	public void findElement() throws Exception
	{
		try {
			softAssertions = new SoftAssertions();
			String id= "search";
//			Boolean value =	helper.getElement(id);
			softAssertions.assertThat(helper.getElement(id));
			
			softAssertions.assertAll();
		
		}catch(Exception e) {
			System.out.println("Exception  "+e.getMessage());
		}
	}
	

	/**
	 * It is used for Quit the Browser
	 */
	@AfterClass(alwaysRun = true)
	public void tearDown()  throws IOException, InterruptedException
	{
		driver.quit();
	}

}
