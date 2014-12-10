package org.jboss.as.quickstarts.kitchensink.model;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Promotion extends Responsabilite {
	
	@NotNull
	@NotEmpty
	private String nom;
	
	@NotNull
	@NotEmpty
	@Pattern(regexp="[0-9]*")
	private int nbEleves;
	
	@NotNull
	@NotEmpty
	private Intervenant responsable;
	
	@NotNull
	@NotEmpty
	@Pattern(regexp="[2-9][0-9][0-9][0-9]")
	private int annee;
	
	@ManyToMany(fetch=FetchType.EAGER)
	private Collection<UV> uvs;
	
	public String getNom() {
		return this.nom;
	}
	
	public void setNom(String nom) {
		this.nom=nom;
	}
	
	public int getNbEleves() {
		return this.nbEleves;
	}
	
	public void setNbEleves(int nbEleves) {
		this.nbEleves=nbEleves;
	}
	
	public Intervenant getResponsable() {
		return this.responsable;
	}
	
	public void setResponsable(Intervenant responsable) {
		this.responsable=responsable;
	}
	
	public int getAnnee() {
		return this.annee;
	}
	
	public Collection<UV> getUVs() {
		return this.uvs;
	}
	
}
