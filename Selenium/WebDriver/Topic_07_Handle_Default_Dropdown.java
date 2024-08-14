package WebDriver;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;



public class Topic_07_Handle_Default_Dropdown {
	WebDriver driver;
	
	String firstname = "Louis", lastname ="Pham", emailAddress = getEmailAddress();
	String companyName = "ABC", password ="123456";
	String day = "20", month ="May" , year = "1996" ;
	@BeforeClass
	public void beforeClass() {
		
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get("https://demo.nopcommerce.com/");

	}
		@Test
	
   public void TC_01_Register() {
		driver.findElement(By.cssSelector("a.ico-register")).click();
		
		driver.findElement(By.id("FirstName")).sendKeys(firstname);

		driver.findElement(By.id("LastName")).sendKeys(lastname);
		
		/*
		Select dayDropdown = new Select(driver.findElement(By.name("DateOfBirthDay")));
		dayDropdown.selectByVisibleText("15");
		Select month =new Select(driver.findElement(By.name("DateOfBirthMonth")));
		month.selectByVisibleText("November");
		Select year =new Select(driver.findElement(By.name("DateOfBirthYear")));
		year.selectByVisibleText("1950");
		*/
		
		// Day dropdown
		
		Select dayDropdown = new Select(driver.findElement(By.name("DateOfBirthDay")));
		
		// Chọn ngày
		dayDropdown.selectByVisibleText(day);
		
		// Veryfi Dropdown là single (ko phải multiple )
		Assert.assertFalse(dayDropdown.isMultiple());
		
		// Veryfi số lượng trong dropdown này là 32 item
		
		/*List<WebElement> dayOption = dayDropdown.getOptions();
		Assert.assertEquals(dayOption.size(), 32); */
		Assert.assertEquals(dayDropdown.getOptions().size(), 32);
		
		// int - string

		new Select(driver.findElement(By.name("DateOfBirthMonth"))).selectByVisibleText(month);
		new Select(driver.findElement(By.name("DateOfBirthYear"))).selectByVisibleText(year);
			
		
		
		
		driver.findElement(By.id("Email")).sendKeys(emailAddress);
		driver.findElement(By.id("Company")).sendKeys(companyName);
		driver.findElement(By.id("Password")).sendKeys(password);
		driver.findElement(By.id("ConfirmPassword")).sendKeys(password);
			
		driver.findElement(By.cssSelector("button#register-button")).click();
		sleepInSeconds(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(),"Your registration completed");
		
   }
	@Test
	public void TC_02_Login() {
		
		//Login
		driver.findElement(By.cssSelector("a.ico-login")).click();
		driver.findElement(By.cssSelector("input#Email")).sendKeys(emailAddress);
		driver.findElement(By.cssSelector("input#Password")).sendKeys(password);
		driver.findElement(By.cssSelector("button.login-button")).click();
		sleepInSeconds(2);
		
		//Verify
		driver.findElement(By.className("ico-account")).click();
		sleepInSeconds(3);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("input#FirstName")).getAttribute("value"),firstname);
		Assert.assertEquals(driver.findElement(By.cssSelector("input#LastName")).getAttribute("value"),lastname);			
		new Select(driver.findElement(By.name("DateOfBirthDay"))).getFirstSelectedOption().getText();
		
		Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthDay"))).getFirstSelectedOption().getText(),day);
		Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthMonth"))).getFirstSelectedOption().getText(),month);
		Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthYear"))).getFirstSelectedOption().getText(),year);
		
		Assert.assertEquals( driver.findElement(By.cssSelector("input#Email")).getAttribute("value"),emailAddress);
		Assert.assertEquals(driver.findElement(By.cssSelector("input#Company")).getAttribute("value"),companyName);

		
	
	}


	public void TC_03_Password_less_6_characters() {
		
		
}
	public void TC_04_Email_Password() {
		

	}
	

public void afterClass()
   {
	   driver.quit();
   }


public void sleepInSeconds(long timeInSecond) {
	   try
	   {
		   Thread.sleep(timeInSecond*1000);
	   } 
	   catch (InterruptedException e)
	   {
		   throw new RuntimeException(e);
	    }
}

public String getEmailAddress() {
	Random rand = new Random();
	return "automation" + rand.nextInt(99999) + "@gmail.net";
}
}
	

