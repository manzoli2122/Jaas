package br.ufes.inf.nemo.jaas.persistence;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.ufes.inf.nemo.jaas.domain.Institution;
import br.ufes.inf.nemo.util.ejb3.persistence.BaseJPADAO;



@Stateless
public class InstitutionJPADAO extends BaseJPADAO<Institution> implements InstitutionDAO{

	
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext(unitName="Jaas")
	private EntityManager entityManager;
	

	@Override
	public Class<Institution> getDomainClass() {
		return Institution.class;
	}

	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

}
