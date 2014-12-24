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
 * UV, Module, Intervention, Option, AdjointEnseignement
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
	 * Nombre d'eleves geres dans le cadre de la Responsabilite
	 * Le nombre d'eleves peut etre utilise pour calculer le nombre d'UP de l'Intervenant
	 */
	@NotNull
	private int nbEleves;
	
	/**
	 * Annee pour laquelle la responsabilite est valable
	 */
	@NotNull
	private int annee;
	
	/**
	 * Coefficient permettant de calculer le nombre d'UP que la Responsabilite va rapporter a l'Intervenant
	 */
	@NotNull
	private double coeffUP;
	
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
	
	public int getNbEleves() {
		return this.nbEleves;
	}
	
	public int getAnnee() {
		return this.annee;
	}
	
	public double getCoeffUP() {
		return this.coeffUP;
	}
	
	public Collection<Relation> getRelations() {
		return this.relations;
	}
	
	public void setIntitule(String intitule) {
		this.intitule=intitule;
	}
	
	public void setNbEleves(int nbEleves) {
		this.nbEleves=nbEleves;
	}

}
