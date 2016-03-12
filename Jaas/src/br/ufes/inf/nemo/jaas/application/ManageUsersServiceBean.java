package br.ufes.inf.nemo.jaas.application;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.inf.nemo.jaas.domain.User;
import br.ufes.inf.nemo.jaas.persistence.UserDAO;
import br.ufes.inf.nemo.util.ejb3.persistence.BaseDAO;



@Stateless
@DeclareRoles({"Admin", "usuario" })
@RolesAllowed({"Admin" })
public class ManageUsersServiceBean extends CrudServiceBean<User> implements ManageUsersService {

	
	private static final long serialVersionUID = 1L;

	@Override
	public void authorize() {
		// TODO Auto-generated method stub
		super.authorize();
	}
	
	
	@EJB 
	private UserDAO userDAO;
	
	@Override
	@PermitAll
	public BaseDAO<User> getDAO() {
		return userDAO;
	}

	@Override
	protected User createNewEntity() {
		return new User();
	}
	
	
	
	@Override
	public void create(User entity) {
		// TODO Auto-generated method stub
		super.create(entity);
	}
	
	@Override
	public void update(User entity) {
		// TODO Auto-generated method stub
		super.update(entity);
	}

	

}
