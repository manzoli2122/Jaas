package br.ufes.inf.nemo.jaas.persistence;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.ufes.inf.nemo.jaas.domain.User;
import br.ufes.inf.nemo.jaas.domain.User_;
import br.ufes.inf.nemo.util.ejb3.persistence.BaseJPADAO;
import br.ufes.inf.nemo.util.ejb3.persistence.exceptions.MultiplePersistentObjectsFoundException;
import br.ufes.inf.nemo.util.ejb3.persistence.exceptions.PersistentObjectNotFoundException;


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
	
	
	
	
	
	
	@Override
	public User retrieveByName(String name) throws PersistentObjectNotFoundException, MultiplePersistentObjectsFoundException{
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<User> cq = cb.createQuery(User.class);
		Root<User> root = cq.from(User.class);
		cq.where(  cb.equal(root.get(User_.username), name));
					
		try {
			User result = getEntityManager().createQuery(cq).getSingleResult();
			return result;
		}
		catch (NoResultException e) {
			throw new PersistentObjectNotFoundException(e, getDomainClass(),name);
		}
		catch (NonUniqueResultException e) {
			throw new MultiplePersistentObjectsFoundException(e, getDomainClass(), name);
		}
		
	}

	
	
	
	
	

}
