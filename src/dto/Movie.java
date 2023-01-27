package dto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Movie {
	private int id;
	private String title;
	private String director;
	private String country;
	private String openDate;
	private String genre;
	private int totalAudience;
	
	public Movie(ResultSet rs) {
			try {
				this.id = rs.getInt("id");
				this.title = rs.getString("title");
				this.director = rs.getString("director");
				this.country = rs.getString("country");
				this.openDate = rs.getString("openDate");
				this.genre = rs.getString("genre");
				this.totalAudience = rs.getInt("totalAudience");
			} catch (SQLException e) {
				System.out.println("Movie dto error");
			}
			
	}
		
	public Movie(int id, String title, String director, String country, String openDate, String genre,
			int totalAudience) {
		this.id = id;
		this.title = title;
		this.director = director;
		this.country = country;
		this.openDate = openDate;
		this.genre = genre;
		this.totalAudience = totalAudience;
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
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getOpenDate() {
		return openDate;
	}
	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public int getTotalAudience() {
		return totalAudience;
	}
	public void setTotalAudience(int totalAudience) {
		this.totalAudience = totalAudience;
	}
	@Override
	public String toString() {
		return " <" + title + ">\n" + director + " 제작 / " + country
				+ " / " + openDate + " / " + genre + " / 누적관객수 " + totalAudience + "명\n";
	}
	
}

