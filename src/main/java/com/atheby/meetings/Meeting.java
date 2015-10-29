package main.java.com.atheby.meetings;

import java.io.Serializable;
import java.util.Date;

public class Meeting implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int  id, withId, whoId;;
	private int start = 12;
	private int end = 14;
	private String status;
	private Date date = new Date(new Date().getTime() + 1000 * 60 * 60 * 24);
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public void setWithId(int withId) {
		this.withId = withId;
	}
	
	public int getWithId() {
		return withId;
	}
	
	public void setWhoId(int whoId) {
		this.whoId = whoId;
	}
	
	public int getWhoId() {
		return whoId;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setStart(int start) {
		this.start = start;
	}
	
	public int getStart() {
		return start;
	}
	
	public void setEnd(int end) {
		this.end = end;
	}
	
	public int getEnd() {
		return end;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getStatus() {
		return status;
	}
}
