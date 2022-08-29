package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;

import org.openqa.selenium.WebDriver;

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
	
	
	
	public String getDate(String date) throws ParseException {
		String date1 = "December 17, 2021 (India)";
		
		String ddate1= new String(date1).replace("(India)", "");
		
		String d1= new String(ddate1).replace(",", "");
		
		return d1;

	}
}
