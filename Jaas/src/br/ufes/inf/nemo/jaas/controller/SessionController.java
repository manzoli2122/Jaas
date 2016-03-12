package br.ufes.inf.nemo.jaas.controller;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.ufes.inf.nemo.jaas.application.SessionInformation;
import br.ufes.inf.nemo.jaas.domain.User;
import br.ufes.inf.nemo.util.ejb3.controller.JSFController;





@Named
@SessionScoped
public class SessionController extends JSFController {
	
	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	
	@EJB
	private SessionInformation sessionInformation;
	
	
	public User getCurrentUser() {
		return sessionInformation.getCurrentUser();
	}
	
	
	public boolean isLoggedIn() {
		return sessionInformation.getCurrentUser() != null;
	}
	

	
	public String logout(){
		sessionInformation.logout();
		return "login.faces?faces-redirect=true";
	}
	
	
	
}
