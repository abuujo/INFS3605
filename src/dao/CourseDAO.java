package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.Course;

public class CourseDAO extends BaseDAO {

	//TODO
	public static Course selectById(int courseId)  {
		String sql = "SELECT * FROM course WHERE courseId=?";
		
		try (
			Connection conn = getDBConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);	
		){
			stmt.setInt(1, courseId);
			
			try(ResultSet rs = stmt.executeQuery()) {
				if(rs.next()) {
					return parseCourse(rs);
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//TODO
	public static Course selectByCourseName(String courseName)  {
		String sql = "SELECT * FROM course WHERE courseName=?";
		
		try (
			Connection conn = getDBConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);	
		){
			stmt.setString(1, courseName);
			
			try(ResultSet rs = stmt.executeQuery()) {
				if(rs.next()) {
					return parseCourse(rs);
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Course selectByCourseCode(String courseCode)  {
		String sql = "SELECT * FROM course WHERE courseCode=?";
		
		try (
			Connection conn = getDBConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);	
		){
			stmt.setString(1, courseCode);
			
			try(ResultSet rs = stmt.executeQuery()) {
				if(rs.next()) {
					return parseCourse(rs);
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;		
	}
	
	//TODO
	public static List<Course> selectAll()  {
		List<Course> courses = new ArrayList<Course>();
		String sql = "SELECT * FROM course";
		
		try (
			Connection conn = getDBConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);	
		){	
			try(ResultSet rs = stmt.executeQuery()) {
				while(rs.next()) {
					courses.add(parseCourse(rs));
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return courses;
	}
	
	//TODO
	public static void add(Course course)  {
		String sql = "INSERT INTO `course`( `courseCode`, `courseName`, `uoc`)"
				+ "VALUES (?,?,?)";
		try (
			Connection conn = getDBConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);	
		){
			fillPreparedStatement(stmt, course);
			
			stmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return;		
	}
	
	//TODO
	public static void update(Course course)  {
		String sql = "UPDATE `course` SET `courseCode`=?, `courseName`=?, `uoc`=? WHERE courseId=?";
		try (
			Connection conn = getDBConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);	
		){
			fillPreparedStatement(stmt, course);
			stmt.setInt(4, course.getCourseId());
			
			stmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return;
	}
	
	//TODO
	public static void addOrUpdate(Course course)  {
		if(course.getCourseId() > 0) {
			update(course);
		} else {
			add(course);
		}		
	}
	
	/**
	 * Helper Function 1: Parsing a booking object
	 * @param rs
	 * @return
	 * @throws SQLException 
	 * @
	 */
	public static Course parseCourse(ResultSet rs) throws SQLException  {
		Course course = new Course();
		course.setCourseId(rs.getInt("courseId"));
		course.setCourseCode(rs.getString("courseCode"));
		course.setCourseName(rs.getString("courseName"));
		course.setUoc(rs.getString("uoc"));
		return course;
	}
	
	/**
	 * Helper method 2: Fill in PreparedStatement
	 * @param stmt
	 * @param booking
	 * @throws SQLException 
	 * @
	 */
	public static void fillPreparedStatement(PreparedStatement stmt, Course course) throws SQLException  {
		stmt.setString(1, course.getCourseCode());
		stmt.setString(2, course.getCourseName());
		stmt.setString(3, course.getUoc());
	}
}
