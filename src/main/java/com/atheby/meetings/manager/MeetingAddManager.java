package main.java.com.atheby.meetings.manager;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.*;

import main.java.com.atheby.meetings.*;

import org.hibernate.Session;

@ManagedBean
@SessionScoped
public class MeetingAddManager implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value="#{loginManager}")
	private LoginManager loginManager;
	
	public Boolean add(Meeting meeting){
		
			meeting.setWhoId(loginManager.getLoggedUserId());
			meeting.setStatus("Pending");
			
			Session session = HibernateUtility.getSessionFactory().openSession();
			
			session.beginTransaction();		
			session.save(meeting);
			session.getTransaction().commit();
			session.close();
			
			return true;
	}
	
	public List<User> getUsersList() {
		Session session = HibernateUtility.getSessionFactory().openSession();
		@SuppressWarnings("unchecked")
		List<User> users = (List<User>) session.createQuery("from User where id != " +  loginManager.getLoggedUserId()).list();
		session.close();
		return users;
	}
	
	public void setLoginManager(LoginManager loginManager) {        
		this.loginManager = loginManager;    
	}
	
	public LoginManager getLoginManager() {        
		return loginManager;    
	}
}
