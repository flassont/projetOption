package org.jboss.as.quickstarts.kitchensink.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 
 * @author Nicolas, Téo, Amandine
 * Responsabilite avec laquelle un intervenant peut etablir une relation
 * UV, Module, Intervention, Option, AdjointEnseignement
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="responsabilite")
public abstract class Responsabilite implements Serializable {
	
	public Responsabilite() {}

	/**
	 * Version de l'entité pour la concurrence
	 */
	@Version
	@Column(name="version")
	protected int version;
	
	/**
	 * Id de la responsabilite
	 * Cle primaire
	 */
	@Id
	@GeneratedValue
	@Column(name="id")
	private long id;
	
	/**
	 * Intitule de la responsabilite
	 */
	@NotNull
	@NotEmpty
	@Column(name="intitule")
	private String intitule;
	
	/**
	 * Nombre d'eleves geres dans le cadre de la Responsabilite
	 * Le nombre d'eleves peut etre utilise pour calculer le nombre d'UP de l'Intervenant
	 */
	@NotNull
	@Column(name="nbEleves")
	private int nbEleves;
	
	/**
	 * Annee pour laquelle la responsabilite est valable
	 */
	@NotNull
	@Column(name="annee")
	private int annee;
	
	/**
	 * Coefficient permettant de calculer le nombre d'UP que la Responsabilite va rapporter a l'Intervenant
	 */
	@NotNull
	@Column(name="coeffUP")
	private double coeffUP;
	
	/**
	 * Ensemble des relations liees a une Responsabilite
	 */
	@OneToMany(fetch = FetchType.EAGER, mappedBy="responsabilite")
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
