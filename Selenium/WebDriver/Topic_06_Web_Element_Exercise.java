package WebDriver;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;



public class Topic_06_Web_Element_Exercise {
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
	
   public void TC_01_Displayed() {
	
			driver.get("https://automationfc.github.io/basic-form/index.html");
	
			// Nếu mong đợi 1 element hiển thị thì verifyTrue
			// Nếu mong đợi 1 element hiển thị thì verifyFalse
			
			if (driver.findElement(By.cssSelector("input#mail")).isDisplayed()) { 
			driver.findElement(By.cssSelector("input#mail")).sendKeys("Automation testing");
			System.out.println("Email textbox is displayed");
			}
			else
			{
				System.out.println("Email textbox is not displayed");	
			}
			
			if (driver.findElement(By.cssSelector("input#under_18")).isDisplayed()) {
				driver.findElement(By.cssSelector("input#under_18")).click();
				System.out.println("Under 18 radio is displayed");
			}
			else
			{
				System.out.println("Under 18 radio is not displayed");	
			}
			
			if(driver.findElement(By.cssSelector("textarea#edu")).isDisplayed()) {
				driver.findElement(By.cssSelector("textarea#edu")).sendKeys("Automation testing");
				System.out.println("Education text is displayed");
			}
			else
			{
				System.out.println("Education text is not displayed");	
			}
			
			if (driver.findElement(By.cssSelector("//h5[text()='Name: User5']")).isDisplayed()) {
				System.out.println("Name: User5 is displayed");
			}
			else
			{
				System.out.println("Name: User5 not is displayed");	
			}
			
			Assert.assertTrue(driver.findElement(By.cssSelector("input#mail")).isDisplayed());
			Assert.assertTrue(driver.findElement(By.cssSelector("input#under_18")).isDisplayed());
			Assert.assertTrue(driver.findElement(By.cssSelector("textarea#edu")).isDisplayed());
			Assert.assertFalse(driver.findElement(By.cssSelector("//h5[text()='Name: User5']")).isDisplayed());
   }
		@Test
		
		public void TC_02_Enabled () {
			driver.get("https://automationfc.github.io/basic-form/index.html");
			
			if(driver.findElement(By.cssSelector("input#mail")).isEnabled()) {
				System.out.println("Mail textbox is enabled");
			}
			else
			{
				System.out.println("Mail textbox disabled");
			}
			
			if(driver.findElement(By.cssSelector("input#disable_password")).isEnabled()) {
				System.out.println("Password textbox is enabled");
			}
			else
			{
				System.out.println("Password textbox disabled");
			}
			
			if(driver.findElement(By.cssSelector("input#radio-disabled")).isEnabled()) {
				System.out.println("radio check is enabled");
			}
			else
			{
				System.out.println("radio check disabled");
			}
		}
		@Test
		public void TC_03_Selected () {
			driver.get("https://automationfc.github.io/basic-form/index.html");
			
			driver.findElement(By.cssSelector("input#under_18")).click();
			driver.findElement(By.cssSelector("input#java")).click();
			
			Assert.assertTrue(driver.findElement(By.cssSelector("input#under_18")).isSelected());
			Assert.assertTrue(driver.findElement(By.cssSelector("input#java")).isSelected());
			driver.findElement(By.cssSelector("input#java")).click();
			
			Assert.assertFalse(driver.findElement(By.cssSelector("input#java")).isSelected());
		}
			
		
		@Test
		
		public void TC_04_MailChimp () {
			
			driver.get("https://login.mailchimp.com/signup/");
			
			driver.findElement(By.cssSelector("input#email")).sendKeys("hiennt.tgnguyen@gmail.com");
			
			// case 1
			
			driver.findElement(By.cssSelector("input#new_password")).sendKeys("123456");
			
			Assert.assertTrue(driver.findElement(By.cssSelector("lowercase-char.not-completed")).isDisplayed());
			Assert.assertTrue(driver.findElement(By.cssSelector("uppercase-char.not-completed")).isDisplayed());
			Assert.assertTrue(driver.findElement(By.cssSelector("special-char.not-completed")).isDisplayed());
			Assert.assertTrue(driver.findElement(By.cssSelector("8-char.not-completed")).isDisplayed());
			Assert.assertTrue(driver.findElement(By.cssSelector("number-char.completed")).isDisplayed());
			
			// case 2
			
			driver.findElement(By.cssSelector("input#new_password")).clear();
			driver.findElement(By.cssSelector("input#new_password")).sendKeys("asdfg");
			
			Assert.assertTrue(driver.findElement(By.cssSelector("lowercase-char.completed")).isDisplayed());
			Assert.assertTrue(driver.findElement(By.cssSelector("uppercase-char.not-completed")).isDisplayed());
			Assert.assertTrue(driver.findElement(By.cssSelector("special-char.not-completed")).isDisplayed());
			Assert.assertTrue(driver.findElement(By.cssSelector("8-char.not-completed")).isDisplayed());
			Assert.assertTrue(driver.findElement(By.cssSelector("number-char.not-completed")).isDisplayed());
			
			//case 3
			
			driver.findElement(By.cssSelector("input#new_password")).clear();
			driver.findElement(By.cssSelector("input#new_password")).sendKeys("ABCD");
						
			Assert.assertTrue(driver.findElement(By.cssSelector("lowercase-char.not-completed")).isDisplayed());
			Assert.assertTrue(driver.findElement(By.cssSelector("uppercase-char.completed")).isDisplayed());
			Assert.assertTrue(driver.findElement(By.cssSelector("special-char.not-completed")).isDisplayed());
			Assert.assertTrue(driver.findElement(By.cssSelector("8-char.not-completed")).isDisplayed());
			Assert.assertTrue(driver.findElement(By.cssSelector("number-char.not-completed")).isDisplayed());
			
			
			//case 4
			
			driver.findElement(By.cssSelector("input#new_password")).clear();
			driver.findElement(By.cssSelector("input#new_password")).sendKeys("@#$!@");
						
			Assert.assertTrue(driver.findElement(By.cssSelector("lowercase-char.not-completed")).isDisplayed());
			Assert.assertTrue(driver.findElement(By.cssSelector("uppercase-char.not-completed")).isDisplayed());
			Assert.assertTrue(driver.findElement(By.cssSelector("special-char.completed")).isDisplayed());
			Assert.assertTrue(driver.findElement(By.cssSelector("8-char.not-completed")).isDisplayed());
			Assert.assertTrue(driver.findElement(By.cssSelector("number-char.not-completed")).isDisplayed());
			
			
			//case 5 - Max lengh
			
			driver.findElement(By.cssSelector("input#new_password")).clear();
			driver.findElement(By.cssSelector("input#new_password")).sendKeys("123456789");
						
			Assert.assertTrue(driver.findElement(By.cssSelector("lowercase-char.not-completed")).isDisplayed());
			Assert.assertTrue(driver.findElement(By.cssSelector("uppercase-char.not-completed")).isDisplayed());
			Assert.assertTrue(driver.findElement(By.cssSelector("special-char.not-completed")).isDisplayed());
			Assert.assertTrue(driver.findElement(By.cssSelector("8-char.completed")).isDisplayed());
			Assert.assertTrue(driver.findElement(By.cssSelector("number-char.completed")).isDisplayed());
			
			//case 6 - Valid
			
			driver.findElement(By.cssSelector("input#new_password")).clear();
			driver.findElement(By.cssSelector("input#new_password")).sendKeys("Abcd#$%1");
						
			Assert.assertTrue(driver.findElement(By.cssSelector("lowercase-char.completed")).isDisplayed());
			Assert.assertTrue(driver.findElement(By.cssSelector("uppercase-char.completed")).isDisplayed());
			Assert.assertTrue(driver.findElement(By.cssSelector("special-char.completed")).isDisplayed());
			Assert.assertTrue(driver.findElement(By.cssSelector("8-char.completed")).isDisplayed());
			Assert.assertTrue(driver.findElement(By.cssSelector("number-char.completed")).isDisplayed());
			
			//case 7 - Empty
			
			driver.findElement(By.cssSelector("input#new_password")).clear();
			driver.findElement(By.cssSelector("button#create-account-enabled")).click();
						
			Assert.assertTrue(driver.findElement(By.cssSelector("lowercase-char.not-completed")).isDisplayed());
			Assert.assertTrue(driver.findElement(By.cssSelector("uppercase-char.not-completed")).isDisplayed());
			Assert.assertTrue(driver.findElement(By.cssSelector("special-char.not-completed")).isDisplayed());
			Assert.assertTrue(driver.findElement(By.cssSelector("8-char.not-completed")).isDisplayed());
			Assert.assertTrue(driver.findElement(By.cssSelector("number-char.not-completed")).isDisplayed());
		}
	
		
   public void afterClass()
   {
	   driver.quit();
   }
}
	

