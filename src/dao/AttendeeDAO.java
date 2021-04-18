package dao;

import dto.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class AttendeeDAO extends BaseDAO {
	/**
	 * Add attendee object to the database
	 * @param bookingId
	 * @param studentId
	 */
	public static void add(int bookingId, int studentId) {
		String sql = "INSERT INTO `attendee`(`bookingId`, `studentId`)" + "VALUES (?,?)";
		
		try (
			Connection conn = getDBConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);	
		){
			stmt.setInt(1, bookingId);
			stmt.setInt(2, studentId);

			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return;		
	}
	
	public static void delete(int bookingId, int studentId)  {
		String sql = "DELETE FROM `attendee` WHERE bookingId=? AND studentId=?";
		
		try (
			Connection conn = getDBConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);	
		){
			stmt.setInt(1, bookingId);
			stmt.setInt(2, studentId);
			
			stmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return;	
	}

	public static List<Student> selectByBookingId(int bookingId) {
		List<Student> list = new ArrayList<Student>();
		String sql = "SELECT * FROM attendee WHERE bookingId=?";
		
		try (
			Connection conn = getDBConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);	
		){
			stmt.setInt(1, bookingId);
			
			try(ResultSet rs = stmt.executeQuery()) {
				while(rs.next()) {
					Student student = StudentDAO.selectById(rs.getInt("studentId"));
					list.add(student);
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static Student selectByStudent(Student s) {
		Student student = null;
		String sql = "SELECT * FROM attendee WHERE studentId=?";
		
		try (
			Connection conn = getDBConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);	
		){
			stmt.setInt(1, s.getStudentId());
		
			try(ResultSet rs = stmt.executeQuery()) {
				if(rs.next()) {
					student = StudentDAO.selectById(rs.getInt("studentId"));
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return student;
	}
}
