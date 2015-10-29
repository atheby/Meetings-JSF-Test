package main.java.com.atheby.meetings.validator;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.validator.*;
import javax.faces.component.*;

import main.java.com.atheby.meetings.*;

import java.util.List;

import org.hibernate.Session;

@FacesValidator("meetingAddValidator")
public class MeetingAddValidator implements Validator {

	@Override
    public void validate(FacesContext context, UIComponent component, Object obj) {
 
        Object withId = ((UIInput) context.getViewRoot().findComponent("meeting-add-form:withId")).getSubmittedValue();
        Object date = ((UIInput) context.getViewRoot().findComponent("meeting-add-form:date")).getSubmittedValue();
        Integer start = Integer.parseInt((String)((UIInput) context.getViewRoot().findComponent("meeting-add-form:start")).getSubmittedValue());
        Integer end = Integer.parseInt((String)((UIInput) context.getViewRoot().findComponent("meeting-add-form:end")).getSubmittedValue());
        
        Session session = HibernateUtility.getSessionFactory().openSession();

        @SuppressWarnings("unchecked")
        List<Meeting> meetings = (List<Meeting>) session.createQuery("from Meeting where withId = " + withId + "and date = '" + date.toString() + "'").list();
        
        session.close();
        
        for(Meeting meeting : meetings){
        	if((start >= meeting.getStart() && start < meeting.getEnd()) || (end > meeting.getStart() && end <= meeting.getEnd()) ||
        			((start <= meeting.getStart()) && (end >= meeting.getEnd())))
        		throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_WARN, "Person already has a meeting.", null));
        }
    }
}
