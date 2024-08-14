package WebDriver;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;


public class Topic_08_09_Handle_Custom_Dropdown {
	WebDriver driver;

	// Tường minh: trạng thái cụ thể của element
	// Visible/ invisible/presence/ Number / CLickkable/...
	WebDriverWait explicitlyWait;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();

		explicitlyWait = new WebDriverWait(driver,Duration.ofSeconds(30));

		// Ngầm định: ko rõ ràng cho 1 trạng thái cụ thể nào của element hết
		// Cho việc tìm element -findelement(s)

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

	}

	@Test

	public void TC_01_JQuery() {

	driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");

		selectItemInDropdown("span#speed-button","ul#speed-menu div", "Medium");
		sleepInSeconds(3);

		selectItemInDropdown("span#files-button","ul#files-menu div", "ui.jQuery.js");
		sleepInSeconds(3);

	//	driver.navigate().refresh();
		selectItemInDropdown("span#number-button","ul#number-menu div", "15");
		sleepInSeconds(3);

		selectItemInDropdown("span#salutation-button","ul#salutation-menu div", "Mrs.");
		sleepInSeconds(3);

		Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(),"Medium");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#files-button>span.ui-selectmenu-text")).getText(),"ui.jQuery.js");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button>span.ui-selectmenu-text")).getText(),"15");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#salutation-button>span.ui-selectmenu-text")).getText(),"Mrs.");



	}

	@Test
	public void TC_02_ReactJS() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");

		selectItemInDropdown("i.dropdown.icon","div.item>span.text","Elliot Fu");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(),"Elliot Fu");
		sleepInSeconds(3);

		selectItemInDropdown("i.dropdown.icon","div.item>span.text","Matt");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(),"Matt");
		sleepInSeconds(3);

	}
	@Test
	public void TC_03_VueJS(){
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		selectItemInDropdown("li.dropdown-toggle","ul.dropdown-menu a","Third Option");
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(),"Third Option");
		sleepInSeconds(3);

		selectItemInDropdown("li.dropdown-toggle","ul.dropdown-menu a","First Option");
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(),"First Option");
		sleepInSeconds(3);
	}

	@Test
	public void TC_04_Editable(){  // dropdown can enter and select
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
		selectItemInEditableDropdown("input.search","div.item span","American Samoa");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(),"American Samoa");
		sleepInSeconds(3);

		selectItemInEditableDropdown("input.search","div.item span","Belize");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(),"Belize");
		sleepInSeconds(3);

		selectItemInEditableDropdown("input.search","div.item span","Aruba");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(),"Aruba");
		sleepInSeconds(3);
	}
	@Test
	public void TC_05_Nopcommerce(){
		driver.get("https://demo.nopcommerce.com/register");
		selectItemInDropdown("select[name='DateOfBirthDay']","select[name='DateOfBirthDay']>option","23");
		Assert.assertTrue(driver.findElement(By.cssSelector("select[name='DateOfBirthDay']>option[value='23']")).isSelected());
		sleepInSeconds(2);
		//Dropdown là default nhưng ko sử dụng thư viện select

		selectItemInDropdown("select[name='DateOfBirthMonth']","select[name='DateOfBirthMonth']>option","March");
		Assert.assertTrue(driver.findElement(By.cssSelector("select[name='DateOfBirthMonth']>option[value='3']")).isSelected());
		sleepInSeconds(2);

		selectItemInDropdown("select[name='DateOfBirthYear']","select[name='DateOfBirthYear']>option","1996");
		Assert.assertTrue(driver.findElement(By.cssSelector("select[name='DateOfBirthYear']>option[value='1996']")).isSelected());
		sleepInSeconds(2);
	}

	public void afterClass() {
		driver.quit();
	}


	public void sleepInSeconds(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	// Những dữ liệu truyền vào là tham số
	public void selectItemInDropdown(String parentCss, String childItemCss, String itemTextExpected){
		driver.findElement(By.cssSelector(parentCss)).click(); //span#number-button
		sleepInSeconds(1);

		List<WebElement> allItems = explicitlyWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childItemCss))); //ul#number-menu div
		for(WebElement item:allItems){
			if(item.getText().equals(itemTextExpected)) {
				item.click();
				break;
			}
		}

	}

	public void selectItemInEditableDropdown(String parentCss, String childItemCss, String itemTextExpected){
		driver.findElement(By.cssSelector(parentCss)).clear(); //span#number-button
		driver.findElement(By.cssSelector(parentCss)).sendKeys(itemTextExpected);
		sleepInSeconds(1);

		List<WebElement> allItems = explicitlyWait.until(ExpectedConditions.
				presenceOfAllElementsLocatedBy(By.cssSelector(childItemCss))); //ul#number-menu div
		for(WebElement item:allItems){
			if(item.getText().equals(itemTextExpected)) {
				item.click();
				break;
			}
		}

	}
}
	

