package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.CourseDAO;
import dto.Course;

public class CourseService {

	public static void addCourse(Course course)  {
		CourseDAO.add(course);
	}
	
	public static List<Course> getAllCourses()  {
		List<Course> list = new ArrayList<Course>();
		list = CourseDAO.selectAll();
		
		return list;
	}
	
	public static List<String> getAllCourseCodes() {
		List<String> list = new ArrayList<String>();
		List<Course> all = getAllCourses();
		
		for(Course c : all) {
			list.add(c.getCourseCode());
		}
		return list;
	}
	
	public static List<Course> getCoursesBySearch(String query)  {
		List<Course> all = getAllCourses();
		List<Course> result = new ArrayList<Course>();
		
		for(Course course : all) {
			if(course.getCourseCode() != null && course.getCourseCode().toLowerCase().contains(query.toLowerCase())) {
				result.add(course);
			} else if(course.getCourseName() != null && course.getCourseName().toLowerCase().contains(query.toLowerCase())) {
				result.add(course);
			}
		}
		return result;
	}
	
	public static Course getCourseByCourseCode(String code)  {
		return CourseDAO.selectByCourseCode(code);
	}
}
