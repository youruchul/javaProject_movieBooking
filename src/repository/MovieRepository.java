package repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import dto.Movie;

public class MovieRepository {
	private static MovieRepository repository = new MovieRepository();
	public static MovieRepository getInstance() {
		return repository;
	}
	
	public List<Movie> findALLMovie(){
		List<Movie> movies = new ArrayList<>();
		DBConnection dbConn = new DBConnection();
		Connection conn = dbConn.getConn();
		String sql = "select * from movie order by totalAudience desc";
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				Movie movie = new Movie(rs);
				movies.add(movie);
			}
			rs.close();
			stmt.close();
		} catch (Exception e){
			System.out.println("쿼리가 잘못된 케이스");
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return movies;
	}
}
