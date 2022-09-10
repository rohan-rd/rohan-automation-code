package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import util.CommonUtils;

public class WikiPage extends CommonUtils{
	WebDriver driver;

	public WikiPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "searchInput")
	WebElement searchBox;

	@FindBy(xpath = "//*[@type='submit']")
	WebElement searchSubmit;

	@FindBy(xpath = "//*[text()='Release date']/../../td/div/ul/li")
	WebElement releaseDate;

	@FindBy(xpath = "//*[text()='Country']/../td")
	WebElement originCountry;

	/*
	 * This method is used to search for movie name
	 */
	public void searchMovieName(String movieName) {
		searchBox.click();
		searchBox.sendKeys(movieName);
		searchSubmit.click();
	}

	/*
	 * This method is used to get the Release date of Movie
	 */
	public String getReleaseDate() {
		
		String date = null;
		date = releaseDate.getText();
		Reporter.log("wiki------------------->" + date);
		return date;

	}

	/*
	 * This method is used to get the Country name of movie
	 */
	public String getCountry() {
		
		String country = originCountry.getText();
		Reporter.log("wiki---------->" + country);
		return country;
	}

}
