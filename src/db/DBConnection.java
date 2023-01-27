package db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	private Connection conn;
	private final String id = "admin"; // 계정이름
	private final String password = "1234"; // 비밀번호
	private final String url = "jdbc:mysql://localhost:3306/movieBooking"; // DB주소
	
	public DBConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url,id,password);
			// System.out.println("연동 성공");
		} catch (Exception e){
			System.out.println("연동 실패");
		}
		
	}

	public Connection getConn() {
		return conn;
	}
}
