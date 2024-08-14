package WebDriver;

import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v85.network.Network;
import org.openqa.selenium.devtools.v85.network.model.Headers;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;


public class Topic_13_Handle_Authentication_Alert {
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectLocation = System.getProperty("user.dir");
	By resultText = By.cssSelector("p#result");
	String username = "admin";
	String password = "admin";

	@BeforeClass
	public void beforeClass() {

		///admin	admin
		// driver = new EdgeDriver();
		driver = new FirefoxDriver();
		//ChromeOptions options= new ChromeOptions();
		//options.setBinary("");
		//driver = new ChromeDriver(options);
		// Selenium version 3x
		// driver = new OperaDriver();

		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);


	}
	@Test
	public void TC_01_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		sleepInSeconds(2);

		//Alert alert = driver.switchTo().alert();
		Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());

		Assert.assertEquals(alert.getText(), "I am a JS Alert");
		alert.accept();

		Assert.assertEquals(driver.findElement(resultText).getText(),"You clicked an alert successfully");

	}
@Test
	public void TC_02_Alert_confirm() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();

		Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());

		Assert.assertEquals(alert.getText(),"I am a JS Confirm");
	    sleepInSeconds(2);
		alert.dismiss();
		Assert.assertEquals(driver.findElement(resultText).getText(), "You clicked: Cancel");

	}

   @Test
	public void TC_03_Alert_Prompt() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();

		Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());

		Assert.assertEquals(alert.getText(),"I am a JS prompt");
		sleepInSeconds(2);

		String text = "Automation Testing";
		alert.sendKeys(text);
		alert.accept();
		Assert.assertEquals(driver.findElement(resultText).getText(), "You entered: " + text);

	}
	@Test
	public void TC_04_Authentication_Pass_Url() {
		// Cách 1: Truyền user/ password

		//driver.get("https://" + username + ":" + password + "@the-internet.herokuapp.com/basic_auth");
		//Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());
		// Cách 2: Từ page A thao tác 1 element qua page B (Cần phải thao tác với Authen Alert trước)
		driver.get("https://the-internet.herokuapp.com/");
		String authenLinkUrl = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
		//System.out.println(authenLinkUrl);
		//driver.get(authenLinkUrl);
		driver.get(getAuthenAlertByUrl(authenLinkUrl,username,password));
		//String[] authenArray = authenLinkUrl.split("//");
		//System.out.println(authenArray[0]);
		//System.out.println(authenArray[1]);
		//driver.get((authenArray[0]+ "//" + username + ":" + password + "@" + authenArray[1]));
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());

	}
	@Test
	public void TC_05_Authentication_AutoIT() throws IOException {

		// Cách 2: Chạy trên window (AutoIT)
		// Mac/Linux ko work
		// Mỗi browser cần 1 đoạn code khác nhau
		// Thực thi đoạn code autoIT chờ Alert xuất hiện

		Runtime.getRuntime().exec(new String[] {projectLocation + "\\AutoIT\\authen_firefox.exe", "admin", "admin"});
		driver.get("https://the-internet.herokuapp.com/basic_auth");
		sleepInSeconds(5);

		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());

	}
    @Test
	public void TC_06_Authentication_Selenium_4x(){
		// Cách 3:
		// Thư viện Alert ko sử dụng cho Authentication Alert
		// Chrome DevTool Protocol (COP) - Chrome/ Edge (Chromium)
		// Cốc cốc/ Opera (Chromium)-  work Around

		//ChromeOptions options = new ChromeOptions();
		//options.setBinary("C:\\Program Files (x86)\\Microsoft\\Edge\\Application\\msedge.exe");
		//driver = new ChromeDriver();
		// ChromeDriver extends ChromiumDriver
		driver = new EdgeDriver();
		// EdgeDriver extends ChromiumDriver
		//driver = new FirefoxDriver();
		// FirefoxDriver extends RemoteWebDriver
		//driver = new SafariDriver();
		// SafariDriver extends RemoteWebDriver
	   // driver = new InternetExplorerDriver();
		// SafariDriver extends RemoteWebDriver

		// Get DevTool object
		DevTools devTools =((HasDevTools) driver).getDevTools();

		// Start new session
		devTools.createSession();

		// Enable the Network domain of devtools
		devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

		// Encode username/ password
		Map<String, Object> headers = new HashMap<String, Object>();
		String basicAuthen = "Basic " + new String(new Base64().encode(String.format("%s:%s", username, password).getBytes()));
		headers.put("Authorization", basicAuthen);

		// Set to Header
		devTools.send(Network.setExtraHTTPHeaders(new Headers(headers)));

		driver.get("https://the-internet.herokuapp.com/basic_auth");

	}

	public void afterClass()
	{
		driver.quit();
	}

	public String getAuthenAlertByUrl(String url, String username, String password){
		String[] authenArray = url.split("//");
		return authenArray[0] + "//" + username + ":" + password + "@" + authenArray[1];

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


