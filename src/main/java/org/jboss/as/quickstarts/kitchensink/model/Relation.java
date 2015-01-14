package org.jboss.as.quickstarts.kitchensink.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 
 * @author Nicolas, Téo, Amandine
 * Lien entre un Intervenant et une Responsabilite
 *
 */
@SuppressWarnings("serial")
@Entity
@XmlRootElement
public class Relation implements Serializable {
	
	public Relation() {}

	/**
	 * Version de l'entité pour la concurrence
	 */
	@Version
	protected int version;
	
	/**
	 * Id de la relation
	 * Cle primaire
	 */
	@Id
	@GeneratedValue
	private long id;
	
	/**
	 * Annee pour laquelle la relation est valable
	 */
	@NotNull
	@Pattern(regexp="[2-9][0-9][0-9][0-9]")
	private int annee;
	
	/**
	 * Intervenant implique dans la relation
	 */
	@NotNull
	@ManyToOne(fetch = FetchType.EAGER)
	private Intervenant intervenant;
	
	/**
	 * Etat de la relation
	 * NON_VALIDE, ACCEPTE, REFUSE, IMPOSE
	 */
	@NotNull
	@ManyToOne
	private EtatRelation etatRelation;
	
	/**
	 * Responsabilite liee a l'intervenant
	 */
	@NotNull
	@ManyToOne(fetch = FetchType.EAGER)
	private Responsabilite responsabilite;
	
	public long getId() {
		return this.id;
	}
	
	public int getAnnee() {
		return this.annee;
	}
	
	public Intervenant getIntervenant() {
		return this.intervenant;
	}
	
	public EtatRelation getEtatRelation() {
		return this.etatRelation;
	}
	
	public Responsabilite getResponsabilite() {
		return this.responsabilite;
	}
	
	public void setEtatRelation(EtatRelation er) {
		this.etatRelation=er;
	}

}
