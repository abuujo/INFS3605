package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.Program;

public class ProgramDAO extends BaseDAO {
	//TODO
	public static Program selectById(int programId)  {
		String sql = "SELECT * FROM program WHERE programId=?";
		try (
			Connection conn = getDBConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);	
		){
			stmt.setInt(1, programId);
			
			try(ResultSet rs = stmt.executeQuery()) {
				if(rs.next()) {
					return parseProgram(rs);
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//TODO
	public static Program selectByProgramCode(String programCode)  {
		String sql = "SELECT * FROM program WHERE programCode=?";
		try (
			Connection conn = getDBConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);	
		){
			stmt.setString(1, programCode);
			
			try(ResultSet rs = stmt.executeQuery()) {
				if(rs.next()) {
					return parseProgram(rs);
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//TODO
	public static Program selectByProgramName(String programName)  {
		String sql = "SELECT * FROM program WHERE programName=?";
		try (
			Connection conn = getDBConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);	
		){
			stmt.setString(1, programName);
			
			try(ResultSet rs = stmt.executeQuery()) {
				if(rs.next()) {
					return parseProgram(rs);
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//TODO
	public static List<Program> selectAll()  {
		List<Program> programs = new ArrayList<Program>();
		String sql = "SELECT * FROM program";
		try (
			Connection conn = getDBConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);	
		){	
			try(ResultSet rs = stmt.executeQuery()) {
				while(rs.next()) {
					programs.add(parseProgram(rs));
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return programs;
	}
	
	//TODO
	public static void add(Program program)  {
		String sql = "INSERT INTO `program`( `programCode`, `programName`, `handbookYear`)"
				+ "VALUES (?,?,?)";
		try (
			Connection conn = getDBConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);	
		){
			fillPreparedStatement(stmt, program);
			
			stmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return;		
	}
	
	//TODO
	public static void update(Program program)  {
		String sql = "UPDATE `program` SET `programCode`=? `programName`=? `handbookYear`=? WHERE programId=?";
		try (
			Connection conn = getDBConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);	
		){
			fillPreparedStatement(stmt, program);
			stmt.setInt(4, program.getProgramId());
			
			stmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return;
	}
	
	//TODO
	public static void addOrUpdate(Program program)  {
		if(program.getProgramId() > 0) {
			update(program);
		} else {
			add(program);
		}		
	}
	
	/**
	 * Helper Function 1: Parsing a booking object
	 * @param rs
	 * @return
	 * @throws SQLException 
	 * @
	 */
	public static Program parseProgram(ResultSet rs) throws SQLException  {
		Program program = new Program();
		program.setProgramId(rs.getInt("programId"));
		program.setProgramCode(rs.getString("programCode"));
		program.setProgramName(rs.getString("programName"));
		program.setHandbookYear(rs.getString("handbookYear"));
		return program;
	}
	
	/**
	 * Helper method 2: Fill in PreparedStatement
	 * @param stmt
	 * @param booking
	 * @throws SQLException 
	 * @
	 */
	public static void fillPreparedStatement(PreparedStatement stmt, Program program) throws SQLException  {
		stmt.setString(1, program.getProgramCode());
		stmt.setString(2, program.getProgramName());
		stmt.setString(3, program.getHandbookYear());
	}
}