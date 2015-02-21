package org.jboss.as.quickstarts.kitchensink.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 
 * @author Nicolas, Téo, Amandine
 * Forme pedagogique de l'Intervention
 * Chaque forme pedagogique implique un coefficient multiplicateur different pour le total d'UP
 * Pour chaque intervenant :
 * total UP = nombre d'UP pour un module * coefficient multiplicateur lie a la forme pedagogique
 * Pour le moment, les formes pedagogiques envisagees sont :
 * PC : Petite Classe
 * CM : Cours Magistral
 * TP : Travaux Pratiques
 * TD : Travaux Diriges
 * D'autres telles que les MOOC pourront etre rajoutees par la suite
 *
 */
@SuppressWarnings("serial")
@Entity
public class FormePedago implements Serializable {
	
	public FormePedago() {}

	/**
	 * Version de l'entité pour la concurrence
	 */
	@Version
	protected int version;
	
	/**
	 * intitule de la forme pedagogique
	 * Cle primaire
	 */
	@NotNull
	@NotEmpty
	@Id
	private String formePedago;
	
	/**
	 * annee pour laquelle le multiplicateur est valable
	 */	
	@NotNull
	private int annee;

	/**
	 * coefficient multiplicateur associe
	 */
	@NotNull
	private double coef;
	
	/**
	 * interventions ayant la forme pedagogique sus-citee
	 */
	@OneToMany
	private Collection<Intervention> interventions;
	
	public String getFormePedago() {
		return this.formePedago;
	}
	
	public int getAnnee() {
		return this.annee;
	}
	
	public double getCoef() {
		return this.coef;
	}
	
	public Collection<Intervention> getInterventions() {
		return this.interventions;
	}
	
	public void setCoef(double coef) {
		this.coef=coef;
	}

}
