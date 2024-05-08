package kh.student;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class StudentMain {
	public static Scanner sc = new Scanner(System.in);
	public static Student stu = null;

	public static void main(String[] args) {
		stu = null;
		boolean quit1 = false;
		int menu1 = 0;
		while (!quit1) {
			showMenu1();
			System.out.print("menu1 >> ");
			menu1 = sc.nextInt();
			sc.nextLine();

			if (menu1 < 1 || menu1 > 3) {
				System.out.println("메뉴의 올바른 숫자 입력>> ");
			} else {
				switch (menu1) {
				case 1:
					stu = login();
					quit1 = true;
					break;
				case 2:
					Student _student = inputStudent();
					createStudent(_student);
					break;
				case 3:
					showAllStudent();
					break;
				}

			}
		}
		// 학생메뉴 (1. 정보보기, 2. 수정, 3. 삭제, 4.종료
		boolean quit = false;
		int menu = 0;
		while (!quit) {
			showMenu2();
			System.out.println("메뉴 입력>> ");
			menu = sc.nextInt();
			sc.nextLine();

			if (menu < 1 || menu > 4) {
				System.out.println("1~4의 숫자를 입력>> ");
			} else {
				switch (menu) {
				case 1:
					showStu();
					break;
				case 2:
					updateStu();
					break;
				case 3:
					deleteStu();
					break;
				case 4:
					System.out.println("종료합니다");
					quit = true;
					break;
				}
			}
		}
		System.out.println("end");
	}

	// 3. 삭제
	public static void deleteStu() {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			String id = stu.getStu_id();
			con = SQLStudentConnect.makeConnection();
			String sql = "delete from student where stu_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			int i = pstmt.executeUpdate();
			if (i == 1) {
				System.out.println(id + " 계정 삭제 완료");
			} else {
				System.out.println(id + " 계정 삭제 실패");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				System.out.println("pstmt, con close eroor!!");
				e.printStackTrace();
			}
		}
	}

	// 2. 수정
	public static void updateStu() {
		if (stu == null) {
			return;
		} else {
			System.out.print("PW>> ");
			String stu_pw = sc.nextLine();
			System.out.print("전화번호>> ");
			String stu_phone = sc.nextLine();
			System.out.print("E-mail>> ");
			String stu_email = sc.nextLine();

			Connection con = null;
			PreparedStatement pstmt = null;
			try {
				con = SQLStudentConnect.makeConnection();
				// ----------------------------------------------------------------------------------------------------
				String sql = "update student set stu_pw = ?, stu_phone = ?, stu_email = ? where stu_id = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, stu_pw); // 첫 ? -> String index = 1 -> title
				pstmt.setString(2, stu_phone);
				pstmt.setString(3, stu_email);
				pstmt.setString(4, stu.getStu_id()); // sql문 세팅

				int i = pstmt.executeUpdate(); // sql문 실행
				if (i == 1) {
					System.out.println(stu.getStu_id() + " 계정 수정 완료");
				} else {
					System.out.println(stu.getStu_id() + " 계정 수정 실패");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (pstmt != null) {
						pstmt.close();
					}
					if (con != null) {
						con.close();
					}
				} catch (SQLException e) {
					System.out.println("pstmt, con close eroor!!");
					e.printStackTrace();
				}
			}
		}
	}

	// 1. 출력
	private static void showStu() {
		System.out.println(stu.toString());
	}

	// login 3. 총 학생정보
	public static void showAllStudent() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = SQLStudentConnect.makeConnection();
			String sql = "select * from student order by no ASC";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int no = rs.getInt("no");
				String stu_num = rs.getString("stu_num");
				String stu_name = rs.getString("stu_name");
				String stu_id = rs.getString("stu_id");
				String stu_pw = rs.getString("stu_pw");
				String stu_birth = rs.getString("stu_birth");
				String stu_phone = rs.getString("stu_phone");
				String stu_email = rs.getString("stu_email");
				Student student = new Student(no, stu_num, stu_name, stu_id, stu_pw, stu_birth, stu_phone, stu_email);
				System.out.println(student.toString());
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
	}

	// login 2. 회원가입
	public static Student inputStudent() {
		System.out.print("학번>> ");
		String stu_num = sc.nextLine();
		System.out.print("이름>> ");
		String stu_name = sc.nextLine();
		System.out.print("ID>> ");
		String stu_id = sc.nextLine();
		System.out.print("PW>> ");
		String stu_pw = sc.nextLine();
		System.out.print("생년월일(ex.19960129)>> ");
		String stu_birth = sc.nextLine();
		System.out.print("전화번호>> ");
		String stu_phone = sc.nextLine();
		System.out.print("E-mail>> ");
		String stu_email = sc.nextLine();

		Student student = new Student(0, stu_num, stu_name, stu_id, stu_pw, stu_birth, stu_phone, stu_email);
		return student;
	}

	public static void createStudent(Student student) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = SQLStudentConnect.makeConnection();
			String sql = "insert into student VALUES (student_seq.nextval, ?, ?, ?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, student.getStu_num());
			pstmt.setString(2, student.getStu_name());
			pstmt.setString(3, student.getStu_id());
			pstmt.setString(4, student.getStu_pw());
			pstmt.setString(5, student.getStu_birth());
			pstmt.setString(6, student.getStu_phone());
			pstmt.setString(7, student.getStu_email());

			int i = pstmt.executeUpdate(); 
			if (i == 1) {
				System.out.println(student.getStu_name() + " 님 계정생성이 완료되셨습니다");
			} else {
				System.out.println(student.getStu_name() + " 님 계정생성 실패");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				System.out.println("pstmt, con close eroor!!");
				e.printStackTrace();
			}
		}
	}

	// login 1. 로그인
	public static Student searchStudent(String id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Student student = null;

		try {
			con = SQLStudentConnect.makeConnection();
			String sql = "select * from student where stu_id = ?";
			pstmt = con.prepareStatement(sql);
			System.out.println(id);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				int no = rs.getInt("no");
				String stu_num = rs.getString("stu_num");
				String stu_name = rs.getString("stu_name");
				String stu_id = rs.getString("stu_id");
				String stu_pw = rs.getString("stu_pw");
				String stu_birth = rs.getString("stu_birth");
				String stu_phone = rs.getString("stu_phone");
				String stu_email = rs.getString("stu_email");
				student = new Student(no, stu_num, stu_name, stu_id, stu_pw, stu_birth, stu_phone, stu_email);
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

	public static Student login() {
		System.out.print("ID를 입력하시오>> ");
		String id = sc.nextLine();
		Student student = searchStudent(id);
		String pw =null;
		if (student == null) {
			student = new Student();
			return student;
		} else {
			boolean flag = false;
			while (!flag) {
				System.out.print("PW를 입력하시오(로그인 종료:-1)>> ");
				pw = sc.nextLine();
				if (pw.equals("-1")) {
					System.out.println("로그인을 종료합니다");
					student = null;
					flag = true;
					break;
				} else {
					if (student.getStu_pw().equals(pw)) {
						System.out.println(student.getStu_id() + "님 정상적으로 로그인되었습니다");
						flag = true;
					} else {
						System.out.println("잘못 입력하셨습니다");
					}
				}
			}
			return student;
		}

	}

	// 로그인 메뉴 (1. 로그인, 2. 회원가입, 3. showALL
	public static void showMenu1() {
		System.out.println("********************************");
		System.out.println("\tLogin Menu");
		System.out.println("********************************");
		System.out.println("1. 로그인");
		System.out.println("2. 회원가입");
		System.out.println("3. 총 학생정보 보기");
		System.out.println("********************************");
	}

	// 학생메뉴 (1. 정보보기, 2. 수정, 3. 삭제, 4.종료
	public static void showMenu2() {
		System.out.println("********************************");
		System.out.println("\tStudent Menu");
		System.out.println("********************************");
		System.out.println("1. 학생정보 보기");
		System.out.println("2. 학생정보 수정하기");
		System.out.println("3. 학생정보 삭제하기");
		System.out.println("4. 종료하기");
		System.out.println("********************************");
	}

}
