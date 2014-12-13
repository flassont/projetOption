package org.jboss.as.quickstarts.kitchensink.model;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public abstract class Responsabilite {
	
	@Id
	@GeneratedValue
	private long id;
	
	@NotNull
	@NotEmpty
	private String intitule;
	
	@OneToMany(fetch = FetchType.EAGER)
    private Collection<Relation> relations;
	
	public long getId() {
		return this.id;
	}
	
	public String getIntitule() {
		return this.intitule;
	}
	
	public void setIntitule(String intitule) {
		this.intitule=intitule;
	}
	
	public Collection<Relation> getRelations() {
		return this.relations;
	}

}
