package main.java.com.atheby.meetings.manager;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.*;

import main.java.com.atheby.meetings.*;

import org.hibernate.Session;

@ManagedBean
@SessionScoped
public class MeetingListManager implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value="#{loginManager}")
	private LoginManager loginManager;
	
	public List<Meeting> getMeetingsList() {
		Session session = HibernateUtility.getSessionFactory().openSession();
		@SuppressWarnings("unchecked")
		List<Meeting> meetings = (List<Meeting>) session.createQuery("from Meeting where who_id = " +  loginManager.getLoggedUserId()).list();
		System.out.println(meetings.size());
		session.close();
		return meetings;
	}

	public void setLoginManager(LoginManager loginManager) {        
		this.loginManager = loginManager;    
	}
	
	public LoginManager getLoginManager() {        
		return loginManager;    
	}
}
