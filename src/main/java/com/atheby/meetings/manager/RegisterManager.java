package main.java.com.atheby.meetings.manager;

import java.io.Serializable;

import main.java.com.atheby.meetings.*;
import main.java.com.atheby.meetings.bean.NavigationBean;

import org.hibernate.Session;

import java.security.spec.InvalidKeySpecException;
import java.security.*;
import java.util.*;

import javax.faces.bean.*;

@ManagedBean
@SessionScoped
public class RegisterManager implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private PasswordManager passMngr = new PasswordManager();
	
	@ManagedProperty(value="#{navigationBean}")
	private NavigationBean navigationBean;
	
	public Boolean add(User user) {
		
		try {
			user.setSalt(passMngr.generateSalt());
			user.setEncryptedPassword(passMngr.encryptPassword(user.getPassword(), user.getSalt()));
			user.setPassword("");
			
			Session session = HibernateUtility.getSessionFactory().openSession();
			
			session.beginTransaction();		
			session.save(user);
			@SuppressWarnings("unchecked")
			List<User> list = (List<User>) session.createQuery("from User where username = '" + user.getUsername() + "'" ).list();
			session.save(new Salt(list.get(0).getId(), user.getSalt()));		
			session.getTransaction().commit();
			session.close();
			
			return true;
			
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();
		}
	 
		return false;
	}
	
	public void setNavigationBean(NavigationBean navigationBean) {        
		this.navigationBean = navigationBean;    
	}
}
