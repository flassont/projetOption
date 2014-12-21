package org.jboss.as.quickstarts.kitchensink.model;

public class CategIntervenant {
	
	/**
	 * Categorie a laquelle appartient l'intervenant
	 * PERM_DPT : permanent du departement informatique
	 * NON_PERM_DPT : non permanent du departement informatique
	 * ECOLE : intervenant de l'ecole qui n'est pas necessairement du departement informatique
	 * EXT : intervenant exterieur
	 */
	private String categ;
	
	public String getCateg() {
		return this.categ;
	}

}
