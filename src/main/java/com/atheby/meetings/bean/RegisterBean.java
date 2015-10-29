package main.java.com.atheby.meetings.bean;

import java.io.Serializable;

import javax.faces.bean.*;

import main.java.com.atheby.meetings.User;
import main.java.com.atheby.meetings.manager.RegisterManager;

@ManagedBean
@SessionScoped
public class RegisterBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private User user = new User();
	
	@ManagedProperty(value="#{registerManager}")
	private RegisterManager registerManager;
	@ManagedProperty(value="#{navigationBean}")
	private NavigationBean navigationBean;
	
	public String register() {
		if(registerManager.add(user)){
			user = new User();
			return navigationBean.redirectToLogin();
		}
		return "error";
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setRegisterManager(RegisterManager registerManager) {        
		this.registerManager = registerManager;    
	}
	
	public void setNavigationBean(NavigationBean navigationBean) {        
		this.navigationBean = navigationBean;    
	}
}
