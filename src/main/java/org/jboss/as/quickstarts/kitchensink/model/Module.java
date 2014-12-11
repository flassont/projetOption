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
	@NotEmpty
	@Pattern(regexp="[2-9][0-9][0-9][0-9]")
	private int annee;
	
	@NotNull
	@NotEmpty
	private String intitule;
	
	@ManyToMany(fetch=FetchType.EAGER)
	private Collection<Intervention> interventions;
	
	@ManyToMany(fetch=FetchType.EAGER)
	private Collection<UV> uvs;
	
	public int getAnnee() {
		return this.annee;
	}
	
	public String getIntitule() {
		return this.intitule;
	}
	
	public void setIntitule(String intitule) {
		this.intitule=intitule;
	}
	
	public Collection<Intervention> getInterventions() {
		return this.interventions;
	}
	
	public Collection<UV> getUVs() {
		return this.uvs;
	}

}
