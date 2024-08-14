package WebDriver;


import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;



public class Topic_06_Web_Element {
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
	
   public void TC_01_WebElement() {
	//	driver.get("https://demo.nopcommerce.com/login?returnUrl=%2F");
		
		// Khi Element được dùng nhiều lần thì khai báo biến
	//	WebElement EmailTextbox = driver.findElement(By.id(""));
	//	EmailTextbox.isDisplayed();
	//	EmailTextbox.clear();
	//	EmailTextbox.sendKeys("");
		
		// Khi Element dùng 1 lần
	//	driver.findElement(By.id("Email")).sendKeys("abc");		
		
		WebElement element = driver.findElement(By.className(""));
		
		// Dùng cho các textbox / textarea / dropdown (editable)
		// Xóa dữ liệu trước khi nhập 
		
		element.clear(); //**
		
		// Dùng cho các textbox / textarea / dropdown (editable)
		// Nhập liệu 
		element.sendKeys(""); //**
		
		// Click vào các button/ link /checkbox / radio / image..
		
		element.click(); //**
		
		driver.findElement(By.cssSelector("div.header-links a.ico-register"));
		
		element.getAttribute("placeholder"); // dùng luôn
		String SearchAtribute = element.getAttribute("placeholder"); // khai báo biến  //**
		String SEmailTextboxAtribute = element.getAttribute("value"); // khai báo value
		// Search store
		
		// Gui: Pont/ zise / location / position/ color...
		element.getCssValue("background-color"); //**
		// #4ab2f1
		
		// Vị trí element so với web (bên ngoài)
		
		// element.getLocation(); ko kb biến
		Point point = element.getLocation(); //kb biến
		point.x  =345;
		point.y =133;
		
		// Kích thước của element (bên trong)
		// element.getSize();  ko kb biến
		Dimension di =  element.getSize();
		di.getHeight(); 
		di.getWidth();
		
		// Location +size
		element.getRect();
		
		// Chụp hình lỗi khi testcase fail
		element.getScreenshotAs(OutputType.FILE); //**
		element.getScreenshotAs(OutputType.BASE64);
		element.getScreenshotAs(OutputType.BYTES);
		
		// Lấy ra tên thẻ HTML của element -> Truyền vào locator khác
		driver.findElement(By.id("email")).getTagName();
		driver.findElement(By.name("email")).getTagName();
		
		String emailTextBoxTagName = driver.findElement(By.cssSelector("#email")).getTagName();
		driver.findElement(By.xpath("//" + emailTextBoxTagName + "[@id='email']"));
		
		// Lấy text từ trong message / success message / label
		element.getText(); //**
		
		// Please input email
		
		// Khi nào dùng getText - Attribute
		// Khi cái value cần lấy nằm ngoài -> getText
		// Khi cái value cần lấy nằm trong -> Attribute
		
		// Dùng để verify xem 1 element có hiển thị ko
		// Phạm vi: Tất cả element
		// element.isDisplayed();
		Assert.assertTrue(element.isDisplayed()); //**
		Assert.assertFalse(element.isDisplayed());
		
		// Dùng để verify xem 1 element có thao tác được hay không
		// Phạm vi: Tất cả element
		Assert.assertTrue(element.isEnabled());
		Assert.assertFalse(element.isEnabled());
		
		// Dùng để verify xem 1 element có chọn được hay chưa
		// Phạm vi: Checlbox / radio
		Assert.assertTrue(element.isSelected()); //**
		Assert.assertFalse(element.isSelected());
		
		// Các element phải nằm trong thẻ form
		// Tương tác với hành vi của enduser
		
		element.submit();
		
		
		}
		
		
   public void afterClass()
   {
	   driver.quit();
   }
}
	

