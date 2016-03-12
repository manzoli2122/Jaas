package br.ufes.inf.nemo.jaas.controller;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.ufes.inf.nemo.jaas.application.ManageUsersService;
import br.ufes.inf.nemo.jaas.domain.User;
import br.ufes.inf.nemo.util.ejb3.application.CrudService;
import br.ufes.inf.nemo.util.ejb3.controller.CrudController;


@Named
@SessionScoped
public class ManageUsersController extends CrudController<User> {

	private static final long serialVersionUID = 1L;


	@EJB
	private ManageUsersService manageUsersService;
	
	
	public ManageUsersController() {
	    viewPath = "/core/manageUsers/";
	    bundleName = "msgs";
	}
	
	
	@Override
	protected void initFilters() {
	}



	@Override
	protected User createNewEntity() {
		return new User();
	}



	@Override
	protected CrudService<User> getCrudService() {
		return manageUsersService;
	}

}
