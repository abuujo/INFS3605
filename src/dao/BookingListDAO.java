package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.Booking;
import dto.BookingList;

public class BookingListDAO extends BaseDAO {
	
	public static List<BookingList> selectAll() {
		List<BookingList> list = new ArrayList<BookingList>();
		String sql = "SELECT * FROM bookingList";
		
		try (
			Connection conn = getDBConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);	
		){
			try(ResultSet rs = stmt.executeQuery()) {
				while(rs.next()) {
					list.add(parseBookingList(rs));
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static void add(int bookingId, int studentId, int userId) {
		String sql = "INSERT INTO `bookingList`(`bookingId`, `studentId`, `userId`)"
				+ " VALUES(?,?,?)";
		
		try (
			Connection conn = getDBConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);	
		){
			stmt.setInt(1, bookingId);
			stmt.setInt(2, studentId);
			stmt.setInt(3, userId);
			
			stmt.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return;
	}
	
	public static void deleteByBookingId(int bookingId) {
		String sql = "DELETE FROM `bookingList` WHERE bookingId=?";
		
		try (
			Connection conn = getDBConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);	
		){
			stmt.setInt(1, bookingId);
			
			stmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return;			
	}
	
	public static void deleteByStudentId(int studentId) {
		String sql = "DELETE FROM `bookingList` WHERE studentId=?";
		
		try (
			Connection conn = getDBConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);	
		){
			stmt.setInt(1, studentId);
			
			stmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return;	
	}
	
	public static BookingList parseBookingList(ResultSet rs) throws SQLException {
		BookingList bl = new BookingList();
		bl.setBookingId(rs.getInt("bookingId"));
		bl.setStudentId(rs.getInt("studentId"));
		bl.setUserId(rs.getInt("userId"));
		return bl;
	}
}
