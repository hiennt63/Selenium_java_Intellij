package WebDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_03_Xpath_Part_I {
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
	public void TC_01_Empty_Data() {
		
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		// ít lỗi sai cú pháp/ thiếu đóng mở
		// Suggest code lại: Ctrl + space
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		
		// Verify
		// Data Type Element = String
		// Data Type String = String
		Assert.assertEquals(driver.findElement(By.id("txtFirstname-error")).getText(),"Vui lòng nhập họ tên");
		Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(),"Vui lòng nhập email");
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(),"Vui lòng nhập lại địa chỉ email");
		Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(),"Vui lòng nhập mật khẩu");
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(),"Vui lòng nhập lại mật khẩu");
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),"Vui lòng nhập số điện thoại.");
	}

	@Test
	public void TC_02_Invalid_Email() {
		
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		// True Action
		driver.findElement(By.id("txtFirstname")).sendKeys("Hien");
		driver.findElement(By.id("txtPassword")).sendKeys("@123456789");
		driver.findElement(By.id("txtCPassword")).sendKeys("@123456789");
		driver.findElement(By.id("txtPhone")).sendKeys("0336917958");
		
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		
		// Verify 
		Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(),"Vui lòng nhập email");
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(),"Vui lòng nhập lại địa chỉ email");
		
	}

	@Test
	public void TC_03_Incorrect_Confirm_Email() {
	
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		// Action
		driver.findElement(By.id("txtFirstname")).sendKeys("Hien");
		driver.findElement(By.id("txtFirstname")).sendKeys("Hien");
		driver.findElement(By.id("txtEmail")).sendKeys("hiennt.tgnguyen@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("hiennt.tgnguyen@gmail.con");
		driver.findElement(By.id("txtPassword")).sendKeys("@123456789");
		driver.findElement(By.id("txtCPassword")).sendKeys("@123456789");
		driver.findElement(By.id("txtPhone")).sendKeys("0336917958");
		
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		
		// Verify 
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(),"Email nhập lại không đúng");
		
		
	}
	@Test
	public void TC_04_Invalid_Password () {
	
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		// Action
		driver.findElement(By.id("txtFirstname")).sendKeys("Hien");
		driver.findElement(By.id("txtEmail")).sendKeys("hiennt.tgnguyen@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("hiennt.tgnguyen@gmail.com");
		driver.findElement(By.id("txtPassword")).sendKeys("@1234");
		driver.findElement(By.id("txtCPassword")).sendKeys("@1234");
		driver.findElement(By.id("txtPhone")).sendKeys("0336917958");
		
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		
		// Vertify
		Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(),"Mật khẩu phải có ít nhất 6 ký tự");
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(),"Mật khẩu phải có ít nhất 6 ký tự");	
	 
	}

	@Test
	public void TC_05_Incorrect_Confirm_Password() {
	
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		// Action
		driver.findElement(By.id("txtFirstname")).sendKeys("Hien");
		driver.findElement(By.id("txtEmail")).sendKeys("hiennt.tgnguyen@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("hiennt.tgnguyen@gmail.com");
		driver.findElement(By.id("txtPassword")).sendKeys("@123456789");
		driver.findElement(By.id("txtCPassword")).sendKeys("@123456788");
		driver.findElement(By.id("txtPhone")).sendKeys("0336917958");
		
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		
		// Vertify
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(),"Mật khẩu bạn nhập không khớp");
		
	}
	
	@Test
	public void TC_06_Invalid_Phone_Number() {
	
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		// Action 1
		
		driver.findElement(By.id("txtFirstname")).sendKeys("Hien");
		driver.findElement(By.id("txtEmail")).sendKeys("hiennt.tgnguyen@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("hiennt.tgnguyen@gmail.com");
		driver.findElement(By.id("txtPassword")).sendKeys("@123456789");
		driver.findElement(By.id("txtCPassword")).sendKeys("@123456789");
		driver.findElement(By.id("txtPhone")).sendKeys("033691795");
		
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		
		// Vertify 1
		
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),"Số điện thoại phải từ 10-11 số.");
		
		// Action 2
		
		driver.findElement(By.id("txtFirstname")).clear();
		driver.findElement(By.id("txtEmail")).clear();
		driver.findElement(By.id("txtCEmail")).clear();
		driver.findElement(By.id("txtPassword")).clear();
		driver.findElement(By.id("txtCPassword")).clear();
		driver.findElement(By.id("txtPhone")).clear();
		
		driver.findElement(By.id("txtFirstname")).sendKeys("Hien");
		driver.findElement(By.id("txtEmail")).sendKeys("hiennt.tgnguyen@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("hiennt.tgnguyen@gmail.com");
		driver.findElement(By.id("txtPassword")).sendKeys("@123456789");
		driver.findElement(By.id("txtCPassword")).sendKeys("@123456789");
		driver.findElement(By.id("txtPhone")).sendKeys("3369179599");
		
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		
		// Vertify 2
	
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),"Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019 - 088 - 03 - 05 - 07 - 08");
	}
	
	
	@AfterClass
	public void afterClass() {
		// driver.quit();
	}
}
