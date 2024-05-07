package kh.book2;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class BookMain {
	public static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) throws FileNotFoundException, IOException {
		// Connection con = SQLBookConnect.makeConnection();
		boolean quit = false;
		int numberSelection = 0;
		while (!quit) {
			// 메인메뉴 표시하기
			menuIntroduction();
			System.out.print("메뉴 번호를 선택해주세요>> ");
			numberSelection = scan.nextInt();
			scan.nextLine(); // 입력버퍼 클리어

			if (numberSelection < 1 || numberSelection > 6) {
				System.out.println("1부터 6까지의 숫자를 입력하세요.");
			} else {
				switch (numberSelection) {
				case 1: // 책정보보기
					selectAllBook();
					break;
				case 2: // 책정보입력하기
					Books books = inputBook();
					insertBook(books);
					break;
				case 3: // 책정보조회하기
					searchBook();
					break;
				case 4: // 책정보수정하기
					updateBook();
					break;
				case 5: // 책정보삭제하기
					deleteBook();
					break;
				case 6: // 종료하기
					System.out.println("종료");
					quit = true;
					break;
				}
			}
		} // end of while
		System.out.println("The end");
	}

	// 책정보삭제하기
	public static void deleteBook() {
		selectAllBook();
		System.out.print("제목 검색>> ");
		String title = scan.nextLine();

		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			// Statement stmt = con.createStatement();
			// >> pstmt 는 ? 로 injection 방어 가능 // stmt는 불가
			con = SQLBookConnect.makeConnection();
			String sql = "delete from books where title = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, title); // 첫 ? -> String index = 1 -> title
			int i = pstmt.executeUpdate(); // sql문 실행
			if (i == 1) {
				System.out.println(title + " 책 삭제 완료");
			} else {
				System.out.println(title + " 책 삭제 실패");
			}
			// executeQuery -> rs로 값 받아오는것
			// executeUpdate -> update
			// DML : select,update,delete,create,insert,join...
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
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

	// 책정보수정하기
	public static void updateBook() {
		// 수정할 책을 선택
		Books books = searchBook();
		// 수정할 정보를 입력
		if (books == null) {
			return;
		} else {
			System.out.print("update title(" + books.getTitle() + ")>> ");
			String title = scan.nextLine();
			System.out.print("update publisher(" + books.getPublisher() + ")>> ");
			String publisher = scan.nextLine();
			System.out.print("update year(" + books.getYear() + ")>> ");
			String year = scan.nextLine();
			System.out.print("update price(" + books.getPrice() + ")>> ");
			int price = scan.nextInt();
			scan.nextLine(); // 입력버퍼클리어

			Connection con = null;
			PreparedStatement pstmt = null;
			try {
				// Statement stmt = con.createStatement();
				// >> pstmt 는 ? 로 injection 방어 가능 // stmt는 불가
				con = SQLBookConnect.makeConnection();
				String sql = "update books set title = ?, publisher = ?, year = ?, price= ? where title = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, title); // 첫 ? -> String index = 1 -> title
				pstmt.setString(2, publisher);
				pstmt.setString(3, year);
				pstmt.setInt(4, price); // sql문 세팅
				pstmt.setString(5, books.getTitle());

				int i = pstmt.executeUpdate(); // sql문 실행
				if (i == 1) {
					System.out.println(title + " 책 수정 완료");
				} else {
					System.out.println(title + " 책 수정 실패");
				}
				// executeQuery -> rs로 값 받아오는것
				// executeUpdate -> update
				// DML : select,update,delete,create,insert,join...
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
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

	// 책정보조회하기
	public static Books searchBook() {
		System.out.print("제목 검색>> ");
		String title = scan.nextLine();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Books books = null;
		try {
			con = SQLBookConnect.makeConnection();
			String sql = "select * from books where title = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, title);
			rs = pstmt.executeQuery();
//			System.out.println("rs: "+rs.next());

			if (rs.next()) {
				int book_id = rs.getInt("book_id");
				String _title = rs.getString("title");
				String publisher = rs.getString("publisher");
				String year = rs.getString("year");
				int price = rs.getInt("price");

				books = new Books(book_id, _title, publisher, year, price);
				System.out.println(books.toString());
			} else {
				System.out.println("찾고자하는 도서(" + title + ")가 없습니다");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
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
		return books;

	}

	// 책정보입력하기
	public static Books inputBook() {
		System.out.print("input title>> ");
		String title = scan.nextLine();
		System.out.print("input publisher>> ");
		String publisher = scan.nextLine();
		System.out.print("input year>> ");
		String year = scan.nextLine();
		System.out.print("input price>> ");
		int price = scan.nextInt();
		scan.nextLine(); // 입력버퍼클리어

		Books books = new Books(0, title, publisher, year, price);
		return books;
	}

	public static void insertBook(Books books) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			// Statement stmt = con.createStatement();
			// >> pstmt 는 ? 로 injection 방어 가능 // stmt는 불가
			con = SQLBookConnect.makeConnection();
			String sql = "insert into books VALUES (books_seq.nextval, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, books.getTitle()); // 첫 ? -> String index = 1 -> title
			pstmt.setString(2, books.getPublisher());
			pstmt.setString(3, books.getYear());
			pstmt.setInt(4, books.getPrice()); // sql문 세팅

			int i = pstmt.executeUpdate(); // sql문 실행
			if (i == 1) {
				System.out.println(books.getTitle() + " 책 추가 완료");
			} else {
				System.out.println(books.getTitle() + " 책 추가 실패");
			}
			// executeQuery -> rs로 값 받아오는것
			// executeUpdate -> update
			// DML : select,update,delete,create,insert,join...
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
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

	// 책정보 보기
	public static void selectAllBook() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = SQLBookConnect.makeConnection();
			String sql = "select * from books order by book_id ASC";
			// order by '~~' asc/dsc -> sorting
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int book_id = rs.getInt("book_id");
				String title = rs.getString("title");
				String publisher = rs.getString("publisher");
				String year = rs.getString("year");
				int price = rs.getInt("price");

				Books books = new Books(book_id, title, publisher, year, price);
				System.out.println(books.toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
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

	// 메뉴
	public static void menuIntroduction() {
		System.out.println("********************************");
		System.out.println("\tBook Market Menu");
		System.out.println("********************************");
		System.out.println("1. 책정보보기");
		System.out.println("2. 책정보입력하기");
		System.out.println("3. 책정보조회하기");
		System.out.println("4. 책정보수정하기");
		System.out.println("5. 책정보삭제하기");
		System.out.println("6. 종료하기");
		System.out.println("********************************");

	}
}
