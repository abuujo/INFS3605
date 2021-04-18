package dto;

public class Enrolment implements Comparable<Enrolment> {

	private int courseId;	//Foreign key
	private int studentId;	//Foregin key
	private int mark;
	private String semYear;
	private String courseCode;
	private String zId;
	
	
	public String getzId() {
		return zId;
	}
	public void setzId(String zId) {
		this.zId = zId;
	}
	public int getMark() {
		return mark;
	}
	public void setMark(int mark) {
		this.mark = mark;
	}
	public String getSemYear() {
		return semYear;
	}
	public void setSemYear(String semYear) {
		this.semYear = semYear;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}	
	public String getCourseCode() {
		return courseCode;
	}
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	
	@Override
	public int compareTo(Enrolment o) {
		// TODO Auto-generated method stub
		return (studentId - o.getStudentId());
	}

}
