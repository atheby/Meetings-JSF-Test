package main.java.com.atheby.meetings.manager;

import java.io.Serializable; 
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.bean.*;

import main.java.com.atheby.meetings.*;
import main.java.com.atheby.meetings.bean.NavigationBean;

import org.hibernate.Session;

@ManagedBean
@SessionScoped
public class LoginManager implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String username;    
	private String password;
	private Integer loggedUserId;
	private boolean loggedIn;
	private PasswordManager passMngr = new PasswordManager();
	private FacesMessage msg;
	
	@ManagedProperty(value="#{navigationBean}")
	private NavigationBean navigationBean;
	
	public Boolean authenticate(String username, String password) {
		
		this.username = username;
		this.password = password;
		
		Session session = HibernateUtility.getSessionFactory().openSession();
		
		@SuppressWarnings("unchecked")
		List<User> users = (List<User>) session.createQuery("from User where username = '" + username + "'" ).list();
		if(users.size() == 0)
			msg = new FacesMessage("Username doesn't exist!", "ERROR MSG");
		else {
			@SuppressWarnings("unchecked")
			List<Salt> salts = (List<Salt>) session.createQuery("from Salt where userId = '" + users.get(0).getId() + "'" ).list();
			try {
				if(passMngr.authenticate(password, users.get(0).getEncryptedPassword(), salts.get(0).getSalt()))
					{
						session.close();
						loggedUserId = users.get(0).getId();
						
						return true;
					}
				msg = new FacesMessage("Password doesn't match!", "ERROR MSG");
			} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
				msg = new FacesMessage("Unexpected error!", "ERROR MSG");
			}
		}
		session.close();
		
		msg.setSeverity(FacesMessage.SEVERITY_ERROR);
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return false;
	}
	
	public String logout() {
		
		loggedUserId = null;
		FacesMessage msg = new FacesMessage("You are logged out", "INFO MSG");
		msg.setSeverity(FacesMessage.SEVERITY_INFO);
		FacesContext.getCurrentInstance().addMessage(null, msg);
		return navigationBean.login();
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Integer getLoggedUserId() {
		return loggedUserId;
	}
	
	public void setLoggedUserId(Integer loggedUserId) {
		this.loggedUserId = loggedUserId;
	}
	
	public boolean isLoggedIn() {
		return loggedIn;
	}
	
	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
	
	public void setNavigationBean(NavigationBean navigationBean) {
		this.navigationBean = navigationBean;
	}
}


