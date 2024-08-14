package WebDriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;

import static java.lang.Thread.sleep;

public class Topic_22_Window_Tab_Selenium4x {
	WebDriver driver;
	WebDriverWait explicitWait;


	@BeforeClass
	public void initialBrowser() {


		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}

	@Test
	public void TC_01_Basic_Window()  {
		driver.get("https://kynaenglish.vn/");
		System.out.println("ID window kynaenglish: " + driver.toString());

		// Mở tab mới /window mới
		WebDriver facebookKyna = driver.switchTo().newWindow(WindowType.TAB);
		facebookKyna.get("https://www.facebook.com/Kynaenglish1kem1");
		System.out.println("ID window facebook Kyna: "+ facebookKyna.toString());
	}


	@Test
	public void TC_02 (){


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
