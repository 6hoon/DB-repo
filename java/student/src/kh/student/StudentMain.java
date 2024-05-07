package kh.student;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class StudentMain {
	public static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		Student student = null;
		boolean quit1 = false;
		int menu1 = 0;
		while (!quit1) {
			showMenu1();
			System.out.print("menu1 >> ");
			menu1 = sc.nextInt();
			sc.nextLine();

			if (menu1 < 1 || menu1 > 2) {
				System.out.println("메뉴의 올바른 숫자 입력>> ");
			} else {
				switch (menu1) {
				case 1:
					student = login();
					quit1 = true;
					break;
				case 2:
					createStudent();
					break;
				}
			}
		}

//		boolean quit = false;
//		int menu = 0;
//		while (!quit) {
//			showMenu2();
//			System.out.println("메뉴 입력>> ");
//			menu = sc.nextInt();
//			sc.nextLine();
//
//			if (menu < 1 || menu > 6) {
//				System.out.println("1~6의 숫자를 입력>> ");
//			} else {
//				switch (menu) {
//				case 1:
//					System.out.println("1");
//					break;
//				case 2:
//					System.out.println("2");
//					break;
//				case 3:
//					System.out.println("3");
//					break;
//				case 4:
//					System.out.println("4");
//					break;
//				case 5:
//					System.out.println("5");
//					break;
//				case 6:
//					System.out.println("6");
//					break;
//				default:
//					break;
//				}
//			}
//		}
		System.out.println("end");
	}

	public static Student searchStudent(String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Student student = null;
		
		try {
			con = SQLStudentConnect.makeConnection();
			String sql = "select * from student where sd_id = ?";
			pstmt = con.prepareStatement(sql);
			System.out.println(id);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			System.out.println("rs: " + rs.next());
			if (rs.next()) {
				int no = rs.getInt("no");
				String sd_num = rs.getString("sd_num");
				String sd_name = rs.getString("sd_name");
				String sd_id = rs.getString("sd_id");
				String sd_pw = rs.getString("sd_pw");
				String sd_birth = rs.getString("sd_birth");
				String sd_phone = rs.getString("sd_phone");
				String sd_email = rs.getString("sd_email");
				Date sd_date = rs.getDate("sd_date");
				student = new Student(no, sd_num, sd_name, sd_id, sd_pw, sd_birth, sd_phone, sd_email, sd_date);
				
			} else {
				System.out.println("존재하지 않는 계정입니다");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				System.out.println("rs, pstmt, con close eroor!!");
				e.printStackTrace();
			}
		}
		return student;
	}

	public static void createStudent() {

	}

	public static Student login() {
		System.out.print("ID를 입력하시오>> ");
		String sd_id = sc.nextLine();
		Student student = searchStudent(sd_id);
		
		if (student == null) {
			return null;
		} else {
			boolean flag = false;
			while (!flag) {
				System.out.print("PW를 입력하시오>> ");
				String sd_pw = sc.nextLine();
				if (sd_pw.equals("-1")) {
					System.out.println("로그인을 종료합니다");
					student = null;
					flag = true;
				}
				if (student.getSd_pw().equals(sd_pw)) {
					System.out.println(student.getSd_name() + "님 정상적으로 로그인되었습니다");
					flag = true;
				} else {
					student = null;
					System.out.println("잘못 입력하셨습니다");
				}
			}
			return student;
		}

	}

	public static void showMenu1() {

	}

	public static void showMenu2() {

	}

}
