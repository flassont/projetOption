package org.jboss.as.quickstarts.kitchensink.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * 
 * @author Nicolas, Téo, Amandine
 * L'adjoint de suivi de l'enseignement
 * TODO quelle est la réelle fonction d'un adjoint de suivi de l'enseignement ?
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="AdjointEnseignement")
public class AdjointEnseignement extends Responsabilite {
	
	public AdjointEnseignement() {}

}
