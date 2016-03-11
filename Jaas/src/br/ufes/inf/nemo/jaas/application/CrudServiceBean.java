package br.ufes.inf.nemo.jaas.application;


import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import br.ufes.inf.nemo.util.ejb3.application.CrudException;
import br.ufes.inf.nemo.util.ejb3.application.CrudOperation;
import br.ufes.inf.nemo.util.ejb3.application.CrudService;
import br.ufes.inf.nemo.util.ejb3.application.filters.Filter;
import br.ufes.inf.nemo.util.ejb3.persistence.PersistentObject;



@Stateless
//@DeclareRoles({"Admin", "egresso" , "guest"})
//@RolesAllowed({"Admin" , "egresso"})
@PermitAll
public abstract class CrudServiceBean<T extends PersistentObject>  implements CrudService<T>{

	
	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(CrudServiceBean.class.getCanonicalName());
	
	protected void log(CrudOperation operation, List<T> entities, int ... interval) {
		logger.log(Level.FINE, "Logging (for operations over multiple entities) not overridden by subclass. No need for this type of logging.");
	}

	public void authorize() {
		logger.log(Level.FINE, "Authorization not overridden by subclass. No need for authorization.");
	}

	@Override
	public long count() {
		logger.log(Level.FINER, "Retrieving the object count...");
		return getDAO().retrieveCount();
	}

	@Override
	public long countFiltered(Filter<?> filter, String value) {
		logger.log(Level.FINER, "Retrieving a filtered object count (filter \"{0}\" with value \"{1}\")...", new Object[] { filter.getKey(), value });
		return getDAO().retrieveFilteredCount(filter, value);
	}

	@Override
	public List<T> list(int ... interval) {
		List<T> entities = getDAO().retrieveSome(interval);
		log(CrudOperation.LIST, entities, interval);
		return entities;
	}

	@Override
	public List<T> filter(Filter<?> filter, String filterParam, int ... interval) {
		List<T> entities = getDAO().retrieveSomeWithFilter(filter, filterParam, interval);
		log(CrudOperation.LIST, entities, interval);
		return entities;
	}

	@Override
	public T fetchLazy(T entity) {
		// Default implementation is to return the entity itself (there are no lazy attributes).
		logger.log(Level.FINEST, "Using default implementation for fetchLazy(): returning the same entity, unchanged");
		return entity;
	}
	
	
	
	/* ****** */
	
	protected abstract T createNewEntity();

	
	protected T validate(T newEntity, T oldEntity) {
		logger.log(Level.FINE, "Validation not overridden by subclass. No need for validation.");
		return newEntity;
	}

	
	protected void log(CrudOperation operation, T entity) {
		logger.log(Level.FINE, "Logging (for operations over single entities) not overridden by subclass. No need for this type of logging.");
	}

	
	protected CrudException addValidationError(CrudException crudException, String message, String messageKey, Object ... messageParams) {
		logger.log(Level.FINER, "Adding a validation error with key \"{0}\"...", messageKey);

		if (crudException == null) {
			crudException = new CrudException(message, messageKey, messageParams);
		}
		else {
			crudException.addValidationError(messageKey, messageParams);
		}
		return crudException;
	}

	
	protected CrudException addValidationError(CrudException crudException, String message, String fieldName, String messageKey, Object ... messageParams) {
		logger.log(Level.FINER, "Adding field validation error with key \"{0}\" to field \"{1}\"...", new Object[] { messageKey, fieldName });

		if (crudException == null) {
			crudException = new CrudException(message, fieldName, messageKey, messageParams);
		}
		else {
			crudException.addValidationError(fieldName, messageKey, messageParams);
		}
		return crudException;
	}

	@Override
	public void validateCreate(T entity) throws CrudException {
		logger.log(Level.FINE, "Validation of CREATE not overridden by subclass. No need for this type of validation.");
	}

	@Override
	public void validateUpdate(T entity) throws CrudException {
		logger.log(Level.FINE, "Validation of UPDATE not overridden by subclass. No need for this type of validation.");
	}

	@Override
	public void validateDelete(T entity) throws CrudException {
		logger.log(Level.FINE, "Validation of DELETE not overridden by subclass. No need for this type of validation.");
	}

	@Override
	public void create(T entity) {
		// Validates the entity before persisting.
		entity = validate(entity, null);

		// Save the entity.
		log(CrudOperation.CREATE, entity);
		getDAO().save(entity);
	}

	@Override
	public T retrieve(Long id) {
		// Retrieves the real entity from the database.
		T entity = getDAO().retrieveById(id);
		log(CrudOperation.RETRIEVE, entity);
		return entity;
	}

	@Override
	public void update(T entity) {
		// Validates the entity before persisting.
		entity = validate(entity, getDAO().retrieveById(entity.getId()));

		// Save the entity.
		log(CrudOperation.UPDATE, entity);
		getDAO().save(entity);
	}

	@Override
	public void delete(T entity) {
		// Retrieves the real entity from the database.
		entity = getDAO().retrieveById(entity.getId());
		if (entity != null) {
			// Deletes the entity.
			getDAO().delete(entity);
			log(CrudOperation.DELETE, entity);
		}
	}
	
}
