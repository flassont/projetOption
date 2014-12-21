package org.jboss.as.quickstarts.kitchensink.model;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class CategIntervenant {
	
	@Id
	@GeneratedValue
	private int id;
	
	/**
	 * Categorie a laquelle appartient l'intervenant
	 * PERM_DPT : permanent du departement informatique
	 * NON_PERM_DPT : non permanent du departement informatique
	 * ECOLE : intervenant de l'ecole qui n'est pas necessairement du departement informatique
	 * EXT : intervenant exterieur
	 */
	@NotNull
	@NotEmpty
	private String categ;
	
	@NotNull
	@NotEmpty
	private int annee;
	
	@OneToMany
	private Collection<Intervenant> intervenants;
	
	public String getCateg() {
		return this.categ;
	}

}
