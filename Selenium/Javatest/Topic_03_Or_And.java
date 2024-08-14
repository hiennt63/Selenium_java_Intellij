package Javatest;

public class Topic_03_Or_And {

	public static void main (String[] args ) {
		
		boolean statusA;
		boolean statusB;
		
		// And - &&
		// Nếu 1 trong 2 điều kiện sai -> Kết quả sai
		// Nếu 1 trong 2 điều kiện đúng -> Kết quả sai
		// Cả 2 điều kiện sai -> Kết quả sai
		// Cả 2 điều kiện đúng -> Kết quả đúng
		
		statusA = true;
		statusB = false;
		System.out.println("Kết quả = " + (statusA && statusB));
		
		statusA = false;
		statusB = true;
		System.out.println("Kết quả = " + (statusA && statusB));
		
		statusA = false;
		statusB = false;
		System.out.println("Kết quả = " + (statusA && statusB));
		
		statusA = true;
		statusB = true;
		System.out.println("Kết quả = " + (statusA && statusB));
		
		// Or - ||
		// Nếu 1 trong 2 điều kiện sai -> Kết quả đúng
		// Nếu 1 trong 2 điều kiện đúng -> Kết quả đúng
		// Cả 2 điều kiện sai -> Kết quả sai
		// Cả 2 điều kiện đúng -> Kết quả đúng
		
		statusA = true;
		statusB = false;
		System.out.println("Kết quả = " + (statusA || statusB));
		
		statusA = false;
		statusB = true;
		System.out.println("Kết quả = " + (statusA || statusB));
		
		statusA = false;
		statusB = false;
		System.out.println("Kết quả = " + (statusA || statusB));
		
		statusA = true;
		statusB = true;
		System.out.println("Kết quả = " + (statusA || statusB));
		
	}
}
