package pages;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import launchBrowser.browserFactory;
import util.CommonUtils;

public class ImdbPage extends CommonUtils{

	
	WebDriver driver;
	
	public ImdbPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//input[@name='q']")
	WebElement searchBox;
	
	@FindBy(xpath="//div[text()='Pushpa: The Rise - Part 1']")
	WebElement movieLink;
	
	@FindBy(xpath="//*[text()='Release date']/..//div//a")
	WebElement releaseDate;
	
	@FindBy(xpath="//*[text()='Country of origin']/..//div//a")
	WebElement originCountry;
	
	
	public void searchField(String Searchinput) {
		try {
			
			searchBox.click();
			searchBox.sendKeys(Searchinput);
			
		}catch(Exception e) {
			System.out.println("Exception caught:"+e.getMessage());	
		}	
	}
	
	public void clickMovie() {
		try {
			Thread.sleep(2000);
			movieLink.click();
		}catch(Exception e){
			System.out.println("Exception caught:"+e.getMessage());
		}
		
	}
	
	public String getReleaseDate() {
		String date = null;
		try {
			date = releaseDate.getText();
			Reporter.log("------------->"+date);
			
		}catch(Exception e) {
			System.out.println("Exception caught:"+e.getMessage());
		}
		return date;
	}
	
	
	public String getCountry() {
		
			String country = originCountry.getText();
			Reporter.log("------->"+country);
			return country;
		
	}
	
}
