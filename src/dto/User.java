package dto;

public class User {
	
	private int userId; // Primary key
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private String type;
	private int loginAttempt;
	private boolean locked; // 0: false, other: true
	private String unlockCode;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getLoginAttempt() {
		return loginAttempt;
	}
	public void setLoginAttempt(int loginAttempt) {
		this.loginAttempt = loginAttempt;
	}
	public boolean isLocked() {
		return locked;
	}
	public void setLocked(boolean locked) {
		this.locked = locked;
	}
	public String getUnlockCode() {
		return unlockCode;
	}
	public void setUnlockCode(String unlockCode) {
		this.unlockCode = unlockCode;
	}
	
}

