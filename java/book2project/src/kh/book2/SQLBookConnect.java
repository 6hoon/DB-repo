package kh.book2;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class SQLBookConnect {
	public static Connection makeConnection() throws FileNotFoundException, IOException {
		String filePath = "D:/04.database/db repo/java/book2project/src/kh/book2/db.properties";
		Properties properties = new Properties(); // property 는 Map방식으로 이루어짐
		properties.load(new FileReader(filePath));
		String url = properties.getProperty("url");
		String user = properties.getProperty("user");
		String password = properties.getProperty("password");
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
//			System.out.println("데이타베이스 드라이버 로드 성공");
			con = DriverManager.getConnection(url, user, password);
//			System.out.println("데이타베이스 접속 성공");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("데이타베이스 드라이버 로드 실패");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("데이타베이스 연결 실패");
		}
		return con;
	}

//	public static void main(String[] args) throws FileNotFoundException, IOException {
//		Connection con = makeConnection();
//	}
}
