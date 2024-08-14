package WebDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogType;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;


public class Topic_05_Web_Browser {
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
	
		@SuppressWarnings("unused")
		@Test
	
   public void TC_01() {
			// Nhiều tab thì đóng tab/ window đang mở hiện tại
			// 1 Tab thì đóng tab = đóng lun browser
			
	   driver.close();
	   
	   // Không quan tâm bao nhiêu tab đóng cả trình duyệt - Browser
	   driver.quit();
	  
	   // Tìm 1 element
	   
	   WebElement emailTextbox = driver.findElement(By.xpath("//input[@id='Email']"));
	   
	   // Tìm nhiều elements
	   List<WebElement> checkboxes = driver.findElements(By.xpath(""));
	   
	   // Mở ra 1 url
	   driver.get("https://www.facebook.com");
	   System.out.println("Window/Tab ID = "+ driver.getWindowHandle());
	   
	   // Click vào tiếng Việt
	   
	   // Trả về Url hiện tại
	   
	   // Có thể lưu nó vào 1 biến để sử dụng cho step sau
	   String vietnamesePageUrl =  driver.getCurrentUrl();
	   Assert.assertEquals(vietnamesePageUrl, "https://vi-vn.facebook.com/");
	   
	   	   
	   // Có thể sử dụng lun không cần tạo biến

	   Assert.assertEquals(driver.getCurrentUrl(), "https://vi-vn.facebook.com/");
	   
	   // Trả về code HTML của web hiện tại
	   // Verify tương đối
	   Assert.assertTrue(driver.getPageSource().contains("Facebook helps you connect and share with the people in your life."));
	   Assert.assertTrue(driver.getPageSource().contains("Facebook kết nối"));
	   Assert.assertTrue(driver.getPageSource().contains("Facebook chia sẻ"));
	   
	   // Trả về tiêu đề page hiện tại
	   Assert.assertEquals(driver.getTitle(),"Facebook – log in or sign up");
	   
	   // Lấy ra được ID của window/ Tab mà driver đang đứng
	   String loginWindowID =   driver.getWindowHandle();
	   
	   // Lấy ra tất cả các Tab của window
	   Set <String> allIDs = driver.getWindowHandles();
	   
	   // Cookies/cache
	   
	   Options opt = driver.manage();

	   // Login thành công => Lưu lại cookies
	   opt.getCookies();
	   
	   // Cookie- Framework
	   driver.manage().getCookies();
	   
	   // Get ra những log ở DevTool - Framework
	   
	   driver.manage().logs().get(LogType.DRIVER);
	   
	   // Testcase khác -> Set cookie vào lại - không cần login nữa
	   
	   opt.logs();
	   Timeouts time = opt.timeouts();
	   
	   // Implicit wait and depend on: Element/ Elements
	   // Khoảng thời gian chờ element xuất hiện trong vòng x giây
	  time.implicitlyWait(5,TimeUnit.SECONDS);
	  time.implicitlyWait(5000,TimeUnit.MILLISECONDS);
	  time.implicitlyWait(500000,TimeUnit.MICROSECONDS);
	  

	  // Khoảng thời gian chờ page load trong x giây
   
	  time.pageLoadTimeout(5,TimeUnit.SECONDS);
	  
	  // WeDriver API -Javascript Excutor (JavascriptExecutor library )
	  // Khoảng thời gian chờ  script được thực thi xong trong vòng x giây
	  
	  time.setScriptTimeout(5,TimeUnit.SECONDS);
	  
	  Window win = opt.window();
	  win.fullscreen();
	  win.maximize();
	  
	  /* Intelliji
	   // Chạy full màn hình

	  
	  driver.manage().window().fullscreen();
	  driver.manage().window().maximize();
	  driver.manage().window().minisize();
	  
	  // Repondsive (solution)

	  */
	  // Test FUI: Functional
	  // Test GUI: Font/ Size/ Color/ Positon / Location...
	  win.getPosition();
	  win.getSize();
	  
	  Navigation nav = driver.navigate();
	  nav.back();
	  nav.refresh();
	  nav.forward();
	  nav.to("https://www.facebook.com/");
	  
	  TargetLocator tar = driver.switchTo();
	  
	  // Webdriver API - Frame / Iframe (frame library )
	  
	  tar.frame("");
	  
	  // Webdriver API - Windows/ Tabs                                                     
	  	  
	  tar.window("");
	  
	  
	  
	  
	  
	  
		}
}
	

