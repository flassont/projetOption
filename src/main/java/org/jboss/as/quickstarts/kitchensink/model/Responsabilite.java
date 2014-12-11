package org.jboss.as.quickstarts.kitchensink.model;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public abstract class Responsabilite {
	
	@Id
	@GeneratedValue
	private long id;
	
	@OneToMany(fetch = FetchType.EAGER)
    private Collection<Relation> relations;
	
	public long getId() {
		return this.id;
	}
	
	public Collection<Relation> getRelations() {
		return this.relations;
	}

}
