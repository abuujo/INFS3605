package dto;

public class Student {

	private int studentId;	//Primary key
	private String zId;
	private String firstName;
	private String lastName;
	private String email;
	private int programId;
	private String privEmail;
	private String privPhone;
	private String workPhone;
	private String gender;
	private String address;
	private String currentWam;
	private String yearEnrolled;
	private String type;
	private String ip1;
	private String ip2;
	private String ip3;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	private String program;
	
	
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public String getzId() {
		return zId;
	}
	public void setzId(String zId) {
		this.zId = zId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getProgramId() {
		return programId;
	}
	public void setProgramId(int programId) {
		this.programId = programId;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getCurrentWam() {
		return currentWam;
	}
	public void setCurrentWam(String currentWam) {
		this.currentWam = currentWam;
	}
	public String getYearEnrolled() {
		return yearEnrolled;
	}
	public void setYearEnrolled(String yearEnrolled) {
		this.yearEnrolled = yearEnrolled;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPrivPhone() {
		return privPhone;
	}
	public void setPrivPhone(String privPhone) {
		this.privPhone = privPhone;
	}
	public String getWorkPhone() {
		return workPhone;
	}
	public void setWorkPhone(String workPhone) {
		this.workPhone = workPhone;
	}
	public String getPrivEmail() {
		return privEmail;
	}
	public void setPrivEmail(String privEmail) {
		this.privEmail = privEmail;
	}

	public String getProgram() {
		return program;
	}
	public void setProgram(String program) {
		this.program = program;
	}
	public String getIp1() {
		return ip1;
	}
	public void setIp1(String ip1) {
		this.ip1 = ip1;
	}
	public String getIp2() {
		return ip2;
	}
	public void setIp2(String ip2) {
		this.ip2 = ip2;
	}
	public String getIp3() {
		return ip3;
	}
	public void setIp3(String ip3) {
		this.ip3 = ip3;
	}
	

}