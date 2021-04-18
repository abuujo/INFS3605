package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.Student;

public class StudentDAO extends BaseDAO {

	/**
	 * Select all students from the database
	 * 
	 * @return
	 */
	public static List<Student> selectAll()  {
		List<Student> students = new ArrayList<Student>();
		String sql = "SELECT * FROM student";

		try (
			Connection conn = getDBConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);	
		){
			try(ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					students.add(parseStudent(rs));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return students;
	}

	/**
	 * Select a student by student's id
	 * 
	 * @param studentId
	 * @return
	 */
	public static Student selectById(int studentId)  {
		String sql = "SELECT * FROM student where studentId = ?";
		try (
			Connection conn = getDBConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);	
		){
			stmt.setInt(1, studentId);

			try(ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					return parseStudent(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Select a student by student's zID
	 * 
	 * @param zId
	 * @return
	 */
	public static Student selectByZId(String zId)  {
		String sql = "SELECT * FROM student where zId=?";
		try (
			Connection conn = getDBConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);	
		){
			stmt.setString(1, zId);

			try(ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					return parseStudent(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Select all students who are doing 'program'
	 * 
	 * @param program
	 * @return
	 */
	public static List<Student> selectAllByProgram(String program)  {
		List<Student> list = new ArrayList<Student>();
		String sql = "SELECT * FROM student where programId = ?";
		try (
			Connection conn = getDBConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);	
		){
			stmt.setString(1, program);

			try(ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					list.add(parseStudent(rs));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * Select a student by student's email address
	 * 
	 * @param email
	 * @return
	 */
	public static Student selectByEmail(String email)  {
		String sql = "SELECT * FROM student where email = ?";
		try (
			Connection conn = getDBConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);	
		){
			stmt.setString(1, email);

			try(ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					return parseStudent(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Add a newly created student
	 * 
	 * @param student
	 */
	public static void add(Student student)  {
		String sql = "INSERT INTO `student`(`zId`, `firstName`, `lastName`, `email`, `programId`,`privEmail`,`privPhone`,`workPhone`,`gender`,`address`,`currentWam`,`yearEnrolled`,`type`,`ip1`,`ip2`,`ip3`)" + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try (
			Connection conn = getDBConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);	
		){
			fillPreparedStatement(stmt, student);

			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return;
	}

	/**
	 * Update existing student's information
	 * 
	 * @param student
	 */
	public static void update(Student student)  {
		String sql = "UPDATE `student` SET `zId`=?,`firstName`=?,`lastName`=?,`email`=?,`programId`=?,`privEmail`=?,`privPhone`=?,`workPhone`=?,`gender`=?,`address`=?,`currentWam`=?,`yearEnrolled`=?, `type`=?, `ip1`=?, `ip2`=?, `ip3`=? WHERE studentId=?";
		try (
			Connection conn = getDBConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);	
		){
			fillPreparedStatement(stmt, student);
			stmt.setInt(17, student.getStudentId());

			stmt.executeUpdate();
			System.out.println("STUDENT DETAIL UPDATED");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return;
	}

	/**
	 * Update a student's information if studentId > 0, otherwise add a new
	 * student
	 * 
	 * @param student
	 */
	public static void addOrUpdate(Student student)  {
		if (student.getStudentId() > 0 || selectByZId(student.getzId()) != null) {
			update(student);
		} else {
			add(student);
		}
	}

	public static void delete(Student student)  {
		String sql = "DELETE FROM `student` WHERE studentId=?";
		try (
			Connection conn = getDBConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);	
		){
			stmt.setInt(1, student.getStudentId());
			
			stmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return;	
	}

	/**
	 * Helper method 1: Parsing a student object
	 * 
	 * @param rs
	 * @return
	 * @throws SQLException 
	 * @
	 */
	public static Student parseStudent(ResultSet rs) throws SQLException  {
		Student student = new Student();
		student.setStudentId(rs.getInt("studentId"));
		student.setzId(rs.getString("zId"));
		student.setFirstName(rs.getString("firstName"));
		student.setLastName(rs.getString("lastName"));
		student.setEmail(rs.getString("email"));
		student.setProgramId(rs.getInt("programId"));
		student.setPrivEmail(rs.getString("privEmail"));
		student.setPrivPhone(rs.getString("privPhone"));
		student.setWorkPhone(rs.getString("workPhone"));
		student.setGender(rs.getString("gender"));
		student.setAddress(rs.getString("address"));
		student.setCurrentWam(rs.getString("currentWam"));
		student.setYearEnrolled(rs.getString("yearEnrolled"));
		student.setType(rs.getString("type"));
		student.setIp1(rs.getString("ip1"));
		student.setIp2(rs.getString("ip2"));
		student.setIp3(rs.getString("ip3"));
		return student;
	}

	/**
	 * Helper method 2: Fill in PreparedStatement
	 * 
	 * @param stmt
	 * @param student
	 * @throws SQLException 
	 * @
	 */
	public static void fillPreparedStatement(PreparedStatement stmt, Student student) throws SQLException  {
		stmt.setString(1, student.getzId());
		stmt.setString(2, student.getFirstName());
		stmt.setString(3, student.getLastName());
		stmt.setString(4, student.getEmail());
		stmt.setInt(5, student.getProgramId());
		stmt.setString(6, student.getPrivEmail());
		stmt.setString(7, student.getPrivPhone());
		stmt.setString(8, student.getWorkPhone());
		stmt.setString(9, student.getGender());
		stmt.setString(10, student.getAddress());
		stmt.setString(11, student.getCurrentWam());
		stmt.setString(12, student.getYearEnrolled());
		stmt.setString(13, student.getType());
		stmt.setString(14, student.getIp1());
		stmt.setString(15, student.getIp2());
		stmt.setString(16, student.getIp3());
	}
}