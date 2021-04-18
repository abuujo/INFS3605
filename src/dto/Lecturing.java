package dto;

public class Lecturing {

	private int coordinatorId;	//Foreign Key
	private int courseId;	//Foreign Key
	private String semester;
	
	
	public int getCoordinatorId() {
		return coordinatorId;
	}
	public void setCoordinatorId(int coordinatorId) {
		this.coordinatorId = coordinatorId;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}

}
