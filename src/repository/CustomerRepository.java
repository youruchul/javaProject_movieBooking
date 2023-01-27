package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DBConnection;
import dto.Customer;

public class CustomerRepository {
	private static CustomerRepository repository = new CustomerRepository();
	public static CustomerRepository getInstance() {
		return repository;
	}
	
	public Customer findCustomerName(Integer memberId){
		Customer customer = new Customer();
		DBConnection dbConn = new DBConnection();
		Connection conn = dbConn.getConn();
		String sql = "select * from customer where id = ?";

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memberId);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				customer = new Customer(rs);
			}
			rs.close();
			pstmt.close();
		} catch (Exception e){
			System.out.println("쿼리가 잘못된 케이스");
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return customer;
	}
}
