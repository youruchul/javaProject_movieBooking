package dto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Seat {
	private int id;
	private String title;
	private String scheduleDate;
	private String scheduleTime;
	private String seatName;
	private int emptySeat;
	private Integer finalPrice;
	private Integer counts;
	private int movieId;
	private int scheduleId;
	
	public Seat(ResultSet rs) {
		try {
			this.id = rs.getInt("se.id");
			this.title = rs.getString("ms.title");
			this.scheduleDate = rs.getString("ms.scheduleDate");
			this.scheduleTime = rs.getString("ms.scheduleTime");
			this.seatName = rs.getString("se.seatName");
			this.emptySeat = rs.getInt("se.emptySeat");
			this.movieId = rs.getInt("ms.movieId");
			this.scheduleId = rs.getInt("ms.scheduleId");
		} catch (SQLException e) {
			System.out.println("Seat dto error");
		}
		
	}
	
	public Seat(int id, String title, String scheduleDate, String scheduleTime, String seatName, int emptySeat,
			Integer finalPrice, Integer counts, int movieId, int scheduleId) {
		super();
		this.id = id;
		this.title = title;
		this.scheduleDate = scheduleDate;
		this.scheduleTime = scheduleTime;
		this.seatName = seatName;
		this.emptySeat = emptySeat;
		this.finalPrice = finalPrice;
		this.counts = counts;
		this.movieId = movieId;
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

	public String getSeatName() {
		return seatName;
	}

	public void setSeatName(String seatName) {
		this.seatName = seatName;
	}

	public int getEmptySeat() {
		return emptySeat;
	}

	public void setEmptySeat(int emptySeat) {
		this.emptySeat = emptySeat;
	}

	public Integer getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(Integer finalPrice) {
		this.finalPrice = finalPrice;
	}

	public Integer getCounts() {
		return counts;
	}

	public void setCounts(Integer counts) {
		this.counts = counts;
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public int getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
	}

	@Override
	public String toString() {
		return "Seat [id=" + id + ", title=" + title + ", scheduleDate=" + scheduleDate + ", scheduleTime="
				+ scheduleTime + ", seatName=" + seatName + ", emptySeat=" + emptySeat + ", finalPrice=" + finalPrice
				+ ", Counts=" + counts + ", movieId=" + movieId + ", scheduleId=" + scheduleId + "]\n";
	}
	
	
}
// ms.title, ms.scheduleDate, ms.scheduleTime, se.seatName, se.emptySeat