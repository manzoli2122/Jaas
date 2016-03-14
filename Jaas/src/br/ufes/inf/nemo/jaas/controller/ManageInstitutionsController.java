package br.ufes.inf.nemo.jaas.controller;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.ufes.inf.nemo.jaas.application.ManageInstitutionsService;
import br.ufes.inf.nemo.jaas.domain.Institution;
import br.ufes.inf.nemo.util.ejb3.application.CrudService;
import br.ufes.inf.nemo.util.ejb3.controller.CrudController;



@Named
@SessionScoped
public class ManageInstitutionsController extends CrudController<Institution>{

	
	
	private static final long serialVersionUID = 1L;

	
	
	@EJB
	private ManageInstitutionsService manageInstitutionsService;
	
	
	
	
	public ManageInstitutionsController() {
	    viewPath = "/core/manageInstitutions/";
	    bundleName = "msgs";
	}
	
	
	
	@Override
	protected CrudService<Institution> getCrudService() {
		return manageInstitutionsService;
	}

	@Override
	protected Institution createNewEntity() {
		return new Institution();
	}

	@Override
	protected void initFilters() {}

}
