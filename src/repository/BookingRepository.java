package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import dto.Booking;
import dto.Movie;
import dto.Schedule;

public class BookingRepository {
	
	private static BookingRepository repository = new BookingRepository();
	public static BookingRepository getInstance() {
		return repository;
	}
	
	public void insertBooking(int counts, int finalPrice, int customerId, int useCouponId, int scheduleId) {
		DBConnection dbConn = new DBConnection();
		Connection conn = dbConn.getConn();
		String sql = "insert into booking (counts,finalPrice,customerId,useCouponId,scheduleId) "
				+ "values (?,?,?,?,?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, counts);
			pstmt.setInt(2, finalPrice);
			pstmt.setInt(3, customerId);
			pstmt.setInt(4, useCouponId);
			pstmt.setInt(5, scheduleId);
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

	public List<Booking> findMyBooking(int customerId){
		List<Booking> bookings = new ArrayList<>();
		DBConnection dbConn = new DBConnection();
		Connection conn = dbConn.getConn();
		String sql = "select distinct mss.name as name, "
				+ "mss.title as title, mss.scheduleDate as scheduleDate, mss.scheduleTime as scheduleTime, mss.mySeats as mySeats, "
				+ "b.counts as counts, b.finalPrice as finalPrice, hc.couponName as couponName, hc.percent as percent "
				+ "from booking as b "
				+ "left join (select sns.scheduleId as scheduleId, sns.customerId as customerId, cu.name as name, "
				+ "ms.title as title, ms.scheduleDate as scheduleDate, ms.scheduleTime as scheduleTime, sns.seatNames as mySeats "
				+ "from (select scheduleId, customerId, group_concat(seatName) as seatNames "
				+ "from seat "
				+ "where emptySeat = 0 "
				+ "group by scheduleId, customerId) as sns "
				+ "left join (select distinct m.title as title, s.scheduleDate as scheduleDate, s.scheduleTime as scheduleTime, m.id as movieId, s.id as scheduleId "
				+ "from schedule as s "
				+ "left join movie as m on s.movieId = m.id) as ms on ms.scheduleId = sns.scheduleId "
				+ "left join customer as cu on cu.id = sns.customerId) as mss on mss.scheduleId = b.scheduleId and mss.customerId = b.customerId "
				+ "left join (select b.id as bookingId, cc.couponName as couponName, cc.percent as percent, b.counts as counts, b.finalPrice as finalPrice "
				+ "from booking as b "
				+ "left join (select cu.name as customerName, co.name as couponName, co.percent as percent, "
				+ "hc.id as id, hc.customerId as customerId, hc.couponId as couponId "
				+ "from haveCoupon as hc "
				+ "left join customer as cu on cu.id = hc.customerId "
				+ "left join coupon as co on co.id = hc.couponId "
				+ "where couponDelete = 1) as cc on cc.id = b.useCouponId) as hc on hc.bookingId = b.id "
				+ "where b.customerId = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, customerId);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Booking booking = new Booking(rs);
				bookings.add(booking);
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
		return bookings;
	}
}
