package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import dto.Coupon;

public class CouponRepository {
	private static CouponRepository repository = new CouponRepository();
	public static CouponRepository getInstance() {
		return repository;
	}
	
	public List<Coupon> findMyCoupon(Integer memberId){
		List<Coupon> coupons = new ArrayList<>();
		DBConnection dbConn = new DBConnection();
		Connection conn = dbConn.getConn();
		String sql = "select cu.name, co.name, co.percent, hc.id, hc.customerId, hc.couponId "
				+ "from haveCoupon as hc "
				+ "left join customer as cu on cu.id = hc.customerId "
				+ "left join coupon as co on co.id = hc.couponId "
				+ "where cu.id = ? and hc.couponDelete = 0 "
				+ "order by percent desc;";

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, memberId);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Coupon coupon = new Coupon(rs);
				coupons.add(coupon);
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
		return coupons;
	}
	
	public void useCoupon(int couponId) {
		DBConnection dbConn = new DBConnection();
		Connection conn = dbConn.getConn();
		String sql = "update haveCoupon "
				+ "set couponDelete = 1 "
				+ "where id = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, couponId);
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
}
