package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import dto.Schedule;

public class ScheduleRepository {
	private static ScheduleRepository repository = new ScheduleRepository();
	public static ScheduleRepository getInstance() {
		return repository;
	}
	
	public List<Schedule> findSchedule(Integer movieId){
		List<Schedule> schedules = new ArrayList<>();
		DBConnection dbConn = new DBConnection();
		Connection conn = dbConn.getConn();
		String sql = "select m.title, s.scheduleDate, s.scheduleTime, s.price, m.id as movieId, s.id as scheduleId "
				+ "from schedule as s left join movie as m on s.movieId = m.id where m.id = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, movieId);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Schedule schedule = new Schedule(rs);
				schedules.add(schedule);
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
		return schedules;
	}
}
