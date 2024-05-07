package kh.student;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class SQLStudentConnect {

//	public static void main(String[] args) {
//		makeConnection();
//	}
	public static Connection makeConnection()  {
		String filePath = "D:/04.database/db repo/java/student/src/kh/student/stu.properties";
		Properties properties = new Properties(); // property 는 Map방식으로 이루어짐
		try {
			properties.load(new FileReader(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		String url = properties.getProperty("url");
		String user = properties.getProperty("user");
		String password = properties.getProperty("password");
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("데이타베이스 드라이버 로드 성공");
			con = DriverManager.getConnection(url, user, password);
			System.out.println("데이타베이스 접속 성공");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("데이타베이스 드라이버 로드 실패");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("데이타베이스 연결 실패");
		}
		return con;
	}

}
