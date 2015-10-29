package main.java.com.atheby.meetings;

import java.io.Serializable;

public class Salt implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int id, userId;
	private byte[] salt;
	
	public Salt() {}
	
	public Salt(int userId, byte[] salt) {
		this.userId = userId;
		this.salt = salt;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public void setSalt(byte[] salt) {
		this.salt = salt;
	}
	
	public byte[] getSalt() {
		return salt;
	}
}
