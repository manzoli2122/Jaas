package br.ufes.inf.nemo.jaas.domain;

import br.ufes.inf.nemo.util.ejb3.persistence.PersistentObjectSupport_;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-03-14T09:33:56.589-0300")
@StaticMetamodel(Institution.class)
public class Institution_ extends PersistentObjectSupport_ {
	public static volatile SingularAttribute<Institution, String> name;
	public static volatile SingularAttribute<Institution, String> acronym;
	public static volatile SingularAttribute<Institution, String> email;
}
