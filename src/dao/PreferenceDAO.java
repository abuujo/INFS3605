package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.Preference;

public class PreferenceDAO extends BaseDAO {
	//
	public static Preference selectById(int preferenceId)  {
		String sql = "SELECT * FROM preferences WHERE preferenceId=?";
		try (
			Connection conn = getDBConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);	
		){
			stmt.setInt(1, preferenceId);
			
			try(ResultSet rs = stmt.executeQuery()) {
				if(rs.next()) {
					return parsePreference(rs);
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	//TODO
	public static List<Preference> selectAll()  {
		List<Preference> preferences = new ArrayList<Preference>();
		String sql = "SELECT * FROM preferences";
		try (
			Connection conn = getDBConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);	
		){
			
			try(ResultSet rs = stmt.executeQuery()) {
				while(rs.next()) {
					preferences.add(parsePreference(rs));
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return preferences;
	}
	
	public static List<Preference> selectAllByIPLevel(String IPLevel)  {
		List<Preference> preferences = new ArrayList<Preference>();
		String sql = "SELECT * FROM preferences WHERE IPLEvel = ?";
		try (
			Connection conn = getDBConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);	
		){
			stmt.setString(1, IPLevel);
			
			try(ResultSet rs = stmt.executeQuery()) {
				while(rs.next()) {
					preferences.add(parsePreference(rs));
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return preferences;
	}
	
	//TODO
	public static List<Preference> selectByStudentId(int studentId)  {
		List<Preference> list = new ArrayList<Preference>();
		String sql = "SELECT * FROM preferences WHERE studentId=?";
		try (
			Connection conn = getDBConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);	
		){
			stmt.setInt(1, studentId);
			
			try(ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					list.add(parsePreference(rs));
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
		
	//TODO
	public static Preference selectByStudentIdAndIpLevel(int studentId, String ipLevel)  {
		String sql = "SELECT * FROM preferences WHERE studentId=? and IPLevel=?";
		try (
			Connection conn = getDBConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);	
		){
			stmt.setInt(1, studentId);
			stmt.setString(2, ipLevel);
			
			try(ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					return (parsePreference(rs));
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	//TODO
	public static void add(Preference preference)  {
		String sql = "INSERT INTO `preferences`( `studentId`, `firstPref`, `secondPref`, `thirdPref`, `fourthPref`, `fifthPref`, `sixthPref`, `IPLevel`)"
				+ "VALUES (?,?,?,?,?,?,?,?)";
		try (
			Connection conn = getDBConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);	
		){
			fillPreparedStatement(stmt, preference);
			
			stmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return;		
	}
	
	//TODO
	public static void update(Preference preference)  {
		String sql = "UPDATE `preferences` SET `studentId`=? ,`firstPref`=?, `secondPref`=?, `thirdPref`=?, `fourthPref`=?, `fifthPref`=?, `sixthPref`=?, `IPLevel`=? WHERE preferenceId=?";
		try (
			Connection conn = getDBConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);	
		){
			fillPreparedStatement(stmt, preference);
			stmt.setInt(9, preference.getPreferenceId());
			
			stmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return;
	}
	
	//TODO
	public static void addOrUpdate(Preference preference)  {
		if(preference.getPreferenceId() > 0) {
			update(preference);
		} else {
			add(preference);
		}		
	}
	
	public static void delete(Preference preference) {
		String sql = "DELETE FROM `preferences` WHERE preferenceId=?";
		try (
			Connection conn = getDBConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);	
		){
			stmt.setInt(1, preference.getPreferenceId());
			
			stmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return;	
	}
	
	/**
	 * Helper Function 1: Parsing a booking object
	 * @param rs
	 * @return
	 * @throws SQLException 
	 * @
	 */
	public static Preference parsePreference(ResultSet rs) throws SQLException  {
		Preference preference = new Preference();
		preference.setPreferenceId(rs.getInt("preferenceId"));
		preference.setStudentId(rs.getInt("studentId"));
		preference.setFirstPref(rs.getInt("firstPref"));
		preference.setSecondPref(rs.getInt("secondPref"));
		preference.setThirdPref(rs.getInt("thirdPref"));
		preference.setFourthPref(rs.getInt("fourthPref"));
		preference.setFifthPref(rs.getInt("fifthPref"));
		preference.setSixthPref(rs.getInt("sixthPref"));
		preference.setIPLevel(rs.getString("IPLevel"));
		return preference;
	}
	
	/**
	 * Helper method 2: Fill in PreparedStatement
	 * @param stmt
	 * @param booking
	 * @throws SQLException 
	 * @
	 */
	public static void fillPreparedStatement(PreparedStatement stmt, Preference preference) throws SQLException  {
		stmt.setInt(1, preference.getStudentId());
		stmt.setInt(2, preference.getFirstPref());
		stmt.setInt(3, preference.getSecondPref());
		stmt.setInt(4, preference.getThirdPref());
		stmt.setInt(5, preference.getFourthPref());
		stmt.setInt(6, preference.getFifthPref());
		stmt.setInt(7, preference.getSixthPref());
		stmt.setString(8, preference.getIPLevel());
	}
}
