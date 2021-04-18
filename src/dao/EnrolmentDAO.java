package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.Booking;
import dto.Enrolment;
import dto.Lecturing;

public class EnrolmentDAO extends BaseDAO{

	//TODO
	public static List<Enrolment> selectByCourseId(int courseId)  {
		List<Enrolment> list = new ArrayList<Enrolment>();
		String sql = "SELECT * FROM enrolment where courseId = ?";
		try (
			Connection conn = getDBConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);	
		){
			stmt.setInt(1, courseId);
			
			try(ResultSet rs = stmt.executeQuery()) {
				while(rs.next()) {
					list.add(parseEnrolment(rs));
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	public static List<Enrolment> selectBySemYear(String semYear) {
		List<Enrolment> list = new ArrayList<Enrolment>();
		String sql = "SELECT * FROM enrolment WHERE semYear=?";
		try (
			Connection conn = getDBConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);	
		){
			stmt.setString(1, semYear);
			
			try(ResultSet rs = stmt.executeQuery()) {
				while(rs.next()) {
					list.add(parseEnrolment(rs));
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//TODO
	public static List<Enrolment> selectByStudentId(int studentId)  {
		List<Enrolment> list = new ArrayList<Enrolment>();
		String sql = "SELECT * FROM enrolment where studentId = ?";
		try (
			Connection conn = getDBConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);	
		){
			stmt.setInt(1, studentId);
			
			try(ResultSet rs = stmt.executeQuery()) {
				while(rs.next()) {
					list.add(parseEnrolment(rs));
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//TODO: not really needed
	public static List<Enrolment> selectAll()  {
		List<Enrolment> list = new ArrayList<Enrolment>();
		String sql = "SELECT * FROM enrolment";
		try (
			Connection conn = getDBConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);	
		){	
			try(ResultSet rs = stmt.executeQuery()) {
				while(rs.next()) {
					list.add(parseEnrolment(rs));
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
		
	}
	
	public static void add(Enrolment enrolment)  {
		String sql = "INSERT INTO `enrolment`(`courseId`, `studentId`, `mark`, `semYear`)"
				+ " VALUES(?,?,?,?)";
		try (
			Connection conn = getDBConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);	
		){
			fillPreparedStatement(stmt, enrolment);
			
			stmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return;
	}
	
	//hello
	public static void update(Enrolment enrolment)  {
		String sql = "UPDATE `enrolment` SET `courseId`=?, `studentId`=?, `mark`=?, `semYear`=?"
				+ " WHERE courseId=? and studentId=?";
		try (
			Connection conn = getDBConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);	
		){
			fillPreparedStatement(stmt, enrolment);
			stmt.setInt(5, enrolment.getCourseId());
			stmt.setInt(6, enrolment.getCourseId());
			
			stmt.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return;		
	}
	
	// Helper function for addOrUpdate
	public static boolean isExist(Enrolment enrolment)  {
		String sql = "SELECT * FROM enrolment WHERE courseId=? and studentId=?";
		try (
			Connection conn = getDBConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);	
		){
			fillPreparedStatement(stmt, enrolment);
			
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
	
	public static void addOrUpdate(Enrolment enrolment)  {
		if(isExist(enrolment)) {
			update(enrolment);
		} else {
			add(enrolment);
		}	
	}
	
	/**
	 * Helper method 1: Parsing a enrolement object
	 * @param stmt
	 * @param enrolment
	 * @throws SQLException 
	 * @
	 */
	public static Enrolment parseEnrolment(ResultSet rs) throws SQLException  {
		Enrolment enrolment = new Enrolment();
		enrolment.setCourseId(rs.getInt("courseId"));
		enrolment.setStudentId(rs.getInt("studentId"));
		enrolment.setMark(rs.getInt("mark"));
		enrolment.setSemYear(rs.getString("semYear"));
		return enrolment;
	}
	
	/**
	 * Helper method 2: Fill in PreparedStatement
	 * @param stmt
	 * @param enrolment
	 * @throws SQLException 
	 * @
	 */
	public static void fillPreparedStatement(PreparedStatement stmt, Enrolment enrolment) throws SQLException  {
		stmt.setInt(1, enrolment.getCourseId());
		stmt.setInt(2, enrolment.getStudentId());
		stmt.setInt(3, enrolment.getMark());
		stmt.setString(4, enrolment.getSemYear());
	}
}
