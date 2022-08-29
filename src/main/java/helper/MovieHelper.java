package helper;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import util.CommonUtils;
import util.WebDriverUtils;

import pages.ImdbPage;
import pages.WikiPage;


public class MovieHelper {

	SoftAssert sofAssert;
	public WebDriver driver;
	public Properties properties;

	ImdbPage imdbPage;
	WikiPage wikiPage;
	
	CommonUtils ngHelper;
	WebDriverUtils utils;
	
	String imdbCountry=null;
	String wikiCountry=null;
	
	String imdbDate = null;
	String wikiDate = null;
				
	public MovieHelper(WebDriver driver) {
		imdbPage = new ImdbPage(driver);
		wikiPage = new WikiPage(driver);
		
	}
	
	public void imdbSearchMovie(String movieName) throws Exception {
		
		imdbPage.searchField(movieName);
		imdbPage.clickMovie();
		String year = imdbPage.getReleaseDate();
	    imdbDate = wikiPage.getDate(year);

		imdbCountry = imdbPage.getCountry();

	}
	
	public void wikiSearchMovie(String movieName) throws Exception {
		wikiPage.searchMovieName(movieName);
		wikiDate=wikiPage.getReleaseDate();

		wikiCountry = wikiPage.getCountry();
		
	}
	
	public boolean countryComparision() {
		
		boolean flag=false;
		if(imdbCountry.equalsIgnoreCase(wikiCountry)) {
			flag=true;
		}
		return flag;
	}
	
	public boolean YearComparision() {
		boolean flag =true;
		String d1[]= new String(imdbDate).split(" ");
		String d2[]= new String(wikiDate).split(" ");
		Arrays.sort(d1);
		Arrays.sort(d2);
		
		if(d1.length!=d2.length) {
			flag= false;
		}
		for(int i=0;i<d1.length;i++) {
			if(d1[i].equals(d2[i])) {
				Reporter.log("both string are equals");
			
			}else {
				Reporter.log("both string are NOT equals");
				flag =false;
			}
		}
		return flag;
	}
}
