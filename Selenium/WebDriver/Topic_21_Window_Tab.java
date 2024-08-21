package WebDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;

import static java.lang.Thread.sleep;

public class Topic_21_Window_Tab {
	WebDriver driver;
	WebDriverWait explicitWait;


	@BeforeClass
	public void initialBrowser() {


		//driver = new FirefoxDriver();
		driver = new ChromeDriver();
		// driver = new EdgeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}

	@Test
	public void TC_01_Basic_Window()  {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		// Lấy ra ID của trang web hiện tại
		String basicFormID = driver.getWindowHandle();
		System.out.println("Parent ID: " + basicFormID);

		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		sleepInSeconds(5);


		switchToWindowByTitle("Google");
		driver.findElement(By.cssSelector("textarea[name='q']")).sendKeys("Selenium");
		sleepInSeconds(3);

		switchToWindowByTitle("Selenium WebDriver");
		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
		sleepInSeconds(3);

		switchToWindowByTitle("Facebook – log in or sign up");
	    driver.findElement(By.cssSelector("input#email")).sendKeys("0336917958");
		driver.findElement(By.cssSelector("input#pass")).sendKeys("Hiennt@230796");
		driver.findElement(By.xpath("//button[@name='login']")).click();
		sleepInSeconds(3);
	}
	@Test
	public void TC_02(){

		String ParentID = driver.getWindowHandle();
		driver.get("https://kynaenglish.vn/");

		driver.findElement(By.cssSelector("img[alt='Facebook']")).click();

		switchToWindowByTitle("Kyna English 1 kèm 1 | Ho Chi Minh City | Facebook");
		driver.findElement(By.cssSelector("form#login_popup_cta_form input[name='email']")).sendKeys("0336917958");
		sleepInSeconds(3);

		switchToWindowByTitle("KYNA ENGLISH - MANG LẠI NHIỀU TRẢI NGHIỆM HỌC TẬP HIỆU QUẢ HƠN");
		driver.findElement(By.cssSelector("img[alt='Youtube']")).click();
		sleepInSeconds(3);

		switchToWindowByTitle("Kyna For Kids - YouTube");
		Assert.assertEquals(driver.findElement(By.cssSelector("div#page-header-container yt-dynamic-text-view-model span.yt-core-attributed-string.yt-core-attributed-string--white-space-pre-wrap")).getText(),"Kyna For Kids");

		CloseAllWindowsWithoutPearent(ParentID);
		sleepInSeconds(3);
}

	@Test
	public void TC_03 (){
		driver.get("http://live.techpanda.org/");

		driver.findElement(By.xpath("//a[text()='Mobile']")).click();
		sleepInSeconds(3);

		driver.findElement(By.xpath("//a[@title='Sony Xperia']/parent::h2//following-sibling::div[@class='actions']//a[@class='link-compare']")).click();
		sleepInSeconds(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(),"The product Sony Xperia has been added to comparison list.");

		driver.findElement(By.xpath("//a[@title='Samsung Galaxy']/parent::h2//following-sibling::div[@class='actions']//a[@class='link-compare']")).click();
		sleepInSeconds(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(),"The product Samsung Galaxy has been added to comparison list.");

		driver.findElement(By.xpath("//span[text()='Compare']")).click();
		switchToWindowByTitle("Products Comparison List - Magento Commerce");

		Assert.assertEquals(driver.findElement(By.cssSelector("div.page-title>h1")).getText(),"COMPARE PRODUCTS");
		driver.findElement(By.cssSelector("button[title='Close Window'] span span")).click();

		switchToWindowByTitle("Mobile");

	//	driver.findElement(By.xpath("//div[@class='actions']/a[text()='Clear All']")).click();

	//	Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());
	//	Assert.assertEquals(alert.getText(),"Are you sure you would like to remove all products from your comparison?");
	//	alert.accept();

	//	Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span']")).getText(),"The comparison list was cleared.");

		driver.findElement(By.cssSelector("input#search")).sendKeys("IPhone");
		driver.findElement(By.cssSelector("button[title='Search']")).click();
		sleepInSeconds(3);


	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public void switchToWindowByID (String expectedID){
		Set<String> allIDs = driver.getWindowHandles();
		for(String id:allIDs){
			// Nếu ID nào khác ID parentID thì switch to
			if(!id.equals(expectedID)){
				driver.switchTo().window(id);

				// Ngược lại thì thoát khỏi vòng lặp ko cần kiểm tra tiếp
				break;
			}
		}
	}

	public void switchToWindowByTitle(String TitleExpected){
		//Lấy all ID của Tab
		Set<String> allIDs = driver.getWindowHandles();
		// Dùng vòng lặp duyệt qua ID trên
		for(String id: allIDs){
			// switch vào từng ID trước
			driver.switchTo().window(id);
			sleepInSeconds(2);

			// Lấy ra title của từng window/ tab trên
			String actualTitle = driver.getTitle();
			if (actualTitle.equals(TitleExpected)){
				break;
			}
		}

	}

	public void CloseAllWindowsWithoutPearent(String ParentID){
		//Lấy all ID của Tab
		Set<String> allIDs = driver.getWindowHandles();
		// Dùng vòng lặp duyệt qua ID trên
		for(String id: allIDs){
			if (!id.equals(ParentID)){
				driver.switchTo().window(id);
				driver.close();
			}
		} driver.switchTo().window(ParentID);

	}
	public void sleepInSeconds(long timeInSecond) {
		try
		{
			sleep(timeInSecond*1000);
		}
		catch (InterruptedException e)
		{
			throw new RuntimeException(e);
		}
	}
}
