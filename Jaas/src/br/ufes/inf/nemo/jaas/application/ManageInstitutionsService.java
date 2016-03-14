package br.ufes.inf.nemo.jaas.application;

import javax.ejb.Local;

import br.ufes.inf.nemo.jaas.domain.Institution;
import br.ufes.inf.nemo.util.ejb3.application.CrudService;

@Local
public interface ManageInstitutionsService extends CrudService<Institution>{

}
