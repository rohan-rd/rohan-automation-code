package pages;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import util.CommonUtils;

public class ImdbPage extends CommonUtils{

	
	WebDriver driver;
	Duration minTime = Duration.ofSeconds(30);
	
	public ImdbPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//input[@name='q']")
	WebElement searchBox;
	
	@FindBy(xpath="//*[@id=\"react-autowhatever-1--item-0\"]/a")
	WebElement movieLink;
	
	@FindBy(xpath="//*[text()='Release date']/..//div//a")
	WebElement releaseDate;
	
	@FindBy(xpath="//*[text()='Country of origin']/..//div//a")
	WebElement originCountry;
	
	/*
	 * This method is used to enter the movie name in search field
	 */
	public void searchField(String Searchinput) {
		
			searchBox.click();
			searchBox.sendKeys(Searchinput);
				
	}
	
	/*
	 * This method is used to click on movie name
	 */
	public void clickMovie(String Searchinput) {
		
			waitTillElementIsVisible(movieLink, minTime);
			movieLink.click();
			
			
//			String movieLinkNew ="//div[contains(text(),'"+Searchinput+"')]";
//			driver.findElement(By.xpath(movieLinkNew)).click();
			
		
	}
	
	
	/*
	 * This method is used to get the Movie Release date
	 */
	public String getReleaseDate() {
		
		String date = null;
		date = releaseDate.getText();
		Reporter.log("------------->"+date);
		return date;
	}
	
	/*
	 * This method is used to get the country name
	 */
	public String getCountry() {
		
		String country = originCountry.getText();
		Reporter.log("------->"+country);
		return country;
		
	}
	
	public Boolean findElement(String idName) {
		
//		waitTillElementIsVisible(idName, minTime);
		WebElement ele =	driver.findElement(By.xpath("//*[contains(@id,'"+idName+"')]"));
		System.out.println("------------->"+ele.isDisplayed());
		if(ele.isDisplayed()) {
			return true;
		}else {
			return false;
		}
	}
	
}
