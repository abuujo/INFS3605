package dto;

import java.sql.Date;

public class Booking implements Comparable<Booking> {

	private int bookingId;	//Primary key
	private String studentType;
	private String zId;
	private String category;
	private String startDate;
	private String endDate;
	private String location;
	private String status;
	private int priority;
	
	
	
	public String getStudentType() {
		return studentType;
	}
	public void setStudentType(String studentType) {
		this.studentType = studentType;
	}
	public int getBookingId() {
		return bookingId;
	}
	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}
	public String getzId() {
		return zId;
	}
	public void setzId(String zId) {
		this.zId = zId;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getStartDate() {
		return startDate;
	}



	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}



	public String getEndDate() {
		return endDate;
	}



	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}



	public String getLocation() {
		return location;
	}



	public void setLocation(String location) {
		this.location = location;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	public int getPriority() {
		return priority;
	}



	public void setPriority(int priority) {
		this.priority = priority;
	}



	@Override
	public int compareTo(Booking o) {
		return getStartDate().compareTo(o.getStartDate());
	}
		
}
