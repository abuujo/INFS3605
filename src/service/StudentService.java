package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.ProgramDAO;
import dao.StudentDAO;
import dto.Program;
import dto.Student;

public class StudentService {

	public static void addStudent(Student student)  {
		StudentDAO.addOrUpdate(student);
	}
	
	public static List<Student> getStudentList()  {
		List<Student> list = new ArrayList<Student>();
		list = StudentDAO.selectAll();
		for(Student student : list) {
			Program program = ProgramDAO.selectById(student.getProgramId());
			student.setProgram(program.getProgramName());
		}
		
		return list;
	}
	
	public static List<Student> getStudentsBySearch(String query)  {
		List<Student> all = getStudentList();
		List<Student> result = new ArrayList<Student>();
		
		for(Student student : all) {
			if(student.getzId() != null && student.getzId().toLowerCase().contains(query.toLowerCase())) {
				result.add(student);
			} else if(student.getFirstName() != null && student.getFirstName().toLowerCase().contains(query.toLowerCase())) {
				result.add(student);
			} else if(student.getLastName() != null && student.getLastName().toLowerCase().contains(query.toLowerCase())) {
				result.add(student);
			} else if(student.getEmail() != null && student.getEmail().toLowerCase().contains(query.toLowerCase())) {
				result.add(student);
			} else if(student.getProgram() != null && student.getProgram().toLowerCase().contains(query.toLowerCase())) {
				result.add(student);
			} else {
				//
			}
		}
		
		return result;
	}
	
	public static Student getStudentById(int studentId)  {
		Student student = StudentDAO.selectById(studentId);
		return student;
	}
	
	public static void update(Student student)  {
		StudentDAO.update(student);
	}
	
	public static void setProgramName(List<Student> list) {
		for(Student s : list) {
			s.setProgram(ProgramDAO.selectById(s.getProgramId()).getProgramName());
		}
	}
}
