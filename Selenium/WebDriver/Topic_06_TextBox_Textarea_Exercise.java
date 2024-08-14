package WebDriver;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;



public class Topic_06_TextBox_Textarea_Exercise {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		

	}
		@Test
	
   public void TC_01_Empty_Email_Password() {
			
	 driver.get("http://live.techpanda.org/");
	 driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	 driver.findElement(By.cssSelector("button#send2.button")).click();
	 
	 Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-required-entry-email")).getText(),"This is a required field.");
	 Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-required-entry-pass")).getText(),"This is a required field.");
	 
			
   }
	
	public void TC_02_Invalid_Email() {
		
			 driver.get("http://live.techpanda.org/");
			 driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
			 
		     driver.findElement(By.cssSelector("input#email")).sendKeys("123434234@12312.123");
			 driver.findElement(By.cssSelector("input#password")).sendKeys("123456");
			 
			 driver.findElement(By.cssSelector("button#send2.button")).click();
			 
			 Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-validate-email-email")).getText(),"Please enter a valid email address. For example johndoe@domain.com.");
					
   }
	
	public void TC_03_Password_less_6_characters() {
		
		 driver.get("http://live.techpanda.org/");
		 driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		 
		 driver.findElement(By.cssSelector("input#email")).sendKeys("automation@gmail.com");
		 driver.findElement(By.cssSelector("input#password")).sendKeys("123");
		 
		 driver.findElement(By.cssSelector("button#send2.button")).click();
		 
		 Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-validate-email")).getText(),"Please enter 6 or more characters without leading or trailing spaces.");
				
}
	public void TC_04_Email_Password() {
		
		 driver.get("http://live.techpanda.org/");
		 driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		 
		 driver.findElement(By.cssSelector("input#email")).sendKeys("automation@gmail.com");
		 driver.findElement(By.cssSelector("input#password")).sendKeys("123123");
		 
		 driver.findElement(By.cssSelector("button#send2.button")).click();
		 Assert.assertEquals(driver.findElement(By.cssSelector("li.error-msg")).getText(),"Invalid login or password.");	

	 	 driver.findElement(By.cssSelector("input#email")).clear();
	 	 driver.findElement(By.cssSelector("input#password")).clear();
	 
		 driver.findElement(By.cssSelector("input#email")).sendKeys("automation@gmail.com");
		 driver.findElement(By.cssSelector("input#password")).sendKeys("123456789");
	}
	
	public void TC_05_Success_Login () {
		
		 driver.get("http://live.techpanda.org/");
		 driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		 
		 // Đăng kí trước 1 tài khoản
		 driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		 sleepInSeconds(3);
		 
		 String firstName ="automation",lastName ="FC", emailAddress = getEmailAddress() , passWord = "123456789";
		 String fullName = firstName + " " + lastName;
		 
		 driver.findElement(By.cssSelector("input#firstname")).sendKeys("firstName");
		 driver.findElement(By.cssSelector("input#lastname")).sendKeys("lastName");
		 driver.findElement(By.cssSelector("input#email_address")).sendKeys("emailAddress");
		 driver.findElement(By.cssSelector("input#password")).sendKeys("password");
		 driver.findElement(By.cssSelector("input#confirmation")).sendKeys("password");
		 driver.findElement(By.cssSelector("button[title='Register']")).click();
		 
		 Assert.assertEquals(driver.findElement(By.cssSelector("li.succes-msg span")).getText(),"Thank you for registering with Main website Store");
		 Assert.assertEquals(driver.findElement(By.cssSelector("div.welcome msg strong")).getText(),"Hello, " + fullName + "!");
		
		 String contactinfo = driver.findElement(By.xpath("//h3[text()= 'Contact Information']/parent::div/following-sibling::div/p")).getText();
		 Assert.assertTrue(contactinfo.contains(fullName));
		 Assert.assertTrue(contactinfo.contains(emailAddress));
		 
		 // Logout
		 
		 driver.findElement(By.cssSelector("a.skip-account")).click();
		 sleepInSeconds(2);
		 driver.findElement(By.cssSelector("a[title='Log-out']"));
		 sleepInSeconds(5);
		 
		 // Login
		 
		 driver.findElement(By.cssSelector("//div[@class='footer']//a[title='My account']")).click();
		 sleepInSeconds(2);
		 
		 driver.findElement(By.cssSelector("input#email")).sendKeys("emailAddress");
		 driver.findElement(By.cssSelector("input#pass")).sendKeys("password");
		 driver.findElement(By.cssSelector("button#send2']")).click();
		 
		 Assert.assertEquals(driver.findElement(By.cssSelector("div.welcome msg strong")).getText(),"Hello, " + fullName + "!");
			
		 contactinfo = driver.findElement(By.xpath("//h3[text()= 'Contact Information']/parent::div/following-sibling::div/p")).getText();
		 Assert.assertTrue(contactinfo.contains(fullName));
		 Assert.assertTrue(contactinfo.contains(emailAddress));
		 
		 // Verify Account
		 
		 driver.findElement(By.xpath("//a[text()= 'Account Information')")).click();
		 sleepInSeconds(2);
		 Assert.assertEquals(driver.findElement(By.cssSelector("input#firstname")).getAttribute("value"),firstName);
		 Assert.assertEquals(driver.findElement(By.cssSelector("input#lastname")).getAttribute("value"),firstName);
		 Assert.assertEquals(driver.findElement(By.cssSelector("input#email")).getAttribute("value"),emailAddress);
		 
			
		 
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
	

