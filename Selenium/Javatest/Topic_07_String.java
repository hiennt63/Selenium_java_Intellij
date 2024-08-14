package Javatest;

public class Topic_07_String {

	public static void main(String[] args) {
		String firstName = "Automation";
		String lastName = "FC";
		
		String fullName = firstName + " " + lastName;
		System.out.println(fullName);
		
		fullName = firstName.concat(" ").concat(lastName);
		System.out.println(fullName);
		
		String hotelms = "Welcome " + fullName +" to HN hotel" ;
		System.out.println(hotelms);
	}


}
