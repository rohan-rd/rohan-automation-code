package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Arrays;
import java.util.Calendar;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class CommonUtils {
	
	public WebDriver driver;
	
	public CommonUtils(WebDriver driver)
	{
		this.driver = driver;		
	}
	

	/**
	 * Method to Maximize Browser WIndow
	 */
	public void maximizeWindow(){
		driver.manage().window().maximize();
	}
	
	/**
	 * Method to wait till the Element is Visible
	 */
	public void waitTillElementIsVisible(WebElement element, Duration time)
	{
		
		WebDriverWait wait = new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.or(ExpectedConditions.visibilityOf(element)));
		
		
	}

	
	/**
	 * Method to Navigate to URL
	 */
	public void navigateTo(String url){
		System.out.println("------------------------->"+url);
		driver.get(url);
	}
	


	public String getmonthNumber(String date) throws ParseException {
		String month = date;
		SimpleDateFormat inputFormat = new SimpleDateFormat("MMM");
		Calendar cal = Calendar.getInstance();
		cal.setTime(inputFormat.parse(month));
		SimpleDateFormat outputFormat = new SimpleDateFormat("M"); // 01-12
	//	System.out.println(outputFormat.format(cal.getTime()));
		return outputFormat.format(cal.getTime());

	}
	
	
	/**
	 * Method to get date without India text and comma ( "December 17, 2021 (India)")
	 */
	public String getDate(String date) throws ParseException {
	//	String date1 = "December 17, 2021 (India)";
		
		String ddate1= new String(date).replace("(India)", "");
		
		String d1= new String(ddate1).replace(",", "");
		
		return d1;

	}
}
