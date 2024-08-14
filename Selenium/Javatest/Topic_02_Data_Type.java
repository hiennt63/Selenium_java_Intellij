package Javatest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Topic_02_Data_Type {

	public static void main(String[] args) {
		// I - Kiểu dữ liệu nguyên thủy (Primitive)
		
		// Số nguyên : Byte short int long (ko có phần thập phân)
		// Kích thước/ độ rộng để lưu trữ dữ liệu từ nhỏ đến lớn
		
		byte bNumber = 127;
		short sNumber = 32000;
		int iNumber = 499233299;
		long lNumber = 83294;
		
		// Số thực: FLoat double (có phần thập phân)
		float studentPoint =9.5f;
		
		double employeeSalary =35.6f;
		
		// Logic: Boolean
		boolean status = true; //Nam
		status = false; //Nữ
		
		// Kí tự: Char
		char a ='A';
		
		// II - Kiểu dữ liệu tham chiếu (Reference)

		// Class
		FirefoxDriver driver = new FirefoxDriver();
		
		// Interface
		WebElement firstNameTextbox;
		
		// String
		String firstName = "Automation Testing";
		
		// Object
		Object people;
		
		//Array
		
		String[] studentName = {"Nguyễn Thu An","Trần Đình Quý","Nguyễn Thị Hoa"};
		
		// Collection: List/ Set/ Queue
		
		List<WebElement> checkboxes = driver.findElements(By.cssSelector(""));
		
		// Map
		
		Map<String, Integer> student = new HashMap<String, Integer>() ;
	}

	//public static void main(String[] args){
	//	boolean status = true;
	//	if(status){
	//		Assert.assertTrue(true);
	//	} else {}
	//}
}
