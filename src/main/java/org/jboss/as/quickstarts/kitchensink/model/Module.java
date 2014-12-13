package org.jboss.as.quickstarts.kitchensink.model;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Module extends Responsabilite {
	
	@NotNull
	private int annee;
	
	@ManyToMany(fetch=FetchType.EAGER)
	private Collection<Intervention> interventions;
	
	@ManyToMany(fetch=FetchType.EAGER)
	private Collection<UV> uvs;
	
	public int getAnnee() {
		return this.annee;
	}
	
	public Collection<Intervention> getInterventions() {
		return this.interventions;
	}
	
	public Collection<UV> getUVs() {
		return this.uvs;
	}

}
