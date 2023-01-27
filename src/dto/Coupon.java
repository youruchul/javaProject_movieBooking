package dto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Coupon {
	private int id;
	private String customerName;
	private String couponName;
	private int percent;
	private int cusomerId;
	private int couponId;
	
	public Coupon(ResultSet rs) {
		try {
			this.id = rs.getInt("hc.id");
			this.customerName = rs.getString("cu.name");
			this.couponName = rs.getString("co.name");
			this.percent = rs.getInt("co.percent");
			this.cusomerId = rs.getInt("hc.customerId");
			this.couponId =rs.getInt("hc.couponId");
		} catch (SQLException e) {
			System.out.println("Coupon dto error");
		}
		
	}
	
	public Coupon(int id, String customerName, String couponName, int percent, int cusomerId, int couponId) {
		this.id = id;
		this.customerName = customerName;
		this.couponName = couponName;
		this.percent = percent;
		this.cusomerId = cusomerId;
		this.couponId = couponId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	public int getPercent() {
		return percent;
	}

	public void setPercent(int percent) {
		this.percent = percent;
	}

	public int getCusomerId() {
		return cusomerId;
	}

	public void setCusomerId(int cusomerId) {
		this.cusomerId = cusomerId;
	}

	public int getCouponId() {
		return couponId;
	}

	public void setCouponId(int couponId) {
		this.couponId = couponId;
	}

	@Override
	public String toString() {
		return couponName + " : " + percent + "% 할인";
	}
	
}
