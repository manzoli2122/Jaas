package br.ufes.inf.nemo.jaas.application;

import java.io.Serializable;

import javax.ejb.Local;

import br.ufes.inf.nemo.jaas.domain.User;

@Local
public interface SessionInformation extends Serializable {
	
	User getCurrentUser();

	void logout();

	
}
