package main.java.com.atheby.meetings.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.*;

import main.java.com.atheby.meetings.*;
import main.java.com.atheby.meetings.manager.MeetingAddManager;

@ManagedBean
@RequestScoped
public class MeetingAddBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Meeting meeting = new Meeting();
	
	@ManagedProperty(value="#{meetingAddManager}")
	private MeetingAddManager meetingAddManager;
	@ManagedProperty(value="#{navigationBean}")
	private NavigationBean navigationBean;
	
	
	public String addMeeting() {
		if(meetingAddManager.add(meeting)){
			meeting = new Meeting();
			return navigationBean.redirectToMeetings();
		}
		return "error";
	}
	
	public void setMeeting(Meeting meeting) {
		this.meeting = meeting;
	}
	
	public Meeting getMeeting() {
		return meeting;
	}
	
	public List<User> getUsersList() {
		return meetingAddManager.getUsersList();
	}
	
	public void setMeetingAddManager(MeetingAddManager meetingAddManager) {        
		this.meetingAddManager = meetingAddManager;    
	}
	
	public MeetingAddManager getMeetingAddManager() {        
		return meetingAddManager;    
	}
	
	public void setNavigationBean(NavigationBean navigationBean) {        
		this.navigationBean = navigationBean;    
	}
}
