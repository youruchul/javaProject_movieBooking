package dto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Schedule {
	private int id;
	private String title;
	private String scheduleDate;
	private String scheduleTime;
	private int price;
	private int movieId;
	
	public Schedule(ResultSet rs) {
		try {
			this.id = rs.getInt("scheduleId");
			this.title = rs.getString("m.title");
			this.scheduleDate = rs.getString("s.scheduleDate");
			this.scheduleTime = rs.getString("s.scheduleTime");
			this.price = rs.getInt("s.price");
			this.movieId = rs.getInt("movieId");
		} catch (SQLException e) {
			System.out.println("Schedule dto error");
		}
		
	}
	
	public Schedule(int id, String title, String scheduleDate, String scheduleTime, int price, int movieId) {
		this.id = id;
		this.title = title;
		this.scheduleDate = scheduleDate;
		this.scheduleTime = scheduleTime;
		this.price = price;
		this.movieId = movieId;
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

	public String getScheduleDate() {
		return scheduleDate;
	}

	public void setScheduleDate(String scheduleDate) {
		this.scheduleDate = scheduleDate;
	}

	public String getScheduleTime() {
		return scheduleTime;
	}

	public void setScheduleTime(String scheduleTime) {
		this.scheduleTime = scheduleTime;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	@Override
	public String toString() {
		return scheduleDate + " "
				+ scheduleTime + " / 가격 : " + price + "원";
	}
	
}
