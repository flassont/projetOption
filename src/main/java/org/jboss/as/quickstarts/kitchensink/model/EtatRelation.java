package org.jboss.as.quickstarts.kitchensink.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 
 * @author Nicolas, Téo, Amandine
 * Etat de la demande de Responsabilite
 * NON_VALIDE : la demande n'a pas encore ete acceptee
 * REFUSE : la demande a ete rejetee
 * ACCEPTE : la demande a ete acceptee
 * IMPOSE : aucune demande n'a ete faite mais la Responsabilite incombe tout de meme a l'Intervenant
 *
 */
@SuppressWarnings("serial")
@Entity
public class EtatRelation implements Serializable {
	
	public EtatRelation() {}

	/**
	 * Version de l'entité pour la concurrence
	 */
	@Version
	protected int version;
	
	/**
	 * Etat de la relation
	 * Cle primaire
	 */
	@NotNull
	@NotEmpty
	@Id
	private String etat;
	
	/**
	 * Liste des responsabilites ayant pour etat celui sus-cite
	 */
	@OneToMany
	private Collection<Responsabilite> responsabilites;
	
	public String getEtat() {
		return this.etat;
	}
	
	public Collection<Responsabilite> getResponsabilites() {
		return this.responsabilites;
	}

}
