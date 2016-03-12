package br.ufes.inf.nemo.jaas.persistence;

import javax.ejb.Local;

import br.ufes.inf.nemo.jaas.domain.User;
import br.ufes.inf.nemo.util.ejb3.persistence.BaseDAO;
import br.ufes.inf.nemo.util.ejb3.persistence.exceptions.MultiplePersistentObjectsFoundException;
import br.ufes.inf.nemo.util.ejb3.persistence.exceptions.PersistentObjectNotFoundException;


@Local
public interface UserDAO extends BaseDAO<User> {

	User retrieveByName(String name) throws PersistentObjectNotFoundException, MultiplePersistentObjectsFoundException;
	
}
