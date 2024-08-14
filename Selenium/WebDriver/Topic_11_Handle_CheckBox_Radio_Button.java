package WebDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;


public class Topic_11_Handle_CheckBox_Radio_Button {
	WebDriver driver;

	// Tường minh: trạng thái cụ thể của element
	// Visible/ invisible/presence/ Number / CLickkable/...
	WebDriverWait explicitlyWait;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}

	@Test
	public void TC_01_Default_Telerik_Checkbox(){
		 driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		 By rearSideCheckbox = By.xpath("//label[text()='Rear side airbags']/preceding-sibling::span/input");
		 By dualZoneCheckbox = By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::span/input");

		 // Case 1: Nếu như app mở ra mà checkbox đã chọn thì sao
		//checkToElement(rearSideCheckbox);
		checkToElement(dualZoneCheckbox);

		Assert.assertTrue(driver.findElement(rearSideCheckbox).isSelected());
		Assert.assertTrue(driver.findElement(dualZoneCheckbox).isSelected());

		uncheckToElement(rearSideCheckbox);
		uncheckToElement(dualZoneCheckbox);

		Assert.assertFalse(driver.findElement(rearSideCheckbox).isSelected());
		Assert.assertFalse(driver.findElement(dualZoneCheckbox).isSelected());
		 }

	@Test
	public void TC_02_Default_Telerik_Radio (){
		driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");

		By twoPetrolRadio = By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::span/input");
		By twoDieselRadio = By.xpath("//label[text()='1.6 Diesel, 77kW']/preceding-sibling::span/input");

		// chọn 1 trong 2
		checkToElement(twoPetrolRadio);

		Assert.assertTrue(driver.findElement(twoPetrolRadio).isSelected());
		Assert.assertFalse(driver.findElement(twoDieselRadio).isSelected());

		checkToElement(twoDieselRadio);

		Assert.assertFalse(driver.findElement(twoPetrolRadio).isSelected());
		Assert.assertTrue(driver.findElement(twoDieselRadio).isSelected());

	}
	@Test
	public void TC_03_Select_All_Checkbox () {
		driver.get("https://automationfc.github.io/multiple-fields/");

		List <WebElement> allCheckboxes = driver.findElements(By.cssSelector("div.form-single-column input[type='checkbox']"));

		for(WebElement checkbox: allCheckboxes){
			if(!checkbox.isSelected()){
				checkbox.click();
			//	sleepInSeconds(1);
			}
		}
		for (WebElement checkbox:allCheckboxes){
			Assert.assertTrue(checkbox.isSelected());
		}
		driver.manage().deleteAllCookies();
		driver.navigate().refresh();

		allCheckboxes = driver.findElements(By.cssSelector("div.form-single-column input[type='checkbox']"));

		// Chọn 1 checkbox/radio
		for(WebElement checkbox: allCheckboxes){
			if(checkbox.getAttribute("value").equals("Heart Attack") && !checkbox.isSelected()) {
				checkbox.click();
				sleepInSeconds(1);
			}}

		for(WebElement checkbox: allCheckboxes){
			if(checkbox.getAttribute("value").equals("Heart Attack") ){
				Assert.assertTrue(checkbox.isSelected());
			} else {
				Assert.assertFalse(checkbox.isSelected());
			}

		}
	}
	@Test
	public void TC_04_Custom_Radio(){
		
	}

	public void afterClass() {
		driver.quit();
	}

	public void checkToElement(By byXpath){
		//Nếu như element chưa được chọn thì click
		if(!driver.findElement(byXpath).isSelected()){
			driver.findElement(byXpath).click();
			sleepInSeconds(1);
		}
	}
	public void uncheckToElement(By byXpath){
		//Nếu như element đã chọn thì click lần nữa để bỏ chọn
		if(driver.findElement(byXpath).isSelected()){
			driver.findElement(byXpath).click();
			sleepInSeconds(1);
		}
	}


	public void sleepInSeconds(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}


}
	

