package main.java.com.atheby.meetings.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.*;

import main.java.com.atheby.meetings.Meeting;
import main.java.com.atheby.meetings.manager.MeetingListManager;

@ManagedBean
@SessionScoped
public class MeetingListBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value="#{meetingListManager}")
	private MeetingListManager meetingListManager;
	
	public List<Meeting> getMeetingsList() {
		return meetingListManager.getMeetingsList();
	}
	
	public void setMeetingListManager(MeetingListManager meetingListManager) {        
		this.meetingListManager = meetingListManager;    
	}
	
	public MeetingListManager getMeetingListManager() {        
		return meetingListManager;    
	}
}
