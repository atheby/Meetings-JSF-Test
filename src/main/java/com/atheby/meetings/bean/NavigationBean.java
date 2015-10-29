package main.java.com.atheby.meetings.bean;

import java.io.Serializable; 
import javax.faces.bean.*;

@ManagedBean
@SessionScoped
public class NavigationBean implements Serializable {
	
	private static final long serialVersionUID = 1L;  
	
	public String redirectToLogin() {
		return "/login.xhtml?faces-redirect=true";
	}
	
	public String redirectToMeetings() {
		return "/secured/meeting_list.xhtml?faces-redirect=true";
	}
	
	public String login() {
		return "/login.xhtml";
	}
	
	public String register() {
		return "/register.xhtml";
	}
	
	public String meetings() {
		return "/secured/meeting_list.xhtml";
	}
}

