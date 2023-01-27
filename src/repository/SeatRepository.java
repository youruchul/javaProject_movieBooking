package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import dto.Seat;

public class SeatRepository {
	private static SeatRepository repository = new SeatRepository();
	public static SeatRepository getInstance() {
		return repository;
	}
	
	public List<Seat> findSeat(Integer movieId, Integer scheduleId){
		List<Seat> seats = new ArrayList<>();
		DBConnection dbConn = new DBConnection();
		Connection conn = dbConn.getConn();
		String sql = "select ms.title, ms.scheduleDate, ms.scheduleTime, se.seatName, se.emptySeat, se.id, ms.movieId, ms.scheduleId "
				+ "from seat as se "
				+ "left join (select m.title, s.scheduleDate, s.scheduleTime, s.price, m.id as movieId, s.id as scheduleId "
				+ "from schedule as s "
				+ "left join movie as m on s.movieId = m.id "
				+ "where m.id = ?) as ms on se.scheduleId = ms.scheduleId "
				+ "where ms.scheduleId = ?";

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, movieId);
			pstmt.setInt(2, scheduleId);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Seat seat = new Seat(rs);
				seats.add(seat);
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
		return seats;
	}
	
	public void choiceSeat(int selectSeat) {
		DBConnection dbConn = new DBConnection();
		Connection conn = dbConn.getConn();
		String sql = "update seat "
				+ "set emptySeat = 0 "
				+ "where id = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, selectSeat);
			pstmt.executeUpdate();
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
		return;
	}
	
	public void cancelSeat(int selectSeat) {
		DBConnection dbConn = new DBConnection();
		Connection conn = dbConn.getConn();
		String sql = "update seat "
				+ "set emptySeat = 1 "
				+ "where id = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, selectSeat);
			pstmt.executeUpdate();
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
		return;
	}
	
	public void insertCustomerId(int selectSeat, int customerId) {
		DBConnection dbConn = new DBConnection();
		Connection conn = dbConn.getConn();
		String sql = "update seat "
				+ "set customerId = ? "
				+ "where id = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, customerId);
			pstmt.setInt(2, selectSeat);
			pstmt.executeUpdate();
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
		return;
	}
	
	public List<Seat> emptySeatCount(Integer movieId, Integer scheduleId){
		List<Seat> seats = new ArrayList<>();
		DBConnection dbConn = new DBConnection();
		Connection conn = dbConn.getConn();
		String sql = "select ms.title, ms.scheduleDate, ms.scheduleTime, se.seatName, se.emptySeat, se.id, ms.movieId, ms.scheduleId "
				+ "from seat as se "
				+ "left join (select m.title, s.scheduleDate, s.scheduleTime, s.price, m.id as movieId, s.id as scheduleId "
				+ "from schedule as s "
				+ "left join movie as m on s.movieId = m.id "
				+ "where m.id = ?) as ms on se.scheduleId = ms.scheduleId "
				+ "where ms.scheduleId = ? and se.emptySeat = 1";

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, movieId);
			pstmt.setInt(2, scheduleId);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Seat seat = new Seat(rs);
				seats.add(seat);
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
		return seats;
	}
}
