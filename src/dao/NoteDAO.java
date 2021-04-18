package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.Enrolment;
import dto.Note;

public class NoteDAO extends BaseDAO{

	//TODO
	public static Note selectById(int noteId)  {
		String sql = "SELECT * FROM note where noteId =?";
		try (
			Connection conn = getDBConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);	
		){
			stmt.setInt(1, noteId);
			
			try(ResultSet rs = stmt.executeQuery()) {
				if(rs.next()) {
					return parseNote(rs);
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//TODO: this function is for the search function
	// ie. return list of Note, if note contains "text"
	
	public static List<Note> selectAllByTexts(String texts)  {
		List<Note> list = new ArrayList<Note>();
		String sql = "SELECT * FROM note where texts =?";
		try (
			Connection conn = getDBConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);	
		){
			stmt.setString(1, texts);
			
			try(ResultSet rs = stmt.executeQuery()) {
				while(rs.next()) {
					list.add(parseNote(rs));
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//TODO
	public static List<Note> selectAllByDateCreated(Date dateCreated)  {
		List<Note> list = new ArrayList<Note>();
		String sql = "SELECT * FROM note where dateCreated =?";
		try (
			Connection conn = getDBConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);	
		){
			stmt.setDate(1, dateCreated);
			
			try(ResultSet rs = stmt.executeQuery()) {
				while(rs.next()) {
					list.add(parseNote(rs));
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<Note> selectAllByBookingId(int bookingId)  {
		List<Note> list = new ArrayList<Note>();
		String sql = "SELECT * FROM note where bookingId=?";
		try (
			Connection conn = getDBConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);	
		){
			stmt.setInt(1, bookingId);
			
			try(ResultSet rs = stmt.executeQuery()) {
				while(rs.next()) {
					list.add(parseNote(rs));
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static void add(Note note)  {
		String sql = "INSERT INTO `note`(`texts`, `dateCreated`, `bookingId`)"
				+ "VALUES (?,?,?)";
		try (
			Connection conn = getDBConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);	
		){
			fillPreparedStatement(stmt, note);
			
			stmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return;
	}
	
	public static void update(Note note)  {
		String sql = "UPDATE `note` SET `texts`=?,`dateCreated`=? WHERE noteId=?";
		try (
			Connection conn = getDBConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);	
		){
			stmt.setString(1, note.getTexts());
			stmt.setString(2, note.getDateCreated());
			stmt.setInt(3, note.getNoteId());
			
			stmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return;
	}
	
	public static void addOrUpdate(Note note)  {
		if(note.getNoteId() > 0) {
			update(note);
		} else {
			add(note);
		}
	}
	
	public static void delete(int noteId)  {
		String sql = "DELETE FROM `note` WHERE noteId=?";
		try (
			Connection conn = getDBConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);	
		){
			stmt.setInt(1, noteId);
			
			stmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return;
	}
	
	public static Note parseNote(ResultSet rs) throws SQLException  {
		Note note = new Note();
		note.setNoteId(rs.getInt("noteId"));
		note.setTexts(rs.getString("texts"));
		note.setDateCreated(rs.getString("dateCreated"));
		return note;
	}
	
	/**
	 * Helper method 2: Fill in PreparedStatement
	 * @param stmt
	 * @param booking
	 * @throws SQLException 
	 * @
	 */
	public static void fillPreparedStatement(PreparedStatement stmt, Note note) throws SQLException  {
		stmt.setString(1, note.getTexts());
		stmt.setString(2, note.getDateCreated());
		stmt.setInt(3, note.getBookingId());
	}
}
