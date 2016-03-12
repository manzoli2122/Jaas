package br.ufes.inf.nemo.jaas.persistence;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.ufes.inf.nemo.jaas.domain.User;
import br.ufes.inf.nemo.util.ejb3.persistence.BaseJPADAO;


@Stateless
public class UserJPADAO extends BaseJPADAO<User> implements UserDAO {

	
	private static final long serialVersionUID = 1L;

	
	@PersistenceContext(unitName="Jaas")
	private EntityManager entityManager;
	
	
	
	
	@Override
	public Class<User> getDomainClass() {
		return User.class;
	}

	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

}
