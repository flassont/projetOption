package org.jboss.as.quickstarts.kitchensink.model;
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
public enum EtatRelation {
	
	NON_VALIDE, REFUSE, ACCEPTE, IMPOSE;

}
