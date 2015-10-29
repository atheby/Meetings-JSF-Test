package main.java.com.atheby.meetings.bean;

import java.io.Serializable;

import javax.faces.bean.*;

import main.java.com.atheby.meetings.manager.LoginManager;

@ManagedBean
@SessionScoped
public class LoginBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String username;    
	private String password;
	
	@ManagedProperty(value="#{loginManager}")
	private LoginManager loginManager;
	@ManagedProperty(value="#{navigationBean}")
	private NavigationBean navigationBean;
	
	public String login() {
		if(loginManager.authenticate(username, password))
			return navigationBean.redirectToMeetings();
		return "error";
	}
	
	public String logout() {
		return loginManager.logout();
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
	
	public void setLoginManager(LoginManager loginManager) {
		this.loginManager = loginManager;
	}
	
	public LoginManager getLoginManager() {        
		return loginManager;    
	}
	
	public void setNavigationBean(NavigationBean navigationBean) {        
		this.navigationBean = navigationBean;    
	}
	
	public NavigationBean getNavigationBean() {        
		return navigationBean;    
	}
}
