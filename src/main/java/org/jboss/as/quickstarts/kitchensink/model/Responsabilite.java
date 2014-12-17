package org.jboss.as.quickstarts.kitchensink.model;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 
 * @author Nicolas, Téo, Amandine
 * Responsabilite avec laquelle un intervenant peut etablir une relation
 * UV, Module, Intervention, Option
 *
 */
@Entity
public abstract class Responsabilite {
	
	/**
	 * Id de la responsabilite
	 * Cle primaire
	 */
	@Id
	@GeneratedValue
	private long id;
	
	/**
	 * Intitule de la responsabilite
	 */
	@NotNull
	@NotEmpty
	private String intitule;
	
	/**
	 * Ensemble des relations liees a une Responsabilite
	 */
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
