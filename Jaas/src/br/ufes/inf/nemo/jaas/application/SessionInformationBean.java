package br.ufes.inf.nemo.jaas.application;


import java.io.IOException;
import java.security.Principal;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.ufes.inf.nemo.jaas.domain.User;
import br.ufes.inf.nemo.jaas.persistence.UserDAO;
import br.ufes.inf.nemo.util.ejb3.persistence.exceptions.MultiplePersistentObjectsFoundException;
import br.ufes.inf.nemo.util.ejb3.persistence.exceptions.PersistentObjectNotFoundException;



@SessionScoped
@Stateful
public class SessionInformationBean implements SessionInformation {
	
	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	@Resource 	
	private SessionContext sessionC;
	
	
	@EJB        
	private UserDAO userDAO;
	

	/** The current user logged in. */
	private User currentUser;
	

	/** @see br.org.feees.sigme.core.application.SessionInformation#getCurrentUser() */
	@Override
	public User getCurrentUser() {
		
		if(currentUser==null){
			Principal principal = sessionC.getCallerPrincipal();
			if(principal != null){
				try {
					currentUser = userDAO.retrieveByName(principal.getName());
				} catch (PersistentObjectNotFoundException | MultiplePersistentObjectsFoundException e) {
					//e.printStackTrace();
				}
			}	
		}
		return currentUser;
	}
	
	/** @see sae.core.application.SessionService#logout() */
	@Override
	public void logout(){
		
		currentUser = null;
		
		FacesContext fc = FacesContext.getCurrentInstance();  
		
		// Destroys the session for this user.
		HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);     
		session.invalidate(); 
		
		// Redirects back to the initial page.
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath());
		} catch (IOException e) {} 
		
	}
	

	
}
