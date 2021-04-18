package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentListDAO extends BaseDAO {

	public static void add(int studentId, int coordinatorId)  {
		String sql = "INSERT INTO `studentList`(`bookingId`, `studentId`, `coordinatorId`)"
				+ " VALUES(?,?,?)";
		try (
			Connection conn = getDBConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);	
		){
			stmt.setInt(1, studentId);
			stmt.setInt(2, studentId);
			stmt.setInt(3, coordinatorId);
			
			stmt.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return;
	}
}
