package Javatest;
import java.util.List;
import java.util.ArrayList;
public class Topic_15_Generic {

	public Topic_15_Generic(String[] args) {
		List<String> students = new ArrayList<String>();
		students.add("Hien");
		students.add("Tram");
		students.add("Mary");
		students.add("Lan");

		List address = new ArrayList<>();
		address.add("1223 aa"); //string
		address.add(15); //integer
		address.add(true); //Boolean
		address.add(15.77); //Float

	}

	public static void main(String[] args) {

	}
		
}


