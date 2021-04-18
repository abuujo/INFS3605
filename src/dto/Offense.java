package dto;

public class Offense {
	private int offenseId;
	private int studentId;
	private String offenseName;
	private String dateCommitted;
	
	
	public int getOffenseId() {
		return offenseId;
	}
	public void setOffenseId(int offenseId) {
		this.offenseId = offenseId;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public String getOffenseName() {
		return offenseName;
	}
	public void setOffenseName(String offenseName) {
		this.offenseName = offenseName;
	}
	public String getDateCommitted() {
		return dateCommitted;
	}
	public void setDateCommitted(String dateCommitted) {
		this.dateCommitted = dateCommitted;
	}
	
	
}
