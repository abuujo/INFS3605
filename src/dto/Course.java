package dto;

public class Course {

	private int courseId;	// Primary key
	private String courseCode;
	private String courseName;
	private String uoc;
	
	
	public String getCourseCode() {
		return courseCode;
	}
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getUoc() {
		return uoc;
	}
	public void setUoc(String uoc) {
		this.uoc = uoc;
	}
	
}
