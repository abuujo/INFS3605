package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.Booking;
import dto.User;
import dto.Student;

import notification.NotificationEmail;

public class BookingDAO extends BaseDAO {

	/**
	 * Select all bookings
	 * @return
	 * @throws SQLException 
	 */
	public static List<Booking> selectAll() {
		List<Booking> bookings = new ArrayList<Booking>();
		String sql = "SELECT * FROM booking";
		
		try (
			Connection conn = getDBConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
		){
			try(ResultSet rs = stmt.executeQuery()) {		
				while(rs.next()) {
					bookings.add(parseBooking(rs));
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return bookings;
	}
	
	
	public static Booking selectById(int bookingId) {
		String sql = "SELECT * FROM booking WHERE bookingId = ?";
		
		try (
			Connection conn = getDBConnection();	
			PreparedStatement stmt = conn.prepareStatement(sql);
		){
			stmt.setInt(1, bookingId);
			
			try(ResultSet rs = stmt.executeQuery()) {			
				if(rs.next()) {
					return parseBooking(rs);
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Booking selectByAllAttributes(String zId, String category, String startDate, String endDate, String location, String status, int priority) {
		String sql = "SELECT * FROM booking WHERE zId = ? and category = ? and startDate = ? and endDate = ? and location = ? and status = ? and priority =?";
		
		
		try (
			Connection conn = getDBConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
		){			
			stmt.setString(1, zId);
			stmt.setString(2, category);
			stmt.setString(3, startDate);
			stmt.setString(4, endDate);
			stmt.setString(5, location);
			stmt.setString(6, status);
			stmt.setInt(7, priority);

			try(ResultSet rs = stmt.executeQuery()) {			
				if(rs.next()) {
					return parseBooking(rs);
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static List<Booking> selectAllByZid(String zId) {
		List<Booking> list = new ArrayList<Booking>();
		String sql = "SELECT * FROM booking where zId = ?";
		
		
		try (
			Connection conn = getDBConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
		){
			stmt.setString(1, zId);
			
			try(ResultSet rs = stmt.executeQuery()) {		
				while(rs.next()) {
					list.add(parseBooking(rs));
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
		
	}
	
	
	//TODO
	public static Booking selectByNoteId(int noteId) {
		String sql = "SELECT * FROM booking where noteId = ?";
		
		try (
			Connection conn = getDBConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
		){
			stmt.setInt(1, noteId);
			
			try(ResultSet rs = stmt.executeQuery()) {			
				if(rs.next()) {
					return parseBooking(rs);
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//TODO
	public static List<Booking> selectAllByCategory(String category) {
		List<Booking> list = new ArrayList<Booking>();
		String sql = "SELECT * FROM booking where category = ?";
		
		try (
			Connection conn = getDBConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
		){
			stmt.setString(1, category);
			
			try(ResultSet rs = stmt.executeQuery()) {			
				while(rs.next()) {
					list.add(parseBooking(rs));
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//TODO
	public static List<Booking> selectAllByDate(Date startDate) {
		List<Booking> list = new ArrayList<Booking>();
		String sql = "SELECT * FROM booking where startDate = ?";
		
		
		try (
			Connection conn = getDBConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);	
		){
			stmt.setDate(1, startDate);
			
			try(ResultSet rs = stmt.executeQuery()) {		
				while(rs.next()) {
					list.add(parseBooking(rs));
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//TODO
	public static List<Booking> selectAllByLocation(String location) {
		List<Booking> list = new ArrayList<Booking>();
		String sql = "SELECT * FROM booking where location = ?";	
		
		try (
			Connection conn = getDBConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);	
		){
			stmt.setString(1, location);
			
			try(ResultSet rs = stmt.executeQuery()) {
				while(rs.next()) {
					list.add(parseBooking(rs));
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//TODO
	public static List<Booking> selectAllByStatus(String status) {
		List<Booking> list = new ArrayList<Booking>();
		String sql = "SELECT * FROM booking where status = ?";
		
		try (
				Connection conn = getDBConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);	
		){
			stmt.setString(1, status);
			
			try(ResultSet rs = stmt.executeQuery()) {			
				while(rs.next()) {
					list.add(parseBooking(rs));
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//TODO
	public static List<Booking> selectAllByPriority(int priority) {
		List<Booking> list = new ArrayList<Booking>();
		String sql = "SELECT * FROM booking where priority = ?";
		
		try (
				Connection conn = getDBConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);	
		){
			stmt.setInt(1, priority);
			
			try(ResultSet rs = stmt.executeQuery()) {			
				while(rs.next()) {
					list.add(parseBooking(rs));
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static void add(Booking booking) {
		String sql = "INSERT INTO `booking`(`zId`, `category`, `startDate`, `endDate`, `location`, `status`, `priority`)"
				+ " VALUES(?,?,?,?,?,?,?)";
		
		try (
			Connection conn = getDBConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);	
		){
			fillPreparedStatement(stmt, booking);
			
			stmt.executeUpdate();
			System.out.print("BOOKING ADDED");
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return;
	}
	

	public static void update(Booking booking) {
		String sql = "UPDATE `booking` SET `zId`=?, `category`=?, `startDate`=?, `endDate`=?, `location`=?, `status`=?, `priority`=?"
				+ " WHERE bookingId=?";
		
		try (
			Connection conn = getDBConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);	
		){
			fillPreparedStatement(stmt, booking);
			stmt.setInt(8, booking.getBookingId());
			
			stmt.executeUpdate();
			System.out.print("BOOKING UPDATED");
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return;		
	}
	
	public static void delete(Booking booking) {
		String sql = "DELETE FROM `booking` WHERE bookingId=?";
		
		try (
			Connection conn = getDBConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);	
		){
			stmt.setInt(1, booking.getBookingId());
			
			stmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return;		
	}
	
	public static void addOrUpdate(Booking booking) {
		if(booking.getBookingId() > 0) {
			update(booking);
		} else {
			add(booking);
		}	
	}
	
	/**
	 * Helper Function 1: Parsing a booking object
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	public static Booking parseBooking(ResultSet rs) throws SQLException {
		Booking booking = new Booking();
		booking.setBookingId(rs.getInt("bookingId"));
		booking.setzId(rs.getString("zId"));
		booking.setCategory(rs.getString("category"));
		booking.setStartDate(rs.getString("startDate"));
		booking.setEndDate(rs.getString("endDate"));
		booking.setLocation(rs.getString("location"));
		booking.setStatus(rs.getString("status"));
		booking.setPriority(rs.getInt("priority"));
		return booking;
	}
	
	/**
	 * Helper method 2: Fill in PreparedStatement
	 * @param stmt
	 * @param booking
	 * @throws SQLException
	 */
	public static void fillPreparedStatement(PreparedStatement stmt, Booking booking) throws SQLException {
		stmt.setString(1, booking.getzId());
		stmt.setString(2, booking.getCategory());
		stmt.setString(3, booking.getStartDate());
		stmt.setString(4, booking.getEndDate());		
		stmt.setString(5, booking.getLocation());
		stmt.setString(6, booking.getStatus());
		stmt.setInt(7, booking.getPriority());
	}
}