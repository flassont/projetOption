package org.jboss.as.quickstarts.kitchensink.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public abstract class Responsabilite {
	
	@Id
	@GeneratedValue
	private long id;
	
	public long getId() {
		return this.id;
	}

}
