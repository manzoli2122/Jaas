package br.ufes.inf.nemo.jaas.application;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufes.inf.nemo.jaas.domain.User;
import br.ufes.inf.nemo.jaas.persistence.UserDAO;
import br.ufes.inf.nemo.util.ejb3.persistence.BaseDAO;



@Stateless
public class ManageUsersServiceBean extends CrudServiceBean<User> implements ManageUsersService {

	
	private static final long serialVersionUID = 1L;

	
	@EJB 
	private UserDAO userDAO;
	
	@Override
	public BaseDAO<User> getDAO() {
		return userDAO;
	}

	@Override
	protected User createNewEntity() {
		return new User();
	}

	

}
