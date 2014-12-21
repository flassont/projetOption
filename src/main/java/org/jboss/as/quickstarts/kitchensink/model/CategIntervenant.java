package org.jboss.as.quickstarts.kitchensink.model;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 
 * @author Nicolas, Téo, Amandine
 * L'intervenant peut appartenir à différentes catégories
 * PERM_DPT : permanent du departement informatique
 * NON_PERM_DPT : non permanent du departement informatique
 * ECOLE : intervenant de l'ecole qui n'est pas necessairement du departement informatique
 * EXT : intervenant exterieur
 *
 */
@Entity
public class CategIntervenant {
	
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
	@NotEmpty
	private int annee;
	
	/**
	 * Liste des intervenants appartenant a la categorie sus-citee
	 */
	@OneToMany
	private Collection<Intervenant> intervenants;
	
	public int getId() {
		return this.id;
	}
	
	public String getCateg() {
		return this.nom;
	}
	
	public int getAnnee() {
		return this.annee;
	}
	
	public Collection<Intervenant> getIntervenants() {
		return this.intervenants;
	}

}
