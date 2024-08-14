package WebDriver;

import java.util.Random;

public class Topic_06_Random {
	// Java Bultin cung cấp sẵn - lấy ra sử dụng )
	// Java Libraries (Do 1 cá nhân / 1 tổ chức họ tự viết )
	
	public static void main(String[] arg) {
		Random  rand = new Random ();
		System.out.print(rand.nextInt());
		System.out.print(rand.nextInt());
		
		System.out.print(rand.nextDouble());
		System.out.print(rand.nextFloat());
		System.out.print(rand.nextBoolean());
		
		System.out.println("automation" + rand.nextInt(99999) + "@email.net");
		System.out.println("automation" + rand.nextInt(99999) + "@email.net");
		System.out.println("automation" + rand.nextInt(99999) + "@email.net");
		System.out.println("automation" + rand.nextInt(99999) + "@email.net");
		
	}
	
}
