package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.Booking;
import dto.Sponsor;

public class SponsorDAO extends BaseDAO {

	//TODO
		public static Sponsor selectById(int sponsorId)  {
			String sql = "SELECT * FROM sponsors WHERE sponsorId=?";
			try (
				Connection conn = getDBConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);	
			){
				stmt.setInt(1, sponsorId);
				
				try(ResultSet rs = stmt.executeQuery()) {
					if(rs.next()) {
						return parseSponsor(rs);
					}
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
			return null;
		}
		
		//TODO
		public static Sponsor selectBySponsorName(String sponsorName)  {
			String sql = "SELECT * FROM sponsors WHERE sponsorName=?";
			try (
				Connection conn = getDBConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);	
			){
				stmt.setString(1, sponsorName);
				
				try(ResultSet rs = stmt.executeQuery()) {
					if(rs.next()) {
						return parseSponsor(rs);
					}
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
			return null;
		}
		
		//TODO
		public static List<Sponsor> selectAll()  {
			List<Sponsor> sponsors = new ArrayList<Sponsor>();
			String sql = "SELECT * FROM sponsors";
			try (
				Connection conn = getDBConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);	
			){
				try(ResultSet rs = stmt.executeQuery()) {
					while(rs.next()) {
						sponsors.add(parseSponsor(rs));
					}
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
			return sponsors;
		}
		
		//TODO
		public static void add(Sponsor sponsor)  {
			String sql = "INSERT INTO `sponsors`( `sponsorName`, `description`)"
					+ "VALUES (?,?)";
			try (
				Connection conn = getDBConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);	
			){
				fillPreparedStatement(stmt, sponsor);
				
				stmt.executeUpdate();
			} catch(SQLException e) {
				e.printStackTrace();
			}
			return;		
		}
		
		//TODO
		public static void update(Sponsor sponsor)  {
			String sql = "UPDATE `sponsors` SET `sponsorName`=?, `description`=? WHERE sponsorId=?";
			try (
				Connection conn = getDBConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);	
			){
				fillPreparedStatement(stmt, sponsor);
				stmt.setInt(3, sponsor.getSponsorId());
				
				stmt.executeUpdate();
			} catch(SQLException e) {
				e.printStackTrace();
			}
			return;
		}
		
		//TODO
		public static void addOrUpdate(Sponsor sponsor)  {
			if(sponsor.getSponsorId() > 0) {
				update(sponsor);
			} else {
				add(sponsor);
			}		
		}
		
		public static void delete(Sponsor sponsor) {
			String sql = "DELETE FROM `sponsors` WHERE sponsorId=?";
			try (
				Connection conn = getDBConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);	
			){
				stmt.setInt(1, sponsor.getSponsorId());
				
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
		public static Sponsor parseSponsor(ResultSet rs) throws SQLException  {
			Sponsor Sponsor = new Sponsor();
			Sponsor.setSponsorId(rs.getInt("sponsorId"));
			Sponsor.setSponsorName(rs.getString("sponsorName"));
			Sponsor.setDescription(rs.getString("description"));
			return Sponsor;
		}
		
		/**
		 * Helper method 2: Fill in PreparedStatement
		 * @param stmt
		 * @param booking
		 * @throws SQLException 
		 * @
		 */
		public static void fillPreparedStatement(PreparedStatement stmt, Sponsor sponsor) throws SQLException  {
			stmt.setString(1, sponsor.getSponsorName());
			stmt.setString(2, sponsor.getDescription());
		}
	
	
}
