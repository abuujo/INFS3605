package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.Offense;

public class OffenseDAO extends BaseDAO {
	//TODO
	public static Offense selectById(int offenseId)  {
		String sql = "SELECT * FROM offenses WHERE offenseId=?";
		try (
			Connection conn = getDBConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);	
		){
			stmt.setInt(1, offenseId);
			
			try(ResultSet rs = stmt.executeQuery()) {
				if(rs.next()) {
					return parseOffense(rs);
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	//TODO
	public static List<Offense> selectAll()  {
		List<Offense> offenses = new ArrayList<Offense>();
		String sql = "SELECT * FROM offenses";
		try (
			Connection conn = getDBConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);	
		){
			
			try(ResultSet rs = stmt.executeQuery()) {
				while(rs.next()) {
					offenses.add(parseOffense(rs));
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return offenses;
	}
	
	//TODO
		public static List<Offense> selectAllByStudentId(int studentId)  {
			List<Offense> offenses = new ArrayList<Offense>();
			String sql = "SELECT * FROM offenses WHERE studentId =?";
			try (
				Connection conn = getDBConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);	
			){
				stmt.setInt(1, studentId);
				
				try(ResultSet rs = stmt.executeQuery()) {
					while(rs.next()) {
						offenses.add(parseOffense(rs));
					}
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
			return offenses;
		}
		
	
	//TODO
	public static void add(Offense offense)  {
		String sql = "INSERT INTO `offenses`( `studentId`, `offenseName`, `dateCommitted`)"
				+ "VALUES (?,?,?)";
		try (
			Connection conn = getDBConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);	
		){
			fillPreparedStatement(stmt, offense);
			
			stmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return;		
	}
	
	//TODO
	public static void update(Offense offense)  {
		String sql = "UPDATE `offenses` SET `studentId`=? `offenseName`=? `dateCommitted`=? WHERE offenseId=?";
		try (
			Connection conn = getDBConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);	
		){
			fillPreparedStatement(stmt, offense);
			stmt.setInt(4, offense.getOffenseId());
			
			stmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return;
	}
	
	//TODO
	public static void addOrUpdate(Offense offense)  {
		if(offense.getOffenseId() > 0) {
			update(offense);
		} else {
			add(offense);
		}		
	}
	
	/**
	 * Helper Function 1: Parsing a booking object
	 * @param rs
	 * @return
	 * @throws SQLException 
	 * @
	 */
	public static Offense parseOffense(ResultSet rs) throws SQLException  {
		Offense offense = new Offense();
		offense.setStudentId(rs.getInt("studentId"));
		offense.setOffenseName(rs.getString("offenseName"));
		offense.setDateCommitted(rs.getString("dateCommitted"));
		return offense;
	}
	
	/**
	 * Helper method 2: Fill in PreparedStatement
	 * @param stmt
	 * @param booking
	 * @throws SQLException 
	 * @
	 */
	public static void fillPreparedStatement(PreparedStatement stmt, Offense offense) throws SQLException  {
		stmt.setInt(1, offense.getStudentId());
		stmt.setString(2, offense.getOffenseName());
		stmt.setString(3, offense.getDateCommitted());
	}
}
