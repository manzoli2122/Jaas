package br.ufes.inf.nemo.jaas.domain;

public enum Role {
	
	Admin("Admin"),
	usuario("usuario");
	
	private final String label;

	private Role(String label) {
		this.label = label;
	}

	public String getLabel() {
		return this.label;
    }

}
