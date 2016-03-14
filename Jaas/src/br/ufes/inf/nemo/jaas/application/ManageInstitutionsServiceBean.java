package br.ufes.inf.nemo.jaas.application;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.inf.nemo.jaas.domain.Institution;
import br.ufes.inf.nemo.jaas.persistence.InstitutionDAO;
import br.ufes.inf.nemo.util.ejb3.persistence.BaseDAO;



@Stateless
@DeclareRoles({"Admin", "usuario" })
@RolesAllowed({"Admin" })
public class ManageInstitutionsServiceBean extends CrudServiceBean<Institution> implements ManageInstitutionsService {

	
	private static final long serialVersionUID = 1L;

	
	@EJB 
	private InstitutionDAO institutionDAO;
	
	
	
	@Override
	public void authorize() {
		super.authorize();
	}
	
	@Override
	public BaseDAO<Institution> getDAO() {
		return institutionDAO;
	}

	@Override
	protected Institution createNewEntity() {
		return new Institution();
	}

}
