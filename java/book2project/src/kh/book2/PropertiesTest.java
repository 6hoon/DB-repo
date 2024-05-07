package kh.book2;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesTest {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		String filePath = "D:/04.database/db repo/java/book2project/src/kh/book2/db.properties";
		Properties properties = new Properties();	//property 는 Map방식으로 이루어짐
		properties.load(new FileReader(filePath));
		System.out.println(properties.getProperty("url"));
		System.out.println(properties.getProperty("user"));
		System.out.println(properties.getProperty("password"));
		
	}

}
