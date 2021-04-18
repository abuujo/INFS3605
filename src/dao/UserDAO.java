package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.User;
import dto.Student;

public class UserDAO extends BaseDAO {
	

	/**
	 * Admin Function: select all users
	 * @return
	 */
	public static List<User> selectAll() throws SQLException {
		List<User> users = new ArrayList<User>();
		String sql = "SELECT * FROM user";
		
		try (
			Connection conn = getDBConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
		) {
			
			try(ResultSet rs = stmt.executeQuery()) {
				while(rs.next()) {
					users.add(parseUser(rs));
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return users;
	}
	
	/**
	 * Select a user by email address
	 * @param email
	 * @return
	 */
	public static User selectByEmail(String email) {
		String sql = "SELECT * FROM user WHERE email = ?";
		
		try (
			Connection conn = getDBConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);			
		){
			
			stmt.setString(1, email);
			
			try(ResultSet rs = stmt.executeQuery()){			
				if(rs.next()) {
					return parseUser(rs);
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Select a user by id
	 * @param firstName
	 * @return
	 */
	public static User selectById(int userId) {
		String sql = "SELECT * FROM user WHERE userId=?";
		
		try (
				Connection conn = getDBConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);			
			){
			stmt.setInt(1, userId);
			
			try(ResultSet rs = stmt.executeQuery()) {		
				if(rs.next()) {
					return parseUser(rs);
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Select a user by first name
	 * @param firstName
	 * @return
	 */
	public static User selectByFirstName(String firstName) {
		String sql = "SELECT * FROM user WHERE firstName=?";
		
		try (
				Connection conn = getDBConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);			
			){
			stmt.setString(1, firstName);
			
			try(ResultSet rs = stmt.executeQuery()) {		
				if(rs.next()) {
					return parseUser(rs);
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Select a user by last name
	 * @param firstName
	 * @return
	 */
	public static User selectByLastName(String lastName) {
		String sql = "SELECT * FROM user WHERE lastName=?";
		
		try (
				Connection conn = getDBConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);			
			){
			stmt.setString(1, lastName);
			
			try(ResultSet rs = stmt.executeQuery()) {		
				if(rs.next()) {
					return parseUser(rs);
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}	
	
	/**
	 * Add a newly created user
	 * @param user
	 */
	public static void add(User user) {
		String sql = "INSERT INTO `user`(`email`, `password`, `firstName`, `lastName`, `type`, `loginAttempt`, `locked`, `unlockCode`)"
				+ "VALUES (?,?,?,?,?,?,?,?)";
		
		
		try (
			Connection conn = getDBConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);	
		){
			
			fillPreparedStatement(stmt, user);
			
			stmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return;
	}
	
	/**
	 * Update existing user's information
	 * @param user
	 */
	public static void update(User user) {
		String sql = "UPDATE `user` SET `email`=?,`password`=?,`firstName`=?,`lastName`=?, `type`=?, `loginAttempt`=?, `locked`=?, `unlockCode`=? WHERE userId=?";
		
		
		try (
			Connection conn = getDBConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
		){
			
			fillPreparedStatement(stmt, user);
			stmt.setInt(9, user.getUserId());
			
			stmt.executeUpdate();
			System.out.println("USER UPDATED");
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return;
	}
	
	/**
	 *  Update a student's information if studentId > 0, otherwise add a new student
	 * @param student
	 */
	public static void addOrUpdate(User user) {
		if(user.getUserId() > 0) {
			update(user);
		} else {
			add(user);
		}
	}

	
	/**
	 * Helper Function 1: Parsing a user object
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	public static User parseUser(ResultSet rs) throws SQLException {
		User user = new User();
		user.setUserId(rs.getInt("userId"));
		user.setEmail(rs.getString("email"));
		user.setPassword(rs.getString("password"));
		user.setFirstName(rs.getString("firstName"));
		user.setLastName(rs.getString("lastName"));
		user.setType(rs.getString("type"));
		user.setLoginAttempt(rs.getInt("loginAttempt"));
		user.setLocked(rs.getBoolean("locked"));
		user.setUnlockCode(rs.getString("unlockCode"));
		return user;
	}
	
	/**
	 * Helper method 2: Fill in PreparedStatement
	 * @param stmt
	 * @param user
	 * @throws SQLException
	 */
	public static void fillPreparedStatement(PreparedStatement stmt, User user) throws SQLException {
		stmt.setString(1, user.getEmail());
		stmt.setString(2, user.getPassword());
		stmt.setString(3, user.getFirstName());
		stmt.setString(4, user.getLastName());
		stmt.setString(5, user.getType());
		stmt.setInt(6, user.getLoginAttempt());
		stmt.setBoolean(7, user.isLocked());
		stmt.setString(8, user.getUnlockCode());
	}
}

