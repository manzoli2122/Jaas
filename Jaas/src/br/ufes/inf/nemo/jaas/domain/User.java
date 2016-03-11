package br.ufes.inf.nemo.jaas.domain;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import br.ufes.inf.nemo.util.ejb3.persistence.PersistentObjectSupport;

@Entity
public class User extends PersistentObjectSupport {

	
	private static final long serialVersionUID = 1L;

	@NotNull 
	private String username;
	
	@NotNull 
	private String password;
	
	
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}	
}
