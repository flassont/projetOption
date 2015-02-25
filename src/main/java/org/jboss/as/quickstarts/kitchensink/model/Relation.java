package org.jboss.as.quickstarts.kitchensink.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
	
	public Relation(int annee, Intervenant intervenant, Responsabilite responsabilite, EtatRelation etatInitial) {
		this.annee = annee;
		this.intervenant = intervenant;
		this.responsabilite = responsabilite;
		this.etatsRelation = new ArrayList<>();
		this.etatsRelation.add(etatInitial);
	}

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
	private int annee;
	
	/**
	 * Intervenant implique dans la relation
	 */
	@NotNull
	@ManyToOne(fetch = FetchType.EAGER)
	@JsonIgnoreProperties("relations")
	private Intervenant intervenant;

	/**
	 * Responsabilite liee a l'intervenant
	 */
	@NotNull
	@ManyToOne(fetch = FetchType.EAGER)
	@JsonIgnoreProperties("relations")
	private Responsabilite responsabilite;

	/**
	 * Etat de la relation
	 * NON_VALIDE, ACCEPTE, REFUSE, IMPOSE
	 */
	@ElementCollection(fetch=FetchType.EAGER, targetClass=EtatRelation.class)
	@Enumerated(EnumType.STRING)
	private Collection<EtatRelation> etatsRelation;
	
	public long getId() {
		return this.id;
	}
	
	public int getAnnee() {
		return this.annee;
	}
	
	public Intervenant getIntervenant() {
		return this.intervenant;
	}
	
	public Collection<EtatRelation> getEtatsRelation() {
		return this.etatsRelation;
	}
	
	public Responsabilite getResponsabilite() {
		return this.responsabilite;
	}
	
	public void addEtatRelation(EtatRelation er) {
		this.etatsRelation.add(er);
	}

}
