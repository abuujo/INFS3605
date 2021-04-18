package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dao.ProgramDAO;
import dto.*;

public class ProgramService {

	public static List<String> getAllProgramNames(List<Program> programs) {
		List<String> names = new ArrayList<String>();
		
		for(Program program : programs) {
			names.add(program.getProgramName());
		}
		
		Collections.sort(names);
		
		return names;
	}
	
	public static List<Program> getAllPrograms()  {
		List<Program> list = ProgramDAO.selectAll();
		return list;
	}
	
	public static List<Program> getProgramsBySearch(String query)  {
		List<Program> all = getAllPrograms();
		List<Program> result = new ArrayList<Program>();
		
		for(Program program : all) {
			if(program.getProgramCode() != null && program.getProgramCode().toLowerCase().contains(query.toLowerCase())) {
				result.add(program);
			} else if(program.getProgramName() != null && program.getProgramName().toLowerCase().contains(query.toLowerCase())) {
				result.add(program);
			} else if(program.getHandbookYear() != null && program.getHandbookYear().contains(query)) {
				result.add(program);
			}
		}
		return result;
	}
}