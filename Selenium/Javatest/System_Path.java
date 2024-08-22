package Javatest;

import org.openqa.selenium.Keys;
import org.openqa.selenium.Platform;

import java.io.File;
import java.lang.reflect.Field;

public class System_Path {

	public static void main(String[] args) {
		String projectPath = System.getProperty("user.dir");
		//System.out.println(projectPath);
		String filename = "halong.jpg";
		System.out.println(projectPath + "\\uploadFiles\\" + filename);

		String osName = System.getProperty("os.name");
		//System.out.println(osName);
		Keys keys;

		if(osName.startsWith("Windows")){
			keys = Keys.CONTROL;
		} else {
			keys = Keys.COMMAND;
		}
		Keys cmdCtrl = Platform.getCurrent().is(Platform.WINDOWS)? Keys.CONTROL : Keys.COMMAND;

		//String character = Platform.getCurrent().is(Platform.WINDOWS) ? "\\" : "/";
		//String character = File.separator;

		String hcmName = "hcm.jpg";
		String hlName = "halong.jpg";
		String hnName = "hanoi.jpg";

		String hcmFileName = projectPath + File.separator + "uploadFiles" + File.separator + hcmName;
		String hlFileName = projectPath + File.separator + "uploadFiles" + File.separator + hlName;
		String hnFileName = projectPath + File.separator + "uploadFiles" + File.separator + hnName;

		System.out.println(hcmFileName);
	}
}
