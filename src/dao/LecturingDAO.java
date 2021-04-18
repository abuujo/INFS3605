package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.Lecturing;
import dto.Student;

public class LecturingDAO extends BaseDAO {

	//TODO
	public static List<Lecturing> selectAllByCoordinatorId(int coordinatorId)  {
		List<Lecturing> lecturings = new ArrayList<Lecturing>();
		String sql = "SELECT * FROM lecturing WHERE coordinatorId=?";
		try (
			Connection conn = getDBConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);	
		){
			stmt.setInt(1, coordinatorId);
			
			try(ResultSet rs = stmt.executeQuery()) {
				while(rs.next()) {
					lecturings.add(parseLecturing(rs));
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return lecturings;
	}
	
	//TODO
	public static List<Lecturing> selectAllByCourseId(int courseId)  {
		List<Lecturing> lecturings = new ArrayList<Lecturing>();
		String sql = "SELECT * FROM lecturing WHERE courseId=?";
		try (
			Connection conn = getDBConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);	
		){
			stmt.setInt(1, courseId);
			
			try(ResultSet rs = stmt.executeQuery()) {
				while(rs.next()) {
					lecturings.add(parseLecturing(rs));
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return lecturings;	
	}
	
	//TODO : not really needed
	public static List<Lecturing> selectAll()  {
		List<Lecturing> lecturings = new ArrayList<Lecturing>();
		String sql = "SELECT * FROM lecturing";
		try (
			Connection conn = getDBConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);	
		){	
			try(ResultSet rs = stmt.executeQuery()) {
				while(rs.next()) {
					lecturings.add(parseLecturing(rs));
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return lecturings;
	}
	
	// Helper function for addOrUpdate
	public static boolean isExist(Lecturing lecturing)  {
		String sql = "SELECT * FROM lecturing WHERE coordinatorId=? and courseId=? and semester=?";
		try (
			Connection conn = getDBConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);	
		){
			fillPreparedStatement(stmt, lecturing);
			
			try(ResultSet rs = stmt.executeQuery()) {
				if(rs.next()) {
					return true;
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * Add a newly created lecturing object
	 * @param lecturing
	 */
	public static void add(Lecturing lecturing)  {
		String sql = "INSERT INTO `lecturing`(`coordinatorId`, `courseId`, `semester`)"
				+ "VALUES (?,?,?)";
		try (
			Connection conn = getDBConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);	
		){
			fillPreparedStatement(stmt, lecturing);
			
			stmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return;
	}
	
	/**
	 * Update existing lecturing information (update semester)
	 * @param student
	 */
	public static void update(Lecturing lecturing)  {
		String sql = "UPDATE `lecturing` SET `coordinatorId`=?,`courseId`=?, `semester`=? WHERE coordinatorId=? and courseId=?";
		try (
			Connection conn = getDBConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);	
		){
			fillPreparedStatement(stmt, lecturing);
			stmt.setString(4, lecturing.getSemester());
			
			stmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return;
	}
	
	/**
	 *  Update a student's information if studentId > 0, otherwise add a new student
	 * @param student
	 */
	public static void addOrUpdate(Lecturing lecturing)  {
		if(isExist(lecturing)) {
			update(lecturing);
		} else {
			update(lecturing);
		}
	}
	
	/**
	 * Helper method 1: Parsing a lecturing object
	 * @param rs
	 * @return
	 * @throws SQLException 
	 * @
	 */
	public static Lecturing parseLecturing(ResultSet rs) throws SQLException  {
		Lecturing lecturing = new Lecturing();
		lecturing.setCoordinatorId(rs.getInt("coordinatorId"));
		lecturing.setCourseId(rs.getInt("courseId"));
		return lecturing;
	}
	
	/**
	 * Helper method 2: Fill in PreparedStatement
	 * @param stmt
	 * @param lecturing
	 * @throws SQLException 
	 * @
	 */
	public static void fillPreparedStatement(PreparedStatement stmt, Lecturing lecturing) throws SQLException  {
		stmt.setInt(1, lecturing.getCoordinatorId());
		stmt.setInt(2, lecturing.getCourseId());
		stmt.setString(3, lecturing.getSemester());
	}
}
