package org.jboss.as.quickstarts.kitchensink.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 
 * @author Nicolas, Téo, Amandine
 * L'intervenant peut appartenir à différentes catégories
 * ADMIN : administrateur du système
 * PERM_DPT : permanent du departement informatique
 * NON_PERM_DPT : non permanent du departement informatique
 * ECOLE : intervenant de l'ecole qui n'est pas necessairement du departement informatique
 * EXT : intervenant exterieur
 *
 */
@SuppressWarnings("serial")
@Entity
public class CategIntervenant implements Serializable {
	
	public CategIntervenant() {}

	/**
	 * Version de l'entité pour la concurrence
	 */
	@Version
	protected int version;
	
	/**
	 * Id
	 * Cle primaire
	 */
	@Id
	@GeneratedValue
	private int id;
	
	/**
	 * Nom de la categorie
	 */
	@NotNull
	@NotEmpty
	private String nom;
	
	/**
	 * Annee pour laquelle est valable la categorie
	 */
	@NotNull
	private int annee;
	
	/**
	 * Liste des intervenants appartenant a la categorie sus-citee
	 */
	@OneToMany(fetch = FetchType.EAGER)
	private Collection<Intervenant> intervenants;
	
	public int getId() {
		return this.id;
	}
	
	public String getNom() {
		return this.nom;
	}
	
	public int getAnnee() {
		return this.annee;
	}
	
	public Collection<Intervenant> getIntervenants() {
		return this.intervenants;
	}

}
