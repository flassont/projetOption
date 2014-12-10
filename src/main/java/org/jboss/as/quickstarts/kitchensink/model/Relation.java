package org.jboss.as.quickstarts.kitchensink.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@SuppressWarnings("serial")
@Entity
@XmlRootElement
public class Relation implements Serializable {
	
	@Id
	@GeneratedValue
	private long id;
	
	@NotNull
	@NotEmpty
	@Pattern(regexp="[2-9][0-9][0-9][0-9]")
	private int annee;
	
	@NotNull
	@NotEmpty
	@ManyToOne(fetch = FetchType.EAGER)
	private Intervenant intervenant;
	
	@NotNull
	@NotEmpty
	@Enumerated(EnumType.STRING)
	private EtatRelation etatRelation;
	
	@NotNull
	@NotEmpty
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
	
	public void setEtatRelation(EtatRelation er) {
		this.etatRelation=er;
	}
	
	public Responsabilite getResponsabilite() {
		return this.responsabilite;
	}

}
