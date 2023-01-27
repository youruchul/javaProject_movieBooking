package dto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Booking {
	private int id;
	private String title;
	private String date;
	private String time;
	private String seatname;
	private String customerName;
	private String couponName;
	private int couponPercent;
	private int counts;
	private int finalPrice;
	private int customerID;
	private int useCouponID;
	private int scheduleId;
	
	public Booking(ResultSet rs) {
		try {
			this.customerName = rs.getString("name");
			this.title = rs.getString("title");
			this.date = rs.getString("scheduleDate");
			this.time = rs.getString("scheduleTime");
			this.seatname = rs.getString("mySeats");
			this.counts =rs.getInt("counts");
			this.finalPrice =rs.getInt("finalPrice");
			this.couponName =rs.getString("couponName");
			this.couponPercent =rs.getInt("percent");
		} catch (SQLException e) {
			System.out.println("Coupon dto error");
		}
		
	}		
	
	public Booking(int id, String title, String date, String time, String seatname, String customerName,
			String couponName, int couponPercent, int counts, int finalPrice, int customerID, int useCouponID,
			int scheduleId) {
		this.id = id;
		this.title = title;
		this.date = date;
		this.time = time;
		this.seatname = seatname;
		this.customerName = customerName;
		this.couponName = couponName;
		this.couponPercent = couponPercent;
		this.counts = counts;
		this.finalPrice = finalPrice;
		this.customerID = customerID;
		this.useCouponID = useCouponID;
		this.scheduleId = scheduleId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getSeatname() {
		return seatname;
	}

	public void setSeatname(String seatname) {
		this.seatname = seatname;
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

	public int getCouponPercent() {
		return couponPercent;
	}

	public void setCouponPercent(int couponPercent) {
		this.couponPercent = couponPercent;
	}

	public int getCounts() {
		return counts;
	}

	public void setCounts(int counts) {
		this.counts = counts;
	}

	public int getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(int finalPrice) {
		this.finalPrice = finalPrice;
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public int getUseCouponID() {
		return useCouponID;
	}

	public void setUseCouponID(int useCouponID) {
		this.useCouponID = useCouponID;
	}

	public int getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
	}

	@Override
	public String toString() {
		return title + " : " + date + " " + time + "\n" + counts +"명" +" / 좌석정보 : " + seatname
				+ "\n사용하신 쿠폰은 <" + couponName + ">이며 " + couponPercent + "% 할인받아 " + finalPrice + "원 결제하셨습니다.\n";
	}
	
}
