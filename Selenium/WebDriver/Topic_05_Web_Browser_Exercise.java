package WebDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


public class Topic_05_Web_Browser_Exercise {
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
		
		public void TC_01_Page_Url() {
			
		driver.get("http://live.techpanda.org/insex.php/");
		
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepInSeconds(3);
		
		Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/login/");
		
		// driver.findElement(By.cssSelector("div.footer a[title=\'My Account\']")).click(); css
		
		driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
		sleepInSeconds(3);
		
		Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/create/");
		
	  
		}
		
		@Test
		public void TC_02_Page_Title() {
			
		driver.get("http://live.techpanda.org/");
		
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepInSeconds(3);
		
		Assert.assertEquals(driver.getTitle(), "Customer Login");
		
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		sleepInSeconds(3);
		
		Assert.assertEquals(driver.getTitle(),"Create New Customer Account");
		
		}
		
		@Test
		public void TC_03_Page_Function() {
			
		driver.get("http://live.techpanda.org/");
		
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepInSeconds(3);
		
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		sleepInSeconds(3);
		
		Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/create/");
		
		Navigation nav = driver.navigate();
		nav.back();
		sleepInSeconds(3);
		
		Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/login/");
		
		nav.forward();
		sleepInSeconds(3);
		
		Assert.assertEquals(driver.getTitle(),"Create New Customer Account");
		
		}
		
		
		@Test
		public void TC_04_Page_Source_Code() {
			
		driver.get("http://live.techpanda.org/");
		
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		sleepInSeconds(3);		
		Assert.assertTrue(driver.getPageSource().contains("Login or Create an Account"));
		
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		sleepInSeconds(3);
		Assert.assertTrue(driver.getPageSource().contains("Create an Account"));
		
			
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
}

