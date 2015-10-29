package main.java.com.atheby.meetings.validator;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.validator.*;
import javax.faces.component.*;

@FacesValidator("registerValidator")
public class RegisterValidator implements Validator {
	
	@Override
    public void validate(FacesContext context, UIComponent component, Object obj) {
 
        Object username = ((UIInput) context.getViewRoot().findComponent("register:username")).getSubmittedValue();
        Object email = ((UIInput) context.getViewRoot().findComponent("register:email")).getSubmittedValue();
        Object password = ((UIInput) context.getViewRoot().findComponent("register:password")).getSubmittedValue();
        Object repass = ((UIInput) context.getViewRoot().findComponent("register:repass")).getSubmittedValue();
        
        if (username.equals("") || email.equals("") || password.equals("") || repass.equals(""))
        	throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fill out all required fields", null));
        else
        	if(!password.equals(repass))
        		throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Passwords don't match!", null));
    }
}
