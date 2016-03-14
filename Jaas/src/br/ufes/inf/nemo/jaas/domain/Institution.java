package br.ufes.inf.nemo.jaas.domain;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.ufes.inf.nemo.util.ejb3.persistence.PersistentObjectSupport;

@Entity
public class Institution extends PersistentObjectSupport implements Comparable<Institution>{

	/** Serialization id. */
	private static final long serialVersionUID = 1L;

	
	/** The institution's name. */
	@Basic
	@NotNull
	@Size(max = 200)
	private String name;

	/** THe institution's acronym. */
	@Basic
	@NotNull
	@Size(max = 10)
	private String acronym;
	
	
	/** Main electronic address of the institution. */
	@Basic
	@Size(max = 100)
	private String email;
	
	
	
	/** Getter for name. */
	public String getName() {
		return name;
	}

	/** Setter for name. */
	public void setName(String name) {
		this.name = name;
	}

	/** Getter for acronym. */
	public String getAcronym() {
		return acronym;
	}

	/** Setter for acronym. */
	public void setAcronym(String acronym) {
		this.acronym = acronym;
	}
	
	
	
	/** Getter for email. */
	public String getEmail() {
		return email;
	}

	/** Setter for email. */
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	@Override
	public int compareTo(Institution o) {
		// Compare the institutions' names
		if (name == null)
			return 1;
		if (o.name == null)
			return -1;
		int cmp = name.compareTo(o.name);
		if (cmp != 0)
			return cmp;
		
		return super.compareTo(o);
			
	}

}
