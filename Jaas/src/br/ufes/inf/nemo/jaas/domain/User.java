package br.ufes.inf.nemo.jaas.domain;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import br.ufes.inf.nemo.util.ejb3.persistence.PersistentObjectSupport;

@Entity
public class User extends PersistentObjectSupport  implements Comparable<User>{

	
	private static final long serialVersionUID = 1L;

	@NotNull 
	private String username;
	
	@NotNull 
	private String password;
	
	private Role role;
	
	
	
	public String getUsername() {
		return username;
	}
	
	public String getName() {
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	
	
	@Override
	public int compareTo(User o) {
		if (username == null)	return 1;
		if (o.username == null) return -1;
		int cmp = username.compareTo(o.username);
		if (cmp != 0 ) return cmp;
		
		return super.compareTo(o);	
	}
	
	
}
